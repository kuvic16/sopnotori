package com.okrur.st.mj.data.domain;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.okrur.st.mj.rest.model.UserModel;
import com.okrur.st.mj.rest.model.UserSettingsModel;
import com.okrur.st.mj.util.DateUtil;
import com.okrur.st.mj.util.IUtil;

/**
 * @File User.java: User database entity 
 * @author shaiful islam palash | kuvic16@gmail.com
 * @CreationDate Nov 24, 2015
 */
@Entity
@Table(name = "users")
public class User extends AuditableEntity {
	private static final long serialVersionUID = 5528108238923555372L;

	@Size(min = 0, max = 100)
	@Column(name = "username", length = 100, unique = true)
	private String username;

	@Size(min = 0, max = 100)
	@Column(name = "email", length = 100, nullable = true)
	private String email;

	@NotNull
    @Size(min = 1, max = 500)
	@Column(name = "password", nullable = false, length = 500)
	private String password;

	@Size(min = 0, max = 100)
	@Column(name = "first_name", length = 100)
	private String firstName;

	@Size(min = 0, max = 100)
	@Column(name = "middle_name", length = 100)
	private String middleName;

	@Size(min = 0, max = 100)
	@Column(name = "last_name", length = 100)
	private String lastName;

	@Size(min = 0, max = 100)
	@Column(name = "father_name", length = 100)
	private String fatherName;

	@Size(min = 0, max = 100)
	@Column(name = "mother_name", length = 100)
	private String motherName;

	@Size(min = 0, max = 100)
	@Column(name = "husband_name", length = 100)
	private String husbandName;

	@Size(min = 0, max = 100)
	@Column(name = "mobile_number", length = 100)
	private String mobileNumber;

	@Column(name = "date_of_birth")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dateOfBirth;

	@Size(min = 0, max = 10)
	@Column(name = "gender", length = 10)
	private String gender;

