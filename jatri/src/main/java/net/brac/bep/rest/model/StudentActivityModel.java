package net.brac.bep.rest.model;

import java.util.Calendar;
import org.codehaus.jackson.annotate.JsonIgnore;

import net.brac.bep.data.domain.StudentActivity;
import net.brac.bep.util.DateUtil;
import net.brac.bep.util.IUtil;

/**
 * @File StudentActivity.java: Student activity Model for rest communication
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 24, 2015
 */
public class StudentActivityModel {
	
	@JsonIgnore
	private String id;
	private String activityTypeUdvId;
	private String instituteId;
	private String result;
	private String startTime;
	private String endTime;
	private String certificateNumber;
	private String dateOfIssue;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActivityTypeUdvId() {
		return activityTypeUdvId;
	}

	public void setActivityTypeUdvId(String activityTypeUdvId) {
		this.activityTypeUdvId = activityTypeUdvId;
	}

	public String getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(String instituteId) {
		this.instituteId = instituteId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCertificateNumber() {
		return certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}

	public String getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(String dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public StudentActivityModel(){}
	
	public StudentActivityModel(StudentActivity entity){
		this.setId(entity.getId()); 
		this.setInstituteId(entity.getInstituteId());
		this.setResult(entity.getResult());		
		this.setStartTime(DateUtil.getDateString(entity.getStartTime(), DateUtil.DATE_FORMAT_YYYY_MM_DD)); 
		this.setEndTime(DateUtil.getDateString(entity.getEndTime(), DateUtil.DATE_FORMAT_YYYY_MM_DD)); 
		this.setActivityTypeUdvId(entity.getActivityTypeUdvId());
		this.setCertificateNumber(entity.getCertificateNumber());
		this.setDateOfIssue(DateUtil.getDateString(entity.getDateOfIssue(), DateUtil.DATE_FORMAT_YYYY_MM_DD));
	}
}
