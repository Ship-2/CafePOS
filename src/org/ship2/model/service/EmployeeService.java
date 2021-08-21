package org.ship2.model.service;

import static org.ship2.common.JDBCTemplate.close;
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
		
		int dmlResult = 0;
		
		dmlResult = employeeDAO.insertNewEmployee(conn, empDTO);
		
		close(conn);
		
		return dmlResult;
	}

}
