package com.github.mitchweber.app.rest.model;

import java.time.OffsetDateTime;

import com.github.mitchweber.domain.model.Customer;
import com.github.mitchweber.domain.model.CustomerStatus;

public class CustomerExcerpt {

    private Long id;
    private String name;
    private OffsetDateTime created;
    private CustomerStatus status;

    public CustomerExcerpt(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.created = customer.getCreated();
        this.status = customer.getStatus();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public void setCreated(OffsetDateTime created) {
        this.created = created;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

}
