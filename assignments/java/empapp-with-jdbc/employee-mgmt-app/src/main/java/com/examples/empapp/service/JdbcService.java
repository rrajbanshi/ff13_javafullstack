package com.examples.empapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import com.examples.empapp.exception.EmployeeException;
import com.examples.empapp.model.Employee;

public interface JdbcService {
	public boolean create(Employee employee);
	public boolean update(Employee emp);
	public boolean delete(int empId);
	public Employee get(int id) throws EmployeeException;
	public List<Employee> getAll();
	public void statistics() ;
	public void export() ;
	public void exit();
	public boolean validate(Employee emp, String msg, Predicate<Employee> condition,Function<String, Boolean> operation);	
	public ArrayList<Integer> getEmployeeIdsAgeGreaterThan();
	public Map<String,Integer> getEmployeeCountByDepartment();
	public Map<String,Integer> getEmployeeCountByDepartmentOdered();
	public Map<Integer,Integer> getEmployeeCountAgeGreaterThan(int age);
	public void bulkImport();
}
