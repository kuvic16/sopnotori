/**
 * 
 */
package com.okrur.st.mj.rest.model;

import java.util.List;

/**
 * @File BkashModel.java
 * @author shaiful islam palash | kuvic16@gmail.com
 * @CreationDate Jun 1, 2016
 */
public class BkashModel {
	private String PushId;
	private String SecurityToken;
	private List<BkashTransaction> transactions;
	
	public String getPushId() {
		return PushId;
	}
	public void setPushId(String pushId) {
		PushId = pushId;
	}
	public String getSecurityToken() {
		return SecurityToken;
	}
	public void setSecurityToken(String securityToken) {
		SecurityToken = securityToken;
	}
	public List<BkashTransaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<BkashTransaction> transactions) {
		this.transactions = transactions;
	}
}
