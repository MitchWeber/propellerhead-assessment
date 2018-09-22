package com.github.mitchweber.app.rest.model;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.mitchweber.domain.model.Customer;
import com.github.mitchweber.domain.model.CustomerStatus;

public class CustomerExcerptTest {

    @Test
    public void testThatConstructorInitiallisesCorrect() {
        // Given
        Customer customer = new Customer(CustomerStatus.PROSPECTIVE, "Test User");

        // When
        CustomerExcerpt customerExcerpt = new CustomerExcerpt(
                customer.getId(),
                customer.getName(),
                customer.getCreated(),
                customer.getStatus());

        // Then
        assertThat(customerExcerpt.getCreated(), is(customer.getCreated()));
        assertThat(customerExcerpt.getName(), is(customer.getName()));
        assertThat(customerExcerpt.getStatus(), is(customer.getStatus()));
    }

}
