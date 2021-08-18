package org.ship2.model.dto;

import java.io.Serializable;

public class OrderDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 380410694057740078L;		
		
	private int orderCode;
	private String orderDate;
	private int payCode;
	private int memCode;
	
	public OrderDTO() {
	}

	public OrderDTO(int orderCode, String orderDate, int payCode, int memCode) {
		this.orderCode = orderCode;
		this.orderDate = orderDate;
		this.payCode = payCode;
		this.memCode = memCode;
	}

	public int getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public int getPayCode() {
		return payCode;
	}

	public void setPayCode(int payCode) {
		this.payCode = payCode;
	}

	public int getMemCode() {
		return memCode;
	}

	public void setMemCode(int memCode) {
		this.memCode = memCode;
	}

	@Override
	public String toString() {
		return "OrderDTO [orderCode=" + orderCode + ", orderDate=" + orderDate + ", payCode=" + payCode + ", memCode="
				+ memCode + "]";
	}
	
	
}