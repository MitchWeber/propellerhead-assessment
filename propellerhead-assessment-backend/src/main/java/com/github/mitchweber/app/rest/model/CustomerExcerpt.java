package com.github.mitchweber.app.rest.model;

import java.time.OffsetDateTime;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.mitchweber.domain.model.CustomerStatus;

public class CustomerExcerpt extends ResourceSupport {

    private Long entityId;
    private String name;
    private OffsetDateTime created;
    private CustomerStatus status;

    @JsonCreator
    public CustomerExcerpt(
            @JsonProperty("entityId") Long entityId,
            @JsonProperty("name") String name,
            @JsonProperty("created") OffsetDateTime created,
            @JsonProperty("status") CustomerStatus status) {
        this.entityId = entityId;
        this.name = name;
        this.created = created;
        this.status = status;
    }

    public Long getEntityId() {
        return entityId;
    }

    public String getName() {
        return name;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public CustomerStatus getStatus() {
        return status;
    }

}
