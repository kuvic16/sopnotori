package net.brac.bep.rest.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import net.brac.bep.data.domain.Student;
import net.brac.bep.util.DateUtil;
import net.brac.bep.util.IUtil;

/**
 * @File StudentModel.java: Student Model for rest communication
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 24, 2015
 */
public class StudentModel {
	
	@JsonIgnore
	private String id;
	private String instituteId;
	private String gradeId;
	private String studentId;
	private String sessionStart;
	private String sessionEnd;
	private String sex;
	private String studentFirstName;
	private String studentMiddleName;
	private String studentLastName;
	private String studentTypeUdvId;
	private String typeOfCsnUdvId;
	private String typeOfEthnicityCommunityUdvId;
	private String residentialAddress;
	private String dateOfBirth;
	private String religion;
	
	//Mtrs
	private String height;

	//Kilo
	private String weight;
	private String dialectSpoken;
	private String fatherName;
	private String fatherDob;
	private String fatherEducationalAttainment;
	private String fatherOccupation;
	private String motherName;
	private String motherDob;
	private String motherEducationalAttainment;
	private String motherOccupation;
	private String familyMembersInvolveWithBrac;
	private String familyMembersInvolveWithBracService;
	private String transferedSchool;
	private String transferredSchoolId;
	private String nameOfTransferredSchool;
	private String address;
	private String involvedWithChhatrabandhu;
	private String bracGraduate;
	private String dropout;
	
	@JsonIgnore
	private String replacement;
	
	private String guardianMobile;
	private String waiver;
	
