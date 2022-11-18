package com.examples.empapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import com.examples.empapp.dao.DaoImplementation;
import com.examples.empapp.exception.EmployeeException;
import com.examples.empapp.model.Employee;

public class JdbcServiceImpl implements JdbcService {

	@Override
	public boolean create(Employee employee) {
		return new DaoImplementation().create(employee);
	}

	@Override
	public boolean update(Employee emp) {
		return new DaoImplementation().update(emp);
	}

	@Override
	public boolean delete(int empId) {
		return new DaoImplementation().delete(empId);
	}

	@Override
	public Employee get(int id) throws EmployeeException {
		return new DaoImplementation().get(id);
	}

	@Override
	public List<Employee> getAll() {
		return new DaoImplementation().getAll();
	}

	@Override
	public void statistics() {
		new DaoImplementation().statistics();
		
	}

	@Override
	public void export() {
		new DaoImplementation().export();
		
	}

	@Override
	public void exit() {
		new DaoImplementation().exit();
		
	}

	@Override
	public boolean validate(Employee emp, String msg, Predicate<Employee> condition,
			Function<String, Boolean> operation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Integer> getEmployeeIdsAgeGreaterThan() {
		return  new DaoImplementation().getEmployeeIdsAgeGreaterThan();
	}

	@Override
	public Map<String, Integer> getEmployeeCountByDepartment() {
		return new DaoImplementation().getEmployeeCountByDepartment();
	}

	@Override
	public Map<String, Integer> getEmployeeCountByDepartmentOdered() {
		return new DaoImplementation().getEmployeeCountByDepartmentOdered();
	}

	@Override
	public Map<Integer, Integer> getEmployeeCountAgeGreaterThan(int age) {
		return new DaoImplementation().getEmployeeCountAgeGreaterThan(age);
	}

	@Override
	public void bulkImport() {
		new DaoImplementation().bulkImport();
		
	}

}
