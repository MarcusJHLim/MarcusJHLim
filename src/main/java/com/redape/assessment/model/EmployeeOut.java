package com.redape.assessment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class EmployeeOut{

	private String name;

	private int salary;

	private int taxPayable = 0;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getTaxPayable() {
		return taxPayable;
	}

	public void setTaxPayable(int taxPayable) {
		this.taxPayable = taxPayable;
	}

}
