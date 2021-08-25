package org.ship2.controller;

import java.util.List;
import java.util.Map;

import org.ship2.model.dto.MembershipDTO;
import org.ship2.model.service.MembershipService;
import org.ship2.view.MemberResultView;

public class MembershipController {
	private MembershipService membershipService = new MembershipService();
	private MemberResultView memberResultView = new MemberResultView();
	
	public void selectAllMembers() {
		List<MembershipDTO> memberList = membershipService.selectAllMembers();
		
		if (!memberList.isEmpty()) {
			memberResultView.show(memberList);
		} else {
			memberResultView.showResult("addFailed");
		}
		
		
	}
	public void registNewMember(Map<String, String> memberInfoMap) {
		MembershipDTO memDTO = new MembershipDTO();
		
		memDTO.setMemName(memberInfoMap.get("name"));
		memDTO.setMemPhone(memberInfoMap.get("phone"));
//		memDTO.setMemCode(Integer.valueOf(memberInfoMap.get("memCode")));
		
		
		int addResult = membershipService.insertNewMember(memDTO);
		
		if (addResult > 0) {
			memberResultView.showResult("addSuccess");
		} else {
			memberResultView.showResult("addFailed");
		}
	}
	
	public void deleteEmployee(String memCode) {
		
		int deleteResult = membershipService.deleteMember(memCode);
		
		if (deleteResult > 0) {
			memberResultView.showResult("deleteSuccess");
		} else {
			memberResultView.showResult("deleteFailed");
		}
	}

	

	
//	public void updateMember(String , Map<String, String> empInfoMap) {
//		EmployeeDTO empDTO = new EmployeeDTO();
//		
//		empDTO.setEmpName(empInfoMap.get("name"));
//		empDTO.setEmpPhone(empInfoMap.get("phone"));
//		empDTO.setJobCode(Integer.valueOf(empInfoMap.get("jobCode")));
//		empDTO.setEmpId(empInfoMap.get("id"));
//		empDTO.setEmpPw(empInfoMap.get("pw"));
//		
//		int updateResult = employeeService.updateEmployeeInfo(empIdFromUser, empDTO);
//		
//		if (updateResult > 0) {
//			employeeResultView.displayDmlResult("updateSuccess");
//		} else {
//			employeeResultView.displayDmlResult("updateFailed");
//		}
//	}
}

