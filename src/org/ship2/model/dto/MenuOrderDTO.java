package org.ship2.model.dto;

import java.io.Serializable;

public class MenuOrderDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7460587992217626524L;
	
	private int quan;

	public MenuOrderDTO() {
	}

	public MenuOrderDTO(int quan) {
		this.quan = quan;
	}

	public int getQuan() {
		return quan;
	}

	public void setQuan(int quan) {
		this.quan = quan;
	}

	@Override
	public String toString() {
		return "MenuOrderDTO [quan=" + quan + "]";
	}
	
	
}
