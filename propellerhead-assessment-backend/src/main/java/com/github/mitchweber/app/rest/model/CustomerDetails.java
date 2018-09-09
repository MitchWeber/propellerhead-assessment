package com.github.mitchweber.app.rest.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.github.mitchweber.domain.model.Customer;
import com.github.mitchweber.domain.model.CustomerStatus;

public class CustomerDetails {

    private Long id;

    private String name;
    private OffsetDateTime created;
    private CustomerStatus status;
    private String mobileNumber;
    private String telefoneNumber;
    private String email;

    private List<NoteDetails> notes = new ArrayList<>();

    CustomerDetails() {
    }

    public CustomerDetails(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.created = customer.getCreated();
        this.status = customer.getStatus();
        this.mobileNumber = customer.getMobileNumber();
        this.telefoneNumber = customer.getTelefoneNumber();
        this.email = customer.getEmail();
        this.notes = customer.getNotes().stream().map(NoteDetails::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public List<NoteDetails> getNotes() {
        return Collections.unmodifiableList(notes);
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getTelefoneNumber() {
        return telefoneNumber;
    }

    public String getEmail() {
        return email;
    }

}
