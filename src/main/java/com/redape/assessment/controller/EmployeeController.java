package com.redape.assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redape.assessment.model.Employee;
import com.redape.assessment.model.EmployeeOut;
import com.redape.assessment.repository.EmployeeRepository;
import com.redape.assessment.service.EmployeeService;

@RestController
@RequestMapping("employees")
@Controller
public class EmployeeController {
 
 @Autowired
 private EmployeeService service;
 
 @Autowired
 EmployeeRepository employeeRepository;
 
 /************************* REST **************************/
 // retrieve all employees from database
 @GetMapping("all")
 public List<Employee> getAllEmployee()
 {
  List<Employee> employee=(List<Employee>) employeeRepository.findAll(); 
  return employee;
 }
 
 // get particular employee by their ID
 @GetMapping("employee/{id}")
 public Optional<EmployeeOut> getEmployeeId(@PathVariable String id)
 {
	 //final String ALPHANUMERIC_PATTERN = "^[a-zA-Z0-9 ]+$";
	 final String ALPHA_AND_SPACE_PATTERN = "^[a-zA-Z ]+$";
	 
	 EmployeeOut empOutput = null;
	 
	 // Validate input
	 if (null != id) {
		 if ((id.length()>=3) && (id.matches(ALPHA_AND_SPACE_PATTERN))){
			 
			 empOutput = new EmployeeOut();
			  Optional<Employee> employee = employeeRepository.findById(id);
			  if (employee.isPresent()) {
				  
				  int monthlySalary = employee.get().getMonthlySalary();
				  empOutput.setName(employee.get().getName());
				  empOutput.setTaxPayable(service.getTaxPayable(monthlySalary));
				  empOutput.setSalary(service.getAnnualSalary(monthlySalary));
			  }
		 }else {
			 throw new InvalidParameterException();
		 }
	 }else {
		 throw new InvalidParameterException();
	 }
	 
  return Optional.of(empOutput);
 }
 
 // update existing employee 
 @PutMapping("update/{id}")
 public Employee updateStudent(@RequestBody Employee employee)
 {
	 Employee updatedEmployee = null;
	 int MAX_MONTHLY_SALARY_ALLOWED = 100000;
	 System.out.println(employee);
	 // Validate
	 double salaryInput = employee.getMonthlySalary();
	 if (isWholeNumber(salaryInput) && 
			 ((salaryInput>0) && (salaryInput<MAX_MONTHLY_SALARY_ALLOWED))) {
		 updatedEmployee = employeeRepository.save(employee);
	 }else {
		 throw new InvalidParameterException();
	 }
  return updatedEmployee;
 }
 
 /**
  * Check if the passed argument is a whole number.
  *
  * @param number double
  * @return true if the passed argument is an integer value.
  */
 boolean isWholeNumber(double number) {
     return (number % 1 == 0);
 }
 
 /************************* MVC **************************/
 @RequestMapping("/")
 public String viewIndexPage(Model model){
  List<Employee> employeeList = service.listAll();
  model.addAttribute("getAllEmployee", employeeList);
  return "index";
 }
 
 @RequestMapping("/new_add")
 public String viewNewEmployeeForm(Model model) {
  Employee employee = new Employee();
  model.addAttribute("employee", employee);
  return "insert";
 }
 
 @RequestMapping(value = "/save_employee", method = RequestMethod.POST)
 public String addNewEmployee(@ModelAttribute("employee") Employee employee) {
  service.create(employee);
  return "redirect:/";
 }
 
 @RequestMapping("/edit/{id}")
 public ModelAndView viewEditEmployeeForm(@PathVariable(name = "id") String id) {
  
  ModelAndView mav = new ModelAndView("update"); 
  Employee employee = service.updateid(id);
  mav.addObject("employee", employee);
  return mav; 
 }
 
 @RequestMapping("/delete/{id}")
 public String deleteEmployee(@PathVariable(name = "id") String id) {
  
  service.delete(id);
  return "redirect:/";
 }

}