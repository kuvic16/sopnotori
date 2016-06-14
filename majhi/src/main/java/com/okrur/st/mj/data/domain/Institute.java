package com.okrur.st.mj.data.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.okrur.st.mj.rest.model.InstituteModel;
import com.okrur.st.mj.util.DateUtil;
import com.okrur.st.mj.util.IUtil;

/**
 * @File School.java: School database entity
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 23, 2015
 */
@Entity
@XmlRootElement
@Table(name = "institute")
public class Institute extends AuditableEntity implements Serializable{

	private static final long serialVersionUID = -5924144505041035322L;

	@Size(min = 0, max = 150)
	@Column(name = "code", length = 150)
	private String code;
	
	@NotNull
	@Size(min = 1, max = 150)
	@Column(name = "name", nullable = false, length = 150)
	private String name;

	@Size(min = 0, max = 500)
	@Column(name = "nearest_public_school", length = 500)
	private String nearestPublicSchool;
	
	@Size(min = 0, max = 10)
	@Column(name = "distance_from_nearest_public_school", length = 10)
	private String distanceFromNearestPublicSchool;
	
	
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "location_id", length = DomainConstant.ID_LENGTH)
	private String locationId;
	
	@Size(min = 0, max = DomainConstant.LOCATION_HIERARCHY_ID_LENGTH)
	@Column(name = "location_hierarchy", length = DomainConstant.LOCATION_HIERARCHY_ID_LENGTH)
	private String locationHierarchy;
	
	@Column(name = "toilet_availability")
	private boolean toiletAvailability;
	
	@Column(name = "electricity")
	private boolean electricity; 
	
	@Column(name = "school_status")
	private boolean schoolStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "opening_date")
	private Calendar openingDate;
	
	@Size(min = 0, max = 50)
	@Column(name = "session_start", length = 50)
	private String sessionStart;
	
	@Size(min = 0, max = 50)
	@Column(name = "session_end", length = 50)
	private String sessionEnd;
	
	@Size(min = 0, max = 10)
	@Column(name = "distance_from_school_to_branch_office", length = 10)
	private String distanceFromSchoolToBranchOffice; 
	
	@Size(min = 0, max = 20)
	@Column(name = "latitude", length=20)
	private String latitude; 
	
	@Size(min = 0, max = 20)
	@Column(name = "longitude",length=20)
	private String longitude; 
	
	@Column(name = "number_of_shift")
	private int numberOfShift;
	
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "location_type_udv_id", length = DomainConstant.ID_LENGTH)
	private String locationTypeUdvId;
	
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "organization_id", length = DomainConstant.ID_LENGTH)
	private String organizationId;
	
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "institute_type_udv_id", length = DomainConstant.ID_LENGTH)
	private String instituteTypeUdvId;
	
	
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "po_id", length = DomainConstant.ID_LENGTH)
	private String poId;
	
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "donor_id", length = DomainConstant.ID_LENGTH)
	private String donorId;
	
	@Column(name= "total_boys")
	private int totalBoys;
	
	@Column(name = "total_girls")
	private int totalGirls;
	
	@Column(name="total_ethnic")
	private int totalEthnic;
	
	@Column(name="total_csn")
	private int totalCsn;
	
	@Column(name="total_student")
	private int totalStudent;
	
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name="project_code_udv_id",length = DomainConstant.ID_LENGTH)
	private String projectCodeUdvId;
	
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "govt_location_id", length = DomainConstant.ID_LENGTH)
	private String govtLocationId;
	
	@Size(min = 0, max = DomainConstant.LOCATION_HIERARCHY_ID_LENGTH)
	@Column(name = "govt_location_hierarchy", length = DomainConstant.LOCATION_HIERARCHY_ID_LENGTH)
	private String govtLocationHierarchy;
	
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "current_grade_id", length = DomainConstant.ID_LENGTH)
	private String currentGradeId;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNearestPublicSchool() {
		return nearestPublicSchool;
	}

	public void setNearestPublicSchool(String nearestPublicSchool) {
		this.nearestPublicSchool = nearestPublicSchool;
	}

	public String getDistanceFromNearestPublicSchool() {
		return distanceFromNearestPublicSchool;
	}

	public void setDistanceFromNearestPublicSchool(String distanceFromNearestPublicSchool) {
		this.distanceFromNearestPublicSchool = distanceFromNearestPublicSchool;
	}

	public boolean isToiletAvailability() {
		return toiletAvailability;
	}

	public void setToiletAvailability(boolean toiletAvailability) {
		this.toiletAvailability = toiletAvailability;
	}

	public boolean isElectricity() {
		return electricity;
	}

	public void setElectricity(boolean electricity) {
		this.electricity = electricity;
	}

	public boolean isSchoolStatus() {
		return schoolStatus;
	}

	public void setSchoolStatus(boolean schoolStatus) {
		this.schoolStatus = schoolStatus;
	}

	public Calendar getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Calendar openingDate) {
		this.openingDate = openingDate;
	}

	public String getDistanceFromSchoolToBranchOffice() {
		return distanceFromSchoolToBranchOffice;
	}

	public void setDistanceFromSchoolToBranchOffice(String distanceFromSchoolToBranchOffice) {
		this.distanceFromSchoolToBranchOffice = distanceFromSchoolToBranchOffice;
	}

	public int getNumberOfShift() {
		return numberOfShift;
	}

	public void setNumberOfShift(int numberOfShift) {
		this.numberOfShift = numberOfShift;
	}

	public String getLocationTypeUdvId() {
		return locationTypeUdvId;
	}

	public void setLocationTypeUdvId(String locationTypeUdvId) {
		this.locationTypeUdvId = locationTypeUdvId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getSessionStart() {
		return sessionStart;
	}

	public void setSessionStart(String sessionStart) {
		this.sessionStart = sessionStart;
	}

	public String getSessionEnd() {
		return sessionEnd;
	}

	public void setSessionEnd(String sessionEnd) {
		this.sessionEnd = sessionEnd;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getInstituteTypeUdvId() {
		return instituteTypeUdvId;
	}

	public void setInstituteTypeUdvId(String instituteTypeUdvId) {
		this.instituteTypeUdvId = instituteTypeUdvId;
	}

	public String getPoId() {
		return poId;
	}

	public void setPoId(String poId) {
		this.poId = poId;
	}
	
	public String getDonorId() {
		return donorId;
	}

	public void setDonorId(String donorId) {
		this.donorId = donorId;
	}
	
	public String getLocationHierarchy() {
		return locationHierarchy;
	}

	public void setLocationHierarchy(String locationHierarchy) {
		this.locationHierarchy = locationHierarchy;
	}
	
	public int getTotalBoys() {
		return totalBoys;
	}

	public void setTotalBoys(int totalBoys) {
		this.totalBoys = totalBoys;
	}

	public int getTotalGirls() {
		return totalGirls;
	}

	public void setTotalGirls(int totalGirls) {
		this.totalGirls = totalGirls;
	}

	public int getTotalEthnic() {
		return totalEthnic;
	}

	public void setTotalEthnic(int totalEthnic) {
		this.totalEthnic = totalEthnic;
	}

	public int getTotalCsn() {
		return totalCsn;
	}

	public void setTotalCsn(int totalCsn) {
		this.totalCsn = totalCsn;
	}

	public int getTotalStudent() {
		return totalStudent;
	}

	public void setTotalStudent(int totalStudent) {
		this.totalStudent = totalStudent;
	}

	public String getProjectCodeUdvId() {
		return projectCodeUdvId;
	}

	public void setProjectCodeUdvId(String projectCodeUdvId) {
		this.projectCodeUdvId = projectCodeUdvId;
	}
	
	public String getGovtLocationId() {
		return govtLocationId;
	}

	public void setGovtLocationId(String govtLocationId) {
		this.govtLocationId = govtLocationId;
	}

	public String getGovtLocationHierarchy() {
		return govtLocationHierarchy;
	}

	public void setGovtLocationHierarchy(String govtLocationHierarchy) {
		this.govtLocationHierarchy = govtLocationHierarchy;
	}
	
	public String getCurrentGradeId() {
		return currentGradeId;
	}

	public void setCurrentGradeId(String currentGradeId) {
		this.currentGradeId = currentGradeId;
	}

	@Override
	public String getObjCode() {
		return "institute";
	}
	
	public Institute(){}
	public Institute setInstitute(InstituteModel model){
		this.setId(model.getId());
		this.setCode(model.getCode());
		this.setName(model.getName());
		this.setNearestPublicSchool(model.getNearestPublicSchool());
		this.setDistanceFromNearestPublicSchool(model.getDistanceFromNearestPublicSchool());
		this.setLocationId(model.getLocationId());
		this.setToiletAvailability(Boolean.parseBoolean(model.getToiletAvailability()));
		this.setElectricity(Boolean.parseBoolean(model.getElectricity()));
		this.setSchoolStatus(Boolean.parseBoolean(model.getSchoolStatus()));
		this.setOpeningDate(DateUtil.getCalender(model.getOpeningDate(), DateUtil.DATE_FORMAT_YYYY_MM_DD));
		this.setSessionStart(model.getSessionStart());
		this.setSessionEnd(model.getSessionEnd());
		this.setDistanceFromSchoolToBranchOffice(model.getDistanceFromSchoolToBranchOffice());
		this.setLatitude(model.getLatitude());
		this.setLongitude(model.getLongitude());
		this.setNumberOfShift(IUtil.toInteger(model.getNumberOfShift()));
		this.setLocationTypeUdvId(model.getLocationTypeUdvId());
		this.setOrganizationId(model.getOrganizationId());
		this.setInstituteTypeUdvId(model.getInstituteTypeUdvId());
		this.setPoId(model.getPoId());
		this.setDonorId(model.getDonorId());
		this.setLocationHierarchy(model.getLocationHierarchy());
		this.setTotalBoys(IUtil.toInteger(model.getTotalBoys()));
		this.setTotalGirls(IUtil.toInteger(model.getTotalGirls()));
		this.setTotalEthnic(IUtil.toInteger(model.getTotalEthnic()));
		this.setTotalCsn(IUtil.toInteger(model.getTotalCsn()));
		this.setTotalStudent(IUtil.toInteger(model.getTotalStudent()));
		this.setProjectCodeUdvId(model.getProjectCodeUdvId());
		this.setGovtLocationId(model.getGovtLocationId());
		this.setGovtLocationHierarchy(model.getGovtLocationHierarchy());
		this.setCurrentGradeId(model.getCurrentGradeId());
		
		return this;
	}
	
}
