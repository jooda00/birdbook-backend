package com.birdbook.birdbook.service.kakao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.birdbook.birdbook.domain.user.User;
import com.birdbook.birdbook.dto.kakao.response.KakaoToken;
import com.birdbook.birdbook.dto.kakao.response.KakaoUserRes;
import com.birdbook.birdbook.repository.user.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KakaoService {
	private static final String ROLE = "ROLE_USER";
	private final String KAKAO_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
	private final String KAKAO_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";
	private final UserRepository userRepository;
	private final RestTemplate restTemplate;
	private final String clientId;
	private final String redirectUri;

	public KakaoService(UserRepository userRepository, RestTemplate restTemplate,
		@Value("${spring.security.oauth2.client.registration.kakao.client-id}") String clientId,
		@Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}") String redirectUri) {
		this.userRepository = userRepository;
		this.restTemplate = restTemplate;
		this.clientId = clientId;
		this.redirectUri = redirectUri;
	}

	public String getAccessToken(String code) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpBody 객체 생성 -> 카카오 공식문서 참고
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", clientId);
		params.add("redirect_uri", redirectUri);
		params.add("code", code);

		// 헤더와 바디 합치기 위해 HttpEntity 객체 생성
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
		System.out.println(kakaoTokenRequest);

		// 카카오로부터 Access token 수신
		ResponseEntity<String> accessTokenResponse = restTemplate.exchange(KAKAO_TOKEN_URL, HttpMethod.POST,
			kakaoTokenRequest, String.class);

		// JSON Parsing (-> KakaoTokenDto)
		ObjectMapper objectMapper = new ObjectMapper();
		KakaoToken kakaoToken = null;
		try {
			kakaoToken = objectMapper.readValue(accessTokenResponse.getBody(), KakaoToken.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		log.info("access token : {}", kakaoToken.getAccessToken());
		return kakaoToken.getAccessToken();
	}

	public KakaoUserRes getUserInfo(String accessToken) {
		Map<String, Object> response = restTemplate.getForObject(KAKAO_USER_INFO_URL + "?access_token=" + accessToken,
			Map.class);
		String nickname = (String)((Map<String, Object>)response.get("properties")).get("nickname");

		User user = userRepository.findByUsername(nickname);

		if (user == null) {
			// 사용자 저장
			user = User.of(nickname, ROLE);
			userRepository.save(user);
			log.info("create user : {}", (user.getId() + " " + user.getUsername()));
		} else {
			log.info("already existed user info : {}", (user.getId() + " " + user.getUsername()));
		}
		return new KakaoUserRes(nickname, ROLE);
	}
}
