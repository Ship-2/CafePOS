package org.ship2.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.ship2.model.dto.MenuCategoriSizeDTO;
import org.ship2.model.dto.MenuDTO;
import org.ship2.model.dto.PosOrderDTO;

import static org.ship2.common.JDBCTemplate.close;

public class PosOrderDAO {
	
	private Properties prop = new Properties();
	
	public PosOrderDAO() {
		try {
			prop.loadFromXML(new FileInputStream("mapper/order-query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertMemOrder(Connection con, PosOrderDTO insertOrder) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertMemOrder");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, insertOrder.getPayCode());
			pstmt.setInt(2, insertOrder.getMemCode());
			result = pstmt.executeUpdate();
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
				
		return result;
	}
	
	public int insertOrder(Connection con, PosOrderDTO insertOrder) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertOrder");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, insertOrder.getPayCode());
			result = pstmt.executeUpdate();
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
				
		return result;
	}
	
	public int selectOrderCode(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("selectOrderCode");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			while (rset.next()) {
				result = rset.getInt("MAX(A.ORDER_CODE)");
			}
			
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
				
		return result;
	}
	
}
