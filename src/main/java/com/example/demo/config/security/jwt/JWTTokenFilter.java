package com.example.demo.config.security.jwt;

import com.example.demo.config.security.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class JWTTokenFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserDetailsServiceImpl userDetailsImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        if(headerAuthorizationEhValido(authorization)) {
            String token = authorization.substring(7);

            if(jwtService.isValidToken(token) == true) {
                String loginCliente = jwtService.captureCliente(token);
                UserDetails login   = userDetailsImpl.loadUserByUsername(loginCliente);

                UsernamePasswordAuthenticationToken obj = new UsernamePasswordAuthenticationToken(login, null, login.getAuthorities());
                obj.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(obj);
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean headerAuthorizationEhValido(String authorization) {
        return authorization != null && authorization.startsWith("Bearer");
    }
}
