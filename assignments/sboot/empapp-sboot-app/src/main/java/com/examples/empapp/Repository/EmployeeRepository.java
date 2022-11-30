package com.examples.empapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examples.empapp.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
