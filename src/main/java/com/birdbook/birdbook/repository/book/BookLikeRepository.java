package com.birdbook.birdbook.repository.book;

import org.springframework.data.jpa.repository.JpaRepository;

import com.birdbook.birdbook.domain.book.BookLike;

public interface BookLikeRepository extends JpaRepository<BookLike, Long> {
}
