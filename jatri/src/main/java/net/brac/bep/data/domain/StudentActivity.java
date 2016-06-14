package net.brac.bep.data.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import net.brac.bep.rest.model.StudentActivityModel;
import net.brac.bep.util.DateUtil;

/**
 * @File StudentActivity.java: StudentActivity database entity
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 24, 2015
 */
@Entity
@XmlRootElement
@Table(name = "student_activity")
public class StudentActivity extends AuditableEntity implements Serializable{

	private static final long serialVersionUID = -5924144505041035322L;

	@NotNull
	@Size(min = 1, max = DomainConstant.ID_LENGTH)
	@Column(name = "activity_type_udv_id", nullable = false, length = DomainConstant.ID_LENGTH)
	private String activityTypeUdvId;
	
	@NotNull
	@Size(min = 1, max = DomainConstant.ID_LENGTH)
	@Column(name = "institute_id", nullable = false, length = DomainConstant.ID_LENGTH)
	private String instituteId;
	
	
	@Size(min = 0, max = 20)
	@Column(name = "result", length = 20)
	private String result;
	
	@Column(name="start_time")
	private Calendar startTime;
	
	@Column(name="end_time")
	private Calendar endTime;
	
	@Size(min=0, max=100)
	@Column(name="certificate_number", length=DomainConstant.ID_LENGTH)
	private String certificateNumber;
	
	@Column(name="date_of_issue")
	private Calendar dateOfIssue;
	
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

	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}
	
	public String getCertificateNumber() {
		return certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}

	public Calendar getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(Calendar dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	@Override
	public String getObjCode() {
		return "student_activity";
	}
	
	public StudentActivity() {}

	public StudentActivity(StudentActivityModel model) {
		this.setId(model.getId());
		this.setInstituteId(model.getInstituteId());
		this.setResult(model.getResult());
		this.setStartTime(DateUtil.getCalender(model.getStartTime(), DateUtil.DATE_FORMAT_YYYY_MM_DD));
		this.setEndTime(DateUtil.getCalender(model.getEndTime(), DateUtil.DATE_FORMAT_YYYY_MM_DD)); 
		this.setActivityTypeUdvId(model.getActivityTypeUdvId());
		this.setCertificateNumber(model.getCertificateNumber());
		this.setDateOfIssue(DateUtil.getCalender(model.getDateOfIssue(), DateUtil.DATE_FORMAT_YYYY_MM_DD));
	}

}
