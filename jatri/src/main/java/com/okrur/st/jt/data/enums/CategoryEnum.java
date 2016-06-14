/**
 * 
 */
package com.okrur.st.jt.data.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @File CategoryEnum.java
 * @author shaiful islam palash | kuvic16@gmail.com
 * @CreationDate Dec 9, 2015
 */
public enum CategoryEnum {
	DESIGNATION("DESIGNATION", "Designation"),
	USER_CATEGORY("USER_CATEGORY", "User category"),
	LOCATION_TYPE("LOCATION_TYPE", "Location type"),
	INSTITUTE_TYPE("INSTITUTE_TYPE", "Institute type"),
	DONOR_TYPE("DONOR_TYPE", "Donor type"),
	FEE_TYPE("FEE_TYPE", "Fee type"),
	FEE_CATEGORY("FEE_CATEGORY","Fee category"),
	STUDENT_TYPE("STUDENT_TYPE","Student type"),
	CSN_TYPE("CSN_TYPE","Csn type"),
	ETHNICITY_COMMUNITY("ETHNICITY_COMMUNITY","Ethnicity community type"),
	STUDENT_ACTIVITY_TYPE("STUDENT_ACTIVITY_TYPE", "Student activity type"),
	STAFF_GRADE("STAFF_GRADE", "Staff grade"),
	PROJECT_CODE("PROJECT_CODE","Project code"),
	DIALECT_SPOKEN("DIALECT_SPOKEN","Dialect spoken"),
	RELIGION("RELIGION","Religion"),
	HEAD_OF_ACCOUNTS("HEAD_OF_ACCOUNTS","Heads of Accounts"),
	EXPENDITURE_TYPE("EXPENDITURE_TYPE","Expenditure type"),
	INCOME_INDICATOR("INCOME_INDICATOR","Income indicator");

	private String code;
	private String name;

	private CategoryEnum(String code, String name) {
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
	
	public static CategoryEnum getCategoryEnum(String code) {
		CategoryEnum[] categories = CategoryEnum.values();
		for (CategoryEnum e : categories) {
			if (e.getCode().equals(code))
				return e;
		}
		return null;
	}

	public static String getCategoryNameFromCode(String code){
		if(StringUtils.isNotBlank(code)){
			CategoryEnum ctgEnum = getCategoryEnum(code);
			if(ctgEnum != null){
				return ctgEnum.getName();
			}
		}
		return "";
	}
	
	public static Object[] categoryList() {
		CategoryEnum[] categories = CategoryEnum.values();
		List<String> categoryList = new ArrayList<>();
		for (CategoryEnum e : categories) {
			categoryList.add(e.name);
		}
		return categoryList.toArray();
	}
}
