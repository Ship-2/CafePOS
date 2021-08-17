package org.ship2.model.dto;

import java.io.Serializable;

public class PaymentDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -504893537878465373L;

	private int pay_code;
	private String pay_method;
	public PaymentDTO() {
	}
	public PaymentDTO(int pay_code, String pay_method) {
		this.pay_code = pay_code;
		this.pay_method = pay_method;
	}
	public int getPay_code() {
		return pay_code;
	}
	public void setPay_code(int pay_code) {
		this.pay_code = pay_code;
	}
	public String getPay_method() {
		return pay_method;
	}
	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}
	@Override
	public String toString() {
		return "PaymentDTO [pay_code=" + pay_code + ", pay_method=" + pay_method + "]";
	}
}
