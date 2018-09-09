package com.github.mitchweber.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.Repository;

import com.github.mitchweber.domain.model.Customer;

public interface ICustomerRepository extends Repository<Customer, Long>, QuerydslPredicateExecutor<Customer> {

    List<Customer> findAll();

    void flush();

    Customer save(Customer customer);

    Optional<Customer> findById(Long id);

}
