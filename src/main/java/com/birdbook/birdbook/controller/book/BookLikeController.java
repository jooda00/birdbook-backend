package com.birdbook.birdbook.controller.book;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;

import com.birdbook.birdbook.domain.book.BookLike;
import com.birdbook.birdbook.dto.book.request.BookLikeReq;
import com.birdbook.birdbook.dto.oauth.reponse.CustomUserDetails;
import com.birdbook.birdbook.service.book.BookLikeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookLikeController {

	private final BookLikeService bookLikeService;

	@MutationMapping
	public BookLike saveBookLike(@Argument BookLikeReq input, @AuthenticationPrincipal CustomUserDetails userDetails) {
		return bookLikeService.saveBookLike(input);
	}
}
