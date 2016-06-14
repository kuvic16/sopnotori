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
 * @CreationDate Dec 29, 2015
 */
public enum FeeCategoryEnum {
	ONE_TIME("ONE_TIME", "One time"),
	MONTHLY("MONTHLY", "Monthly"),
	YEARLY("YEARLY", "Yearly");
	

	private String code;
	private String name;

	private FeeCategoryEnum(String code, String name) {
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
	
	public static FeeCategoryEnum getCategoryEnum(String code) {
		FeeCategoryEnum[] categories = FeeCategoryEnum.values();
		for (FeeCategoryEnum e : categories) {
			if (e.getCode().equals(code))
				return e;
		}
		return null;
	}

	public static String getCategoryNameFromCode(String code){
		if(StringUtils.isNotBlank(code)){
			FeeCategoryEnum ctgEnum = getCategoryEnum(code);
			if(ctgEnum != null){
				return ctgEnum.getName();
			}
		}
		return "";
	}
	
	public static Object[] categoryList() {
		FeeCategoryEnum[] categories = FeeCategoryEnum.values();
		List<String> categoryList = new ArrayList<>();
		for (FeeCategoryEnum e : categories) {
			categoryList.add(e.name);
		}
		return categoryList.toArray();
	}
}
