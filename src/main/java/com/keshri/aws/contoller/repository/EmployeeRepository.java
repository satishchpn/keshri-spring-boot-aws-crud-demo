package com.keshri.aws.contoller.repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import com.keshri.aws.contoller.model.Employee;

@Repository
public class EmployeeRepository {

	List<Employee> employees = Stream.of(new Employee(1, "Satish", "Keshri", "satish@gmail.com"),
			new Employee(2, "Sujit", "Keshri", "sujit@gmail.com"),
			new Employee(3, "Sanjay", "Keshri", "sanjay@gmail.com"),
			new Employee(4, "Sanjiv", "Keshri", "sanjiv@gmail.com")).collect(Collectors.toList());

	public List<Employee> findAll() {
		return employees;

	}

	public List<Employee> findById(Integer employeeId) {
		return employees.stream().filter(e -> e.getId().equals(employeeId)).toList();
	}

	public Employee save(Employee employee) {
		employees.add(employee);
		return employee;
	}

	public void delete(Employee employee) {
		employees.remove(employee);
	}

}
