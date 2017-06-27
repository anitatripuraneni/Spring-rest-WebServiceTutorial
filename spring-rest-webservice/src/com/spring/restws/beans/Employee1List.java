package com.spring.restws.beans;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "emoployees-list")
public class Employee1List {
	private List<Employee1> employees = new ArrayList<Employee1>();

	public List<Employee1> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee1> employees) {
		this.employees = employees;
	}

}
