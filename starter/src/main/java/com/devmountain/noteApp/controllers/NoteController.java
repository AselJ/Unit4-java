package com.devmountain.noteApp.controllers;

import com.devmountain.noteApp.dtos.NoteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMopping("api/v1/notes")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @GetMapping("/user/{userId}")
    public List<NoteDto> getNotesByUser(@PathVariable Long userId){
        return noteService.getAllNotesByUser(userId);
    }

    @PostMapping("user/{userId}")
    public void addNote(@RequestBody NoteDto noteDto, @PathVariable Long userId){
        noteService.addNote(noteDto, userId);
    }

    @DeleteMapping("/{noteId}")
    public void DeleteNoteById(@PathVariable Long noteId){
        noteService.deleteNoteById(noteId);
    }

    @PutMapping
    public void updateNote(@RequestBody NoteDto noteDto){
        noteService.updateNoteById(noteDto);
    }

    @GetMapping("/{noteId}")
    public Optional<NoteDto> getAllNotesById(@PathVariable Long noteId){
        return noteService.getNoteById(noteId);
    }

}
