//package com.dbzz.matchingproject.jwt;
//
//import com.dbzz.matchingproject.security.SecurityExceptionDto;
//import com.dbzz.matchingproject.security.UserDetailsServiceImpl;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.jsonwebtoken.Claims;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Slf4j
//@RequiredArgsConstructor
//public class JwtAuthFilter extends OncePerRequestFilter {
//    private final JwtUtil jwtUtil;
//    private final UserDetailsServiceImpl userDetailsService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String token = jwtUtil.resolveToken(request);
//        if (token != null) {
//            if (!jwtUtil.validateToken(token)){
//                jwtExceptionHandler(response, "Token Error", HttpStatus.UNAUTHORIZED.value());
//                return;
//            }
//            Claims info = jwtUtil.getUserInfoFromToken(token);
//            setAuthentication(info.getSubject());
//        }
//        filterChain.doFilter(request, response);
//    }
//
//    public Authentication createAuthentication (String userId) {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
//        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//    }
//
//    public void setAuthentication(String userId) {
//        SecurityContext context = SecurityContextHolder.createEmptyContext();
//        Authentication authentication = this.createAuthentication(userId);
//        context.setAuthentication(authentication);
//
//        SecurityContextHolder.setContext(context);
//    }
//
//    public void jwtExceptionHandler(HttpServletResponse response, String msg, int statusCode) {
//        response.setStatus(statusCode);
//        response.setContentType("application/json");
//        try {
//            String json = new ObjectMapper().writeValueAsString(new SecurityExceptionDto(statusCode, msg));
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//    }
//}
