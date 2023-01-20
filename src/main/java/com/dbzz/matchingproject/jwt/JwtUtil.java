package com.dbzz.matchingproject.jwt;

import com.dbzz.matchingproject.enums.UserRoleEnum;
import com.dbzz.matchingproject.repository.RefreshTokenRepository;
import com.dbzz.matchingproject.security.UserDetailsImpl;
import com.dbzz.matchingproject.security.UserDetailsServiceImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserDetailsServiceImpl userDetailsService;
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String REFRESH_HEADER = "Refresh";
    public static final String AUTHORIZATION_KEY = "auth";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final long TOKEN_TIME = 60 * 60 * 1000L;
    private static final long REFRESH_TOKEN_TIME = 24 * 60 * 60 * 1000;

    @Value("${jwt.secret.key}")
    private String secretKey;
    private Key key;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    // header 토큰을 가져오기
    public String resolveToken(HttpServletRequest request, String header) {
        String bearerToken = request.getHeader(header);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // 토큰 생성
    public String createToken(String username, UserRoleEnum role) {
        Date date = new Date();

        return BEARER_PREFIX +
                Jwts.builder()
                        .setSubject(username)
                        .claim(AUTHORIZATION_KEY, role) // "auth" : role
                        .setExpiration(new Date(date.getTime() + TOKEN_TIME))
                        .setIssuedAt(date)
                        .signWith(key, signatureAlgorithm)
                        .compact();
    }

    // 토큰 검증
    public JwtCode validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return JwtCode.ACCESS;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token, 만료된 JWT token 입니다.");
            return JwtCode.EXPIRED;
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
        }
//        return false;
        return JwtCode.DENIED;
    }


    // 토큰에서 사용자 정보 가져오기
    public Claims getUserInfoFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    // 토큰 검증 통합 메소드
//    public AuthenticatedUserInfoDto validateAndGetUserInfo(String token) {
//        if (this.validateToken(token)) {
//            Claims claims = this.getUserInfoFromToken(token);
//            String userId = claims.getSubject();
//            UserRoleEnum role = UserRoleEnum.valueOf(claims.get("auth").toString());
//            return new AuthenticatedUserInfoDto(role, userId);
//        } else {
//            throw new IllegalArgumentException("Token Error");
//        }
//    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        UserDetails userDetails = userDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
    }

    public String reissueRefreshToken(String refreshToken) throws RuntimeException {
        Claims claims = getUserInfoFromToken(refreshToken);
        RefreshToken findRefreshToken = refreshTokenRepository.findByUserId(claims.getSubject())
                .orElseThrow(() -> new UsernameNotFoundException("아이디를 찾을 수 없습니다."));

        if (findRefreshToken.getToken().equals(refreshToken)) {
            String newRefreshToken = createRefreshToken(claims.getSubject(), UserRoleEnum.valueOf(claims.get("auth").toString()));
            findRefreshToken.changeToken(newRefreshToken);
            return newRefreshToken;
        } else {
            log.info("Refresh Token이 일치하지 않습니다.");
            return null;
        }
    }

    @Transactional
    public String issueRefreshToken(String username, UserRoleEnum role) {
        String newRefreshToken = createRefreshToken(username, role);

        refreshTokenRepository.findByUserId(username)
                .ifPresentOrElse(
                        refreshToken -> refreshToken.changeToken(newRefreshToken),
                        () -> {
                            RefreshToken token = RefreshToken.createToken(username, newRefreshToken);
                            refreshTokenRepository.save(token);
                        }
                    );
        return newRefreshToken;
    }


    private String createRefreshToken(String username, UserRoleEnum role) {
        Date date = new Date();
        return BEARER_PREFIX +
                Jwts.builder()
                        .setSubject(username)
                        .claim(AUTHORIZATION_KEY, role) // "auth" : role
                        .setExpiration(new Date(date.getTime() + REFRESH_TOKEN_TIME))
                        .setIssuedAt(date)
                        .signWith(key, signatureAlgorithm)
                        .compact();
    }

    // JWT 토큰 상태
    public static enum JwtCode {
        DENIED,
        ACCESS,
        EXPIRED;
    }
}
