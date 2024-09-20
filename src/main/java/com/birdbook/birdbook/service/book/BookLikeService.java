package com.birdbook.birdbook.service.book;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.birdbook.birdbook.domain.book.Book;
import com.birdbook.birdbook.domain.book.BookLike;
import com.birdbook.birdbook.domain.user.User;
import com.birdbook.birdbook.dto.book.request.BookLikeReq;
import com.birdbook.birdbook.repository.book.BookLikeRepository;
import com.birdbook.birdbook.repository.book.BookRepository;
import com.birdbook.birdbook.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookLikeService {

	private final BookLikeRepository bookLikeRepository;
	private final UserRepository userRepository;
	private final BookRepository bookRepository;

	@Transactional
	public BookLike saveBookLike(BookLikeReq req) {
		User user = userRepository.findById(req.getUserId())
			.orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
		Book book = bookRepository.findById(req.getBookId())
			.orElseThrow(() -> new IllegalArgumentException("도서가 존재하지 않습니다."));
		BookLike bookLike = BookLike.of(user, book);
		bookLikeRepository.save(bookLike);
		return bookLike;
	}
}
