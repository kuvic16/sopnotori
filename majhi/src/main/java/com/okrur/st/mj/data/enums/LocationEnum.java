package com.okrur.st.mj.data.enums;

public enum LocationEnum {
	COUNTRY("COUNTRY", "COUNTRY"), 
	DIVISION("DIVISION", "DIVISION"), 
	DISTRICT("DISTRICT", "DISTRICT"), 
	UPAZILA("UPAZILA", "UPAZILA"),
	UNION("UNION", "UNION"),
	VILLAGE("VILLAGE", "VILLAGE"),
	REGION("REGION", "REGION"),
	AREA("AREA", "AREA"),
	BRANCH("BRANCH", "BRANCH");

	private String code;
	private String name;

	private LocationEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static LocationEnum getWSStatusEnum(int code) {
		LocationEnum[] types = LocationEnum.values();
		for (LocationEnum e : types) {
			if (e.getCode().equals(code))
				return e;
		}
		return null;
	}
};
