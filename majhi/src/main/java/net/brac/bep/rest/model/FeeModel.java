package net.brac.bep.rest.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import net.brac.bep.data.domain.Fee;
import net.brac.bep.util.IUtil;


/**
 * @File FeeModel.java: FeeModel for rest communication
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 25, 2015
 */
public class FeeModel {
	
	@JsonIgnore
	private String id;
	private String feeTypeUdvId; 
	private String feeCategoryUdvId;
	private String instituteId; 
	private String gradeId;
	private String year;
	private String mandatory;
	private String amount;
	@JsonIgnore
	private String feeTypeName;
	@JsonIgnore
	private String feeCategoryName;
	private String code;
	
	@JsonIgnore
	private String locationHierarchy;
	
	@JsonIgnore
	private String locationId;
	
	@JsonIgnore
	private String locationTypeUdvId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String isMandatory() {
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMandatory() {
		return mandatory;
	}

	public String getLocationHierarchy() {
		return locationHierarchy;
	}

	public void setLocationHierarchy(String locationHierarchy) {
		this.locationHierarchy = locationHierarchy;
	}
	
	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	
	public String getLocationTypeUdvId() {
		return locationTypeUdvId;
	}

	public void setLocationTypeUdvId(String locationTypeUdvId) {
		this.locationTypeUdvId = locationTypeUdvId;
	}

	public FeeModel(){}
	
	public FeeModel(Fee entity){
		this.setId(entity.getId());
		this.setFeeTypeUdvId(entity.getFeeTypeUdvId());
		this.setFeeCategoryUdvId(entity.getFeeCategoryUdvId());
		this.setInstituteId(entity.getInstituteId());
		this.setGradeId(entity.getGradeId());
		this.setYear(IUtil.toString(entity.getYear()));
		this.setMandatory(String.valueOf(entity.isMandatory()));
		this.setAmount(IUtil.toString(entity.getAmount()));
		this.setCode(entity.getCode());
	}
	
	public FeeModel(Fee entity, String feeTypeName, String feeCategoryName){
		this(entity);
		this.setFeeTypeName(feeTypeName);
		this.setFeeCategoryName(feeCategoryName);
	}
}
