package org.ship2.view;

import java.util.List;

import org.ship2.model.dto.MembershipDTO;

public class MemberResultView {
	
	public void show(List<MembershipDTO> memberList) {
		for (MembershipDTO member : memberList) {
			System.out.println(member);	
		}
	}
	public void showResult(String resultCode) {
		
		switch(resultCode) {
		case "selectfailed" : System.out.println("[에러} 멤버를 조회할 수 없습니다"); break;
		case "addSuccess" : System.out.println(" [성공] 멤버 추가 성공"); break;
		case "addFailed" : System.out.println(" [실패] 멤버 추가 실패"); break;
		case "updateSuccess" : System.out.println(" [성공] 멤버정보 수정 성공"); break; 
		case "updateFailed" : System.out.println(" [실패] 멤버정보 수정 실패"); break;
		case "deleteSuccess" : System.out.println(" [성공] 멤버 삭제 성공");break;
		case "deleteFailed" : System.out.println(" [실패] 멤버 삭제 실패"); break;
		
		default : System.out.println("에러 발생 : 자세한 사항은" 
					 + "/CafePOS/src/org/ship2/controller/MembershipController.java를 참조하시오.");
		}
	}
	

}
