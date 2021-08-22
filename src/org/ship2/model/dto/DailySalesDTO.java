package org.ship2.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class DailySalesDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -630603213846258810L;
	
	private Date salesDate;
	private int sales;
	private int refund;
	private int totalSales;
	
	public DailySalesDTO() { }
	public DailySalesDTO(Date salesDate, int sales, int refund, int totalSales) {
		this.salesDate = salesDate;
		this.sales = sales;
		this.refund = refund;
		this.totalSales = totalSales;
	}
	public Date getSalesDate() {
		return salesDate;
	}
	public void setSalesDate(Date salesDate) {
		this.salesDate = salesDate;
	}
	public int getSales() {
		return sales;
	}
	public void setSales(int sales) {
		this.sales = sales;
	}
	public int getRefund() {
		return refund;
	}
	public void setRefund(int refund) {
		this.refund = refund;
	}
	public int getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(int totalSales) {
		this.totalSales = totalSales;
	}
	
	@Override
	public String toString() {
		return "DailySalesDTO [salesDate=" + salesDate + ", sales=" + sales + ", refund=" + refund + ", totalSales="
				+ totalSales + "]";
	}
}
