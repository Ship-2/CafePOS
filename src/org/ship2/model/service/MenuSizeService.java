package org.ship2.model.service;

import static org.ship2.common.JDBCTemplate.getConnection;
import static org.ship2.common.JDBCTemplate.close;

import java.sql.Connection;
import java.util.List;

import org.ship2.model.dao.MenuSizeDAO;
import org.ship2.model.dto.MenuSizeDTO;

public class MenuSizeService {
	
	private MenuSizeDAO menuSizeDAO = new MenuSizeDAO();

	public List<MenuSizeDTO> selectAllMenuSize() {
		
		Connection con = getConnection();
		
		List<MenuSizeDTO> menuSizeList = menuSizeDAO.selectAllMenuSize(con);
		
		close(con);
		
		return menuSizeList;
	}



	public MenuSizeDTO selectBySizeCode(int inputMenuSizeCode) {
		
		Connection con = getConnection();
		
		MenuSizeDTO menuSize = menuSizeDAO.selectBySizeCode(con, inputMenuSizeCode);
		
		close(con);
		
		return menuSize;
	}

}
