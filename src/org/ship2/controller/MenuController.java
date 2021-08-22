package org.ship2.controller;

import java.util.List;

import org.ship2.model.dto.MenuCategoriSizeDTO;
import org.ship2.model.dto.MenuDTO;
import org.ship2.model.service.MenuService;
import org.ship2.view.Menu;
import org.ship2.view.MenuResult;

public class MenuController {

	private MenuService orderService = new MenuService();
	private MenuResult menuResult = new MenuResult();
//	private Menu menu = new Menu();
	
	public void selectMenu() {
		menuResult.displayMenu(orderService.selectMenu());
	}

	public int insertMenu(MenuDTO inputMenu) {
		int result = orderService.insertMenu(inputMenu);
		
		return result;
	}
	
	public List<MenuCategoriSizeDTO> selectMenu2() {
		List<MenuCategoriSizeDTO> menulist = orderService.selectMenu();
		return menulist;
	}

	public int updateMenu(MenuDTO menu) {
		int result = orderService.updateMenu(menu);
		
		return result;
	}

	public int deleteMenu(MenuDTO menu) {
		int result = orderService.deleteMenu(menu);
		
		return result;
	}
	
}
