package org.ship2.model.dao;

import static org.ship2.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.ship2.model.dto.EmployeeDTO;

public class EmployeeDAO {
	private Properties prop = new Properties();
	
	public EmployeeDAO() {
		try {
			prop.loadFromXML(new FileInputStream("mapper/employee-query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<EmployeeDTO> selectAllEmployees(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<EmployeeDTO> employeeList = null;
		
		String query = prop.getProperty("selectAllEmployees");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			employeeList = new ArrayList<>();
			
			while (rset.next()) {
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
			close(pstmt);
		}
		
		return employeeList;
	}

	public int insertNewEmployee(Connection conn, EmployeeDTO emp) {
		PreparedStatement pstmt = null;
		int createResult = 0;
		
		String query = prop.getProperty("insertNewEmployee");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, emp.getEmpName());
			pstmt.setString(2, emp.getEmpPhone());
			pstmt.setInt(3, emp.getJobCode());
			pstmt.setString(4, emp.getEmpId());
			pstmt.setString(5, emp.getEmpPw());
			
			createResult = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return createResult;
	}

}
