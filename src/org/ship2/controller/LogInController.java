package org.ship2.controller;

import java.util.List;

import org.ship2.model.dto.EmployeeDTO;
import org.ship2.model.service.LogInService;

public class LogInController {
	
	private LogInService loginService = new LogInService();

	public List<EmployeeDTO> selectAllEmployee() {
		
		List<EmployeeDTO> employeeList = loginService.selectAllEmployee();
		
		return employeeList;
	}

	public void getEmployeeId() {
		
	}
}
