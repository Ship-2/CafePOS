package org.ship2.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class HrDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2157835203959533624L;
	private Date hrDate;		// 날짜
	private int empCode;		// 직원코드
	private String clockIn;		// 출근시간
	private String clockOut;	// 퇴근시간
	
	public HrDTO() {	
	}
	public HrDTO(Date hrDate, int empCode, String clockIn, String clockOut) {
		this.hrDate = hrDate;
		this.empCode = empCode;
		this.clockIn = clockIn;
		this.clockOut = clockOut;
	}
	
	public Date getHrDate() {
		return hrDate;
	}
	public void setHrDate(Date hrDate) {
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