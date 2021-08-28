package org.ship2.model.service;

import java.sql.Connection;
import java.util.List;

import org.ship2.model.dao.MenuDAO;
import org.ship2.model.dto.MenuCategoriSizeDTO;
import org.ship2.model.dto.MenuDTO;

import static org.ship2.common.JDBCTemplate.getConnection;
import static org.ship2.common.JDBCTemplate.close;
import static org.ship2.common.JDBCTemplate.commit;
import static org.ship2.common.JDBCTemplate.rollback;

public class MenuService {
	
	private MenuDAO menuDAO = new MenuDAO();
	
	public List<MenuCategoriSizeDTO> selectMenu() {
		Connection con = getConnection();
		
		List<MenuCategoriSizeDTO> menuList = menuDAO.selectMenu(con);
		
		close(con);
		
		return menuList;
		
	}

	public int insertMenu(MenuDTO inputMenu) {
		Connection con = getConnection();
		
		int result = menuDAO.insertMenu(con, inputMenu);
		
		if(result > 0 && result < 100) {
			commit(con);
		} else if(result == 7777) {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public int updateMenu(MenuDTO menu) {
		Connection con = getConnection();
		
		int result = menuDAO.updateMenu(con, menu);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public int deleteMenu(MenuDTO menu) {
		Connection con = getConnection();
		
		int result = menuDAO.deleteMenu(con, menu);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public int seletMenuCode(String menuName) {
		Connection con = getConnection();
		
		int result = menuDAO.seletMenuCode(con, menuName);
		
		close(con);
		
		return result;
	}

}
