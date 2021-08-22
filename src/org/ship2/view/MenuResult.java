package org.ship2.view;

import java.util.List;
import java.util.Scanner;

import org.ship2.controller.MenuController;
import org.ship2.model.dto.MenuCategoriSizeDTO;
import org.ship2.model.dto.MenuDTO;

public class MenuResult {
	
	public void displayDmlResult(String code) {
		
		switch(code) {
			case "insertFailed" : System.out.println("메뉴 추가 실패!"); break;
			case "updateFailed" : System.out.println("메뉴 수정 실패!"); break;
			case "deleteFailed" : System.out.println("메뉴 삭제 실패!"); break;
			case "selectFailed" : System.out.println("메뉴 조회 실패!"); break;
			case "insertSuccess" : System.out.println("insert 성공!"); break;
			case "updateSuccess" : System.out.println("update 성공!"); break;
			case "deleteSuccess" : System.out.println("delete 성공!"); break;
			default : System.out.println("알 수 없는 에러 발생!"); break;
		}
		
	}
	
	public void displayMenu(List<MenuCategoriSizeDTO> list) {
		
		for(MenuCategoriSizeDTO m : list) {
			System.out.println(m);
		}

	}

}
