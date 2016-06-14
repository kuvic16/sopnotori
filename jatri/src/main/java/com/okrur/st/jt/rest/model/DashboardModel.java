package com.okrur.st.jt.rest.model;

import java.io.Serializable;

public class DashboardModel implements Serializable {
	private static final long serialVersionUID = -8525635887333623127L;
	
	private int totalInstitute;
	private int totalStudent;
	private int totalFemaleStudent;
	private int totalDroppedStudent;
	
	
	public int getTotalInstitute() {
		return totalInstitute;
	}
	public void setTotalInstitute(int totalInstitute) {
		this.totalInstitute = totalInstitute;
	}
	public int getTotalStudent() {
		return totalStudent;
	}
	public void setTotalStudent(int totalStudent) {
		this.totalStudent = totalStudent;
	}
	public int getTotalFemaleStudent() {
		return totalFemaleStudent;
	}
	public void setTotalFemaleStudent(int totalFemaleStudent) {
		this.totalFemaleStudent = totalFemaleStudent;
	}
	public int getTotalDroppedStudent() {
		return totalDroppedStudent;
	}
	public void setTotalDroppedStudent(int totalDroppedStudent) {
		this.totalDroppedStudent = totalDroppedStudent;
	}
}
