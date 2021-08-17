package org.ship2.model.dto;

import java.io.Serializable;

public class SizeDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1100587984819679617L;
	
	private String catCode;
	private String catName;
	
	public SizeDTO() {}
	public SizeDTO(String catCode, String catName) {
		this.catCode = catCode;
		this.catName = catName;
	}
	
	public String getCatCode() {
		return catCode;
	}
	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "SizeDTO [catCode=" + catCode + ", catName=" + catName + "]";
	}
}
