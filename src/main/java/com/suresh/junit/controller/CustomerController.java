package com.suresh.junit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.suresh.junit.model.Customer;
import com.suresh.junit.services.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/customers")
@Api(value="onlinestore", description="Operations pertaining to products in Online Store")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@ApiOperation(value = "Add a consumer")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	public Customer addCustomer(@RequestBody Customer customer) {
		Customer customerRes = customerService.saveCustomer(customer);
		return customerRes;
	}
	
	@ApiOperation(value = "View a list of available consumers",response = List.class)
	 @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Successfully retrieved list"),
	            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    }
	    )
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public List<Customer> getCustomers() {
		List<Customer> customerList = customerService.findAll();
		return customerList;
	}
	
	@ApiOperation(value = "Search a customer with an ID",response = Customer.class)
	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET, produces = "application/json")
	public Customer getCustomer(@PathVariable int id) {
		Customer customer = customerService.findOne(id);
		return customer;
	}
	
}
