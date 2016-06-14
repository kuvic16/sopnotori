package com.okrur.st.jt.data.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.okrur.st.jt.rest.model.FeeModel;
import com.okrur.st.jt.util.IUtil;

/**
 * @File Fee.java: Fee database entity
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 25, 2015
 */
@Entity
@XmlRootElement
@Table(name = "fee")
public class Fee extends AuditableEntity implements Serializable{

	private static final long serialVersionUID = -5924144505041035322L;
	
	@NotNull
	@Size(min = 1, max = DomainConstant.ID_LENGTH)
	@Column(name = "fee_type_udv_id", nullable = false, length = DomainConstant.ID_LENGTH)
	private String feeTypeUdvId; 

	@NotNull
	@Size(min = 1, max = DomainConstant.ID_LENGTH)
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
	
	@Size(min=0, max=100)
	@Column(name="code", length=DomainConstant.ID_LENGTH)
	private String code;
	
	@Column(name = "year")
	private int year;
	
	@Column(name = "mandatory")
	private boolean mandatory;
	
	@Column(name = "amount")
	private double amount;
	
	
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

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
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
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getObjCode() {
		return "Fee";
	}
	
	public Fee(){}
	public Fee setFee(FeeModel model){
		this.setId(model.getId());
		this.setFeeTypeUdvId(model.getFeeTypeUdvId());
		this.setFeeCategoryUdvId(model.getFeeCategoryUdvId());
		this.setInstituteId(model.getInstituteId());
		this.setGradeId(model.getGradeId());
		this.setYear(IUtil.toInteger(model.getYear()));
		this.setMandatory(Boolean.parseBoolean(model.getMandatory()));
		this.setAmount(IUtil.toDouble(model.getAmount()));
		this.setCode(model.getCode());
		return this;
	}	
}
