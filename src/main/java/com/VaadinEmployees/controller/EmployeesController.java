package com.VaadinEmployees.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.VaadinEmployees.entity.Employees;
import com.VaadinEmployees.servs.EmployeesServic;

@RestController
public class EmployeesController {
	
	@Autowired
	private EmployeesServic employeesService;
	
	//GETALL Empleyee
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public List<Employees>getAllEmployees(){
		return employeesService.getAllEmployes();
	}
	
	//SAVE Employeee
	@RequestMapping(value = "/employees/save",method = RequestMethod.POST)
	public void saveEmployee (@RequestBody Employees employee) {
		employeesService.saveEmployees(employee);
	}
	
	//DELETE Employee
	@RequestMapping(value = "/employees/delete",method = RequestMethod.DELETE)
	public void delateEmployee(@RequestParam int id) {
		employeesService.deleteEmployee(id);
	}
	
	//FILTER
	public List<Employees>filtriranje(@RequestParam String textFilter){
		return employeesService.filterFind(textFilter);
	}

}