	private String locationTypeUdvId;
	private String locationId;
	private String locationHierarchy;
	private String latitude;
	private String longitude;
	
	
	@JsonIgnore
	private String fullName;
	@JsonIgnore
	private String roll;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getStudentFirstName() {
		return studentFirstName;
	}

	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}

	public String getStudentMiddleName() {
		return studentMiddleName;
	}

	public void setStudentMiddleName(String studentMiddleName) {
		this.studentMiddleName = studentMiddleName;
	}

	public String getStudentLastName() {
		return studentLastName;
	}

	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}

	public String getResidentialAddress() {
		return residentialAddress;
	}

	public String getFatherDob() {
		return fatherDob;
	}

	public String getMotherDob() {
		return motherDob;
	}

	public void setResidentialAddress(String residentialAddress) {
		this.residentialAddress = residentialAddress;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setFatherDob(String fatherDob) {
		this.fatherDob = fatherDob;
	}

	public void setMotherDob(String motherDob) {
		this.motherDob = motherDob;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getDialectSpoken() {
		return dialectSpoken;
	}

	public void setDialectSpoken(String dialectSpoken) {
		this.dialectSpoken = dialectSpoken;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getFatherEducationalAttainment() {
		return fatherEducationalAttainment;
	}

	public void setFatherEducationalAttainment(String fatherEducationalAttainment) {
		this.fatherEducationalAttainment = fatherEducationalAttainment;
	}

	public String getFatherOccupation() {
		return fatherOccupation;
	}

	public void setFatherOccupation(String fatherOccupation) {
		this.fatherOccupation = fatherOccupation;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getMotherEducationalAttainment() {
		return motherEducationalAttainment;
	}

	public void setMotherEducationalAttainment(String motherEducationalAttainment) {
		this.motherEducationalAttainment = motherEducationalAttainment;
	}

	public String getMotherOccupation() {
		return motherOccupation;
	}

	public void setMotherOccupation(String motherOccupation) {
		this.motherOccupation = motherOccupation;
	}

	public String getNameOfTransferredSchool() {
		return nameOfTransferredSchool;
	}

	public void setNameOfTransferredSchool(String nameOfTransferredSchool) {
		this.nameOfTransferredSchool = nameOfTransferredSchool;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getFamilyMembersInvolveWithBrac() {
		return familyMembersInvolveWithBrac;
	}

	public void setFamilyMembersInvolveWithBrac(String familyMembersInvolveWithBrac) {
		this.familyMembersInvolveWithBrac = familyMembersInvolveWithBrac;
	}

	public String getTransferedSchool() {
		return transferedSchool;
	}

	public void setTransferedSchool(String transferedSchool) {
		this.transferedSchool = transferedSchool;
	}

	public String getInvolvedWithChhatrabandhu() {
		return involvedWithChhatrabandhu;
	}

	public void setInvolvedWithChhatrabandhu(String involvedWithChhatrabandhu) {
		this.involvedWithChhatrabandhu = involvedWithChhatrabandhu;
	}

	public String getBracGraduate() {
		return bracGraduate;
	}

	public void setBracGraduate(String bracGraduate) {
		this.bracGraduate = bracGraduate;
	}

	public String getDropout() {
		return dropout;
	}

	public void setDropout(String dropout) {
		this.dropout = dropout;
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

	public String getStudentTypeUdvId() {
		return studentTypeUdvId;
	}

	public void setStudentTypeUdvId(String studentTypeUdvId) {
		this.studentTypeUdvId = studentTypeUdvId;
	}

	public String getTypeOfCsnUdvId() {
		return typeOfCsnUdvId;
	}

	public void setTypeOfCsnUdvId(String typeOfCsnUdvId) {
		this.typeOfCsnUdvId = typeOfCsnUdvId;
	}

	public String getTypeOfEthnicityCommunityUdvId() {
		return typeOfEthnicityCommunityUdvId;
	}

	public void setTypeOfEthnicityCommunityUdvId(String typeOfEthnicityCommunityUdvId) {
		this.typeOfEthnicityCommunityUdvId = typeOfEthnicityCommunityUdvId;
	}

	public String getFamilyMembersInvolveWithBracService() {
		return familyMembersInvolveWithBracService;
	}

	public void setFamilyMembersInvolveWithBracService(String familyMembersInvolveWithBracService) {
		this.familyMembersInvolveWithBracService = familyMembersInvolveWithBracService;
	}

	public String getTransferredSchoolId() {
		return transferredSchoolId;
	}

	public void setTransferredSchoolId(String transferredSchoolId) {
		this.transferredSchoolId = transferredSchoolId;
	}

	public String getGuardianMobile() {
		return guardianMobile;
	}

	public void setGuardianMobile(String guardianMobile) {
		this.guardianMobile = guardianMobile;
	}
	
	public String getWaiver() {
		return waiver;
	}

	public void setWaiver(String waiver) {
		this.waiver = waiver;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public String getLocationHierarchy() {
		return locationHierarchy;
	}

	public void setLocationHierarchy(String locationHierarchy) {
		this.locationHierarchy = locationHierarchy;
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
	
	public String getRoll() {
		return roll;
	}

	public void setRoll(String roll) {
		this.roll = roll;
	}
	
	public String getReplacement() {
		return replacement;
	}

	public void setReplacement(String replacement) {
		this.replacement = replacement;
	}

	public StudentModel(){}
	
	public StudentModel(Student entity){
		this.setId(entity.getId());
		this.setInstituteId(entity.getInstituteId());
		this.setGradeId(entity.getGradeId());
		this.setStudentId(entity.getStudentId()); 
		this.setSessionStart(entity.getSessionStart()); 
		this.setSessionEnd(entity.getSessionEnd());
		this.setSex(entity.getSex()); 
		this.setStudentFirstName(entity.getFirstName()); 
		this.setStudentMiddleName(entity.getMiddleName()); 
		this.setStudentLastName(entity.getLastName()); 
		this.setStudentTypeUdvId(entity.getStudentTypeUdvId()); 
		this.setTypeOfCsnUdvId(entity.getTypeOfCsnUdvId()); 
		this.setTypeOfEthnicityCommunityUdvId(entity.getTypeOfEthnicityCommunityUdvId()); 
		this.setResidentialAddress(entity.getResidentialAddress());
		this.setDateOfBirth(DateUtil.getDateString(entity.getDateOfBirth(),DateUtil.DATE_FORMAT_YYYY_MM_DD)); 
		this.setReligion(entity.getReligion()); 
		this.setHeight(entity.getHeight());  
		this.setWeight(entity.getWeight());  
		this.setDialectSpoken(entity.getDialectSpoken());
		this.setFatherName(entity.getFatherName());
		this.setFatherDob(DateUtil.getDateString(entity.getFatherDob(),DateUtil.DATE_FORMAT_YYYY_MM_DD));
		this.setFatherEducationalAttainment(entity.getFatherEducationalAttainment());  
		this.setFatherOccupation(entity.getFatherOccupation());  
		this.setMotherName(entity.getMotherName());  
		this.setMotherDob(DateUtil.getDateString(entity.getMotherDob(),DateUtil.DATE_FORMAT_YYYY_MM_DD));
		this.setMotherEducationalAttainment(entity.getMotherEducationalAttainment());  
		this.setMotherOccupation(entity.getMotherOccupation());  
		this.setFamilyMembersInvolveWithBrac(IUtil.toString(entity.isFamilyMembersInvolveWithBrac())); 
		this.setFamilyMembersInvolveWithBracService(entity.getFamilyMembersInvolveWithBracService());
		this.setTransferedSchool(IUtil.toString(entity.isTransferedSchool()));  
		this.setNameOfTransferredSchool(entity.getNameOfTransferredSchool());  
		this.setAddress(entity.getAddress());  
		this.setInvolvedWithChhatrabandhu(IUtil.toString(entity.isInvolvedWithChhatrabandhu()));
		this.setBracGraduate(IUtil.toString(entity.isBracGraduate()));  
		this.setDropout(IUtil.toString(entity.isDropout()));  
		this.setGuardianMobile(entity.getGuardianMobile());
		this.setWaiver(IUtil.toString(entity.getWaiver()));
		this.setTransferredSchoolId(entity.getTransferredSchoolId());
		this.setLocationTypeUdvId(entity.getLocationTypeUdvId());
		this.setLocationId(entity.getLocationId());
		this.setLocationHierarchy(entity.getLocationHierarchy());
		this.setLatitude(entity.getLatitude());
		this.setLongitude(entity.getLongitude());
		this.setRoll(entity.getRoll());
		this.setReplacement(IUtil.toString(entity.isReplacement()));
	}
}
