package com.birdbook.birdbook.repository.note;

import org.springframework.data.jpa.repository.JpaRepository;

import com.birdbook.birdbook.domain.note.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
