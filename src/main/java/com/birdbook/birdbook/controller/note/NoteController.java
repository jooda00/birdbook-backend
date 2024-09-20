package com.birdbook.birdbook.controller.note;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;

import com.birdbook.birdbook.domain.note.Note;
import com.birdbook.birdbook.dto.note.request.NoteDeleteReq;
import com.birdbook.birdbook.dto.note.request.NoteReq;
import com.birdbook.birdbook.dto.oauth.reponse.CustomUserDetails;
import com.birdbook.birdbook.service.note.NoteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class NoteController {

	private final NoteService noteService;

	@MutationMapping
	public Note saveNote(@Argument NoteReq input, @AuthenticationPrincipal CustomUserDetails userDetails) {
		return noteService.saveNote(input);
	}

	@MutationMapping
	public Long deleteNote(@Argument NoteDeleteReq input, @AuthenticationPrincipal CustomUserDetails userDetails) {
		return noteService.deleteNote(input);
	}
}
