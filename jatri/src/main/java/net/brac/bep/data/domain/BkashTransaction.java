package net.brac.bep.data.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import net.brac.bep.rest.model.BkashTransactionModel;
import net.brac.bep.util.DateUtil;
import net.brac.bep.util.IUtil;

/**
 * @File BkashTransaction.java: BkashTransaction database entity
 * @author Md. Shaiful Islam | kuvic16@gmail.com
 * @CreationDate June 01, 2016
 */

@Entity
@XmlRootElement
@Table(name = "bkash_transaction")
public class BkashTransaction extends AuditableEntity implements Serializable{

	private static final long serialVersionUID = -5924144505041035322L;
	
	@NotNull
	@Size(min = 1, max = 36)
	@Column(name = "push_id", nullable = false, length = 36)
	private String pushId;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "trans_id", nullable = false, length = 50)
	private String transId;
	
	@NotNull
	@Size(min = 1, max = 128)
	@Column(name = "service", nullable = false, length = 150)
	private String service;
	
	@NotNull
	@Size(min = 1, max = 14)
	@Column(name = "sender", nullable = false, length = 14)
	private String sender;
	
	@NotNull
	@Size(min = 1, max = 14)
	@Column(name = "receiver", nullable = false, length = 14)
	private String receiver;
	
	@Column(name = "amount")
	private double amount;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "reference", nullable = false, length = 50)
	private String reference;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "counter", nullable = false, length = 50)
	private String counter;
	
	@Column(name="trans_time")
	private Calendar transTime;
	
	@Column(name="processed")
	private boolean processed;
	
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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

	public Calendar getTransTime() {
		return transTime;
	}

	public void setTransTime(Calendar transTime) {
		this.transTime = transTime;
	}
	
	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	@Override
	public String getObjCode() {
		return "bkash_transaction";
	}
	
	public BkashTransaction(){}
	
	public BkashTransaction setBkashTransaction(BkashTransactionModel model){
		this.setId(model.getId());
		this.setPushId(model.getPushId());
		this.setTransId(model.getTransId());
		this.setService(model.getService());
		this.setSender(model.getSender());
		this.setReceiver(model.getReceiver());
		this.setAmount(IUtil.toDouble(model.getAmount()));
		this.setReference(model.getReference());
		this.setCounter(model.getCounter());
		this.setTransTime(DateUtil.getCalender(model.getTransTime(), DateUtil.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS));
		this.setProcessed(IUtil.toBoolean(model.getProcessed()));
		return this;
	}
}
