package org.ship2.model.dto;

import java.io.Serializable;

public class EmployeeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -729792144064722835L;
	
	private int empCode;
	private String empName;
	private String empPhone;
	private String jobCode;
	private String empId;
	private String empPw;
	
	public EmployeeDTO() {
	}
	
	public EmployeeDTO(int empCode, String empName, String empPhone,
			String jobCode, String empId, String empPw) {
		this.empCode = empCode;
		this.empName = empName;
		this.empPhone = empPhone;
		this.jobCode = jobCode;
		this.empId = empId;
		this.empPw = empPw;
	}

	public int getEmpCode() {
		return empCode;
	}

	public void setEmpCode(int empCode) {
		this.empCode = empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpPw() {
		return empPw;
	}

	public void setEmpPw(String empPw) {
		this.empPw = empPw;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "EmployeeDTO [empCode=" + empCode + ", empName=" + empName
				+ ", empPhone=" + empPhone + ", jobCode="
				+ jobCode + ", empId=" + empId + ", empPw=" + empPw + "]";
	}

}
