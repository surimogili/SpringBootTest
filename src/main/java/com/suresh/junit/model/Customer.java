package com.suresh.junit.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="CUSTOMERS")
public class Customer {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMERS_SEQ")
	@SequenceGenerator(sequenceName = "CUSTOMERS_SEQ", allocationSize = 1, name = "CUSTOMERS_SEQ")
	@ApiModelProperty(notes="The database generated consumer ID")
	private long id;
	
	@Column(name="NAME", nullable=false)
	@ApiModelProperty(notes="The consumer name", required=true)
	private String name;
	
	@Column(name="EMAIL", nullable=false)
	@ApiModelProperty(notes="The consumer email address", required=true)
	private String email;
	
	@Column(name="CITY")
	@ApiModelProperty(notes="The consumer city names")
	private String city;
	
	
	public Customer(long id, String name, String email, String city, Date creationDate) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.city = city;
		this.creationDate = creationDate;
	}
	
	public Customer()
	{
		
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@CreationTimestamp
	@Column(name = "CREATION_DATE", nullable=false)
	@ApiModelProperty(notes="The consumer creation date", required=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", city=" + city + ", creationDate="
				+ creationDate + "]";
	}
	
}
