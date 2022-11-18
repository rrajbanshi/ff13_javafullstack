package com.examples.empapp.dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;

import com.examples.empapp.Utils.HibernateConnection;
import com.examples.empapp.exception.EmployeeException;
import com.examples.empapp.model.Employee;

public class EmpDaoHibernateImpl implements EmpDaoHibernate{

	SessionFactory sessionFactory = HibernateConnection.getSessionFactory();
	@Override
	public boolean create(Employee emp) {
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(emp);
			tx.commit();
			return true;
		}
		catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean update(Employee emp) {
		 Session session = null;
		 Transaction tx = null;
		 try {
			 session = sessionFactory.openSession();
			 tx = session.beginTransaction();
			 session.saveOrUpdate(emp);
			 tx.commit();
			 return true;
		 }
		 catch(HibernateException e) {
			 tx.rollback();
			 e.printStackTrace();
		 }
	     finally {
	    	 session.close();
	     }
		
		return false;
	}

	@Override
	public boolean delete(int empId) throws EmployeeException {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			
			Employee employee = session.get(Employee.class, empId);
			if(employee != null) {
				tx = session.beginTransaction();
				session.delete(employee);
				tx.commit();
				return true;
			} else {
				throw new EmployeeException("No record found for this id");
			}
		}
		catch(HibernateException e) {
			e.printStackTrace();
		}
		finally {
	    	 session.close();
	     }
		return false;
	}

	@Override
	public Employee get(int empId) throws EmployeeException {
		 Session session = null;
		 Transaction tx = null;
		 Employee employee = null;
		 try {
			 session = sessionFactory.openSession();
			 tx = session.beginTransaction();
			 employee = session.get(Employee.class, empId);
			 tx.commit();
			 return employee;
			 
		 }
		 catch(HibernateException e) {
			 tx.rollback();
			 e.printStackTrace();
		 }
	     finally {
	    	 session.close();
	     }
		return null;
	}

	@Override
	public List<Employee> getAll() throws EmployeeException{
		Transaction tx = null ;
		Session session = null ;

			try {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				ArrayList<Employee> employees = (ArrayList<Employee>) session.createQuery("From Employee").list();
				tx.commit();
				if(employees.isEmpty()) {
					throw new EmployeeException("No data in the Database");
				}
				return employees ;
			} catch (HibernateException he) {
				tx.rollback();
				he.printStackTrace();
			}finally {
				session.close();
			}
			return null ;
	}

	

	@Override
	public List<Employee> getEmployeeCountAgeGreaterThan(int age) throws EmployeeException {
		List<Employee> employees = null;
		Transaction tx = null ;
		Session session = null ;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			//employees
			Criteria criteria  = session.createCriteria(Employee.class);
			criteria.add(Restrictions.gt("age", 30));
			employees = criteria.list();
			return employees;
		}
		catch(HibernateException e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return employees;
	}

	@Override
	public List<Integer> getEmployeeIdsAgeGreaterThan(int age) throws EmployeeException {
		List<Employee> employeeData = null;
		List<Integer> result = new ArrayList<>();
		Transaction tx = null ;
		Session session = null ;
		try {
			session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			employeeData = getEmployeeCountAgeGreaterThan(30);
			result = employeeData.stream().map(str -> str.getEmpId()).toList();
			if(result.size() == 0) {
				throw new EmployeeException("No Id present");
			}
			return result;
			
		}
		catch(HibernateException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Map<Object,Object> getEmployeeCountByDepartment() throws EmployeeException {
		
		Map<Object,Object> map = new HashMap<Object,Object>();
		Session session = null ;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			String hql = "SELECT department, COUNT(*) from Employee GROUP BY department";
			List<Object> list = session.createQuery(hql).list();
			for(int i=0; i<list.size(); i++) {
				Object[] row = (Object[]) list.get(i);
				map.put(row[0],row[1]);
			}
			return map ;
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return map ;
	}

	@Override
	public Map<Object,Object> getEmployeeCountByDepartmentOdered() throws EmployeeException {
		Map<Object,Object> map = new LinkedHashMap<Object,Object>();
		Transaction tx = null ;
		Session session = null ;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			String hql = "SELECT department, COUNT(*) from Employee GROUP BY department ORDER BY COUNT(*)";
			List<?> list = session.createQuery(hql).list();
			for(int i=0; i<list.size(); i++) {
				Object[] row = (Object[]) list.get(i);
				map.put(row[0],row[1]);
			}
			return map ;
		} catch (HibernateException he) {
			tx.rollback();
			he.printStackTrace();
		}finally {
			session.close();
		}
	
		return null ;
	}
	@Override
	public void bulkImport() throws EmployeeException {
		System.out.format("%n%s - Import started %n", Thread.currentThread().getName());
		int counter = 0;
		// windows path - .\\input\\employee-input.txt
		// mac/linux path - /input/employee-input.txt
		try (Scanner in = new Scanner(new FileReader(".\\input\\employee-input.txt"))) {
			System.out.println("Implorting file...");
			while (in.hasNextLine()) {
				String emp = in.nextLine();
				System.out.println("Importing employee - " + emp);
				Employee employee = new Employee();
				StringTokenizer tokenizer = new StringTokenizer(emp, ",");

				// Emp ID
				employee.setEmpId(Integer.parseInt(tokenizer.nextToken()));
				// Name
				employee.setName(tokenizer.nextToken());
				// Age
				employee.setAge(Integer.parseInt(tokenizer.nextToken()));
				// Designation
				employee.setDesignation(tokenizer.nextToken());
				// Department
				employee.setDepartment(tokenizer.nextToken());
				// Country
				employee.setCountry(tokenizer.nextToken());

//				employees.put(employee.getEmpId(), employee);
				this.create(employee);
				counter++;
			}
			System.out.format("%s - %d Employees are imported successfully.", Thread.currentThread().getName(),
					counter);
		} catch (Exception e) {
			System.out.println("Error occured while importing employee data. " + e.getMessage());
		}
		
	}

	@Override
	public void bulkExport() throws EmployeeException {
		System.out.format("%n%s - Export started %n", Thread.currentThread().getName());
		// windows path - .\\output\\employee-output.txt
		// mac/linux path - /output/employee-output.txt
		try (FileWriter out = new FileWriter(".\\output\\employee-output.txt")) {
			Map<Integer, Employee> employees = new HashMap<>();
			employees.values().stream().map(emp -> emp.getEmpId() + "," + emp.getName() + "," + emp.getAge() + ","
							+ emp.getDesignation() + "," + emp.getDepartment() + "," + emp.getCountry() + "\n")
					.forEach(rec -> {
						try {
							out.write(rec);
						} catch (IOException e) {
							System.out
									.println("Error occured while writing employee data into file. " + e.getMessage());
							e.printStackTrace();
						}
					});
			System.out.format("%d Employees are exported successfully.", employees.values().size());
		} catch (IOException e) {
			System.out.println("Error occured while exporting employee data. " + e.getMessage());
		}
		
	}

	@Override
	public boolean validate(Employee emp, String msg, Predicate<Employee> condition,
			Function<String, Boolean> operation)  throws EmployeeException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void statistics() throws EmployeeException {
		try {
			System.out.println("No of employees older than thirty years: "
					+ getEmployeeCountAgeGreaterThan(30).size());
		}
		catch(EmployeeException e) {
			e.printStackTrace();
		}
		System.out.println("List employee IDs older than thirty years: " + getEmployeeIdsAgeGreaterThan(30));
		System.out.println("Employee count by Department: " + getEmployeeCountByDepartment());
		System.out.println("Employee count by Department ordered: " + getEmployeeCountByDepartmentOdered());
	}

}
