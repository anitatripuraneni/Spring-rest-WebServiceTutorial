package com.spring.restws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.restws.beans.Employee;
import com.spring.restws.beans.Employee1;
import com.spring.restws.dao.EmployeeDao;

@Service
public class EmployeeService {

	private EmployeeDao employeeDao;

	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	@Autowired
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;

	}

	public List<Employee> getAllEmployees() {
		return getEmployeeDao().getAllEmployees();
	}
	public List<Employee1> getAllEmployees1() {
		return getEmployeeDao().getAllEmployees1();
	}
	
	public Employee getEmployee(int id){
		return getEmployeeDao().getEmployee(id);
	}
	
	public boolean createEmployee(Employee emp){
		return getEmployeeDao().createEmployee(emp);
	}
	
	public boolean updateEmployee(Employee emp){
		return getEmployeeDao().updateEmployee(emp);
	}
	
	public boolean deleteEmployee(int id){
		return getEmployeeDao().deleteEmployee(id);
	}
}
