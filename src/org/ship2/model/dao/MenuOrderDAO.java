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
import org.ship2.model.dto.MenuOrderDTO;
import org.ship2.model.dto.PosOrderDTO;

import static org.ship2.common.JDBCTemplate.close;

public class MenuOrderDAO {
	
	private Properties prop = new Properties();
	
	public MenuOrderDAO() {
		try {
			prop.loadFromXML(new FileInputStream("mapper/menuOrder-query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertMenuOrder(Connection con, List<MenuOrderDTO> menuOrderList) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertMenuOrder");
		
		for(int i = 0; i < menuOrderList.size(); i++) {
			MenuOrderDTO menuOrder = new MenuOrderDTO();
			menuOrder = menuOrderList.get(i);
//			System.out.println(menuOrder.getMenuCode());
			
			try {
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, menuOrder.getMenuCode());
				pstmt.setInt(2, menuOrder.getQuan());
				pstmt.setInt(3, menuOrder.getSizeCode());
				result = pstmt.executeUpdate();
				result += result;
			}  catch (SQLException e) {
				e.printStackTrace();
			}
		}
		close(pstmt);
				
		return result;
	}
}
