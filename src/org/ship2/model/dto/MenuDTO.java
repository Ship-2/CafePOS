package org.ship2.model.dto;

import java.io.Serializable;

public class MenuDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8612335235827957320L;
	
	private String menuCode;
	private String menuName;
	private String unitPrice;
	private String categoryCode;
	private String sizeCode;
	
	public MenuDTO() {
	}

	public MenuDTO(String menuCode, String menuName, String unitPrice, String categoryCode, String sizeCode) {
		super();
		this.menuCode = menuCode;
		this.menuName = menuName;
		this.unitPrice = unitPrice;
		this.categoryCode = categoryCode;
		this.sizeCode = sizeCode;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getSizeCode() {
		return sizeCode;
	}

	public void setSizeCode(String sizeCode) {
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