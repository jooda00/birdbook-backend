package com.birdbook.birdbook.dto.oauth.reponse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.birdbook.birdbook.dto.user.response.UserResponse;

public class CustomOauth2UserResponse implements OAuth2User {

	private final UserResponse userResponse;

	public CustomOauth2UserResponse(UserResponse userResponse) {
		this.userResponse = userResponse;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return userResponse.getAttributes();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<>();

		collection.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return userResponse.getRole();
			}
		});

		return collection;
	}

	@Override
	public String getName() {
		return userResponse.getName();
	}

	public String getUsername() {
		return userResponse.getUsername();
	}
}
