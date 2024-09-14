package com.birdbook.birdbook.service.oauth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.birdbook.birdbook.dto.oauth.KakaoUserInfo;
import com.birdbook.birdbook.dto.oauth.reponse.CustomOauth2UserResponse;
import com.birdbook.birdbook.dto.oauth.reponse.Oauth2Response;
import com.birdbook.birdbook.dto.user.response.UserResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomOauth2UserService extends DefaultOAuth2UserService {

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);
		log.info("유저 정보 : {} ", oAuth2User.getAttributes());

		Oauth2Response response = new KakaoUserInfo(oAuth2User.getAttributes());

		// 서버에서 관리할 유저 식별자
		String username = response.getProvider() + " " + response.getProviderId();

		UserResponse userResponse = UserResponse.builder()
			.role("ROLE_USER")
			.name(response.getName())
			.username(username)
			.build();
		log.info("userResponse dto : {} ", (userResponse.getName() + " " + userResponse.getUsername()));

		return new CustomOauth2UserResponse(userResponse);
	}
}
