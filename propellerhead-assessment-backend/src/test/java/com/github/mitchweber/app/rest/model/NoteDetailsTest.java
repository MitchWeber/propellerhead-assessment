package com.github.mitchweber.app.rest.model;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.mitchweber.domain.model.Note;

public class NoteDetailsTest {

    @Test
    public void testThatConstructorInitiallisesCorrect() {
        // Given
        Note note = new Note("Test note");

        // When
        NoteDetails noteDetails = new NoteDetails(note);

        // Then
        assertThat(noteDetails.getContent(), is(note.getContent()));
        assertThat(noteDetails.getCreatedDate(), is(note.getCreationDate()));
        assertThat(noteDetails.getId(), is(note.getId()));
        assertThat(noteDetails.getModifiedDate(), is(note.getModifiedDate()));
    }

}
