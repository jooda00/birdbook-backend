package com.birdbook.birdbook.dto.note.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoteReq {
	private Long bookId;
	private String title;
	private String content;
}
