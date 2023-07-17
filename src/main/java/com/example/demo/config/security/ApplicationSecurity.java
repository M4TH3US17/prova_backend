package com.example.demo.config.security;

import com.example.demo.config.security.jwt.JWTService;
import com.example.demo.config.security.jwt.JWTTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration @RequiredArgsConstructor
public class ApplicationSecurity {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final JWTService jwtService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST,  "/api/v1/usuarios/**").permitAll()
                .antMatchers(HttpMethod.GET,    "/api/v1/carros/**").permitAll()

                .antMatchers(HttpMethod.GET,   "/api/v1/marcas/**")      .hasAnyRole("USUARIO", "ADMIN")
                .antMatchers(HttpMethod.POST,  "/api/v1/marcas/**")      .hasRole("ADMIN")

                .antMatchers(HttpMethod.DELETE, "/api/v1/carros/**")     .hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,    "/api/v1/carros/**")     .hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,   "/api/v1/carros/**")     .hasRole("ADMIN")

                .and() .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }

    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JWTTokenFilter(jwtService, userDetailsServiceImpl);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
                        "/configuration/security", "/swagger-ui.html", "/webjars/**", "/h2-console/**");

    }
}
