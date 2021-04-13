package com.VaadinEmployees.servs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VaadinEmployees.entity.Employees;
import com.VaadinEmployees.repository.EmployeesRepository;

@Service
public class EmployeesServic {
	
	@Autowired
	private EmployeesRepository employeesRepository;
	
	//GET all employees
	public List<Employees> getAllEmployes(){
		List<Employees> listOfEmployees= new ArrayList<>();
		employeesRepository.findAll().forEach(y->listOfEmployees.add(y));
		return listOfEmployees;
	}
	
	//SAVE Employees
	public void saveEmployees(Employees employee) {
	employeesRepository.save(employee);
	}
	//DELETE
	public void deleteEmployee(int id) {
		employeesRepository.deleteById(id);
	}
	
	//Filter
	public List<Employees>filterFind(String filterText){
		if(filterText==null||filterText.isEmpty()) {
			return (List<Employees>) employeesRepository.findAll();
		}else {
			return employeesRepository.search(filterText);
		}
	}

}
