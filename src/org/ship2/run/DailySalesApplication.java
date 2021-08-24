package org.ship2.run;

import org.ship2.view.DailySalesView;

public class DailySalesApplication {

	public static void main(String[] args) {

		/* 새로운 주문 입력시 날짜를 보고 DAILY_SALES 테이블에 insert or update */
		DailySalesView dailySalesInsertView = new DailySalesView();
		dailySalesInsertView.displayDailySales();

	}

}
