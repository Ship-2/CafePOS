package org.ship2.view;

import java.util.List;
import java.util.Scanner;

import org.ship2.controller.MenuSizeController;
import org.ship2.model.dto.MenuSizeDTO;


public class MenuSizeView {
	
	private MenuSizeController menuSizeController = new MenuSizeController();

	public void sampleDisplay() {
				
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n=========== 전체 mesuSise select하기 ===========");
		List<MenuSizeDTO> menuSizeList = menuSizeController.selectAllMenuSize();
		
		for (MenuSizeDTO menuSize : menuSizeList) {
			System.out.println(menuSize);
		}
		
		
		
		System.out.println("\n=========== SizeCode를 입력해 해당되는 SizeName 출력 ===========");
		System.out.print("sizeCode(1/2/3) 입력 : ");
		int inputMenuSizeCode = sc.nextInt();
		
		MenuSizeDTO menuSize = menuSizeController.selectBySizeCode(inputMenuSizeCode);
		System.out.println("입력한 SizeCode의 정보 : " + menuSize);

		
		
//		System.out.println("\n=========== (버전2)SizeCode를 입력해 해당되는 SizeName 출력 ===========");
//		System.out.print("sizeCode(1/2/3) 입력 : ");
//		
//		String sizeName = "";
//		int inputMenuSizeCode = sc.nextInt();
//		
//		for(int i = 0; i < menuSizeList.size(); i++) {
//			
//			MenuSizeDTO menuSize = menuSizeList.get(i);
//			
//			if(menuSize.getSizeCode() == inputMenuSizeCode) {
//				sizeName = menuSize.getSizeName();
//			}
//		}
//		System.out.println("입력한 SizeCode의 SizeName : " + sizeName);
	}
}
