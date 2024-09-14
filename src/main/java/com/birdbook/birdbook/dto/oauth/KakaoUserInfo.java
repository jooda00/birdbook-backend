package com.birdbook.birdbook.dto.oauth;

import java.util.Map;

import com.birdbook.birdbook.dto.oauth.reponse.Oauth2Response;

public class KakaoUserInfo implements Oauth2Response {
	private final Map<String, Object> attributes;
	private final Map<String, Object> attributesAccount;
	private final Map<String, Object> attributesProfile;

	public KakaoUserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;
		this.attributesAccount = (Map<String, Object>)attributes.get("kakao_account");
		this.attributesProfile = (Map<String, Object>)attributesAccount.get("profile");
	}

	@Override
	public String getProviderId() {
		return attributes.get("id").toString();
	}

	@Override
	public String getProvider() {
		return "kakao";
	}

	@Override
	public String getName() {
		return attributesProfile.get("nickname").toString(); // account -> profile -> nickname 계층으로 JSON 반환
	}
}
