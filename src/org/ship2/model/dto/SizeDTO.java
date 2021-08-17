package org.ship2.model.dto;

import java.io.Serializable;

public class SizeDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1100587984819679617L;
	
	private int categoryCode;
	private String categoryName;
	
	public SizeDTO() { }
	public SizeDTO(int categoryCode, String categoryName) {
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
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
		return "SizeDTO [categoryCode=" + categoryCode + ", categoryName=" + categoryName + "]";
	}
}
