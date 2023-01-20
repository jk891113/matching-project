package com.dbzz.matchingproject.jwt;

import com.dbzz.matchingproject.enums.UserRoleEnum;
import com.dbzz.matchingproject.security.SecurityExceptionDto;
import com.dbzz.matchingproject.security.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.dbzz.matchingproject.jwt.JwtUtil.AUTHORIZATION_HEADER;
import static com.dbzz.matchingproject.jwt.JwtUtil.REFRESH_HEADER;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtUtil.resolveToken(request, AUTHORIZATION_HEADER);
        if (token != null) {
            if (jwtUtil.validateToken(token) == JwtUtil.JwtCode.DENIED){
                jwtExceptionHandler(response, "Token Error", HttpStatus.UNAUTHORIZED.value());
                return;
            } else if (jwtUtil.validateToken(token) == JwtUtil.JwtCode.EXPIRED) {
                String refresh = jwtUtil.resolveToken(request, REFRESH_HEADER);
                // refresh token 확인 후 재발급
                if (refresh != null && jwtUtil.validateToken(refresh) == JwtUtil.JwtCode.ACCESS) {
                    String newRefresh = jwtUtil.reissueRefreshToken(refresh);
                    if (newRefresh != null) {
                        response.setHeader(REFRESH_HEADER, newRefresh);

                        // access token 생성
                        Authentication authentication = jwtUtil.getAuthentication(refresh);
                        response.setHeader(AUTHORIZATION_HEADER, jwtUtil.createToken(authentication.getName(), UserRoleEnum.valueOf(authentication.getAuthorities().toString())));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            } else {
                log.info("Token Error");
            }
            Claims info = jwtUtil.getUserInfoFromToken(token);
            setAuthentication(info.getSubject());
        }
        filterChain.doFilter(request, response);
    }

    public Authentication createAuthentication (String userId) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public void setAuthentication(String userId) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = this.createAuthentication(userId);
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }

    public void jwtExceptionHandler(HttpServletResponse response, String msg, int statusCode) {
        response.setStatus(statusCode);
        response.setContentType("application/json");
        try {
            String json = new ObjectMapper().writeValueAsString(new SecurityExceptionDto(statusCode, msg));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
