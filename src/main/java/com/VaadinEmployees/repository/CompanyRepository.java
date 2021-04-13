package com.VaadinEmployees.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.VaadinEmployees.entity.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Integer> {

}
