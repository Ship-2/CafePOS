package org.ship2.model.service;

import static org.ship2.common.JDBCTemplate.getConnection;
import static org.ship2.common.JDBCTemplate.close;

import java.sql.Connection;
import java.util.List;

import org.ship2.model.dao.LogInDAO;
import org.ship2.model.dto.EmployeeDTO;

public class LogInService {
	
	private LogInDAO loginDAO = new LogInDAO();

	public List<EmployeeDTO> selectAllEmployee() {

		Connection con = getConnection();
		
		List<EmployeeDTO> employeeList = loginDAO.selectAllEmployee(con);
		
		close(con);
		
		return employeeList;
	}

}
