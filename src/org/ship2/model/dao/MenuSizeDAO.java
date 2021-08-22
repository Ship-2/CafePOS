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

import org.ship2.model.dto.MenuSizeDTO;

public class MenuSizeDAO {

	private Properties prop = new Properties();
	
	public MenuSizeDAO() {
		try {
			prop.loadFromXML(new FileInputStream("mapper/menuSize-query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<MenuSizeDTO> selectAllMenuSize(Connection con) {
		
		Statement stmt = null;
		ResultSet rset = null;
		
		List<MenuSizeDTO> menuSizeList = null;
		
		String query = prop.getProperty("selectAllMenuSize");
//		System.out.println(query);
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			menuSizeList = new ArrayList<>();
			
			while (rset.next()) {
				MenuSizeDTO menuSize = new MenuSizeDTO();
				
				menuSize.setSizeCode(rset.getInt("SIZE_CODE"));
				menuSize.setSizeName(rset.getString("SIZE_NAME"));
				menuSize.setSizePrice(rset.getInt("SIZE_PRICE"));
				
				menuSizeList.add(menuSize);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return menuSizeList;
	}



	public MenuSizeDTO selectBySizeCode(Connection con, int inputMenuSizeCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
				
		String query = prop.getProperty("selectBySizeCode");
//		System.out.println(query);
		
		MenuSizeDTO menuSize = null;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, inputMenuSizeCode);
			
			rset = pstmt.executeQuery();
						
			if(rset.next()) {
				menuSize = new MenuSizeDTO();
				
				menuSize.setSizeCode(inputMenuSizeCode);
				menuSize.setSizeName(rset.getString("SIZE_NAME"));
				menuSize.setSizePrice(rset.getInt("SIZE_PRICE"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return menuSize;
	}

}
