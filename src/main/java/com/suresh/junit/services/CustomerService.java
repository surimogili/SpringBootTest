package com.suresh.junit.services;

import java.util.List;

import com.suresh.junit.model.Customer;

public interface CustomerService {
	
	public List<Customer> findAll();
	public Customer saveCustomer(Customer customer);
	public Customer findOne(long id);
	public List<Customer> findByCity(String city);
	public Customer findByName(String name);
}
