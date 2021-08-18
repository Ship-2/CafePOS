package org.ship2.model.dto;

import java.io.Serializable;

public class OrderDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 380410694057740078L;		
		
	private int OrderCode;
	private String OrderDate;
	
	public OrderDTO() {
	}

	public OrderDTO(int OrderCode, String OrderDate) {
		this.OrderCode = OrderCode;
		this.OrderDate = OrderDate;
	}

	public int getOrderCode() {
		return OrderCode;
	}

	public void setJobCode(int OrderCode) {
		this.OrderCode = OrderCode;
	}

	public String getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(String OrderDate) {
		this.OrderDate = OrderDate;
	}

	@Override
	public String toString() {
		return "OrderDTO [OrderCode=" + OrderCode + ", OrderDate=" + OrderDate + "]";
	}
}