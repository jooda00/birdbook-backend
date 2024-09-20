package com.birdbook.birdbook.service.note;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.birdbook.birdbook.domain.book.Book;
import com.birdbook.birdbook.domain.note.Note;
import com.birdbook.birdbook.dto.note.request.NoteDeleteReq;
import com.birdbook.birdbook.dto.note.request.NoteReq;
import com.birdbook.birdbook.dto.note.response.NoteRes;
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

	@Transactional
	public Long deleteNote(NoteDeleteReq req) {
		Note note = noteRepository.findById(req.getNoteId())
			.orElseThrow(() -> new IllegalArgumentException("작성한 노트가 존재하지 않습니다."));
		if (note.isDeleted()) {
			throw new IllegalArgumentException("이미 삭제된 노트입니다.");
		}
		note.deleteNote();
		return note.getId();
	}

	public List<NoteRes> getNotes() {
		List<Note> notes = noteRepository.findAllByOrderByCreatedAtDesc();
		return notes.stream()
			.filter(note -> !note.isDeleted())
			.map(NoteRes::from)
			.collect(Collectors.toList());
	}
}
