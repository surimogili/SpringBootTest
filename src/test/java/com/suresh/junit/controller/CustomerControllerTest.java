package com.suresh.junit.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suresh.junit.model.Customer;
import com.suresh.junit.services.CustomerService;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private CustomerService customerService;
	
	@Test
	public void givenCustomers_whenGetEmpoyees_thenReturnJsonArray() throws Exception
	{
		ObjectMapper mapper = new ObjectMapper();
		Customer customer = mapper.readValue(new File("src/test/resources/customer.json"), Customer.class);
		List<Customer> allCustomer = Arrays.asList(customer);
		
		given(customerService.findAll()).willReturn(allCustomer);
		
		mvc.perform(get("/customers/list")
				.contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name", is(customer.getName())));
	}
	
	@Test
	public void createCustomer() throws Exception
	{
		Customer customer = new Customer(47, "Krishna", "krishna@gmail.com", "Vizag", new Date());
		ObjectMapper mapper = new ObjectMapper();
		String customerJson = mapper.writeValueAsString(customer);
		
		when(customerService.saveCustomer(any(Customer.class))).thenReturn(customer);
		
		
		mvc.perform(post("/customers/add")
				.accept(MediaType.APPLICATION_JSON_UTF8).content(customerJson)
				.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.name", is(customer.getName())));
	}
}
