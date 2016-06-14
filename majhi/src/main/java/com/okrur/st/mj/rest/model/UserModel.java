package com.okrur.st.mj.rest.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.okrur.st.mj.data.domain.User;
import com.okrur.st.mj.util.DateUtil;
import com.okrur.st.mj.util.IConstant;
import com.okrur.st.mj.util.IUtil;

/**
 * @File UserModel.java: user model for rest communication 
 * @author shaiful islam palash | kuvic16@gmail.com
 * @CreationDate Nov 24, 2015
 */
public class UserModel{
	@JsonIgnore
	private String id;
	
	private String username;
	private String email;
	private String password;
	private String firstName;
	private String middleName;
	private String lastName;
	private String fatherName;
	private String motherName;
	private String husbandName;
	private String mobileNumber;
	private String dateOfBirth;
	private String gender;
	private String status;
	private String designationUdvId;
	private String roleId;
	private String upLevelId;
	private String imagePath;
	private String locationId;
	private String userCategoryUdvId;
	private String userHierarchyId;
	private String locationTypeUdvId;
	private String latitude;
	private String longitude;
	private String organizationId;
	private String instituteId;
	private String locationHierarchy;
	private String staffGrageUdvId;
	private String dropOut;
	private String dropOutReason;
	private String bracGraduate;
	private String teachingExpBrac;
	private String replacement;
	private String maritalStatus;
	
	@JsonIgnore
	private String religion;
	@JsonIgnore
	private String education;
	@JsonIgnore
	private String nid;
	@JsonIgnore
	private String presentAddress;
	@JsonIgnore
	private String permanentAddress;
	@JsonIgnore
	private String joiningDate;
	
	@JsonIgnore
	private boolean onlyTeacher;
	
	@JsonIgnore
	private RoleModel roleModel;
	
	@JsonIgnore
	private UdvModel categoryModel;
	
	@JsonIgnore
	private boolean po;
	
	@JsonIgnore
	private boolean admin;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String isStatus() {
		return status;
	}
	public void setStatus(String status) {
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
	
	public String getStatus() {
		return status;
	}
	
	public String getStaffGrageUdvId() {
		return staffGrageUdvId;
	}
	public void setStaffGrageUdvId(String staffGrageUdvId) {
		this.staffGrageUdvId = staffGrageUdvId;
	}
	public String getDropOut() {
		return dropOut;
	}
	public void setDropOut(String dropOut) {
		this.dropOut = dropOut;
	}
	public String getDropOutReason() {
		return dropOutReason;
	}
	public void setDropOutReason(String dropOutReason) {
		this.dropOutReason = dropOutReason;
	}
	
	public String getTeachingExpBrac() {
		return teachingExpBrac;
	}
	public void setTeachingExpBrac(String teachingExpBrac) {
		this.teachingExpBrac = teachingExpBrac;
	}
	public String getBracGraduate() {
		return bracGraduate;
	}
	public void setBracGraduate(String bracGraduate) {
		this.bracGraduate = bracGraduate;
	}
	public String getReplacement() {
		return replacement;
	}
	public void setReplacement(String replacement) {
		this.replacement = replacement;
	}
	
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public boolean isOnlyTeacher() {
		return onlyTeacher;
	}
	public void setOnlyTeacher(boolean onlyTeacher) {
		this.onlyTeacher = onlyTeacher;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	public RoleModel getRoleModel() {
		return roleModel;
	}
	public void setRoleModel(RoleModel roleModel) {
		this.roleModel = roleModel;
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
	public String getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}
	
	public UdvModel getCategoryModel() {
		return categoryModel;
	}
	public void setCategoryModel(UdvModel categoryModel) {
		this.categoryModel = categoryModel;
	}
	
	@JsonIgnore
	public boolean isAdmin(){
		try{
			if(this.getUsername().equalsIgnoreCase(IConstant.ADMIN_USER) || this.getRoleModel().getName().equalsIgnoreCase(IConstant.ADMIN_ROLE)){
				return true;
			}
		}catch(Throwable t){}
		return false;
	}
	
	@JsonIgnore
	public boolean isPo(){
		try{
			if(this.getCategoryModel() != null && this.getCategoryModel().getValue().equalsIgnoreCase(IConstant.PO)){
				return true;
			}
		}catch(Throwable t){}
		return false;
	}
	
	public UserModel(){}
	public UserModel(User entity){
		this.setId(entity.getId());
		this.setUsername(entity.getUsername());
		this.setEmail(entity.getEmail());
		this.setPassword(entity.getPassword());
		this.setFirstName(entity.getFirstName());
		this.setMiddleName(entity.getMiddleName());
		this.setLastName(entity.getLastName());
		this.setFatherName(entity.getFatherName());
		this.setMotherName(entity.getMotherName());
		this.setHusbandName(entity.getHusbandName());
		this.setMobileNumber(entity.getMobileNumber());
		this.setDateOfBirth(DateUtil.getDateString(entity.getDateOfBirth(), DateUtil.DATE_FORMAT_YYYY_MM_DD));
		this.setGender(entity.getGender());
		this.setStatus(IUtil.toBooleanString(entity.isStatus()));
		this.setDesignationUdvId(entity.getDesignationUdvId());
		this.setRoleId(entity.getRoleId());
		this.setUpLevelId(entity.getUpLevelId());
		this.setImagePath(entity.getImagePath());
		this.setLocationId(entity.getLocationId());
		this.setUserCategoryUdvId(entity.getUserCategoryUdvId());
		this.setUserHierarchyId(entity.getUserHierarchyId());
		this.setLocationTypeUdvId(entity.getLocationTypeUdvId());
		this.setLatitude(IUtil.toString(entity.getLatitude()));
		this.setLongitude(IUtil.toString(entity.getLongitude()));
		this.setOrganizationId(entity.getOrganizationId());
		this.setInstituteId(entity.getInstituteId());	
		this.setLocationHierarchy(entity.getLocationHierarchy());
		this.setStaffGrageUdvId(entity.getStaffGrageUdvId());
		this.setDropOut(IUtil.toString(entity.isDropOut()));
		this.setDropOutReason(entity.getDropOutReason());
		this.setBracGraduate(IUtil.toString(entity.isBracGraduate()));
		this.setTeachingExpBrac(entity.getTeachingExpBrac());
		this.setReplacement(IUtil.toString(entity.isReplacement()));
		this.setMaritalStatus(IUtil.toString(entity.isMaritalStatus()));
		this.setOnlyTeacher(this.isOnlyTeacher());
		
		this.setReligion(entity.getReligion());
		this.setEducation(entity.getEducation());
		this.setNid(entity.getNid());
		this.setPermanentAddress(entity.getPermanentAddress());
		this.setPresentAddress(entity.getPresentAddress());
		this.setJoiningDate(DateUtil.getDateString(entity.getJoiningDate(), DateUtil.DATE_FORMAT_YYYY_MM_DD));
	}
	
	public UserModel(User entity, RoleModel roleModel, UdvModel categoryModel){
		this(entity);
		this.setRoleModel(roleModel);
		this.setCategoryModel(categoryModel);
	}
	
}
