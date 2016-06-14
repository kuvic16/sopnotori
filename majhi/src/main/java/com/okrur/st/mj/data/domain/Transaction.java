package com.okrur.st.mj.data.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.okrur.st.mj.rest.model.StudentFeeModel;
import com.okrur.st.mj.rest.model.TransactionModel;
import com.okrur.st.mj.util.DateUtil;

/**
 * @File Transaction.java: Transaction database entity
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Jan 11, 2016
 */
@Entity
@XmlRootElement
@Table(name = "transaction")
public class Transaction extends AuditableEntity implements Serializable{

	private static final long serialVersionUID = -5924144505041035322L;
	
		
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "student_id", length = DomainConstant.ID_LENGTH)
	private String studentId;
	
	@Column(name = "collection_date")
	private Calendar collectionDate;
	
	@Size(min = 0, max = DomainConstant.DESCRIPTION_LENGTH)
	@Column(name = "remarks", length = DomainConstant.DESCRIPTION_LENGTH)
	private String remarks; 

	
	@Column(name = "amount")
	private Double amount;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public Calendar getCollectionDate() {
		return collectionDate;
	}

	public void setCollectionDate(Calendar collectionDate) {
		this.collectionDate = collectionDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String getObjCode() {
		return "transaction";
	}

	public Transaction(){}
	public Transaction setTransaction(TransactionModel model){
		
		this.setStudentId(model.getStudentId());
		this.setCollectionDate(DateUtil.getCalender(model.getCollectionDate(), DateUtil.DATE_FORMAT_YYYY_MM_DD));
		this.setRemarks(model.getRemarks());
		this.setAmount(Double.valueOf(model.getAmount()));
		return this;
		
	}	
	
	public Transaction setTransaction(StudentFeeModel model){
		this.setStudentId(model.getStudentId());
		this.setCollectionDate(DateUtil.getCalender(model.getCollectionDate(), DateUtil.DATE_FORMAT_YYYY_MM_DD));
		//this.setRemarks("Test Data");
		//this.setAmount(Double.valueOf(model.getAmount()));
		return this;
		
	}
}