	@Column(name = "status")
	private boolean status = true;

	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "designation_udv_id", length = DomainConstant.ID_LENGTH)
	private String designationUdvId;

	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "role_id", length = DomainConstant.ID_LENGTH)
	private String roleId;
	
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "uplevel_id", length = DomainConstant.ID_LENGTH)
	private String upLevelId;

	@Size(min = 0, max = 256)
	@Column(name = "image_path", length = 256)
	private String imagePath;

	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "location_id", length = DomainConstant.ID_LENGTH)
	private String locationId;

	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "user_category_udv_id", length = DomainConstant.ID_LENGTH)
	private String userCategoryUdvId;

	@Size(min = 0, max = DomainConstant.HIERARCHY_LENGTH)
	@Column(name = "user_hierarchy_id", length = DomainConstant.HIERARCHY_LENGTH)
	private String userHierarchyId;
	
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "location_type_udv_id", length = DomainConstant.ID_LENGTH)
	private String locationTypeUdvId;

	@Column(name = "latitude")
	private BigDecimal latitude;

	@Column(name = "longitude")
	private BigDecimal longitude;
	
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "organization_id", length = DomainConstant.ID_LENGTH)
	private String organizationId;
	
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "institute_id", length = DomainConstant.ID_LENGTH)
	private String instituteId;
	
	@Size(min = 0, max = DomainConstant.LOCATION_HIERARCHY_ID_LENGTH)
	@Column(name = "location_hierarchy", length = DomainConstant.LOCATION_HIERARCHY_ID_LENGTH)
	private String locationHierarchy;
	
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "staff_grade_udv_id", length = DomainConstant.ID_LENGTH)
	private String staffGrageUdvId;
	
	@Column(name = "drop_out")
	private boolean dropOut;
	
	@Size(min = 0, max = DomainConstant.LONG_DESCRIPTION_LENGTH)
	@Column(name = "drop_out_reason", length = DomainConstant.LONG_DESCRIPTION_LENGTH)
	private String dropOutReason;
	
	@Column(name = "brac_graduate")
	private boolean bracGraduate;
	
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "teaching_exp_brac", length = DomainConstant.ID_LENGTH)
	private String teachingExpBrac;
	
	@Column(name = "replacement")
	private boolean replacement;
	
	@Column(name = "marital_status")
	private boolean maritalStatus;
	
	@Size(min = 0, max = 50)
	@Column(name = "religion", length = 50)
	private String religion;
	
	@Size(min = 0, max = 500)
	@Column(name = "education", length = 500)
	private String education;
	
	@Size(min = 0, max = 100)
	@Column(name = "nid", length = 100)
	private String nid;
	
	@Size(min = 0, max = 500)
	@Column(name = "present_address", length = 500)
	private String presentAddress;
	
	@Size(min = 0, max = 500)
	@Column(name = "permanent_address", length = 500)
	private String permanentAddress;
	
	@Column(name = "joining_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar joiningDate;
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getHusbandName() {
		return husbandName;
	}

	public void setHusbandName(String husbandName) {
		this.husbandName = husbandName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Calendar getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Calendar dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getDesignationUdvId() {
		return designationUdvId;
	}

	public void setDesignationUdvId(String designationUdvId) {
		this.designationUdvId = designationUdvId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUpLevelId() {
		return upLevelId;
	}

	public void setUpLevelId(String upLevelId) {
		this.upLevelId = upLevelId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getUserCategoryUdvId() {
		return userCategoryUdvId;
	}

	public void setUserCategoryUdvId(String userCategoryUdvId) {
		this.userCategoryUdvId = userCategoryUdvId;
	}

	public String getUserHierarchyId() {
		return userHierarchyId;
	}

	public void setUserHierarchyId(String userHierarchyId) {
		this.userHierarchyId = userHierarchyId;
	}

	public String getLocationTypeUdvId() {
		return locationTypeUdvId;
	}

	public void setLocationTypeUdvId(String locationTypeUdvId) {
		this.locationTypeUdvId = locationTypeUdvId;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(String instituteId) {
		this.instituteId = instituteId;
	}
	
	public String getLocationHierarchy() {
		return locationHierarchy;
	}

	public void setLocationHierarchy(String locationHierarchy) {
		this.locationHierarchy = locationHierarchy;
	}

	public String getStaffGrageUdvId() {
		return staffGrageUdvId;
	}

	public void setStaffGrageUdvId(String staffGrageUdvId) {
		this.staffGrageUdvId = staffGrageUdvId;
	}

	public boolean isDropOut() {
		return dropOut;
	}

	public void setDropOut(boolean dropOut) {
		this.dropOut = dropOut;
	}

	public String getDropOutReason() {
		return dropOutReason;
	}

	public void setDropOutReason(String dropOutReason) {
		this.dropOutReason = dropOutReason;
	}

	
	public boolean isBracGraduate() {
		return bracGraduate;
	}

	public void setBracGraduate(boolean bracGraduate) {
		this.bracGraduate = bracGraduate;
	}

	public String getTeachingExpBrac() {
		return teachingExpBrac;
	}

	public void setTeachingExpBrac(String teachingExpBrac) {
		this.teachingExpBrac = teachingExpBrac;
	}

	public boolean isReplacement() {
		return replacement;
	}

	public void setReplacement(boolean replacement) {
		this.replacement = replacement;
	}

	public boolean isMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(boolean maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getPresentAddress() {
		return presentAddress;
	}

	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public Calendar getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Calendar joiningDate) {
		this.joiningDate = joiningDate;
	}

	@Override
	public String getObjCode() {
		return "users";
	}
	
	public User(){}
	public User setUser(UserModel model){
		this.setId(model.getId());
		this.setUsername(model.getUsername());
		this.setEmail(model.getEmail());
		this.setPassword(model.getPassword());
		this.setFirstName(model.getFirstName());
		this.setMiddleName(model.getMiddleName());
		this.setLastName(model.getLastName());
		this.setFatherName(model.getFatherName());
		this.setMotherName(model.getMotherName());
		this.setHusbandName(model.getHusbandName());
		this.setMobileNumber(model.getMobileNumber());
		this.setDateOfBirth(DateUtil.getCalender(model.getDateOfBirth(), DateUtil.DATE_FORMAT_YYYY_MM_DD));
		this.setGender(model.getGender());
		this.setStatus(IUtil.toBoolean(model.isStatus()));
		this.setDesignationUdvId(model.getDesignationUdvId());
		this.setRoleId(model.getRoleId());
		this.setUpLevelId(model.getUpLevelId());
		this.setImagePath(model.getImagePath());
		this.setLocationId(model.getLocationId());
		this.setUserCategoryUdvId(model.getUserCategoryUdvId());
		this.setUserHierarchyId(model.getUserHierarchyId());
		this.setLocationTypeUdvId(model.getLocationTypeUdvId());
		this.setLatitude(IUtil.toBigDecimal(model.getLatitude()));
		this.setLongitude(IUtil.toBigDecimal(model.getLongitude()));
		this.setOrganizationId(model.getOrganizationId());
		this.setInstituteId(model.getInstituteId());
		this.setLocationHierarchy(model.getLocationHierarchy());
		this.setStaffGrageUdvId(model.getStaffGrageUdvId());
		this.setDropOut(IUtil.toBoolean(model.getDropOut()));
		this.setDropOutReason(model.getDropOutReason());
		this.setBracGraduate(IUtil.toBoolean(model.getBracGraduate()));
		this.setTeachingExpBrac(model.getTeachingExpBrac());
		this.setReplacement(IUtil.toBoolean(model.getReplacement()));
		this.setMaritalStatus(IUtil.toBoolean(model.getMaritalStatus()));
		
		this.setReligion(model.getReligion());
		this.setEducation(model.getEducation());
		this.setNid(model.getNid());
		this.setPermanentAddress(model.getPermanentAddress());
		this.setPresentAddress(model.getPresentAddress());
		this.setJoiningDate(DateUtil.getCalender(model.getJoiningDate(), DateUtil.DATE_FORMAT_YYYY_MM_DD));
		
		return this;
	}
	
	public User setUserSettings(UserSettingsModel model){
		this.setId(model.getId());
		this.setEmail(model.getEmail());
		this.setPassword(model.getPassword());
		this.setFirstName(model.getFirstName());
		this.setMiddleName(model.getMiddleName());
		this.setLastName(model.getLastName());
		this.setMobileNumber(model.getMobileNumber());
		this.setDateOfBirth(DateUtil.getCalender(model.getDateOfBirth(), DateUtil.DATE_FORMAT_YYYY_MM_DD));
		this.setGender(model.getGender());
		this.setDesignationUdvId(model.getDesignationUdvId());
		this.setStaffGrageUdvId(model.getStaffGrageUdvId());
		
		return this;
	}
}
