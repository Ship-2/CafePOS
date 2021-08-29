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
			result = pstmt.executeUpdate();
		}  catch (SQLIntegrityConstraintViolationException e) {
			result = 7777;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
				
		return result;
	}

	public int updateMenu(Connection con, MenuDTO menu) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateMenu");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, menu.getMenuName());
			pstmt.setInt(2, menu.getUnitPrice());
			pstmt.setInt(3, menu.getCategoryCode());
			pstmt.setInt(4, menu.getMenuCode());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteMenu(Connection con, MenuDTO menu) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteMenu");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, menu.getMenuCode());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int seletMenuCode(Connection con, String menuName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("seletMenuCode");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, menuName);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				result = rset.getInt("MENU_CODE");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
