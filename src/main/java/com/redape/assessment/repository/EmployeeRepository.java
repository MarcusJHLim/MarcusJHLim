package com.redape.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redape.assessment.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
