package org.ship2.view;

import java.util.List;

import org.ship2.model.dto.EmployeeDTO;

public class EmployeeResultView {

	public void display(List<EmployeeDTO> employeeList) {
		for(EmployeeDTO employee : employeeList) {
			System.out.println(employee);
		}
	}

	public void displayDmlResult(String dmlResultCode) {

		switch(dmlResultCode) {
		case "selectFailed": System.out.println("직원 조회 실패"); break;
		default: System.out.println("알 수 없는 에러 발생: 자세한 에러 목록은"
				+ "/CafePOS/src/org/ship2/controller/EmployeeController.java를 참조하시오.");
		}
	}

}
