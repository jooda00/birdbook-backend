package com.birdbook.birdbook.domain.book;

import com.birdbook.birdbook.dto.book.request.BookReq;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String author;
	private String isbn;

	public static Book from(BookReq req) {
		return Book.builder()
			.title(req.getTitle())
			.author(req.getAuthor())
			.isbn(req.getIsbn())
			.build();
	}
}
