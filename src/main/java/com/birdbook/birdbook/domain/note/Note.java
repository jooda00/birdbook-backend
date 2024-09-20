package com.birdbook.birdbook.domain.note;

import com.birdbook.birdbook.common.entity.BaseEntity;
import com.birdbook.birdbook.domain.book.Book;
import com.birdbook.birdbook.dto.note.request.NoteReq;

import jakarta.persistence.Column;
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
public class Note extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NOTE_ID")
	private Long id;
	private String title;
	private String content;
	private boolean isDeleted = false;

	@ManyToOne
	@JoinColumn(name = "BOOK_ID")
	private Book book;

	public static Note of(NoteReq req, Book book) {
		return Note.builder()
			.title(req.getTitle())
			.content(req.getContent())
			.book(book).
			build();
	}

	public void deleteNote() {
		this.isDeleted = true;
	}
}
