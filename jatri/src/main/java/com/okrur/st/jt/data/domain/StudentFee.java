package com.okrur.st.jt.data.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.okrur.st.jt.rest.model.StudentFeeModel;
import com.okrur.st.jt.util.DateUtil;
import com.okrur.st.jt.util.IUtil;

/**
 * @File StudentFee.java: StudentFee database entity
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 25, 2015
 */
@Entity
@XmlRootElement
@Table(name = "student_fee")
public class StudentFee extends AuditableEntity implements Serializable{

	private static final long serialVersionUID = -5924144505041035322L;
	
	
	@NotNull
	@Size(min = 1, max = DomainConstant.ID_LENGTH)
	@Column(name = "fee_id", nullable = false, length = DomainConstant.ID_LENGTH)
	private String feeId;
	
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "fee_type_udv_id", nullable = false, length = DomainConstant.ID_LENGTH)
	private String feeTypeUdvId; 

	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "fee_category_udv_id", nullable = false, length = DomainConstant.ID_LENGTH)
	private String feeCategoryUdvId;

	@NotNull
	@Size(min = 1, max = DomainConstant.ID_LENGTH)
	@Column(name = "institute_id", nullable = false, length = DomainConstant.ID_LENGTH)
	private String instituteId; 
	
	@NotNull
	@Size(min = 1, max = DomainConstant.ID_LENGTH)
	@Column(name = "grade_id", nullable = false, length = DomainConstant.ID_LENGTH)
	private String gradeId;
	
	@NotNull
	@Size(min = 1, max = DomainConstant.ID_LENGTH)
	@Column(name = "student_id", nullable = false, length = DomainConstant.ID_LENGTH)
	private String studentId;
	
	
	@Column(name = "year")
	private int year= -1;
	
	@Column(name = "month")
	private int month= -1;
	
	@Column(name = "mandatory")
	private boolean mandatory;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "deposited")
	private double deposited;
	
	@Column(name = "collection_date")
	private Calendar collectionDate;
	
	
	public String getFeeId() {
		return feeId;
	}

	public void setFeeId(String feeId) {
		this.feeId = feeId;
	}

	public String getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(String instituteId) {
		this.instituteId = instituteId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeID(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentID(String studentId) {
		this.studentId = studentId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
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
	
	public int getMonth() {
		return month;
	}

	@Override
	public String getObjCode() {
		return "student_fee";
	}

	public StudentFee(){}
	public StudentFee setStudentFee(StudentFeeModel model){
		//this.setId(model.getId());
		this.setFeeId(model.getFeeId());
		this.setInstituteId(model.getInstituteId());
		this.setGradeId(model.getGradeId());
		this.setStudentID(model.getStudentId());
		this.setYear(model.getYear());
		this.setMandatory(Boolean.parseBoolean(model.getMandatory()));
		this.setAmount(IUtil.toDouble(model.getAmount()));
		this.setFeeTypeUdvId(model.getFeeTypeUdvId());
		this.setFeeCategoryUdvId(model.getFeeCategoryUdvId());
		this.setMonth(model.getMonth());
		if(IUtil.toDouble(model.getPay())> 0){
			this.setCollectionDate(DateUtil.getTodayCalender(DateUtil.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS));
		}
		this.setDeposited(IUtil.toDouble(model.getDeposited()) + IUtil.toDouble(model.getPay()));
		return this;
	}	
}
