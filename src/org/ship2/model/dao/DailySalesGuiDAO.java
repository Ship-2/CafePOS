package org.ship2.model.dao;

import static org.ship2.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.ship2.model.dto.DailySalesDTO;

public class DailySalesGuiDAO {
	
	private Properties prop = new Properties();
	
	public DailySalesGuiDAO() {
		
		try {
			prop.loadFromXML(new FileInputStream("mapper/dailySales-query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<DailySalesDTO> selectDailySales(Connection con, String startDate, String endDate) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectDailySales");
//		System.out.println(query);
		
		List<DailySalesDTO> dailySalesList = null;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			
			rset = pstmt.executeQuery();
			
			dailySalesList = new ArrayList<>();
			
			while (rset.next()) {
				DailySalesDTO dailySales = new DailySalesDTO();
				
				dailySales.setSalesDate(rset.getDate("SALES_DATE"));
				dailySales.setSales(rset.getInt("SALES"));
				dailySales.setRefund(rset.getInt("REFUND"));
				dailySales.setTotalSales(rset.getInt("TOTAL_SALES"));
				
				dailySalesList.add(dailySales);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return dailySalesList;
	}

}
