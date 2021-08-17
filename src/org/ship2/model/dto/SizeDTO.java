package org.ship2.model.dto;

import java.io.Serializable;

public class SizeDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1100587984819679617L;
	
	private int sizeCode;
	private String sizeName;
	
	public SizeDTO() {
	}
	public SizeDTO(int sizeCode, String sizeName) {
		this.sizeCode = sizeCode;
		this.sizeName = sizeName;
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


	@Override
	public String toString() {
		return "SizeDTO [sizeCode=" + sizeCode + ", sizeName=" + sizeName + "]";
	}
}
