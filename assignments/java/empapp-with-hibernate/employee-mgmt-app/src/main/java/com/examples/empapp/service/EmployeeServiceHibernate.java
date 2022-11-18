package com.examples.empapp.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import com.examples.empapp.exception.EmployeeException;
import com.examples.empapp.model.Employee;

public interface EmployeeServiceHibernate {
	public boolean create(Employee emp);

	public boolean update(Employee emp);

	public boolean delete(int empId)throws EmployeeException ;

	public Employee get(int empId) throws EmployeeException;

	public List<Employee> getAll()throws EmployeeException;

	public void bulkImport()throws EmployeeException;

	public void bulkExport() throws EmployeeException;

	public boolean validate(Employee emp, String msg, Predicate<Employee> condition,
			Function<String, Boolean> operation) throws EmployeeException;

	public List<Employee> getEmployeeCountAgeGreaterThan(int age) throws EmployeeException;

	public List<Integer> getEmployeeIdsAgeGreaterThan(int age) throws EmployeeException;

	public Map<Object,Object> getEmployeeCountByDepartment() throws EmployeeException;

	public Map<Object,Object> getEmployeeCountByDepartmentOdered() throws EmployeeException;
	
	public void statistics() throws EmployeeException;
}
