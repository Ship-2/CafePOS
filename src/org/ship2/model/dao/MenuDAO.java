package org.ship2.model.dao;

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

import org.ship2.model.dto.MenuCategoriSizeDTO;
import org.ship2.model.dto.MenuDTO;

import static org.ship2.common.JDBCTemplate.close;

public class MenuDAO {
	
	private Properties prop = new Properties();
	
	public MenuDAO() {
		try {
			prop.loadFromXML(new FileInputStream("mapper/menu-query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<MenuCategoriSizeDTO> selectMenu(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		List<MenuCategoriSizeDTO> menuList = null;
		String query = prop.getProperty("selectAllMenu");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			menuList = new ArrayList<>();
			while(rset.next()) {
				MenuCategoriSizeDTO menu = new MenuCategoriSizeDTO();
				menu.setMenuCode(rset.getInt("MENU_CODE"));
				menu.setMenuName(rset.getString("MENU_NAME"));
				menu.setUnitPrice(rset.getInt("UNIT_PRICE"));
				menu.setCategoryCode(rset.getInt("CATEGORY_CODE"));
				menu.setCategoryName(rset.getString("CATEGORY_NAME"));
				menu.setSizeCode(rset.getInt("SIZE_CODE"));
				menu.setSizeName(rset.getString("SIZE_NAME"));
				
				menuList.add(menu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rset);
		}
		
		return menuList;
	}

	public int insertMenu(Connection con, MenuDTO inputMenu) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertMenu");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, inputMenu.getMenuName());
			pstmt.setInt(2, inputMenu.getUnitPrice());
			pstmt.setInt(3, inputMenu.getCategoryCode());
			pstmt.setInt(4, inputMenu.getSizeCode());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
				
		return result;
	}

}
