package com.devmountain.noteApp.entities;


import com.devmountain.noteApp.dtos.NoteDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "Notes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String body;

    @ManyToOne
    @JsonBackReference
    private User user;

    public Note(NoteDto noteDto) {
        if (noteDto.getBody() != null) {
            this.body = noteDto.getBody();
        }

    }
    @Transactional
    public void addNote(NoteDto noteDto, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Note note = new Note(noteDto);
        userOptional.isPresent(note::setUser);
        noteRepository.saveAndFlush(note);
    }
    @Transactional
    public void deleteNoteById(Long noteId) {
        Optional<Note> noteOptional = noteRepository.findById(noteId);
        noteOptional.isPresent(note -> noteRepository.delete(note));
    }
    @Transactional
    public void updateNoteById(NoteDto noteDto) {
        Optional<Note> noteOptional = noteRepository.findById(noteDto.getId());
        noteOptional.isPresent(note -> {
            note.setBody(noteDto.getBody());
            noteRepository.saveAndFlush(note);
        });
    }

}
