package net.brac.bep.rest.model;

import java.io.Serializable;

public class BaseModel implements Serializable {
	private static final long serialVersionUID = -8525635887333623127L;

	private int responseCode;

	private String responseMessage;

	private String createdByID;

	private String createdByName;

	private String createdByDate;

	private String lastModifiedByID;

	private String lastModifiedByName;

	private String lastModifiedByDate;

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getCreatedByID() {
		return createdByID;
	}

	public void setCreatedByID(String createdByID) {
		this.createdByID = createdByID;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public String getCreatedByDate() {
		return createdByDate;
	}

	public void setCreatedByDate(String createdByDate) {
		this.createdByDate = createdByDate;
	}

	public String getLastModifiedByID() {
		return lastModifiedByID;
	}

	public void setLastModifiedByID(String lastModifiedByID) {
		this.lastModifiedByID = lastModifiedByID;
	}

	public String getLastModifiedByName() {
		return lastModifiedByName;
	}

	public void setLastModifiedByName(String lastModifiedByName) {
		this.lastModifiedByName = lastModifiedByName;
	}

	public String getLastModifiedByDate() {
		return lastModifiedByDate;
	}

	public void setLastModifiedByDate(String lastModifiedByDate) {
		this.lastModifiedByDate = lastModifiedByDate;
	}
}
