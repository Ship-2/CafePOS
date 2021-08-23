package org.ship2.view;

import java.util.List;

import org.ship2.controller.LogInController;
import org.ship2.model.dto.EmployeeDTO;

public class LogIn {

	private LogInController loginController = new LogInController();

	public void logInComplete() {

		do {
			System.out.println("== 직원 리스트 ==");

			List<EmployeeDTO> employeeList = loginController.selectAllEmployee();
			
			for (EmployeeDTO employee : employeeList) {
				System.out.println(employee);
			}
			
		} while (false);
	}
}
