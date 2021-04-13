package com.VaadinEmployees.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.VaadinEmployees.entity.Employees;

@Repository
public interface EmployeesRepository extends CrudRepository<Employees, Integer> {
	
	//For filter
	@Query("select e from Employees e " + 
    "where lower(e.firstName) like lower(concat('%', :searchTerm, '%')) " +
	"or lower(e.birthDay) like lower(concat('%', :searchTerm, '%'))"+
    "or lower(e.lastName) like lower(concat('%', :searchTerm, '%'))")
	List<Employees>search(@Param("searchTerm")String searchTerm);

}
