package org.ship2.model.service;

import java.sql.Connection;
import java.util.List;

import org.ship2.model.dao.MenuDAO;
import org.ship2.model.dao.PosOrderDAO;
import org.ship2.model.dto.MenuCategoriSizeDTO;
import org.ship2.model.dto.MenuDTO;
import org.ship2.model.dto.PosOrderDTO;

import static org.ship2.common.JDBCTemplate.getConnection;
import static org.ship2.common.JDBCTemplate.close;
import static org.ship2.common.JDBCTemplate.commit;
import static org.ship2.common.JDBCTemplate.rollback;

public class PosOrderService {
	
	private PosOrderDAO menuDAO = new PosOrderDAO();
	

	public int insertOrder(PosOrderDTO insetOrder) {
		Connection con = getConnection();
		
		int result = menuDAO.insertMenu(con, insetOrder);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}
}
