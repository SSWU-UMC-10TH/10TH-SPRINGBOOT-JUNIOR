package com.example.umc10th.global.config;

import com.example.umc10th.global.security.filter.JwtAuthFilter;
import com.example.umc10th.global.security.handler.CustomAccessDeniedHandler;
import com.example.umc10th.global.security.handler.CustomAuthenticationEntryPoint;
import com.example.umc10th.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    // ⭕ 8주차 가이드 표준: 스프링 컨테이너가 관리하는 두 핸들러 빈을 주입받습니다.
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    private final String[] allowUris = {
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/auth/**",
            "/api/v1/users/signup",
            "/api/v1/users/login",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)

                // HTTP 요청에 대한 접근 제어 설정
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(allowUris).permitAll()
                        .anyRequest().authenticated()
                )

                // 주입받은 객체를 그대로 바인딩
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(customAuthenticationEntryPoint) // 401 핸들러 연동
                        .accessDeniedHandler(customAccessDeniedHandler)           // 403 핸들러 연동
                )

                // JWT 아키텍처 구성을 위한 불필요 기능 비활성화 및 세션정책 처리
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // UsernamePasswordAuthenticationFilter 이전에 커스텀 JWT 필터 배치
                .addFilterBefore(
                        new JwtAuthFilter(jwtUtil),
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}