package com.github.mitchweber.app.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.mitchweber.app.rest.model.CustomerDetails;
import com.github.mitchweber.app.rest.model.CustomerExcerpt;
import com.github.mitchweber.app.rest.model.NoteDetails;
import com.github.mitchweber.domain.model.Customer;
import com.github.mitchweber.domain.model.Note;
import com.github.mitchweber.domain.service.CustomerService;

@RestController
@RequestMapping("/rest/customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerRestController {

    private final CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public List<CustomerExcerpt> getAllCustomers() {
        return customerService.getAllCustomers().stream().map(CustomerExcerpt::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CustomerDetails getCustomer(@PathVariable Long id) {
        return new CustomerDetails(customerService.getCustomer(id));
    }

    @PostMapping("/{id}")
    public CustomerDetails saveCustomerDetails(@PathVariable Long id, @RequestBody CustomerDetails customerDetails) {
        Customer customer = customerService.updateCustomer(customerDetails);
        return new CustomerDetails(customer);
    }

    @PostMapping("/{customerId}/note")
    public NoteDetails addNote(@PathVariable Long customerId, @RequestBody Note newNote) {
        Note note = customerService.addNote(customerId, newNote);
        return new NoteDetails(note);
    }

    @PostMapping("/{customerId}/note/{noteId}")
    public Note editNote(@PathVariable("customerId") Long customerId, @PathVariable("noteId") Long noteId, @RequestBody Note newNote) {
        Note existingNote = customerService.editNote(customerId, newNote);
        existingNote.edit(newNote.getContent());
        return existingNote;
    }
}
