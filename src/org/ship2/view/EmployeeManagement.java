package org.ship2.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.ship2.controller.EmployeeController;

public class EmployeeManagement {
	
	/**
	 * @brief This Scanner instance {@code sc} is created for unit test.
	 * 		  It would be deleted after GUI is created.
	 */
	private Scanner sc = new Scanner(System.in);

	public void EmployeeManagementTest() {
		EmployeeController employeeController = new EmployeeController();
		
		do {
			System.out.println("-------  < 직원 관리 유닛 테스트 >  -------");
			System.out.println("1. 직원 등록 테스트");
			System.out.println("2. 직원 조회 테스트");
			System.out.println("3. 직원 수정 테스트");
			System.out.println("4. 직원 삭제 테스트");
			System.out.println("0. 테스트 종료");
			System.out.print("번호 선택 : ");
			int num = sc.nextInt();
			sc.nextLine();
			
			switch (num) {
				case 1:		// C: INSERT new employee
					System.out.println("새로운 직원을 등록합니다.");
					System.out.println("등록하고자 하는 직원의 정보를 다음 안내 문구에 따라 작성해주세요.");
					employeeController.registNewEmployee(getEmployeeInfo());
					break;
					
				case 2:		// R : SELECT all employees
					System.out.println("현재 DB에 등록되어 있는 모든 직원의 목록을 조회합니다.");
					employeeController.selectAllEmployees();
					break;
					
				case 3:		// U : UPDATE existing employee's information
					System.out.println("기존 직원의 정보를 수정합니다.");
					System.out.println("단위 테스트이기 때문에 수정하고자 하는 직원의 ID를 입력받고");
					System.out.println("해당 직원의 기존 정보에 새로 덮어쓸 정보를 입력받습니다.");
					System.out.print("수정하고자 하는 ");
					employeeController.modifyEmployee(
													  getEmpIdFromUser(),
													  getEmployeeInfo()
													  );
					break;
					
				case 4:		// D : DELETE existing employee
					System.out.println("기존 직원을 삭제하려면 삭제하고자 하는 직원의"
										+ "ID를 입력하면 됩니다.");
					System.out.print("삭제하고자 하는 ");
					employeeController.deleteEmployee(getEmpIdFromUser());
					break;
					
				case 0:
					return;
					
				default:
					System.out.println("[ERROR]: Wrong Number!");
			}
			
		} while (true);
		
	}

	private String getEmpIdFromUser() {
		System.out.print("직원의 ID를 입력하시오 : ");
		String inputEmpId = sc.nextLine();
		
		return inputEmpId;
	}

	private Map<String, String> getEmployeeInfo() {
		Map<String, String> empInfoMap = new HashMap<>();
		
		System.out.print("직원 이름 입력 : ");
		empInfoMap.put("name", sc.nextLine());
		System.out.print("직원 전화번호 입력 : ");
		empInfoMap.put("phone", sc.nextLine());
		System.out.print("직원 직급 입력(1: 관리자, 2: 직원, 3: 수습) : ");
		empInfoMap.put("jobCode", Integer.valueOf(sc.nextInt()).toString());
		sc.nextLine();
		System.out.print("직원 ID 입력 : ");
		empInfoMap.put("id", sc.nextLine());
		System.out.print("직원 PW 입력 : ");
		empInfoMap.put("pw", sc.nextLine());
		
		return empInfoMap;
	}

}
