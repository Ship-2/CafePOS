package org.ship2.controller;

import java.util.List;

import org.ship2.model.dto.DailySalesDTO;
import org.ship2.model.service.DailySalesGuiService;

public class DailySalesGuiController {
	
	private DailySalesGuiService dailySalesService = new DailySalesGuiService();

	public List<DailySalesDTO> selectDailySales(String startDate, String endDate) {
		
		List<DailySalesDTO> dailySalesList = dailySalesService.selectDailySales(startDate, endDate);
		
		return dailySalesList;
	}

}
