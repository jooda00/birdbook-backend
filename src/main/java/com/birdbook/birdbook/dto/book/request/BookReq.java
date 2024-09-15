package com.birdbook.birdbook.dto.book.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookReq {
	private String title;
	private String author;
	private String isbn;
}
