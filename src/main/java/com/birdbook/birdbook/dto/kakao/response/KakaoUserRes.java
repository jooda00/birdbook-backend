package com.birdbook.birdbook.dto.kakao.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoUserRes {
	private String username;
	private String role;

	public KakaoUserRes(String username, String role) {
		this.username = username;
		this.role = role;
	}
}
