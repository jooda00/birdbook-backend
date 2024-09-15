package com.birdbook.birdbook.controller.book;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.birdbook.birdbook.domain.book.Book;
import com.birdbook.birdbook.dto.book.reponse.BookSearchRes;
import com.birdbook.birdbook.dto.book.request.BookReq;
import com.birdbook.birdbook.service.book.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

	private final BookService bookService;

	@GetMapping("/{keyword}")
	public BookSearchRes searchBooks(@PathVariable("keyword") String keyword) {
		return bookService.searchBook(keyword);
	}

	@MutationMapping
	public Book saveBook(@Argument BookReq input) {
		return bookService.saveBook(input);
	}
}
