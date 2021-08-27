package org.ship2.model.service;

import static org.ship2.common.JDBCTemplate.close;
import static org.ship2.common.JDBCTemplate.getConnection;
import static org.ship2.common.JDBCTemplate.commit;
import static org.ship2.common.JDBCTemplate.rollback;

import java.sql.Connection;

import org.ship2.model.dao.DailySalesDAO;
import org.ship2.model.dto.DailySalesDTO;

public class DailySalesService {
	
	private DailySalesDAO dailySalesDAO = new DailySalesDAO();

	public DailySalesDTO selectDailySalesBySalesDate(String dailySalesDate) {

		Connection con = getConnection();
		
		DailySalesDTO dailySales = dailySalesDAO.selectDailySalesBySalesDate(con, dailySalesDate);
		
		close(con);
		
		return dailySales;
	}

	public int insertDailySales(String dateOfFirstOrder) {

		Connection con = getConnection();
		
		int salesInsertResult = 0;
		
		salesInsertResult = dailySalesDAO.insertDailySales(con, dateOfFirstOrder);
		
		if(salesInsertResult > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return salesInsertResult;
	}

	public int updateDailySalesByInsert(int insertOrderCode, String dateOfNthOrder) {

		Connection con = getConnection();
		
		int salesUpdatetResult = dailySalesDAO.updateDailySalesByInsert(con, insertOrderCode, dateOfNthOrder);
		
		if(salesUpdatetResult > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return salesUpdatetResult;
	}

	public int updateDailySalesByDelete(int deleteOrderCode, String dateOfDelete) {

		Connection con = getConnection();
		
		int refundUpdateResult = dailySalesDAO.updateDailySalesByDelete(con, deleteOrderCode, dateOfDelete);
		
		if(refundUpdateResult > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return refundUpdateResult;
	}

}
