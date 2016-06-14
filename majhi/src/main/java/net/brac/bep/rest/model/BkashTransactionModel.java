/**
 * 
 */
package net.brac.bep.rest.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import net.brac.bep.data.domain.BkashTransaction;
import net.brac.bep.util.DateUtil;
import net.brac.bep.util.IUtil;

/**
 * @File Transaction.java
 * @author shaiful islam palash | kuvic16@gmail.com
 * @CreationDate Jun 1, 2016
 */
public class BkashTransactionModel {
	@JsonIgnore
	private String id;
	private String pushId;
	private String transId;
	private String service;
	private String sender;
	private String receiver;
	private String amount;
	private String reference;
	private String counter;
	private String transTime;
	private String processed;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPushId() {
		return pushId;
	}
	public void setPushId(String pushId) {
		this.pushId = pushId;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getCounter() {
		return counter;
	}
	public void setCounter(String counter) {
		this.counter = counter;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTransTime() {
		return transTime;
	}
	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}
	public String getProcessed() {
		return processed;
	}
	public void setProcessed(String processed) {
		this.processed = processed;
	}
	
	public BkashTransactionModel(){
		
	}
	public BkashTransactionModel(BkashTransaction entity){
		this.setId(entity.getId());
		this.setPushId(entity.getPushId());
		this.setTransId(entity.getTransId());
		this.setService(entity.getService());
		this.setSender(entity.getSender());
		this.setReceiver(entity.getReceiver());
		this.setAmount(IUtil.toString(entity.getAmount()));
		this.setReference(entity.getReference());
		this.setCounter(entity.getCounter());
		this.setTransTime(DateUtil.getDateString(entity.getTransTime(),DateUtil.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS));
		this.setProcessed(IUtil.toString(entity.isProcessed()));
	}
}
