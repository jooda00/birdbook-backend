package com.birdbook.birdbook.service.note;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.birdbook.birdbook.domain.book.Book;
import com.birdbook.birdbook.domain.note.Note;
import com.birdbook.birdbook.dto.note.request.NoteReq;
import com.birdbook.birdbook.repository.book.BookRepository;
import com.birdbook.birdbook.repository.note.NoteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoteService {

	private final NoteRepository noteRepository;
	private final BookRepository bookRepository;

	@Transactional
	public Note saveNote(NoteReq req) {
		Book book = bookRepository.findById(req.getBookId())
			.orElseThrow(() -> new IllegalArgumentException("도서가 존재하지 않습니다."));

		Note note = Note.of(req, book);
		noteRepository.save(note);
		return note;
	}
}
