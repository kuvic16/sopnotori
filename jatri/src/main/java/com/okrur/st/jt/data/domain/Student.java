package com.okrur.st.jt.data.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.okrur.st.jt.rest.model.StudentModel;
import com.okrur.st.jt.util.DateUtil;
import com.okrur.st.jt.util.IUtil;

/**
 * @File Student.java: Student database entity
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 24, 2015
 */
@Entity
@XmlRootElement
@Table(name = "student")
public class Student extends AuditableEntity implements Serializable{

	private static final long serialVersionUID = -5924144505041035322L;

	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "institute_id", length = DomainConstant.ID_LENGTH)
	private String instituteId;
	
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "grade_id", length = DomainConstant.ID_LENGTH)
	private String gradeId;
	
	@Size(min = 0, max = 150)
	@Column(name = "student_id", length = 150)
	private String studentId;

	@Size(min = 0, max = 150)
	@Column(name="session_start", length = 150)
	private String sessionStart;

	@Size(min = 0, max = 150)
	@Column(name="session_end", length = 150)
	private String sessionEnd;

	@Size(min = 0, max = 10)
	@Column(name="sex",length = 10)
	private String sex;

	@NotNull
	@Size(min = 1, max = 150)
	@Column(name="first_name", nullable = false, length = 150)
	private String firstName;

	@Size(min = 0, max = 150)
	@Column(name="middle_name", length = 150)
	private String middleName;

	@Size(min = 0, max = 150)
	@Column(name="last_name", length = 150)
	private String lastName;

	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name="student_type_udv_id", length = DomainConstant.ID_LENGTH)
	private String studentTypeUdvId;

	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name="type_of_csn_udv_id", length = DomainConstant.ID_LENGTH)
	private String typeOfCsnUdvId;

	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name="type_of_ethnicity_community_udv_id", length = DomainConstant.ID_LENGTH)
	private String typeOfEthnicityCommunityUdvId;

	@Size(min = 0, max = 1000)
	@Column(name="residential_address", length = 1000)
	private String residentialAddress;

	@Column(name="date_of_birth")
	private Calendar dateOfBirth;

	@Size(min = 0, max = 20)
	@Column(name="religion", length = 20)
	private String religion;

	//Mtrs
	@Size(min = 0, max = 10)
	@Column(name="height", length = 10)
	private String height;

	//Kilo
	@Size(min = 0, max = 10)
	@Column(name="weight", length = 10)
	private String weight;

	@Size(min = 0, max = 150)
	@Column(name="dialect_spoken", length = 150)
	private String dialectSpoken;

	@Size(min = 0, max = 150)
	@Column(name="father_name", length = 150)
	private String fatherName;

	@Column(name="father_dob")
	private Calendar fatherDob;

	@Size(min = 0, max = 1000)
	@Column(name="father_educational_attainment", length = 1000)
	private String fatherEducationalAttainment;

	@Size(min = 0, max = 150)
	@Column(name="father_occupation", length = 150)
	private String fatherOccupation;

	@Size(min = 0, max = 150)
	@Column(name="mother_name", length = 150)
	private String motherName;

	@Column(name="mother_dob")
	private Calendar motherDob;

	@Size(min = 0, max = 1000)
	@Column(name="mother_educational_attainment", length = 1000)	
	private String motherEducationalAttainment;

	@Size(min = 0, max = 150)
	@Column(name="mother_occupation", length = 150)	
	private String motherOccupation;

	@Column(name="family_members_involve_with_brac")
	private boolean familyMembersInvolveWithBrac;

	@Size(min = 0, max = 150)
	@Column(name="family_members_involve_with_brac_service")
	private String familyMembersInvolveWithBracService; 

	@Column(name="transfered_school")
	private boolean transferedSchool;

	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name="transferred_school_id", length = DomainConstant.ID_LENGTH)
	private String transferredSchoolId;
	
	@Size(min = 0, max = 150)
	@Column(name="name_of_transferred_school", length = 150)
	private String nameOfTransferredSchool;

	@Size(min = 0, max = 300)
	@Column(name="address", length = 300)
	private String address;

	@Column(name="involved_with_chhatrabandhu")
	private boolean involvedWithChhatrabandhu;

	@Column(name="brac_graduate")
	private boolean bracGraduate;

	@Column(name="dropout")
	private boolean dropout;
	
	@Column(name="replacement")
	private boolean replacement;
	
	@Size(min=0, max=45)
	@Column(name="guardian_mobile", length=45)
	private String guardianMobile;
	
	@Column(name="waiver")
	private Integer waiver;
	
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "location_type_udv_id", length = DomainConstant.ID_LENGTH)
	private String locationTypeUdvId;

	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "location_id", length = DomainConstant.ID_LENGTH)
	private String locationId;
	
	@Size(min = 0, max = DomainConstant.LOCATION_HIERARCHY_ID_LENGTH)
	@Column(name = "location_hierarchy", length = DomainConstant.LOCATION_HIERARCHY_ID_LENGTH)
	private String locationHierarchy;
	
	@Size(min = 0, max = 20)
	@Column(name = "latitude", length=20)
	private String latitude; 
	
	@Size(min = 0, max = 20)
	@Column(name = "longitude",length=20)
	private String longitude;
	
	@Size(min = 0, max = 150)
	@Column(name = "roll", length = 150)
	private String roll;
	
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getResidentialAddress() {
		return residentialAddress;
	}

	public void setResidentialAddress(String residentialAddress) {
		this.residentialAddress = residentialAddress;
	}

	public Calendar getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Calendar dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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

	public Calendar getFatherDob() {
		return fatherDob;
	}

	public void setFatherDob(Calendar fatherDob) {
		this.fatherDob = fatherDob;
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

	public Calendar getMotherDob() {
		return motherDob;
	}

	public void setMotherDob(Calendar motherDob) {
		this.motherDob = motherDob;
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

	public boolean isFamilyMembersInvolveWithBrac() {
		return familyMembersInvolveWithBrac;
	}

	public void setFamilyMembersInvolveWithBrac(boolean familyMembersInvolveWithBrac) {
		this.familyMembersInvolveWithBrac = familyMembersInvolveWithBrac;
	}

	public boolean isTransferedSchool() {
		return transferedSchool;
	}

	public void setTransferedSchool(boolean transferedSchool) {
		this.transferedSchool = transferedSchool;
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

	public boolean isInvolvedWithChhatrabandhu() {
		return involvedWithChhatrabandhu;
	}

	public void setInvolvedWithChhatrabandhu(boolean involvedWithChhatrabandhu) {
		this.involvedWithChhatrabandhu = involvedWithChhatrabandhu;
	}

	public boolean isBracGraduate() {
		return bracGraduate;
	}

	public void setBracGraduate(boolean bracGraduate) {
		this.bracGraduate = bracGraduate;
	}

	public boolean isDropout() {
		return dropout;
	}

	public void setDropout(boolean dropout) {
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
	
	public Integer getWaiver() {
		return waiver;
	}

	public void setWaiver(Integer waiver) {
		this.waiver = waiver;
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
	
	public boolean isReplacement() {
		return replacement;
	}

	public void setReplacement(boolean replacement) {
		this.replacement = replacement;
	}

	@Override
	public String getObjCode() {
		return "student";
	}
	
	public Student(){}
	public Student setStudent(StudentModel model){
		this.setId(model.getId()); 
		this.setInstituteId(model.getInstituteId());
		this.setGradeId(model.getGradeId());
		this.setStudentId(model.getStudentId()); 
		this.setSessionStart(model.getSessionStart()); 
		this.setSessionEnd(model.getSessionEnd());
		this.setSex(model.getSex()); 
		this.setFirstName(model.getStudentFirstName()); 
		this.setMiddleName(model.getStudentMiddleName()); 
		this.setLastName(model.getStudentLastName()); 
		this.setStudentTypeUdvId(model.getStudentTypeUdvId()); 
		this.setTypeOfCsnUdvId(model.getTypeOfCsnUdvId()); 
		this.setTypeOfEthnicityCommunityUdvId(model.getTypeOfEthnicityCommunityUdvId()); 
		this.setResidentialAddress(model.getResidentialAddress()); 
		this.setDateOfBirth(DateUtil.getCalender(model.getDateOfBirth(), DateUtil.DATE_FORMAT_YYYY_MM_DD));
		this.setReligion(model.getReligion()); 
		this.setHeight(model.getHeight());  
		this.setWeight(model.getWeight());  
		this.setDialectSpoken(model.getDialectSpoken());
		this.setFatherName(model.getFatherName());
		this.setFatherDob(DateUtil.getCalender(model.getFatherDob(), DateUtil.DATE_FORMAT_YYYY_MM_DD));
		this.setFatherEducationalAttainment(model.getFatherEducationalAttainment());  
		this.setFatherOccupation(model.getFatherOccupation());  
		this.setMotherName(model.getMotherName());  
		this.setMotherDob(DateUtil.getCalender(model.getMotherDob(), DateUtil.DATE_FORMAT_YYYY_MM_DD)); 
		this.setMotherEducationalAttainment(model.getMotherEducationalAttainment());  
		this.setMotherOccupation(model.getMotherOccupation());  
		this.setFamilyMembersInvolveWithBrac(IUtil.toBoolean(model.getFamilyMembersInvolveWithBrac())); 
		this.setFamilyMembersInvolveWithBracService(model.getFamilyMembersInvolveWithBracService());
		this.setTransferedSchool(IUtil.toBoolean(model.getTransferedSchool()));  
		this.setNameOfTransferredSchool(model.getNameOfTransferredSchool());  
		this.setAddress(model.getAddress());  
		this.setInvolvedWithChhatrabandhu(IUtil.toBoolean(model.getInvolvedWithChhatrabandhu()));
		this.setBracGraduate(IUtil.toBoolean(model.getBracGraduate()));  
		this.setDropout(IUtil.toBoolean(model.getDropout()));  
		this.setGuardianMobile(model.getGuardianMobile());
		this.setWaiver(IUtil.toInteger(model.getWaiver()));
		this.setTransferredSchoolId(model.getTransferredSchoolId());
		this.setLocationTypeUdvId(model.getLocationTypeUdvId());
		this.setLocationId(model.getLocationId());
		this.setLocationHierarchy(model.getLocationHierarchy());
		this.setLatitude(model.getLatitude());
		this.setLongitude(model.getLongitude());
		this.setRoll(model.getRoll());
		this.setReplacement(IUtil.toBoolean(model.getReplacement()));
		
		return this;
	}
	
}
