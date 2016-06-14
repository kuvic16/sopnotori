package net.brac.bep.rest.model;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

import net.brac.bep.data.domain.EducationType;
import net.brac.bep.data.domain.Institute;
import net.brac.bep.util.DateUtil;
import net.brac.bep.util.IUtil;

public class InstituteModel {

	@JsonIgnore
	private String id;
	private String code;
	private String name;
	private String nearestPublicSchool;
	private String distanceFromNearestPublicSchool;
	private String locationId;
	
	private String toiletAvailability;
	private String electricity;
	private String schoolStatus;
	
	private String openingDate;
	private String sessionStart;
	private String sessionEnd;
	private String distanceFromSchoolToBranchOffice;
	private String latitude;
	private String longitude;
	private String numberOfShift;

	private String locationTypeUdvId;
	private String organizationId;
	private String instituteTypeUdvId;
	private String poId;
	private String donorId;
	private String locationHierarchy;
	
	private String totalBoys;
	private String totalGirls;
	private String totalEthnic;
	private String totalCsn;
	private String totalStudent;
	@JsonIgnore
	private String educationTypeName;
	
	private String projectCodeUdvId;
	
	@JsonIgnore
	private String govtLocationId;
	@JsonIgnore
	private String govtLocationHierarchy;
	@JsonIgnore
	private String currentGradeId;
	
	
	private List<EducationTypeModel> educationTypeModelList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getToiletAvailability() {
		return toiletAvailability;
	}

	public void setToiletAvailability(String toiletAvailability) {
		this.toiletAvailability = toiletAvailability;
	}

	public String getElectricity() {
		return electricity;
	}

	public void setElectricity(String electricity) {
		this.electricity = electricity;
	}

	public String getSchoolStatus() {
		return schoolStatus;
	}

	public void setSchoolStatus(String schoolStatus) {
		this.schoolStatus = schoolStatus;
	}

	public String getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(String openingDate) {
		this.openingDate = openingDate;
	}

	public String getDistanceFromSchoolToBranchOffice() {
		return distanceFromSchoolToBranchOffice;
	}

	public void setDistanceFromSchoolToBranchOffice(String distanceFromSchoolToBranchOffice) {
		this.distanceFromSchoolToBranchOffice = distanceFromSchoolToBranchOffice;
	}

	public String getNumberOfShift() {
		return numberOfShift;
	}

	public void setNumberOfShift(String numberOfShift) {
		this.numberOfShift = numberOfShift;
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

	public String getLocationTypeUdvId() {
		return locationTypeUdvId;
	}

	public void setLocationTypeUdvId(String locationTypeUdvId) {
		this.locationTypeUdvId = locationTypeUdvId;
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
	
	public List<EducationTypeModel> getEducationTypeModelList() {
		return educationTypeModelList;
	}

	public void setEducationTypeModelList(List<EducationTypeModel> educationTypeModelList) {
		this.educationTypeModelList = educationTypeModelList;
	}
	
	public String getLocationHierarchy() {
		return locationHierarchy;
	}

	public void setLocationHierarchy(String locationHierarchy) {
		this.locationHierarchy = locationHierarchy;
	}
	
	public String getTotalBoys() {
		return totalBoys;
	}

	public void setTotalBoys(String totalBoys) {
		this.totalBoys = totalBoys;
	}

	public String getTotalGirls() {
		return totalGirls;
	}

	public void setTotalGirls(String totalGirls) {
		this.totalGirls = totalGirls;
	}

	public String getTotalEthnic() {
		return totalEthnic;
	}

	public void setTotalEthnic(String totalEthnic) {
		this.totalEthnic = totalEthnic;
	}

	public String getTotalCsn() {
		return totalCsn;
	}

	public void setTotalCsn(String totalCsn) {
		this.totalCsn = totalCsn;
	}

	public String getTotalStudent() {
		return totalStudent;
	}

	public void setTotalStudent(String totalStudent) {
		this.totalStudent = totalStudent;
	}

	public InstituteModel() {
	}
	
	public String getProjectCodeUdvId() {
		return projectCodeUdvId;
	}

	public void setProjectCodeUdvId(String projectCodeUdvId) {
		this.projectCodeUdvId = projectCodeUdvId;
	}

	public String getEducationTypeName() {
		return educationTypeName;
	}

	public void setEducationTypeName(String educationTypeName) {
		this.educationTypeName = educationTypeName;
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

	public InstituteModel(Institute entity) {
		this.setId(entity.getId());
		this.setCode(entity.getCode());
		this.setName(entity.getName());
		this.setNearestPublicSchool(entity.getNearestPublicSchool());
		this.setDistanceFromNearestPublicSchool(entity.getDistanceFromNearestPublicSchool());
		this.setLocationId(entity.getLocationId());
		this.setToiletAvailability(String.valueOf(entity.isToiletAvailability()));
		this.setElectricity(IUtil.toString(entity.isElectricity()));
		this.setSchoolStatus(IUtil.toString(entity.isSchoolStatus()));
		this.setOpeningDate(DateUtil.getDateString(entity.getOpeningDate(),DateUtil.DATE_FORMAT_YYYY_MM_DD));
		this.setSessionStart(entity.getSessionStart());
		this.setSessionEnd(entity.getSessionEnd());
		this.setDistanceFromSchoolToBranchOffice(entity.getDistanceFromSchoolToBranchOffice());
		this.setLatitude(entity.getLatitude());
		this.setLongitude(entity.getLongitude());
		this.setNumberOfShift(IUtil.toString(entity.getNumberOfShift()));
		this.setLocationTypeUdvId(entity.getLocationTypeUdvId());
		this.setOrganizationId(entity.getOrganizationId());
		this.setInstituteTypeUdvId(entity.getInstituteTypeUdvId());
		this.setPoId(entity.getPoId());
		this.setDonorId(entity.getDonorId());
		this.setLocationHierarchy(entity.getLocationHierarchy());
		this.setTotalBoys(IUtil.toString(entity.getTotalBoys()));
		this.setTotalGirls(IUtil.toString(entity.getTotalGirls()));
		this.setTotalEthnic(IUtil.toString(entity.getTotalEthnic()));
		this.setTotalCsn(IUtil.toString(entity.getTotalCsn()));
		this.setTotalStudent(IUtil.toString(entity.getTotalStudent()));
		this.setProjectCodeUdvId(entity.getProjectCodeUdvId());
		this.setGovtLocationId(entity.getGovtLocationId());
		this.setGovtLocationHierarchy(entity.getGovtLocationHierarchy());
		this.setCurrentGradeId(entity.getCurrentGradeId());
	}
	
	
	public InstituteModel(Institute entity, List<EducationType> educationTypeList) {
		new InstituteModel(entity);		
		List<EducationTypeModel> EducationTypeModels = new ArrayList<EducationTypeModel>();
		for(EducationType etm : educationTypeList){
			EducationTypeModels.add(new EducationTypeModel(etm));
		}
		this.setEducationTypeModelList(EducationTypeModels);
	}
}
