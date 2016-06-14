package net.brac.bep.rest.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import net.brac.bep.data.domain.StudentFee;
import net.brac.bep.data.domain.Transaction;
import net.brac.bep.util.DateUtil;


/**
 * @File TransactionModel.java: TransactionModel for rest communication
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Jan 11, 2016
 */
public class TransactionModel {
	
	@JsonIgnore
	private String id;
	
	private String studentId;
	private String collectionDate;
	private String remarks; 
	private String amount;
	
	@JsonIgnore
	private String instituteName;
		
	@JsonIgnore
	private String grade;
	

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	
	public String getCollectionDate() {
		return collectionDate;
	}

	public void setCollectionDate(String collectionDate) {
		this.collectionDate = collectionDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public TransactionModel(){}
	
	public TransactionModel(Transaction entity){
		this.setId(entity.getId());
		this.setStudentId(entity.getStudentId());
		this.setCollectionDate(DateUtil.getDateString(entity.getCollectionDate(), DateUtil.DATE_FORMAT_YYYY_MM_DD));
		this.setRemarks(entity.getRemarks());
		this.setAmount(String.valueOf(entity.getAmount()));
		
		
	}
	
	public TransactionModel(StudentFee entity){
		//this.setId(entity.getId());
		this.setStudentId(entity.getStudentId());
		this.setCollectionDate(DateUtil.getDateString(entity.getCollectionDate(), DateUtil.DATE_FORMAT_YYYY_MM_DD));
		//this.setRemarks("Test Data");
		//this.setAmount(Double.valueOf(model.getAmount()));
		
	}
}
