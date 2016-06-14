/**
 * 
 */
package net.brac.bep.rest.model;

/**
 * @File Transaction.java
 * @author shaiful islam palash | kuvic16@gmail.com
 * @CreationDate Jun 1, 2016
 */
public class BkashTransaction {
	private String TransId;
	private String Service;
	private String Sender;
	private String Receiver;
	private String Amount;
	private String Reference;
	private String Counter;
	private String TransTime;
	
	public String getTransId() {
		return TransId;
	}
	public void setTransId(String transId) {
		TransId = transId;
	}
	public String getService() {
		return Service;
	}
	public void setService(String service) {
		Service = service;
	}
	public String getSender() {
		return Sender;
	}
	public void setSender(String sender) {
		Sender = sender;
	}
	public String getReceiver() {
		return Receiver;
	}
	public void setReceiver(String receiver) {
		Receiver = receiver;
	}
	public String getAmount() {
		return Amount;
	}
	public void setAmount(String amount) {
		Amount = amount;
	}
	public String getReference() {
		return Reference;
	}
	public void setReference(String reference) {
		Reference = reference;
	}
	public String getCounter() {
		return Counter;
	}
	public void setCounter(String counter) {
		Counter = counter;
	}
	public String getTransTime() {
		return TransTime;
	}
	public void setTransTime(String transTime) {
		TransTime = transTime;
	}
}
