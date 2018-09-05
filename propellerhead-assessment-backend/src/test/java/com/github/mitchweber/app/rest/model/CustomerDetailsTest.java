package com.github.mitchweber.app.rest.model;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.mitchweber.domain.model.Customer;
import com.github.mitchweber.domain.model.CustomerStatus;
import com.github.mitchweber.domain.model.Note;

public class CustomerDetailsTest {

    @Test
    public void testThatConstructorInitiallisesCorrect() {
        // Given
        Customer customer = new Customer(CustomerStatus.CURRENT, "Michael Weber");
        customer.setMobileNumber("+49012042394234");
        customer.setTelefoneNumber("+629123787765");
        customer.setEmail("mw@my-mail.com");
        customer.addNote(new Note("A Note"));

        // When
        CustomerDetails customerDetails = new CustomerDetails(customer);

        // Then
        assertThat(customerDetails.getCreated(), is(customer.getCreated()));
        assertThat(customerDetails.getEmail(), is(customer.getEmail()));
        assertThat(customerDetails.getMobileNumber(), is(customer.getMobileNumber()));
        assertThat(customerDetails.getName(), is(customer.getName()));
        assertThat(customerDetails.getStatus(), is(customer.getStatus()));
        assertThat(customerDetails.getTelefoneNumber(), is(customer.getTelefoneNumber()));
        assertThat(customerDetails.getNotes().size(), is(customer.getNotes().size()));
        assertThat(customerDetails.getNotes().get(0).getContent(), is(customer.getNotes().get(0).getContent()));
    }

}
