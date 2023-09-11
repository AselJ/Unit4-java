package com.devmountain.noteApp.services;

import com.devmountain.noteApp.dtos.NoteDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface NoteService {

    @Transactional

    List<NoteDto> getAllNotesByUser(Long userId);

    void deleteNoteById(Long noteId);

    void updateNoteById(NoteDto noteDto);

    Optional<NoteDto> getNoteById(Long noteId);
}
