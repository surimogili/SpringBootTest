package com.suresh.junit.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suresh.junit.exception.CustomerNotFoundException;
import com.suresh.junit.model.Customer;
import com.suresh.junit.repository.CustomerRepository;

@Service
//@Transactional
public class CustomerServiceImpl implements CustomerService {
	private final static Logger slf4jLogger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	//@Transactional(noRollbackFor=RuntimeException.class)
	public Customer saveCustomer(Customer customer) {
		slf4jLogger.info(customer.toString());
		return customerRepository.save(customer);
	}

	@Override
	public Customer findOne(long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		if(!customer.isPresent())
			throw new CustomerNotFoundException("customer id : "+ id );
		return customerRepository.findById(id).get();
	}

	@Override
	public List<Customer> findByCity(String city) {
		return customerRepository.findByCity(city);
	}

	@Override
	public Customer findByName(String name) {
		return customerRepository.findByName(name);
	}
}
