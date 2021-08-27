package org.ship2.model.dao;

import static org.ship2.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.ship2.model.dto.EmployeeDTO;

public class LogInDAO {

	private Properties prop = new Properties();
	
	
	public LogInDAO() {
		try {
			prop.loadFromXML(new FileInputStream("mapper/login-query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<EmployeeDTO> selectAllEmployee(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		
		List<EmployeeDTO> employeeList = null;
		
		String query = prop.getProperty("selectAllEmployee");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			employeeList = new ArrayList<>();
			
			while(rset.next()) {
				EmployeeDTO employee = new EmployeeDTO();
				employee.setEmpCode(rset.getInt("EMP_CODE"));
				employee.setEmpName(rset.getString("EMP_NAME"));
				employee.setEmpPhone(rset.getString("EMP_PHONE"));
				employee.setJobCode(rset.getInt("JOB_CODE"));
				employee.setEmpId(rset.getString("EMP_ID"));
				employee.setEmpPw(rset.getString("EMP_PW"));
				
				
				
				employeeList.add(employee);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return employeeList;
	}
		
}
