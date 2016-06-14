/**
 * 
 */
package com.okrur.st.jt.data.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @File RightsEnum.java
 * @author shaiful islam palash | kuvic16@gmail.com
 * @CreationDate Jan 24, 2016
 */
public enum RightsEnum {
	
	ADD("ADD", "Add"),
	UPDATE("UPDATE", "Update"),
	DELETE("DELETE", "Delete"),
	
	DASHBOARD("DASHBOARD", "Dashboard"),
	
	USER_MANAGEMENT("USER_MANAGEMENT", "User management"),
	USER_MANAGEMENT_RIGHT("USER_MANAGEMENT_RIGHT", "Right"),
	USER_MANAGEMENT_ROLE("USER_MANAGEMENT_ROLE", "Role"),
	USER_MANAGEMENT_USER("USER_MANAGEMENT_USER", "User"),
	USER_MANAGEMENT_USER_DEFINED("USER_MANAGEMENT_USER_DEFINED", "User defined"),
	
	ORGANIZATION_MANAGEMENT("ORGANIZATION_MANAGEMENT", "Organization management"),
	ORGANIZATION_MANAGEMENT_ORGANIZATION("ORGANIZATION_MANAGEMENT_ORGANIZATION", "Organization"),
	ORGANIZATION_MANAGEMENT_DONOR("ORGANIZATION_MANAGEMENT_DONOR", "Donor"),
	
	EDUCATION_TYPE_MANAGEMENT("EDUCATION_TYPE_MANAGEMENT", "Education type management"),
	EDUCATION_TYPE_MANAGEMENT_EDUCATION_TYPE("EDUCATION_TYPE_MANAGEMENT_EDUCATION_TYPE", "Education type"),
	EDUCATION_TYPE_MANAGEMENT_GRADING_POINT("EDUCATION_TYPE_MANAGEMENT_GRADING_POINT", "Grading point"),
	EDUCATION_TYPE_MANAGEMENT_GRADE("EDUCATION_TYPE_MANAGEMENT_GRADE", "Grade"),
	
	INSTITUTE_MANAGEMENT("INSTITUTE_MANAGEMENT", "Institute management"),
	INSTITUTE_MANAGEMENT_INSTITUTE("INSTITUTE_MANAGEMENT_INSTITUTE", "Institute"),
	INSTITUTE_MANAGEMENT_TEACHER("INSTITUTE_MANAGEMENT_TEACHER", "Teacher"),
	INSTITUTE_MANAGEMENT_STUDENT("INSTITUTE_MANAGEMENT_STUDENT", "Student"),
	INSTITUTE_MANAGEMENT_STUDENT_ACTIVITY("INSTITUTE_MANAGEMENT_STUDENT_ACTIVITY", "Student activity"),
	
	FEES_MANAGEMENT("FEES_MANAGEMENT", "Fees management"),
	FEES_MANAGEMENT_FEE_SETUP("FEES_MANAGEMENT_FEE_SETUP", "Fee setup"),
	FEES_MANAGEMENT_FEE_COLLECTION("FEES_MANAGEMENT_FEE_COLLECTION", "Fee collection"),
	FEES_MANAGEMENT_TRANSACTION_HISTORY("FEES_MANAGEMENT_TRANSACTION_HISTORY", "Transaction history"),
	FINANCIAL_MANAGEMENT("FINANCIAL_MANAGEMENT", "Financial management"),
	FINANCIAL_MANAGEMENT_EXPENDITURE("FINANCIAL_MANAGEMENT_EXPENDITURE", "Expenditure"),
	
	REPORTS_MANAGEMENT("REPORTS_MANAGEMENT", "Reports management"),
	REPORTS_MANAGEMENT_STUDENT_REPORT("REPORTS_MANAGEMENT_STUDENT_REPORT", "Student report"),
	REPORTS_MANAGEMENT_TEACHER_REPORT("REPORTS_MANAGEMENT_TEACHER_REPORT", "Teacher report"),
	REPORTS_MANAGEMENT_FINANCIAL_REPORT("REPORTS_MANAGEMENT_FINANCIAL_REPORT", "Financial report"),
	REPORTS_MANAGEMENT_GRADUATION_COMPLETE_REPORT("REPORTS_MANAGEMENT_GRADUATION_COMPLETE_REPORT", "Graduation complete report"),
	
	INCOME("INCOME", "Income");
	

	private String code;
	private String name;

	private RightsEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public static RightsEnum getRightsEnum(String code) {
		RightsEnum[] rights = RightsEnum.values();
		for (RightsEnum e : rights) {
			if (e.getCode().equals(code))
				return e;
		}
		return null;
	}

	public static String getRightsNameFromCode(String code){
		if(StringUtils.isNotBlank(code)){
			RightsEnum rightEnum = getRightsEnum(code);
			if(rightEnum != null){
				return rightEnum.getName();
			}
		}
		return "";
	}
	
	public static Object[] rightList() {
		RightsEnum[] rights = RightsEnum.values();
		List<String> rightList = new ArrayList<>();
		for (RightsEnum e : rights) {
			rightList.add(e.name);
		}
		return rightList.toArray();
	}
}
