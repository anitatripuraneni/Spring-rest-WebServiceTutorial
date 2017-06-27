package com.spring.restws.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.fabric.Response;
import com.spring.restws.beans.Employee;
import com.spring.restws.beans.Employee1;
import com.spring.restws.service.EmployeeService;

@RestController
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// @RequestMapping(value = "/employee", method = RequestMethod.GET, produces
	// = "application/json")
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public List<Employee> getAllEmployees() {
		return getEmployeeService().getAllEmployees();
	}

	// @RequestMapping(value = "/employee1", method = RequestMethod.GET,
	// produces = "application/xml")
	@RequestMapping(value = "/employee1", method = RequestMethod.GET)
	public List<Employee1> getAllEmployees1() {
		return getEmployeeService().getAllEmployees1();
	}

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getEmployeesList() {

		HttpHeaders headers = new HttpHeaders();
		List<Employee> employees = employeeService.getAllEmployees();
		if (employees == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			headers.add("number of records found", String.valueOf(employees.size()));
			return new ResponseEntity<List<Employee>>(employees, headers, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") int id) {
		Employee emp = getEmployeeService().getEmployee(id);
		if (emp == null) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Employee>(emp, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee emp) {
		HttpHeaders headers = new HttpHeaders();
		if (emp == null) {
			return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
		}
		Boolean b = getEmployeeService().createEmployee(emp);
		if (b) {
			headers.add("Employee created -", String.valueOf(emp.getId()));
			return new ResponseEntity<Employee>(emp, headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "employee/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int id, @RequestBody Employee emp) {
		HttpHeaders headers = new HttpHeaders();
		Employee employee = getEmployeeService().getEmployee(id);
		if (employee == null) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		if (emp == null) {
			return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
		}
		emp.setId(id);
		boolean b = getEmployeeService().updateEmployee(emp);
		if (b) {
			headers.add("Employee updated- ", String.valueOf(emp.getId()));
			return new ResponseEntity<Employee>(emp, headers, HttpStatus.CREATED);
		}
		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "employee/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") int id) {
		HttpHeaders headers = new HttpHeaders();
		Employee emp = getEmployeeService().getEmployee(id);
		if (emp == null) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		getEmployeeService().deleteEmployee(id);
		headers.add("Employee deleted-", String.valueOf(emp.getId()));
		return new ResponseEntity<Employee>(emp, headers, HttpStatus.OK);
	}
}
