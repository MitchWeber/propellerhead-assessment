package com.github.mitchweber.app.rest.model;

import java.time.OffsetDateTime;

import com.github.mitchweber.domain.model.Note;

public class NoteDetails {

    private Long id;
    private OffsetDateTime createdDate;
    private OffsetDateTime modifiedDate;
    private String content;

    public NoteDetails(Note note) {
        this.id = note.getId();
        this.createdDate = note.getCreationDate();
        this.modifiedDate = note.getModifiedDate();
        this.content = note.getContent();
    }

    NoteDetails() {

    }

    public Long getId() {
        return id;
    }

    public OffsetDateTime getCreatedDate() {
        return createdDate;
    }

    public OffsetDateTime getModifiedDate() {
        return modifiedDate;
    }

    public String getContent() {
        return content;
    }

}
