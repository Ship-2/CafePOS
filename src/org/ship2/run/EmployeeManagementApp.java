package org.ship2.run;

import org.ship2.view.EmployeeManagement;

public class EmployeeManagementApp {

	public static void main(String[] args) {
		final String thisClassName = new Object(){}.getClass().getEnclosingClass().getName();
		final String thisMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
		
		/**
		 * @note : Uncomment following four lines to implement JDBC Unit test.
		 *         If GUI Test is active, uncomment it before execute this test. 
		 *         
		 *         JDBC Unit test is consisted with four base functions including
		 *         registNewEmployee, selectAllEmployees, modifyEmployee,
		 *         and deleteEmployees.
		 */
//		EmployeeManagement JDBCUnitTest = new EmployeeManagement();
//		JDBCUnitTest.EmployeeManagementTest();
//		System.out.println("[LOG from: " + thisClassName + "." + thisMethodName + "] "
//							+ "직원관리 단위 테스트 종료.");
		
		org.ship2.run.SunminApplication.main(null);
//		System.out.println("[LOG from: " + thisClassName + "." + thisMethodName + "] "
//							+ "직원관리 GUI 테스트 종료.");
		
	}

}
