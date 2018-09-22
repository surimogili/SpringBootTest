package com.suresh.test.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suresh.junit.model.Customer;
import com.suresh.junit.repository.CustomerRepository;
import com.suresh.junit.services.CustomerService;
import com.suresh.junit.services.CustomerServiceImpl;

@RunWith(SpringRunner.class)
public class CustomerServiceImplTest {

	@TestConfiguration
	static class CustomerServiceImplTestContextConfiguration
	{
		@Bean
		public CustomerService customerService()
		{
			return new CustomerServiceImpl();
		}
	}
	
	@Autowired
	private CustomerService customerService;
	
	@MockBean
	private CustomerRepository customerRepository;
	
	@Before
	public void setUp() throws Exception
	{
		ObjectMapper mapper = new ObjectMapper();
		Customer customer = mapper.readValue(new File("src/test/resources/customer.json"), Customer.class);
		//Customer customer = new Customer(47, "Krishna", "krishna@gmail.com", "Vizag", new Date());
		Mockito.when(customerRepository.findByName(customer.getName())).thenReturn(customer);
	}
	
	@Test
	public void whenValidName_thenCustomerShouldBeFound()
	{
		String name = "Suresh";
		Customer customer = customerService.findByName(name);
		assertThat(customer.getName()).isEqualTo(name);
	}
}
