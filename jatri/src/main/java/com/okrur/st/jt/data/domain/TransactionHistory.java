package com.okrur.st.jt.data.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.okrur.st.jt.rest.model.TransactionHistoryModel;
import com.okrur.st.jt.util.DateUtil;
import com.okrur.st.jt.util.IUtil;

/**
 * @File TransactionHistory.java: TransactionHistory database entity
 * @author Shaiful Islam Palash | kuvic16@gmail.com
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Jan 11, 2016
 */
@Entity
@XmlRootElement
@Table(name = "transaction_history")
public class TransactionHistory extends AuditableEntity implements Serializable{

	private static final long serialVersionUID = -5924144505041035322L;
	
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "student_fee_id", length = DomainConstant.ID_LENGTH)
	private String studentFeeId;
	
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "fee_id", length = DomainConstant.ID_LENGTH)
	private String feeId;
	
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "fee_type_udv_id", length = DomainConstant.ID_LENGTH)
	private String feeTypeUdvId; 

	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "fee_category_udv_id", length = DomainConstant.ID_LENGTH)
	private String feeCategoryUdvId;

	
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "institute_id", length = DomainConstant.ID_LENGTH)
	private String instituteId; 
	
	
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "transaction_id", length = DomainConstant.ID_LENGTH)
	private String transactionId; 
	
	
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "grade_id", length = DomainConstant.ID_LENGTH)
	private String gradeId;
	
	
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "student_id", length = DomainConstant.ID_LENGTH)
	private String studentId;
	
	@Column(name = "year")
	private int year;
	
	@Column(name = "month")
	private int month;
		
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "deposited")
	private double deposited;
	
	@Column(name = "total_deposited")
	private double totalDeposited;
	
	@Column(name = "collection_date")
	private Calendar collectionDate;
	
	public String getFeeId() {
		return feeId;
	}

	public void setFeeId(String feeId) {
		this.feeId = feeId;
	}

	public String getFeeTypeUdvId() {
		return feeTypeUdvId;
	}

	public void setFeeTypeUdvId(String feeTypeUdvId) {
		this.feeTypeUdvId = feeTypeUdvId;
	}

	public String getFeeCategoryUdvId() {
		return feeCategoryUdvId;
	}

	public void setFeeCategoryUdvId(String feeCategoryUdvId) {
		this.feeCategoryUdvId = feeCategoryUdvId;
	}

	public String getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(String instituteId) {
		this.instituteId = instituteId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getDeposited() {
		return deposited;
	}

	public void setDeposited(double deposited) {
		this.deposited = deposited;
	}

	public Calendar getCollectionDate() {
		return collectionDate;
	}

	public void setCollectionDate(Calendar collectionDate) {
		this.collectionDate = collectionDate;
	}
	
	public String getStudentFeeId() {
		return studentFeeId;
	}

	public void setStudentFeeId(String studentFeeId) {
		this.studentFeeId = studentFeeId;
	}
	
	public double getTotalDeposited() {
		return totalDeposited;
	}

	public void setTotalDeposited(double totalDeposited) {
		this.totalDeposited = totalDeposited;
	}

	@Override
	public String getObjCode() {
		return "transaction_history";
	}

	public TransactionHistory(){}
	public TransactionHistory setTransactionHistory(TransactionHistoryModel model){
		this.setFeeId(model.getFeeId());
		this.setStudentFeeId(model.getStudentFeeId());
		this.setFeeTypeUdvId(model.getFeeTypeUdvId());
		this.setFeeCategoryUdvId(model.getFeeCategoryUdvId());
		this.setInstituteId(model.getInstituteId());
		this.setTransactionId(model.getTransactionId());
		this.setGradeId(model.getGradeId());
		this.setStudentId(model.getStudentId());
		this.setYear(IUtil.toInteger(model.getYear()));
		this.setMonth(IUtil.toInteger(model.getMonth()));
		this.setAmount(IUtil.toDouble(model.getAmount()));
		this.setDeposited(IUtil.toDouble(model.getDeposited()));
		this.setTotalDeposited(IUtil.toDouble(model.getTotalDeposited()));
		this.setCollectionDate(DateUtil.getCalender(model.getCollectionDate(), DateUtil.DATE_FORMAT_YYYY_MM_DD));
		return this;
	}
	
}
