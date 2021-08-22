package org.ship2.controller;

import java.util.List;

import org.ship2.model.dto.EmployeeDTO;
import org.ship2.model.service.EmployeeService;
import org.ship2.view.EmployeeResultView;

public class EmployeeController {
	private EmployeeService employeeService = new EmployeeService();
	private EmployeeResultView employeeResultView = new EmployeeResultView();

	public void selectAllEmployees() {
		List<EmployeeDTO> employeeList = employeeService.selectAllEmployees();
		
		if (!employeeList.isEmpty()) {
			employeeResultView.display(employeeList);
		} else {
			employeeResultView.displayDmlResult("selectFailed");
		}
		
	}

}
