package com.example.umc10th.global.config;

import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import tools.jackson.databind.ObjectMapper;

// Spring Security 설정 활성화 (우리가 적은 규칙을 우선 적용시킴)
@EnableWebSecurity


@Configuration
public class SecurityConfig {
    // 필터 체인과 보안 정책을 설정하는 역할

    private final String[] allowUris = {
            // Swagger
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/auth/**",
            "/api/v1/users/signup"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, ObjectMapper objectMapper) throws Exception {
        // HttpSecurity 객체를 통해 다양한 보안 설정 구성

        http
                .csrf(AbstractHttpConfigurer::disable)

                // HTTP 요청에 대한 접근 제어
                .authorizeHttpRequests(requests -> requests
                        // 특정 URL 패턴에 대한 접근 설정 (인증 없이 접근 가능한 경로!)
                        .requestMatchers(allowUris).permitAll()

                        // 그 외 모든 요청에 대해 인증 요구
                        .anyRequest().authenticated()
                )

                // 인증 및 인가 예외 처리
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) -> {
                            GeneralErrorCode code = GeneralErrorCode.UNAUTHORIZED;

                            response.setStatus(code.getStatus().value());
                            response.setContentType("application/json;charset=UTF-8");

                            response.getWriter().write(
                                    objectMapper.writeValueAsString(
                                            ApiResponse.onFailure(code, null)
                                    )
                            );
                        })

                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            GeneralErrorCode code = GeneralErrorCode.FORBIDDEN;

                            response.setStatus(code.getStatus().value());
                            response.setContentType("application/json;charset=UTF-8");

                            response.getWriter().write(
                                    objectMapper.writeValueAsString(
                                            ApiResponse.onFailure(code, null)
                                    )
                            );
                        })
                )

                // 로그인 성공 시 해당 화면으로 리다이렉트 (로그인 페이지는 모두가 접근 가능)
                .formLogin(form -> form
                        .defaultSuccessUrl("/swagger-ui/index.html", true)
                        .permitAll()
                )

                // 로그아웃 시 어느 화면으로 리다이렉트 할 건지 처리
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );
        return http.build();
    }

    // 비밀번호 암호화 (솔트)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
