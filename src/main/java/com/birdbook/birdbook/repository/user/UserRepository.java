package com.birdbook.birdbook.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.birdbook.birdbook.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
