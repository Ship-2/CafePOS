package org.ship2.controller;

import java.util.List;

import org.ship2.model.dto.MenuSizeDTO;
import org.ship2.model.service.MenuSizeService;

public class MenuSizeController {
	
	private MenuSizeService sampleService = new MenuSizeService();

	public List<MenuSizeDTO> selectAllMenuSize() {
		
		List<MenuSizeDTO> menuSizeList = sampleService.selectAllMenuSize();
		
		return menuSizeList;
	}


	public MenuSizeDTO selectBySizeCode(int inputMenuSizeCode) {
		
		MenuSizeDTO menuSize = sampleService.selectBySizeCode(inputMenuSizeCode);
		
		return menuSize;
	}

	

}
