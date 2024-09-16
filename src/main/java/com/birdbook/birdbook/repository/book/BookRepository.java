package com.birdbook.birdbook.repository.book;

import org.springframework.data.jpa.repository.JpaRepository;

import com.birdbook.birdbook.domain.book.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
