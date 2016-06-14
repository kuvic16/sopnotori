package com.okrur.st.mj.rest.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.okrur.st.mj.data.domain.StudentFee;
import com.okrur.st.mj.util.DateUtil;
import com.okrur.st.mj.util.IUtil;


/**
 * @File StudentFeeModel.java: StudentFee Model for rest communication
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Jan 11, 2016
 */
public class StudentFeeModel {
	
	@JsonIgnore
	private String id;
	
	@JsonIgnore
	private String feeId; 
	
	@JsonIgnore
	private String instituteId; 
	
	@JsonIgnore
	private String gradeId;
	
	@JsonIgnore
	private String studentId;
	
	@JsonIgnore
	private int year;
	
	@JsonIgnore
	private String mandatory;
	
	@JsonIgnore
	private String amount;
	
	@JsonIgnore
	private String deposited;
	
	@JsonIgnore
	private String collectionDate;
	
	@JsonIgnore
	private String feeTypeUdvId; 
	
	@JsonIgnore
	private String feeCategoryUdvId;
	
	@JsonIgnore
	private String feeTypeName;
	
	@JsonIgnore
	private String feeCategoryName;
	
	@JsonIgnore
	private String status;
	
	@JsonIgnore
	private int month;
	
	@JsonIgnore
	private String due;
	
	@JsonIgnore
	private String pay;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getMandatory() {
		return mandatory;
	}

	public void setMandatory(String mandatory) {
		this.mandatory = mandatory;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getDeposited() {
		return deposited;
	}

	public void setDeposited(String deposited) {
		this.deposited = deposited;
	}

	public String getCollectionDate() {
		return collectionDate;
	}

	public void setCollectionDate(String collectionDate) {
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

	public String getFeeTypeName() {
		return feeTypeName;
	}

	public void setFeeTypeName(String feeTypeName) {
		this.feeTypeName = feeTypeName;
	}

	public String getFeeCategoryName() {
		return feeCategoryName;
	}

	public void setFeeCategoryName(String feeCategoryName) {
		this.feeCategoryName = feeCategoryName;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getDue() {
		return due;
	}

	public void setDue(String due) {
		this.due = due;
	}
	
	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
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

	public StudentFeeModel(){}
	
	public StudentFeeModel(StudentFee entity){
		this.setId(entity.getId());
		this.setFeeId(entity.getFeeId());
		this.setInstituteId(entity.getInstituteId());
		this.setGradeId(entity.getGradeId());
		this.setStudentId(entity.getStudentId());
		this.setYear(entity.getYear());
		this.setMandatory(String.valueOf(entity.isMandatory()));
		this.setAmount(IUtil.toString(entity.getAmount()));
		this.setDeposited(IUtil.toString(entity.getDeposited()));
		this.setCollectionDate(DateUtil.getDateString(entity.getCollectionDate(), DateUtil.DATE_FORMAT_YYYY_MM_DD));
		this.setFeeTypeUdvId(entity.getFeeTypeUdvId());
		this.setFeeCategoryUdvId(entity.getFeeCategoryUdvId());
		this.setMonth(entity.getMonth());
		this.setDue(String.valueOf(entity.getAmount() - entity.getDeposited()));
		this.setPay(this.getDue());
	}
	
	public StudentFeeModel(StudentFee entity, String feeTypeName, String feeCategoryName){
		this(entity);
		this.setFeeTypeName(feeTypeName);
		this.setFeeCategoryName(feeCategoryName);
	}
}
