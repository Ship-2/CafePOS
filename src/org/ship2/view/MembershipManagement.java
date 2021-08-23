package org.ship2.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.ship2.controller.MembershipController;

public class MembershipManagement {
	
	private Scanner sc = new Scanner(System.in);
	
	public void MembershipManagementTest() {
		MembershipController membershipController = new MembershipController();
		
		do {
			System.out.println("-------  < 직원 관리 유닛 테스트 >  -------");
			System.out.println("1. 멤버 등록 테스트");
			System.out.println("2. 멤버 조회 테스트");
			System.out.println("0. 테스트 종료");
			System.out.print("번호 선택 : ");
			int num = sc.nextInt();
			sc.nextLine();
			
			switch (num) {
				case 1: membershipController.registNewMember(getMemberInfo()); break;	
				case 2: membershipController.selectAllMembers(); break;
				case 0: return;
				default: System.out.println("[에러]: 번호가 틀렸습니다!");
			}

		} while (true);
		
	}

	private Map<String, String> getMemberInfo() {
		Map<String, String> memInfoMap = new HashMap<>();
		
		System.out.print("멤버 이름 입력 : ");
		memInfoMap.put("name", sc.nextLine());
		System.out.print("멤버 전화번호 입력 : ");
		memInfoMap.put("phone", sc.nextLine());
		
		return memInfoMap;
	}

}
