package org.ship2.view;

import org.ship2.controller.DailySalesController;
import org.ship2.model.dto.DailySalesDTO;


public class DailySalesView {
	
	private DailySalesController dailySalesController = new DailySalesController();

	public void displayDailySales() {
		
		/* ==================== 해당 날짜의 DailySales select하기 ======================================================= */
		
		String dailySalesDate = "21/08/23"; //★★★수정할 부분(테스트용 데이터)★★★
		
		DailySalesDTO dailySales = dailySalesController.selectDailySalesBySalesDate(dailySalesDate);
		System.out.println(dailySales);
		
		//select한 결과를 이용해 첫주문인지 아닌지 판별
		if (dailySales.getSalesDate() == null) {
			System.out.println("\n작성한 날짜에 해당되는 데이터가 없다.\n");
			
			/* 
			 * 당일 첫 주문시 DailySales에 insert
			 */
			String dateOfFirstOrder = dailySalesDate;
			int salesInsertResult =  dailySalesController.insertDailySales(dateOfFirstOrder);
			
		} else {
			System.out.println("작성한 날짜에 해당되는 데이터가 있다.");
			/* 
			 * 당일 n번째 주문시 DailySales에 update
			 */
			String dateOfNthOrder = dailySalesDate;
			int insertOrderCode = 6; //★★★수정할 부분(테스트용 데이터)★★★
			
			int salesUpdatetResult = dailySalesController.updateDailySalesByInsert(insertOrderCode, dateOfNthOrder);
		}
		
		
		
		/* ==================== 주문 취소시  DailySales에 update ======================================================= */
		
		String dateOfDelete = "21/08/23";
		int deleteOrderCode = 6; //★★★수정할 부분(테스트용 데이터)★★★
		
		int refundUpdateResult = dailySalesController.updateDailySalesByDelete(deleteOrderCode, dateOfDelete);
		
	}
}
