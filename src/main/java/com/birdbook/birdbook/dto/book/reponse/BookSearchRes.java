package com.birdbook.birdbook.dto.book.reponse;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookSearchRes {
	private Item[] items;

	@Getter
	@NoArgsConstructor
	static class Item {
		private String title;
		private String author;
		private String isbn;
	}
}
