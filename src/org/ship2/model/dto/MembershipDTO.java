package org.ship2.model.dto;

import java.io.Serializable;

public class MembershipDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5537838068964234317L;
	private int memCode;			// ȸ���ڵ�
	private String memName;			// �̸�
	private String memPhone;		// ��ȭ��ȣ
	private int memPoint;			// ����Ʈ
	private boolean memYn;			// Ż�𿩺�
	
	public MembershipDTO() {	
	}
	
	public MembershipDTO (int memCode, String memName, String memPhone, int memPoint, boolean memYn) {
		this.memCode = memCode;
		this.memName = memName;
		this.memPhone = memPhone;
		this.memPoint = memPoint;
		this.memYn = memYn;
	}

	public int getMemCode() {
		return memCode;
	}

	public void setMemCode(int memCode) {
		this.memCode = memCode;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemPhone() {
		return memPhone;
	}

	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}

	public int getMemPoint() {
		return memPoint;
	}

	public void setMemPoint(int memPoint) {
		this.memPoint = memPoint;
	}

	public boolean isMemYn() {
		return memYn;
	}

	public void setMemYn(boolean memYn) {
		this.memYn = memYn;
	}

	@Override
	public String toString() {
		return "MembershipDTO [memCode=" + memCode + ", memName=" + memName + ", memPhone=" + memPhone + ", memPoint="
				+ memPoint + ", memYn=" + memYn + "]";
	}
	
}