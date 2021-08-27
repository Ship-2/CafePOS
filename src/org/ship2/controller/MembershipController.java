package org.ship2.controller;

import java.util.List;
import java.util.Map;

import org.ship2.model.dto.EmployeeDTO;
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
	public void deleteMember(String memCode) {
		
		int deleteResult = membershipService.deleteMember(memCode);
		
		if(deleteResult > 0) {
			memberResultView.showResult("deleteSuccess");
		} else {
			memberResultView.showResult("deleteFailed");
		}
	}

	public void updateMember(String memIDfromMember, Map<String, String> memInfoMap) {
		MembershipDTO memDTO = new MembershipDTO();
		memDTO.setMemName(memInfoMap.get("name"));
		memDTO.setMemPhone(memInfoMap.get("phone"));
		memDTO.setMemYn(memInfoMap.get("yn"));
		memDTO.setMemCode(Integer.valueOf(memIDfromMember));
		
		int updateResult = membershipService.updateMember(memDTO);
		
		if(updateResult > 0) {
			memberResultView.showResult("updateSuccess");
		} else {
			memberResultView.showResult("updateFailed");
		}
	}
	
	
}
