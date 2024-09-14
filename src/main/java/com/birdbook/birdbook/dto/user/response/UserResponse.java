package com.birdbook.birdbook.dto.user.response;

import java.util.Map;

import com.birdbook.birdbook.domain.user.User;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {
	private String role;
	private String name;
	private String username;
	private Map<String, Object> attributes;

	public static UserResponse from(User user) {
		return UserResponse.builder()
			.role(user.getRole())
			.name(user.getName())
			.username(user.getUsername())
			.build();
	}
}
