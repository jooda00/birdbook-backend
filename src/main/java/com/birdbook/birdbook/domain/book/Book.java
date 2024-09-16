package com.birdbook.birdbook.domain.book;

import com.birdbook.birdbook.domain.user.User;
import com.birdbook.birdbook.dto.book.request.BookReq;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	public static Book of(BookReq req, User user) {
		return Book.builder()
			.title(req.getTitle())
			.author(req.getAuthor())
			.isbn(req.getIsbn())
			.user(user)
			.build();
	}
}
