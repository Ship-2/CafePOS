package org.ship2.view;

import java.util.Scanner;

import org.ship2.controller.CategoryController;

public class CategoryManagement {
	private CategoryController categoryController = new CategoryController();
	
	public void displayCategory() {
		
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("============ 카테고리 관리 ============");
			System.out.println("1. 모든 카테고리 확인");
			System.out.println("2. 카테고리 추가");
			System.out.println("3. 카테고리 삭제");
			System.out.println("4. 카테고리 수정");
			System.out.print("번호를 선택해 주세요 : ");
			int answer = sc.nextInt();
			sc.nextLine();
			
			switch (answer) {
				
			}
			
		*
		} while (false);
	
	}
}
