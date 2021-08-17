package org.ship2.model.dto;

import java.io.Serializable;

public class HrDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2157835203959533624L;
	private String hrDate;		// ��¥
	private int empCode;		// �����ڵ�
	private String clockIn;		// ��ٽð�
	private String clockOut;	// ��ٽð�
	
	public HrDTO() {	
	}
	
	public HrDTO(String hrDate, int empCode, String clockIn, String clockOut) {
		this.hrDate = hrDate;
		this.empCode = empCode;
		this.clockIn = clockIn;
		this.clockOut = clockOut;
	}
	

	public String getHrDate() {
		return hrDate;
	}

	public void setHrDate(String hrDate) {
		this.hrDate = hrDate;
	}

	public int getEmpCode() {
		return empCode;
	}

	public void setEmpCode(int empCode) {
		this.empCode = empCode;
	}

	public String getClockIn() {
		return clockIn;
	}

	public void setClockIn(String clockIn) {
		this.clockIn = clockIn;
	}

	public String getClockOut() {
		return clockOut;
	}

	public void setClockOut(String clockOut) {
		this.clockOut = clockOut;
	}

	@Override
	public String toString() {
		return "HrDTO [hrDate=" + hrDate + ", empCode=" + empCode + ", clockIn=" + clockIn + ", clockOut=" + clockOut
				+ "]";
	}
	

}