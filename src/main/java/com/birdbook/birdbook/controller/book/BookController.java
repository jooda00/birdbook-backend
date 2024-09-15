package com.birdbook.birdbook.controller.book;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.birdbook.birdbook.dto.book.reponse.BookRes;
import com.birdbook.birdbook.service.book.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

	private final BookService bookService;

	@GetMapping("/{keyword}")
	public BookRes searchBooks(@PathVariable("keyword") String keyword) {
		return bookService.searchBook(keyword);
	}
}
