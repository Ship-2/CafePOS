package org.ship2.model.dao;

import static org.ship2.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.ship2.model.dto.EmployeeDTO;
import org.ship2.model.dto.HrDTO;

public class HrDAO {
	
	private Properties prop = new Properties();
	
	public HrDAO() {
		try {
			prop.loadFromXML(new FileInputStream("mapper/hr-query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public List<EmployeeDTO> selectAllEmployee(Connection con) {

		Statement stmt = null;
		ResultSet rset = null;
		
		List<EmployeeDTO> employeeList = null;
		
		String query = prop.getProperty("selectAllEmployee");
//		System.out.println(query);
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			employeeList = new ArrayList<>();
			
			while(rset.next()) {
				EmployeeDTO employee = new EmployeeDTO();
				
				employee.setEmpCode(rset.getInt("EMP_CODE"));
				employee.setEmpName(rset.getString("EMP_NAME"));
				employee.setEmpPhone(rset.getString("EMP_PHONE"));
				
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


	public List<HrDTO> selectHrByEmpCode(Connection con, int emp_code) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<HrDTO> hrList = null;
		
		String query = prop.getProperty("selectHrByEmpCode");
//		System.out.println(query);
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, emp_code);
			
			rset = pstmt.executeQuery();
			
			hrList = new ArrayList<>();
			
			while(rset.next()) {
				HrDTO hr = new HrDTO();
				
				hr.setHrDate(rset.getString("HR_DATE"));
				hr.setClockIn(rset.getString("CLOCK_IN"));
				hr.setClockOut(rset.getString("CLOCK_OUT"));
				
				hrList.add(hr);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return hrList;
	}


	public int insertHr(Connection con, int emp_code) {
		
		PreparedStatement pstmt = null;
		int hrInsertResult = 0;
		
		String query = prop.getProperty("insertHr");
//		System.out.println(query);
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, emp_code);
			
			hrInsertResult = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return hrInsertResult;
	}


	public int updateClockOut(Connection con, int emp_code) {
		
		PreparedStatement pstmt = null;		
		int clockOutUpdateResult = 0;
		
		String query = prop.getProperty("updateClockOut");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, emp_code);
			
			clockOutUpdateResult = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return clockOutUpdateResult;
	}


	public int updateHr(Connection con, HrDTO hrDTO) {
		
		PreparedStatement pstmt = null;
		int hrUpdateResult = 0;
		
		String query = prop.getProperty("updateHr");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, hrDTO.getClockIn());
			pstmt.setString(2, hrDTO.getClockOut());
			pstmt.setString(3, hrDTO.getHrDate());
			pstmt.setInt(4, hrDTO.getEmpCode());
			
			hrUpdateResult = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return hrUpdateResult;
	}
}
