package edu.sru.group1.proj.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

//Class that stores school information (name, address, numStudents, lowGrade, highGrade)
//Used in SchoolInfoDataset.java

@Entity
public class SchoolInformation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NonNull
	private String schoolName;
	@NonNull
	private String address;
	@NonNull
	private String numStudents;
	@NonNull
	private String lowGrade;
	@NonNull
	private String highGrade;
	
	//Setters and Getters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNumStudents() {
		return numStudents;
	}
	public void setNumStudents(String numStudents) {
		this.numStudents = numStudents;
	}
	public String getLowGrade() {
		return lowGrade;
	}
	public void setLowGrade(String lowGrade) {
		this.lowGrade = lowGrade;
	}
	public String getHighGrade() {
		return highGrade;
	}
	public void setHighGrade(String highGrade) {
		this.highGrade = highGrade;
	}
	
}
