package com.suresh.junit.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.suresh.junit.model.Customer;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Test
	public void whenFindByName_thenReturnCustomer()
	{
		Customer customer = new Customer(47, "Krishna", "Krishna@gmail.com", "Vizag", new Date());
		entityManager.persist(customer);
		entityManager.flush();
		
		Customer result = customerRepo.findByName("Krishna");
		assertThat(result.getName()).isEqualTo(customer.getName());
	}

}
