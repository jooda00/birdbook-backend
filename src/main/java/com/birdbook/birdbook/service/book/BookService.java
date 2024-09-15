package com.birdbook.birdbook.service.book;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.birdbook.birdbook.dto.book.reponse.BookRes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {

	private final RestTemplate restTemplate;
	private final String NAVER_BOOK_SEARCH_URL = "https://openapi.naver.com/v1/search/book.json?query={keyword}";
	@Value("${naver.client-id}")
	private String clientId;
	@Value("${naver.client-secret}")
	private String clientSecret;

	public BookRes searchBook(String keyword) {
		HttpHeaders headers = new HttpHeaders();

		headers.add("X-Naver-Client-Id", clientId);
		headers.add("X-Naver-Client-Secret", clientSecret);

		HttpEntity<String> naverBookSearchRequest = new HttpEntity<>(headers);

		BookRes bookRes = restTemplate.exchange(NAVER_BOOK_SEARCH_URL, HttpMethod.GET,
			naverBookSearchRequest, BookRes.class, keyword).getBody();
		return bookRes;
	}
}
