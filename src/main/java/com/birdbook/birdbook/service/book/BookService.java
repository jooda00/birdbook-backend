package com.birdbook.birdbook.service.book;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.birdbook.birdbook.domain.book.Book;
import com.birdbook.birdbook.domain.user.User;
import com.birdbook.birdbook.dto.book.reponse.BookSearchRes;
import com.birdbook.birdbook.dto.book.request.BookReq;
import com.birdbook.birdbook.repository.book.BookRepository;
import com.birdbook.birdbook.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {

	private final RestTemplate restTemplate;
	private final String NAVER_BOOK_SEARCH_URL = "https://openapi.naver.com/v1/search/book.json?query={keyword}";
	private final BookRepository bookRepository;
	private final UserRepository userRepository;
	@Value("${naver.client-id}")
	private String clientId;
	@Value("${naver.client-secret}")
	private String clientSecret;

	public BookSearchRes searchBook(String keyword) {
		HttpHeaders headers = new HttpHeaders();

		headers.add("X-Naver-Client-Id", clientId);
		headers.add("X-Naver-Client-Secret", clientSecret);

		HttpEntity<String> naverBookSearchRequest = new HttpEntity<>(headers);

		BookSearchRes bookSearchRes = restTemplate.exchange(NAVER_BOOK_SEARCH_URL, HttpMethod.GET,
			naverBookSearchRequest, BookSearchRes.class, keyword).getBody();
		return bookSearchRes;
	}

	@Transactional
	public Book saveBook(BookReq req) {
		User user = userRepository.findById(req.getUserId())
			.orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
		Book book = Book.of(req, user);
		bookRepository.save(book);
		return book;
	}
}
