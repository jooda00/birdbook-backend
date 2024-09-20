package com.birdbook.birdbook.dto.note.response;

import java.time.LocalDateTime;

import com.birdbook.birdbook.domain.note.Note;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NoteRes {
	private Long id;
	private String title;
	private String content;
	private Long bookId;
	private LocalDateTime createdAt;

	public static NoteRes from(Note note) {
		return NoteRes.builder()
			.id(note.getId())
			.title(note.getTitle())
			.content(note.getContent())
			.createdAt(note.getCreatedAt())
			.build();
	}
}
