package com.suresh.junit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suresh.junit.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	List<Customer> findByCity(String city);
	Customer findByName(String name);
}
