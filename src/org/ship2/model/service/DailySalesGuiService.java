package org.ship2.model.service;

import static org.ship2.common.JDBCTemplate.close;
import static org.ship2.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import org.ship2.model.dao.DailySalesGuiDAO;
import org.ship2.model.dto.DailySalesDTO;

public class DailySalesGuiService {
	
	private DailySalesGuiDAO dailySalesGuiDAO = new DailySalesGuiDAO();

	public List<DailySalesDTO> selectDailySales(String startDate, String endDate) {

		Connection con = getConnection();
		
		List<DailySalesDTO> dailySalesList = dailySalesGuiDAO.selectDailySales(con, startDate, endDate);
		
		close(con);
		
		return dailySalesList;
	}

}
