package com.examples.empapp.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import org.hibernate.HibernateException;

import com.examples.empapp.dao.EmpDaoHibernateImpl;
import com.examples.empapp.exception.EmployeeException;
import com.examples.empapp.model.Employee;

public class EmployeeServiceHibernateImpl implements EmployeeServiceHibernate{

	@Override
	public boolean create(Employee emp) {
		boolean create = new EmpDaoHibernateImpl().create(emp);
		return create;
	}

	@Override
	public boolean update(Employee emp) {
		boolean update = new EmpDaoHibernateImpl().update(emp);
		return update;
	}

	@Override
	public boolean delete(int empId) throws EmployeeException {
		return new EmpDaoHibernateImpl().delete(empId);
	}

	@Override
	public Employee get(int empId) throws EmployeeException {
		Employee employee = null ;
		try {
			employee = new EmpDaoHibernateImpl().get(empId);
			if(employee == null) {
				throw new EmployeeException("No employee found for given id.");
			}
			
		}
	    catch(HibernateException e) {
		  System.out.println("error while getting data" + e.getMessage());
		  e.printStackTrace();
	   }
		return employee;
	}

	@Override
	public List<Employee> getAll()throws EmployeeException {
		return new EmpDaoHibernateImpl().getAll();
	}

	@Override
	public void bulkImport() throws EmployeeException {
		 new EmpDaoHibernateImpl().bulkImport();
		
	}

	@Override
	public void bulkExport() throws EmployeeException {
		
		new EmpDaoHibernateImpl().bulkExport();
	}

	@Override
	public boolean validate(Employee emp, String msg, Predicate<Employee> condition,
			Function<String, Boolean> operation) throws EmployeeException {
		return new EmpDaoHibernateImpl().validate(emp, msg, condition, operation);
	}

	@Override
	public List<Employee> getEmployeeCountAgeGreaterThan(int age) throws EmployeeException {
		return new EmpDaoHibernateImpl().getEmployeeCountAgeGreaterThan(age);
	}

	@Override
	public List<Integer> getEmployeeIdsAgeGreaterThan(int age) throws EmployeeException {
		return new EmpDaoHibernateImpl().getEmployeeIdsAgeGreaterThan(age);
	}

	@Override
	public Map<Object,Object> getEmployeeCountByDepartment() throws EmployeeException{
		return new EmpDaoHibernateImpl().getEmployeeCountByDepartment();
	}

	@Override
	public Map<Object,Object> getEmployeeCountByDepartmentOdered() throws EmployeeException {
		return new EmpDaoHibernateImpl().getEmployeeCountByDepartmentOdered();
	}
	
	public void statistics()throws EmployeeException{
		new EmpDaoHibernateImpl().statistics();
	}
}
