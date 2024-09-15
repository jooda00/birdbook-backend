package com.birdbook.birdbook.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.birdbook.birdbook.common.util.JwtUtil;
import com.birdbook.birdbook.dto.kakao.response.KakaoUserRes;
import com.birdbook.birdbook.service.kakao.KakaoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final KakaoService kakaoService;
	private final JwtUtil jwtUtil;

	@PostMapping("/login/oauth2/code/kakao")
	public ResponseEntity<String> login(@RequestParam String code) {
		// 1. 인증 코드로 액세스 토큰 요청
		String accessToken = kakaoService.getAccessToken(code);

		// 2. 액세스 토큰으로 사용자 정보 요청
		KakaoUserRes userInfo = kakaoService.getUserInfo(accessToken);

		// 3. 사용자 정보로 JWT 생성
		String token = jwtUtil.generateToken(userInfo.getUsername(), userInfo.getRole(), 60 * 60 * 1000L);

		return ResponseEntity.ok(token);
	}
}
