package org.ship2.controller;

import java.util.List;

import org.ship2.model.dto.DailySalesDTO;
import org.ship2.model.service.DailySalesService;

public class DailySalesController {
	
	private DailySalesService dailySalesService = new DailySalesService();

	/* selectDailySalesBySalesDate */
	public DailySalesDTO selectDailySalesBySalesDate(String dailySalesDate) {

		DailySalesDTO dailySales = dailySalesService.selectDailySalesBySalesDate(dailySalesDate);
		
		return dailySales;
	}


	/* insertDailySales */
	public int insertDailySales(String dateOfFirstOrder) {
		
		int salesInsertResult = dailySalesService.insertDailySales(dateOfFirstOrder);
		
		if(salesInsertResult > 0) {
//			System.out.println("insertDailySales 성공");
		} else {
//			System.out.println("insertDailySales 실패");
		}
		return salesInsertResult;
	}


	/* updateDailySalesByInsert */
	public int updateDailySalesByInsert(int insertOrderCode, String dateOfNthOrder) {

		int salesUpdatetResult = dailySalesService.updateDailySalesByInsert(insertOrderCode, dateOfNthOrder);
		
		if(salesUpdatetResult > 0) {
//			System.out.println("updateDailySalesByInsert 성공");
		} else {
//			System.out.println("updateDailySalesByInsert 실패");
		}
		return salesUpdatetResult;
	}


	/* updateDailySalesByDelete */
	public int updateDailySalesByDelete(int deleteOrderCode, String dateOfDelete) {

		int refundUpdateResult = dailySalesService.updateDailySalesByDelete(deleteOrderCode, dateOfDelete);
		
		if(refundUpdateResult > 0) {
//			System.out.println("updateDailySalesByDelete 성공");
		} else {
//			System.out.println("updateDailySalesByDelete 실패");
		}
		return refundUpdateResult;
	}

}
