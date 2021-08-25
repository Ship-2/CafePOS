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
			System.out.println("3. 멤버 삭제 테스트");
			System.out.println("4. 멤버 수정 테스트");
			System.out.println("0. 테스트 종료");
			System.out.print("번호 선택 : ");
			int num = sc.nextInt();
			sc.nextLine();
			
			switch (num) {
				case 1: 	// C : register New Member
					System.out.println("새로운 멤버를 등록합니다");
					System.out.println("등록하고자 하는 멤버의 정보를 입력해주세요");
					membershipController.registNewMember(getMemberInfo()); 
					break;	
				case 2:		// R : read(select) All Members 
					System.out.println("현재 DB에 등록되어 있는 모든 멤버의 목록을 조회합니다");
					membershipController.selectAllMembers();
					break;
				case 3 : 	// U : update existing Member's information
					System.out.println("기존 멤버의 정보를 수정합니다");
					System.out.println("단위 테스트이기 때문에 수정하고자 하는 멤버의 코드를 입력받고");
					System.out.println("해당 직원의 기존 정보에 새로 덮어쓸 정보를 입력받습니다.");
					System.out.println("수정하고자 하는");
					
					
				case 0: return;
				default: System.out.println("[에러]: 번호가 틀렸습니다!");
			}

		} while (true);
		
	}
	
//	private String 

	private Map<String, String> getMemberInfo() {
		Map<String, String> memInfoMap = new HashMap<>();
		
		System.out.print("멤버 이름 입력 : ");
		memInfoMap.put("name", sc.nextLine());
		System.out.print("멤버 전화번호 입력 : ");
		memInfoMap.put("phone", sc.nextLine());
		
		return memInfoMap;
	}

}
