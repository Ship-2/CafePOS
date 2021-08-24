package org.ship2.model.service;

import static org.ship2.common.JDBCTemplate.close;
import static org.ship2.common.JDBCTemplate.commit;
import static org.ship2.common.JDBCTemplate.rollback;
import static org.ship2.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import org.ship2.model.dao.EmployeeDAO;
import org.ship2.model.dto.EmployeeDTO;

public class EmployeeService {
	private EmployeeDAO employeeDAO = new EmployeeDAO();

	public List<EmployeeDTO> selectAllEmployees() {
		Connection conn = getConnection();
		
		List<EmployeeDTO> employeeList = employeeDAO.selectAllEmployees(conn);
		
		close(conn);
		
		return employeeList;
	}

	public int insertNewEmployee(EmployeeDTO empDTO) {
		Connection conn = getConnection();
		
		int insertResult = 0;
		
		insertResult = employeeDAO.insertNewEmployee(conn, empDTO);
		
		if (insertResult > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return insertResult;
	}

	public int deleteEmployee(String employeeId) {
		Connection conn = getConnection();
		
		int deleteResult = 0;
		
		deleteResult = employeeDAO.deleteEmployee(conn, employeeId);
		
		if (deleteResult > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return deleteResult;
	}

	public int updateEmployeeInfo(String empIdFromUser, EmployeeDTO empDTO) {
		Connection conn = getConnection();
		
		int updateResult = 0;
		
		updateResult = employeeDAO.updateEmployeeInfo(conn, empIdFromUser, empDTO);
		
		if (updateResult > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return updateResult;
	}

}
