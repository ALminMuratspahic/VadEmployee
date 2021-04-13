package com.VaadinEmployees.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.VaadinEmployees.entity.Company;
import com.VaadinEmployees.entity.Employees;
import com.VaadinEmployees.servs.CompanyService;

@RestController
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(value ="/allcompany", method = RequestMethod.GET)
	public Set<Company> getAllCompany(){
		return companyService.getAllCompany();
	}
	
	@RequestMapping(value = "/company/save", method = RequestMethod.POST)
	public void saveCompany(@ RequestBody Company company) {
		companyService.saveCompany(company);
	}
	
	@RequestMapping(value = "/company/delete",method = RequestMethod.DELETE)
	public void deleteCompany(@RequestParam int id) {
		companyService.deleteCompany(id);
	}
	
	
}
