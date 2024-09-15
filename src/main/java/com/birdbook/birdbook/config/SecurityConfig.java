package com.birdbook.birdbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.birdbook.birdbook.common.util.JwtFilter;
import com.birdbook.birdbook.common.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtUtil jwtUtil;
	private final UserDetailsService userDetailsService;

	@Bean
	public JwtFilter jwtFilter() {
		return new JwtFilter(jwtUtil, userDetailsService);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		// csrf : disable
		http
			.csrf((auth) -> auth.disable());

		// form login : disable
		http
			.formLogin((auth) -> auth.disable());

		// HTTP Basic : disable
		http
			.httpBasic((auth) -> auth.disable());

		// 경로별 인가
		http
			.authorizeHttpRequests((auth) -> auth
				.requestMatchers(
					"/swagger-ui/**",
					"/v3/api-docs/**",
					"/swagger-ui.html",
					"/api-docs/**",
					"/webjars/**",
					"/login",
					"/login/oauth2/**",  // 카카오 로그인 경로 허용
					"/api/auth/**"       // 인증 관련 API 허용
				).permitAll()
				.anyRequest().authenticated());

		// session : stateless
		http
			.sessionManagement((session) -> session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http
			.addFilterBefore(jwtFilter(),
				UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
