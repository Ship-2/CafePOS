package org.ship2.model.dao;

import static org.ship2.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.ship2.model.dto.DailySalesDTO;


public class DailySalesDAO {
	
	private Properties prop = new Properties();
	
	public DailySalesDAO() {
		try {
			prop.loadFromXML(new FileInputStream("mapper/dailySales-query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/* 특정 날짜에 해당하는 Daily_Sales 인스턴스 있는지 select */
	public DailySalesDTO selectDailySalesBySalesDate(Connection con, String dailySalesDate) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectDailySalesBySalesDate");
//		System.out.println(query);
				
		DailySalesDTO dailySales = new DailySalesDTO();
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dailySalesDate);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				dailySales.setSalesDate(rset.getDate("SALES_DATE"));
				dailySales.setSales(rset.getInt("SALES"));
				dailySales.setRefund(rset.getInt("REFUND"));
				dailySales.setTotalSales(rset.getInt("TOTAL_SALES"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return dailySales;
	}

	/* 당일 첫주문이면 DAILY_SALE 테이블에 인스턴스 insert */
	public int insertDailySales(Connection con, String dateOfFirstOrder) {

		PreparedStatement pstmt = null;
		int salesInsertResult = 0;
		
		String query = prop.getProperty("insertDailySales");
//		System.out.println(query);
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, dateOfFirstOrder);
			
			salesInsertResult = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return salesInsertResult;
	}

	/* 당일 첫주문이 아니면 DAILY_SALE 테이블에 인스턴스 update */
	public int updateDailySalesByInsert(Connection con, int insertOrderCode, String dateOfNthOrder) {
		
		PreparedStatement pstmt = null;
		int salesUpdatetResult = 0;
		
		String query = prop.getProperty("updateDailySalesByInsert");
//		System.out.println(query);
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, insertOrderCode);
			pstmt.setInt(2, insertOrderCode);
			pstmt.setString(3, dateOfNthOrder);
			
			salesUpdatetResult = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return salesUpdatetResult;
	}


	/* 주문 취소시 해당 주문번호에 해당되는 만큼 DAILY_SALE 테이블에서 마이너스하는 update  */
	public int updateDailySalesByDelete(Connection con, int deleteOrderCode, String dateOfDelete) {
		
		PreparedStatement pstmt = null;
		int refundUpdateResult = 0;
		
		String query = prop.getProperty("updateDailySalesByDelete");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, deleteOrderCode);
			pstmt.setInt(2, deleteOrderCode);
			pstmt.setString(3, dateOfDelete);
			
			refundUpdateResult = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return refundUpdateResult;
	}
	
}
