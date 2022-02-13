package edu.sru.group1.proj.domain;


import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

/*
 * Structure for the database to hold the information on the user
 * 
 * @Entity, @Id and @Generated value should be from the javax.persistence library
 * @NonNull is from the org.springwork library 
 * 
 * As the User class is designated as a @Entity, the JPA (Java Persistence API), which is Hibernate, will be able to perform CRUD 
 * (Create, Read, Update, Delete) operations on the domain entities.
 * 
 * The name and e-mail have been constrained to @NoNull values and allows the Hibernate Validator for validating the constrained
 * fields before persisting or updating an entity to the database.
 */

//Class that stores the state, county, and district information.
//Used in CountyDataset.java

@Entity
public class StateCounty {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NonNull
	private String state;
	@NonNull
	private String county;
	@javax.persistence.Lob  
	private Vector<String> district;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public Vector<String> getDistrict() {
		return district;
	}

	public void setDistrict(Vector<String> district) {
		this.district = district;
	}
	
	
}
