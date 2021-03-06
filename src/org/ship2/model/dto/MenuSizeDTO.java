package org.ship2.model.dto;

import java.io.Serializable;

public class MenuSizeDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1100587984819679617L;
	
	private int sizeCode;
	private String sizeName;
	private int sizePrice;
	
	public MenuSizeDTO() { }
	public MenuSizeDTO(int sizeCode, String sizeName, int sizePrice) {
		this.sizeCode = sizeCode;
		this.sizeName = sizeName;
		this.sizePrice = sizePrice;
	}
	public int getSizeCode() {
		return sizeCode;
	}
	public void setSizeCode(int sizeCode) {
		this.sizeCode = sizeCode;
	}
	public String getSizeName() {
		return sizeName;
	}
	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}
	public int getSizePrice() {
		return sizePrice;
	}
	public void setSizePrice(int sizePrice) {
		this.sizePrice = sizePrice;
	}
	
	@Override
	public String toString() {
		return "MenuSizeDTO [sizeCode=" + sizeCode + ", sizeName=" + sizeName + ", sizePrice=" + sizePrice + "]";
	}
}
