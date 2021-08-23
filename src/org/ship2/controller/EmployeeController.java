package org.ship2.controller;

import java.util.List;
import java.util.Map;

import org.ship2.model.dto.EmployeeDTO;
import org.ship2.model.service.EmployeeService;
import org.ship2.view.EmployeeResultView;

public class EmployeeController {
	private EmployeeService employeeService = new EmployeeService();
	private EmployeeResultView employeeResultView = new EmployeeResultView();

	public void selectAllEmployees() {
		List<EmployeeDTO> employeeList = employeeService.selectAllEmployees();
		
		if (!employeeList.isEmpty()) {
			employeeResultView.displayDmlResult("selectSuccess");
			employeeResultView.display(employeeList);
		} else {
			employeeResultView.displayDmlResult("selectFailed");
		}
		
	}

	public void registNewEmployee(Map<String, String> empInfoMap) {
		EmployeeDTO empDTO = new EmployeeDTO();
		
		empDTO.setEmpName(empInfoMap.get("name"));
		empDTO.setEmpPhone(empInfoMap.get("phone"));
		empDTO.setJobCode(Integer.valueOf(empInfoMap.get("jobCode")));
		empDTO.setEmpId(empInfoMap.get("id"));
		empDTO.setEmpPw(empInfoMap.get("pw"));
		
		int createResult = employeeService.insertNewEmployee(empDTO);
			
		if (createResult > 0) {
			employeeResultView.displayDmlResult("insertSuccess");
		} else {
			employeeResultView.displayDmlResult("insertFailed");
		}
		
	}

	public void deleteEmployee(String employeeId) {
		int deleteResult = employeeService.deleteEmployee(employeeId);
		
		if (deleteResult > 0) {
			employeeResultView.displayDmlResult("deleteSuccess");
		} else {
			employeeResultView.displayDmlResult("deleteFailed");
		}
	
	}

	/**
	 * @param empIdFromUser ID of the employee to be updated.
	 * @param empInfoMap	Information which will be updated.
	 */
	public void modifyEmployee(String empIdFromUser, Map<String, String> empInfoMap) {
		EmployeeDTO empDTO = new EmployeeDTO();
		
		empDTO.setEmpName(empInfoMap.get("name"));
		empDTO.setEmpPhone(empInfoMap.get("phone"));
		empDTO.setJobCode(Integer.valueOf(empInfoMap.get("jobCode")));
		empDTO.setEmpId(empInfoMap.get("id"));
		empDTO.setEmpPw(empInfoMap.get("pw"));
		
		int updateResult = employeeService.updateEmployeeInfo(empIdFromUser, empDTO);
		
		if (updateResult > 0) {
			employeeResultView.displayDmlResult("updateSuccess");
		} else {
			employeeResultView.displayDmlResult("updateFailed");
		}
	}

}
