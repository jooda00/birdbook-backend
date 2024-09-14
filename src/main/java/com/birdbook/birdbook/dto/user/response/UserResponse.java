package com.birdbook.birdbook.dto.user.response;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {
	private String role;
	private String name;
	private String username;
	private Map<String, Object> attributes;
}
