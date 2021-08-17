package org.ship2.model.dto;

import java.io.Serializable;

public class MenuDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8612335235827957320L;
	
	private int menuCode;
	private String menuName;
	private int unitPrice;
	private int categoryCode;
	private int sizeCode;
	
	public MenuDTO() {
	}

	public MenuDTO(int menuCode, String menuName, int unitPrice, int categoryCode, int sizeCode) {
		this.menuCode = menuCode;
		this.menuName = menuName;
		this.unitPrice = unitPrice;
		this.categoryCode = categoryCode;
		this.sizeCode = sizeCode;
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

	public int getSizeCode() {
		return sizeCode;
	}

	public void setSizeCode(int sizeCode) {
		this.sizeCode = sizeCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "MenuDTO [menuCode=" + menuCode + ", menuName=" + menuName + ", unitPrice=" + unitPrice
				+ ", categoryCode=" + categoryCode + ", sizeCode=" + sizeCode + "]";
	}
}