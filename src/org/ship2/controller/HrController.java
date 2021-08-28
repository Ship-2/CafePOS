package org.ship2.controller;

import java.util.List;

import org.ship2.model.dto.EmployeeDTO;
import org.ship2.model.dto.HrDTO;
import org.ship2.model.service.HrService;

public class HrController {
	
	private HrService hrService = new HrService();

	public List<EmployeeDTO> selectAllEmployee() {
		List<EmployeeDTO> employeeList = hrService.selectAllEmployee();
		
		return employeeList;
	}

	public List<HrDTO> selectHrByEmpCode(int emp_code) {
		List<HrDTO> hrList = hrService.selectHrByEmpCode(emp_code);
		return hrList;
	}

	public int insertHr(int emp_code) {
		
		int hrInsertResult = hrService.insertHr(emp_code);
		
		if (hrInsertResult > 0) {
			//System.out.println("insertHr 성공");
		} else {
			System.out.println("이미 출근하셨습니다. 재출근이 불가합니다.");
		}
		
		return hrInsertResult;
	}

	public int updateClockOut(int emp_code) {

		int clockOutUpdateResult = hrService.updateClockOut(emp_code);
		
		if (clockOutUpdateResult > 0) {
			//System.out.println("update 성공");
		} else {
			//System.out.println("update 실패");
		}
		
		return clockOutUpdateResult;
	}

	public int updateHr(HrDTO hrDTO) {

		int hrUpdateResult = hrService.updateHr(hrDTO);
		
		if (hrUpdateResult > 0) {
			//System.out.println("update 성공");
		} else {
			//System.out.println("update 실패");
		}
		
		return hrUpdateResult;
	}


	

}
