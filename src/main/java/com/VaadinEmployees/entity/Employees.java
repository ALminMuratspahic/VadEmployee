package com.VaadinEmployees.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import org.springframework.lang.NonNull;

@Entity
@Table()
public class Employees {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;
	
	@Column
	@NonNull
	private String firstName;
	
	@Column
	@NonNull
	private String lastName;
	
	@Column
	@NonNull
	private LocalDate birthDay;
	
	@Column
	@NonNull
	private String position;
	
	@Column
	@NonNull
	private double salary;
	
	@ManyToOne
	@JoinColumn(name ="company_id")
	private Company company;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	
	
	

}
