package net.brac.bep.rest.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import net.brac.bep.data.domain.StudentFee;
import net.brac.bep.data.domain.TransactionHistory;
import net.brac.bep.util.DateUtil;
import net.brac.bep.util.IUtil;


/**
 * @File TransactionHistoryModel.java: TransactionHistory Model for rest communication
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Jan 11, 2016
 */
public class TransactionHistoryModel {

	@JsonIgnore
	private String id;
	
	private String feeId;
	private String studentFeeId;
	private String feeTypeUdvId; 
	private String feeCategoryUdvId;
	private String instituteId;
	private String transactionId;
	private String gradeId;
	private String studentId;
	private String year;
	private String month;
	private String amount;
	private String deposited;
	private String totalDeposited;
	private String collectionDate;
	
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getStudentFeeId() {
		return studentFeeId;
	}

	public void setStudentFeeId(String studentFeeId) {
		this.studentFeeId = studentFeeId;
	}
	
	public String getTotalDeposited() {
		return totalDeposited;
	}

	public void setTotalDeposited(String totalDeposited) {
		this.totalDeposited = totalDeposited;
	}

	public TransactionHistoryModel(){}
	
	public TransactionHistoryModel(TransactionHistory entity){
		this.setId(entity.getId());
		this.setFeeId(entity.getFeeId());
		this.setStudentFeeId(entity.getStudentFeeId());
		this.setFeeTypeUdvId(entity.getFeeTypeUdvId());
		this.setFeeCategoryUdvId(entity.getFeeCategoryUdvId());
		this.setInstituteId(entity.getInstituteId());
		this.setTransactionId(entity.getTransactionId());
		this.setGradeId(entity.getGradeId());
		this.setStudentId(entity.getStudentId());
		this.setYear(IUtil.toString(entity.getYear()));
		this.setMonth(IUtil.toString(entity.getMonth()));
		this.setAmount(IUtil.toString(entity.getAmount()));
		this.setDeposited(IUtil.toString(entity.getDeposited()));
		this.setTotalDeposited(IUtil.toString(entity.getTotalDeposited()));
		this.setCollectionDate(DateUtil.getDateString(entity.getCollectionDate(), DateUtil.DATE_FORMAT_YYYY_MM_DD));
	}
	
	public TransactionHistoryModel(StudentFee entity, String payAmount){
		//this.setId(entity.getId());
		this.setFeeId(entity.getFeeId());
		this.setStudentFeeId(entity.getId());
		this.setFeeTypeUdvId(entity.getFeeTypeUdvId());
		this.setFeeCategoryUdvId(entity.getFeeCategoryUdvId());
		this.setInstituteId(entity.getInstituteId());
		//this.setTransactionId(entity.getTransactionId());
		this.setGradeId(entity.getGradeId());
		this.setStudentId(entity.getStudentId());
		this.setYear(IUtil.toString(entity.getYear()));
		this.setMonth(IUtil.toString(entity.getMonth()));
		this.setAmount(IUtil.toString(entity.getAmount()));
		this.setDeposited(payAmount);
		this.setTotalDeposited(IUtil.toString(entity.getDeposited()));
		this.setCollectionDate(DateUtil.getDateString(entity.getCollectionDate(), DateUtil.DATE_FORMAT_YYYY_MM_DD));
		
	}
}
