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
    private OffsetDateTime created;
    private OffsetDateTime modified;
    private String content;

    public Note(String content) {
        this.content = content;
        this.created = OffsetDateTime.now();
        this.modified = OffsetDateTime.now();
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
        return created;
    }

    public OffsetDateTime getModifiedDate() {
        return modified;
    }

    public String getContent() {
        return content;
    }

    public void edit(String newContent) {
        this.modified = OffsetDateTime.now();
        this.content = newContent;
    }

}
