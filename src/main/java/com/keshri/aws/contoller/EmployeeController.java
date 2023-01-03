package com.keshri.aws.contoller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.keshri.aws.contoller.model.Employee;
import com.keshri.aws.contoller.repository.EmployeeRepository;
import com.keshri.aws.exception.ResourceNotFoundException;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/")
	public String welcome() {
		return "Hi Keshri - Welcome to AWS";
	}

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees(
			@RequestParam(value = "id", required = false) Integer employeeId) throws ResourceNotFoundException {
		if (null == employeeId)
			return ResponseEntity.ok(employeeRepository.findAll());
		else {
			List<Employee> employees = employeeRepository.findById(employeeId);
			if (null != employees) {
				return ResponseEntity.ok(employees);
			} else
				throw new ResourceNotFoundException("Employee not found for this id :: " + employeeId);
		}
	}

	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	@PutMapping("/employees")
	public ResponseEntity<Employee> updateEmployee(@RequestParam(value = "id") Integer employeeId,
			@RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId).get(0);
		if (null != employee) {
			employee.setEmailId(employeeDetails.getEmailId());
			employee.setLastName(employeeDetails.getLastName());
			employee.setFirstName(employeeDetails.getFirstName());
			final Employee updatedEmployee = employeeRepository.save(employee);
			return ResponseEntity.ok(updatedEmployee);
		} else
			throw new ResourceNotFoundException("Employee not found for this id :: " + employeeId);
	}

	@DeleteMapping("/employees")
	public Map<String, Boolean> deleteEmployee(@RequestParam(value = "id") Integer employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId).get(0);
		if (null != employee) {
			employeeRepository.delete(employee);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
		} else
			throw new ResourceNotFoundException("Employee not found for this id :: " + employeeId);

	}

}
