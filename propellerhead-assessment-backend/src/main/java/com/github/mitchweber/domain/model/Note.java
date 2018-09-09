package com.github.mitchweber.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTE_ID_SEQ")
    @SequenceGenerator(name = "NOTE_ID_SEQ", sequenceName = "NOTE_ID_SEQ")
    private Long id;
    private OffsetDateTime creationDate;
    private OffsetDateTime modifiedDate;
    private String content;

    public Note(String content) {
        this.content = content;
        this.creationDate = OffsetDateTime.now();
        this.modifiedDate = OffsetDateTime.now();
    }

    Note() {
        // JPA
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getCreationDate() {
        return creationDate;
    }

    public OffsetDateTime getModifiedDate() {
        return modifiedDate;
    }

    public String getContent() {
        return content;
    }

    public void edit(String newContent) {
        this.modifiedDate = OffsetDateTime.now();
        this.content = newContent;
    }

}
