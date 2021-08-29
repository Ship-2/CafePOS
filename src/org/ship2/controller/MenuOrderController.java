package org.ship2.controller;

import java.util.List;

import org.ship2.model.dto.MenuCategoriSizeDTO;
import org.ship2.model.dto.MenuDTO;
import org.ship2.model.dto.MenuOrderDTO;
import org.ship2.model.dto.PosOrderDTO;
import org.ship2.model.service.MenuOrderService;
import org.ship2.model.service.MenuService;
import org.ship2.model.service.PosOrderService;
import org.ship2.view.Menu;
import org.ship2.view.MenuResult;

public class MenuOrderController {

	private MenuOrderService menuOrder = new MenuOrderService();

	public int insertMenuOrder(List<MenuOrderDTO> menuOrderList) {
		int result = menuOrder.insertMenuOrder(menuOrderList);
		
		return result;
	}
	
}
