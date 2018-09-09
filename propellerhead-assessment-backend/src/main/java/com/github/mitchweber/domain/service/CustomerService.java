package com.github.mitchweber.domain.service;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitchweber.app.rest.model.CustomerDetails;
import com.github.mitchweber.domain.model.Customer;
import com.github.mitchweber.domain.model.Note;
import com.github.mitchweber.domain.repository.ICustomerRepository;

@Component
public class CustomerService {

    private final ICustomerRepository customerRepository;

    @Autowired
    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return Collections.unmodifiableList(customerRepository.findAll());
    }

    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Customer updateCustomer(CustomerDetails customerDetails) {
        Customer customer = customerRepository.findById(customerDetails.getId()).orElseThrow(EntityNotFoundException::new);
        customer.setEmail(customerDetails.getEmail());
        customer.setMobileNumber(customerDetails.getMobileNumber());
        customer.setStatus(customerDetails.getStatus());
        customer.setTelefoneNumber(customerDetails.getTelefoneNumber());
        customerRepository.save(customer);
        return customer;
    }

    @Transactional
    public Note editNote(Long customerId, Note newNote) {
        Note existingNote = getCustomer(customerId).getNotes()
                .stream()
                .filter(n -> n.getId().equals(newNote.getId()))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
        existingNote.edit(newNote.getContent());
        return existingNote;
    }

    @Transactional
    public Note addNote(Long customerId, Note note) {
        Customer customer = getCustomer(customerId);
        customer.addNote(note);
        customerRepository.save(customer);
        customerRepository.flush();
        return note;
    }
}
