package com.birdbook.birdbook.dto.book.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookLikeReq {
	private Long userId;
	private Long bookId;
}
