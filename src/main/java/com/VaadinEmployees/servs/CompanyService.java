package com.VaadinEmployees.servs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VaadinEmployees.entity.Company;
import com.VaadinEmployees.entity.Employees;
import com.VaadinEmployees.repository.CompanyRepository;
import com.VaadinEmployees.repository.EmployeesRepository;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepositor;
	
	@Autowired
	private EmployeesRepository employeesRepository;
	
	List<Company>listOfCompany= new ArrayList();
	Set<Company>companySet= new HashSet<>();
	
	//GET all Company
	public Set<Company> getAllCompany(){
		companyRepositor.findAll().forEach(company-> companySet.add(company));
		return companySet;	
	}
	
	//SAVE Company
	public void saveCompany(Company company) {
		companyRepositor.save(company);
	}
	//DELETE Company
	public void deleteCompany(int id) {
		companyRepositor.deleteById(id);
	}
	
}
