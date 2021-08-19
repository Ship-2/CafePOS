package org.ship2.model.dto;

import java.io.Serializable;

public class MenuOrderDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7460587992217626524L;
	
	private int orderCode;
	private int menuCode;
	private int quan;
	
	public MenuOrderDTO() {
	}
	
	public MenuOrderDTO(int orderCode, int menuCode, int quan) {
		this.orderCode = orderCode;
		this.menuCode = menuCode;
		this.quan = quan;
	}

	public int getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}

	public int getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(int menuCode) {
		this.menuCode = menuCode;
	}

	public int getQuan() {
		return quan;
	}

	public void setQuan(int quan) {
		this.quan = quan;
	}

	@Override
	public String toString() {
		return "MenuOrderDTO [orderCode=" + orderCode + ", menuCode=" + menuCode + ", quan=" + quan + "]";
	}

	
	
}
