package com.github.mitchweber.domain.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_ID_SEQ")
    @SequenceGenerator(name = "CUSTOMER_ID_SEQ", sequenceName = "CUSTOMER_ID_SEQ")
    private Long id;

    private String name;
    private OffsetDateTime created;
    @Enumerated(EnumType.STRING)
    private CustomerStatus status;
    private String mobileNumber;
    private String telefoneNumber;
    private String email;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id ASC")
    @JoinColumn(name = "CUSTOMER_ID", nullable = false, unique = false)
    private List<Note> notes = new ArrayList<>();

    public Customer(CustomerStatus status, String name) {
        this.status = status;
        this.name = name;
        this.created = OffsetDateTime.now();
    }

    Customer() {
        // JPA
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Note> getNotes() {
        return Collections.unmodifiableList(notes);
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public Long getId() {
        return id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getTelefoneNumber() {
        return telefoneNumber;
    }

    public void setTelefoneNumber(String telefoneNumber) {
        this.telefoneNumber = telefoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
