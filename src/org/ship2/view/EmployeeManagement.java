package org.ship2.view;

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
			System.out.println("1. 직원 등록 테스트");	// not been created yet.
			System.out.println("2. 직원 조회 테스트");
			System.out.println("3. 직원 수정 테스트");	// not been created yet.
			System.out.println("4. 직원 삭제 테스트");	// not been created yet.
			System.out.println("0. 테스트 종료");
			System.out.print("번호 선택 : ");
			int num = sc.nextInt();
			sc.nextLine();
			
			switch (num) {
//				case 1: employeeController.registNewEmployee(); break;	// not been created yet.
				case 2: employeeController.selectAllEmployees(); break;
//				case 3: employeeController.modifyEmployee(); break;		// not been created yet.
//				case 4: employeeController.deleteEmployee(); break;		// not been created yet.
				case 0: return;
				default: System.out.println("[ERROR]: Wrong Number!");
			}

		} while (true);
		
	}
	

}
