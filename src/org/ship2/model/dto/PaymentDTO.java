package org.ship2.model.dto;

import java.io.Serializable;

public class PaymentDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -504893537878465373L;

	private int payCode;
	private String payMethod;
	public PaymentDTO(int payCode, String payMethod) {
		this.payCode = payCode;
		this.payMethod = payMethod;
	}
	public PaymentDTO() {
	}
	public int getPayCode() {
		return payCode;
	}
	public void setPayCode(int payCode) {
		this.payCode = payCode;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	@Override
	public String toString() {
		return "PaymentDTO [payCode=" + payCode + ", payMethod=" + payMethod + "]";
	}
}
