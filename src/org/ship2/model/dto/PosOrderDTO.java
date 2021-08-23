package org.ship2.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class PosOrderDTO implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = -895951949201820908L;

	private int orderCode;
	private Date orderDate;
	private int payCode;
	private int memCode;
	
	public PosOrderDTO() {
	}
	public PosOrderDTO(int orderCode, Date orderDate, int payCode, int memCode) {
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
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "PosOrderDTO [orderCode=" + orderCode + ", orderDate=" + orderDate + ", payCode=" + payCode
				+ ", memCode=" + memCode + "]";
	}
}
