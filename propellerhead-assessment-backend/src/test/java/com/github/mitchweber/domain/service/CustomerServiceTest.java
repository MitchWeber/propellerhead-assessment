package com.github.mitchweber.domain.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.mitchweber.app.rest.model.CustomerDetails;
import com.github.mitchweber.domain.model.Customer;
import com.github.mitchweber.domain.model.CustomerStatus;
import com.github.mitchweber.domain.repository.ICustomerRepository;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    private ICustomerRepository customerRepository;

    private CustomerService customerService;

    @Before
    public void setup() {
        customerService = new CustomerService(customerRepository);
    }

    @Test
    public void testGetAllCustomer() {
        // Given
        // When
        customerService.getAllCustomers();

        // Then
        verify(customerRepository).findAll();
    }

    @Test
    public void testGetCustomerWorksForKnownId() {
        // Given
        when(customerRepository.findById(Mockito.any())).thenReturn(Optional.of(new Customer(CustomerStatus.CURRENT, "Test Customer")));

        // When
        customerService.getCustomer(1L);

        // Then
        verify(customerRepository).findById(1L);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetCustomerThrowsExceptionForUnknownId() {
        // Given
        when(customerRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        // When
        customerService.getCustomer(1L);

        // Then expect exception
    }

    @Test
    public void thatUpdateCustomerWorks() {
        // Given
        Customer savedCustomer = new Customer(CustomerStatus.NON_ACTIVE, "Test User");
        when(customerRepository.findById(Mockito.any())).thenReturn(Optional.of(savedCustomer));

        Customer expectedCustomer = new Customer(CustomerStatus.PROSPECTIVE, savedCustomer.getName());
        expectedCustomer.setEmail("testmai@test.de");
        expectedCustomer.setMobileNumber("039875682746");
        expectedCustomer.setTelefoneNumber("999928383");
        CustomerDetails customerDetails = new CustomerDetails(expectedCustomer);

        // When
        customerService.updateCustomer(customerDetails);

        // Then
        verify(customerRepository).save(savedCustomer);
        assertThat(savedCustomer.getEmail(), is(expectedCustomer.getEmail()));
        assertThat(savedCustomer.getMobileNumber(), is(expectedCustomer.getMobileNumber()));
        assertThat(savedCustomer.getStatus(), is(expectedCustomer.getStatus()));
        assertThat(savedCustomer.getTelefoneNumber(), is(expectedCustomer.getTelefoneNumber()));
    }

}
