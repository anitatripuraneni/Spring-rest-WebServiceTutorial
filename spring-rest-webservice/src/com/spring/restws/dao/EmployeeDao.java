package com.spring.restws.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.spring.restws.beans.Employee;
import com.spring.restws.beans.Employee1;

@Repository("employeeDao")
public class EmployeeDao {

	private JdbcTemplate jdbc;
	private NamedParameterJdbcTemplate jdbc1;

	public JdbcTemplate getDataSource() {
		return jdbc;
	}
	@Autowired
	public void setDataSource1(DataSource jdbc) throws SQLException {
		this.jdbc1 = new NamedParameterJdbcTemplate(jdbc);
		
	}
	public EmployeeDao() {
		System.out.println("successfully loaded employee dao");
	}

	@Autowired
	public void setDataSource(DataSource jdbc) throws SQLException {
		this.jdbc = new JdbcTemplate(jdbc);
		System.out.println(jdbc.getConnection());
	}

	public List<Employee> getAllEmployees() {
		return jdbc.query("select * from employee", new RowMapper<Employee>() {

			public Employee mapRow(ResultSet rs, int rownum) throws SQLException {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setEmail(rs.getString("email"));
				emp.setText(rs.getString("text"));

				return emp;
			}
		});

	}

	public List<Employee1> getAllEmployees1() {
		return jdbc.query("select * from employee", new RowMapper<Employee1>() {

			public Employee1 mapRow(ResultSet rs, int rownum) throws SQLException {
				Employee1 emp = new Employee1();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setEmail(rs.getString("email"));
				emp.setText(rs.getString("text"));

				return emp;
			}
		});

	}

	public Employee getEmployee(int id) {
		return jdbc.queryForObject("select * from employee where id=?", new Object[] { id }, new RowMapper<Employee>() {
			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getNString("name"));
				emp.setEmail(rs.getString("email"));
				emp.setText(rs.getString("text"));
				return emp;
			}

		});
	}
	
	public boolean createEmployee(Employee emp){
		BeanPropertySqlParameterSource params= new BeanPropertySqlParameterSource(emp);
		return jdbc1.update("insert into employee(name,email,text)values(:name,:email,:text)", params)==1;
	}
	
	public boolean updateEmployee(Employee emp){
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(emp);
		return jdbc1.update("update employee set name=:name,text=:text,email=:email where id =:id", params)==1;
	}

	
	public boolean deleteEmployee(int id){
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		return jdbc1.update("delete from employee where id=:id", params)==1;
	}
}
