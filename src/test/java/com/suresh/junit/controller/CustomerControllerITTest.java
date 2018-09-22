package com.suresh.junit.controller;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.suresh.junit.App;
import com.suresh.junit.model.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=App.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerITTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void createCustomer()
	{
		Customer customerReq = new Customer(47, "Krishna", "Krishna@gmail.com", "Vizag", new Date());
		ResponseEntity<Customer> response = restTemplate.postForEntity("/customers/add", customerReq, Customer.class);
		
		Customer customerRes = response.getBody();
		assertEquals(customerReq.getName(), customerRes.getName());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
