package org.ship2.model.service;

import java.sql.Connection;
import java.util.List;

import org.ship2.model.dao.MenuDAO;
import org.ship2.model.dao.MenuOrderDAO;
import org.ship2.model.dao.PosOrderDAO;
import org.ship2.model.dto.MenuCategoriSizeDTO;
import org.ship2.model.dto.MenuDTO;
import org.ship2.model.dto.MenuOrderDTO;
import org.ship2.model.dto.PosOrderDTO;

import static org.ship2.common.JDBCTemplate.getConnection;
import static org.ship2.common.JDBCTemplate.close;
import static org.ship2.common.JDBCTemplate.commit;
import static org.ship2.common.JDBCTemplate.rollback;

public class MenuOrderService {
	
	private MenuOrderDAO menuOrderDAO = new MenuOrderDAO();

	public int insertMenuOrder(List<MenuOrderDTO> menuOrderList) {
		Connection con = getConnection();
		
		int result = menuOrderDAO.insertMenuOrder(con, menuOrderList);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}
}
