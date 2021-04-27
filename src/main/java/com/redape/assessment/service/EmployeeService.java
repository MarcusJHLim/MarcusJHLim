package com.redape.assessment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redape.assessment.model.Employee;
import com.redape.assessment.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	public List<Employee> listAll() {

		return repository.findAll();
	}

	public void create(Employee employee) {

		repository.save(employee);
	}

	public Employee updateid(String id) {
		return repository.findById(id).get();
	}

	public void delete(String id) {

		repository.deleteById(id);
	}

	/**
	 * Business logic for annual salary calculation.
	 * 
	 * @param monthlySalary
	 * @return
	 */
	public int getAnnualSalary(int monthlySalary) {
		return convertToSen(12 * monthlySalary);
	}

	/**
	 * Business logic for tax calculation.
	 * 
	 * Doesn't consider beyond 600K chargeable income Need to consider rebates and
	 * deductions
	 * 
	 * @param monthlySalary
	 * @return
	 */
	public int getTaxPayable(int monthlySalary) {
		double tax = 0;
		int rebatesAndDeductions = 0;
		double annualSalary = getAnnualSalary(monthlySalary);

		double chargableIncome = annualSalary - rebatesAndDeductions;

		if (chargableIncome <= 5000)
			tax = 0;
		else if (chargableIncome <= 20000)
			tax = 0.01 * (chargableIncome - 15000);
		
		else if (chargableIncome <= 35000)
			tax = 150 + (0.03 * (chargableIncome - 20000));
		
		else if (chargableIncome <= 50000)
			tax = 600 + (0.08 * (chargableIncome - 35000)) ;

		else if (chargableIncome <= 70000)
			tax = 1800 + (0.14 * (chargableIncome - 50000)) ;

		else if (chargableIncome <= 100000)
			tax = 4600 + (0.21 * (chargableIncome - 70000)) ;

		else if (chargableIncome <= 250000)
			tax = 10900 + (0.24 * (chargableIncome - 100000)) ;

		else if (chargableIncome <= 400000)
			tax = 46900 + (0.245 * (chargableIncome - 250000)) ;

		else
			tax = 83650 + (0.25 * (chargableIncome - 400000)) ;

		System.out.println("Income tax amount is " + tax);
		return convertToSen( tax );
	}
	
	/**
	 * Return value in sen.
	 * 
	 * @param value
	 * @return
	 */
	private int convertToSen(double value) {
		return  (int) Math.round(value*100);
	}
	
	public int getTaxPayableWrong(int monthlySalary) {
		double tax = 0;
		int rebatesAndDeductions = 0;
		double annualSalary = getAnnualSalary(monthlySalary);

		double chargableIncome = annualSalary - rebatesAndDeductions;

		if (chargableIncome <= 5000)
			tax = 0;
		else if (chargableIncome <= 20000)
			tax = 0.1 * (chargableIncome - 15000);
		else if (chargableIncome <= 35000)
			tax = (0.3 * (chargableIncome - 20000)) + (0.1 * 15000);
		else if (chargableIncome <= 50000)
			tax = (0.8 * (chargableIncome - 15000)) + (0.3 * (chargableIncome - 20000)) + (0.1 * 15000);

		else if (chargableIncome <= 70000)
			tax = (1.4 * (chargableIncome - 20000)) + (0.8 * (chargableIncome - 15000))
					+ (0.3 * (chargableIncome - 20000)) + (0.1 * 15000);

		else if (chargableIncome <= 100000)
			tax = (2.1 * (chargableIncome - 30000)) + (1.4 * (chargableIncome - 20000))
					+ (0.8 * (chargableIncome - 15000)) + (0.3 * (chargableIncome - 20000)) + (0.1 * 15000);

		else if (chargableIncome <= 250000)
			tax = (2.4 * (chargableIncome - 150000)) + (2.1 * (chargableIncome - 30000))
					+ (1.4 * (chargableIncome - 20000)) + (0.8 * (chargableIncome - 15000))
					+ (0.3 * (chargableIncome - 20000)) + (0.1 * 15000);

		else if (chargableIncome <= 400000)
			tax = (2.45 * (chargableIncome - 150000)) + (2.4 * (chargableIncome - 150000))
					+ (2.1 * (chargableIncome - 30000)) + (1.4 * (chargableIncome - 20000))
					+ (0.8 * (chargableIncome - 15000)) + (0.3 * (chargableIncome - 20000)) + (0.1 * 15000);

		else
			tax = (2.5 * (chargableIncome - 200000)) + (2.45 * (chargableIncome - 150000))
					+ (2.4 * (chargableIncome - 150000)) + (2.1 * (chargableIncome - 30000))
					+ (1.4 * (chargableIncome - 20000)) + (0.8 * (chargableIncome - 15000))
					+ (0.3 * (chargableIncome - 20000)) + (0.1 * 15000);

		System.out.println("Income tax amount is " + tax);
		return (int) Math.round(tax);
	}
}