package org.ship2.model.dto;

import java.io.Serializable;

public class JobDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4440258371877288175L;

	private int jobCode;
	private String jobName;
	
	public JobDTO() {
	}

	public JobDTO(int jobCode, String jobName) {
		this.jobCode = jobCode;
		this.jobName = jobName;
	}

	public int getJobCode() {
		return jobCode;
	}

	public void setJobCode(int jobCode) {
		this.jobCode = jobCode;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	@Override
	public String toString() {
		return "JobDTO [jobCode=" + jobCode + ", jobName=" + jobName + "]";
	}
	
}
