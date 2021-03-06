package org.ship2.model.dto;

import java.io.Serializable;

public class MenuCategoriSizeDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8612335235827957320L;
	
	private int menuCode;
	private String menuName;
	private int unitPrice;
	private int categoryCode;
	private String categoryName;
	
	public MenuCategoriSizeDTO() {
	}

	public MenuCategoriSizeDTO(int menuCode, String menuName, int unitPrice, int categoryCode, String categoryName) {
		this.menuCode = menuCode;
		this.menuName = menuName;
		this.unitPrice = unitPrice;
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
	}

	public int getMenuCode() {
		return menuCode;
	}


	public void setMenuCode(int menuCode) {
		this.menuCode = menuCode;
	}


	public String getMenuName() {
		return menuName;
	}


	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}


	public int getUnitPrice() {
		return unitPrice;
	}


	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}


	public int getCategoryCode() {
		return categoryCode;
	}


	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "메뉴명 = " + menuName + ", 가격 = " + unitPrice + ", 카테고리 = " + categoryName;
	}
}