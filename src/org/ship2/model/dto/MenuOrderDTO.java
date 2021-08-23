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
	private int sizeCode;
	
	public MenuOrderDTO() {
	}
	public MenuOrderDTO(int orderCode, int menuCode, int quan, int sizeCode) {
		this.orderCode = orderCode;
		this.menuCode = menuCode;
		this.quan = quan;
		this.sizeCode = sizeCode;
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
	public int getSizeCode() {
		return sizeCode;
	}
	public void setSizeCode(int sizeCode) {
		this.sizeCode = sizeCode;
	}

	
	@Override
	public String toString() {
		return "MenuOrderDTO [orderCode=" + orderCode + ", menuCode=" + menuCode + ", quan=" + quan + ", sizeCode="
				+ sizeCode + "]";
	}
}
