package com.github.mitchweber.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.RepositoryDefinition;

import com.github.mitchweber.domain.model.Customer;

@RepositoryDefinition(domainClass = Customer.class, idClass = Long.class)
public interface ICustomerRepository extends QuerydslPredicateExecutor<Customer> {

    List<Customer> findAll();

    void flush();

    Customer save(Customer customer);

    Optional<Customer> findById(Long id);

}
