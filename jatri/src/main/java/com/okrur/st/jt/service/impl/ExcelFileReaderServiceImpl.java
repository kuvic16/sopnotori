/**
 * 
 */
package com.okrur.st.jt.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.okrur.st.jt.data.enums.CategoryEnum;
import com.okrur.st.jt.data.repository.UdvRepository;
import com.okrur.st.jt.rest.model.EducationTypeModel;
import com.okrur.st.jt.rest.model.GradeModel;
import com.okrur.st.jt.rest.model.InstituteModel;
import com.okrur.st.jt.rest.model.LocationModel;
import com.okrur.st.jt.rest.model.ResponseModel;
import com.okrur.st.jt.rest.model.StudentModel;
import com.okrur.st.jt.rest.model.UdvModel;
import com.okrur.st.jt.rest.model.UserModel;
import com.okrur.st.jt.service.EducationTypeService;
import com.okrur.st.jt.service.ExcelFileReaderService;
import com.okrur.st.jt.service.GradeService;
import com.okrur.st.jt.service.InstituteService;
import com.okrur.st.jt.service.LocationInfoService;
import com.okrur.st.jt.service.StudentService;
import com.okrur.st.jt.service.UdvService;
import com.okrur.st.jt.service.UserService;
import com.okrur.st.jt.util.APPDate;
import com.okrur.st.jt.util.DateUtil;
import com.okrur.st.jt.util.IConstant;
import com.okrur.st.jt.util.IUtil;
import com.okrur.st.jt.util.RestUtil;

/**
 * @File ReadExcelFilesSericeImpl.java
 * @author shaiful islam palash | kuvic16@gmail.com
 * @CreationDate Apr 6, 2016
 */
public class ExcelFileReaderServiceImpl implements ExcelFileReaderService{
	
	@Inject
	LocationInfoService locationInfoService;
	
	@Inject
	UserService userService;
	
	@Inject
	UdvService udvService;
	
	@Inject
	EducationTypeService educationTypeService;
	
	@Inject
	InstituteService instituteService;
	
	@Inject
	GradeService gradeService;
	
	@Inject
	StudentService studentService;
	
	
	/* (non-Javadoc)
	 * @see net.brac.bep.service.ReadExcelFiles#readInstituteExcel()
	 */
	@Override
	public void readInstituteExcel() {
		XSSFSheet instituteSheet, studentSheet, teacherSheet;
    	instituteSheet = createExcelSheet("Excel_Files/School_Information.xlsx", "School_Information" );
    	try {
			readInstitute(instituteSheet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void readTeacherExcel() {
		XSSFSheet teacherSheet;
		teacherSheet = createExcelSheet("Excel_Files/Teachers_Information.xlsx", "Teachers_Information" );
		try {
			readTeacher(teacherSheet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void readStudentExcel() {
		XSSFSheet studentSheet;
		studentSheet = createExcelSheet("Excel_Files/Student_Information.xlsx", "Student_Information" );
		try {
			readStudent(studentSheet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void readInstitute(XSSFSheet instituteSheet) throws IOException {
    	Iterator<Row> rowIterator = instituteSheet.iterator();
    	InstituteModel instituteModel=null;
    	
    	List<EducationTypeModel> educationTypeModels = getEducationTypeList("Primary School");
    	String poCategoryUdvId = getUdvIdByCategory(CategoryEnum.USER_CATEGORY.getName(), IConstant.PO);
    	
    	int i = 1;
    	try{
			while (rowIterator.hasNext()) {
				//Thread.sleep(2000);
				Row row = rowIterator.next();
				
				//Ignore heading row's value
				if("COM_NM".equals(IUtil.toString(row.getCell(0)))) {
					continue;
				}
				
				//Excel file's row end condition check
				if("".equals(IUtil.toString(row.getCell(0)))) {
					break;
				}
				
				if(i == 47){
					System.out.println("");
				}
					
				String instituteType = 		IUtil.toString(row.getCell(0));
				String regionName = 		IUtil.toString(row.getCell(1));
				String areaName	= 			IUtil.toString(row.getCell(2));
				String branchName = 		IUtil.toString(row.getCell(3));
				String districtName = 		IUtil.toString(row.getCell(4));
				String divisionName = 		IUtil.toString(row.getCell(5));
				
				int SLNO = 					(int)row.getCell(6).getNumericCellValue();
				String SLNAME =				IUtil.toString(row.getCell(7));
				
				String serialTp = 			IUtil.toString(row.getCell(8)); // not need now
				String gradeCode  = 			IUtil.toString(row.getCell(9));
				String openingDate = 		IUtil.toString(row.getCell(10));
				String sessionEnd =			IUtil.toString(row.getCell(11));
				int numberOfShift =		    (int)row.getCell(12).getNumericCellValue();
				String poName =	 			IUtil.toString(row.getCell(13));
				String donorId  = 			IUtil.toString(row.getCell(14));
				String locationType = 		IUtil.toString(row.getCell(15));
				String village = 			IUtil.toString(row.getCell(16));
				String union  = 			IUtil.toString(row.getCell(17));
				String thana  = 			IUtil.toString(row.getCell(18));
				int totalBoys = 			(int)row.getCell(19).getNumericCellValue();
				int totalGirls  = 		    (int)row.getCell(20).getNumericCellValue();
				int totalStudent  = 		(int)row.getCell(21).getNumericCellValue();
				int totalEthnic =		    (int)row.getCell(22).getNumericCellValue();
				int totalCsn = 			    (int)row.getCell(23).getNumericCellValue();
				String toiletAvailablity =	IUtil.toString(row.getCell(24));
				String electricity =		IUtil.toString(row.getCell(25));
				String nearestPublicSchool =IUtil.toString(row.getCell(26));
				String distanceFromNearestPublicSchool = IUtil.toString(row.getCell(27));
				String distanceFromSchoolToBranchOffice = IUtil.toString(row.getCell(28));
				
	
				instituteModel = new InstituteModel();
				instituteModel.setName(SLNAME);
				instituteModel.setCode(String.valueOf(SLNO));
				instituteModel.setOpeningDate(DateUtil.getDateString(DateUtil.getCalender(openingDate, DateUtil.DATE_FORMAT_DD_MMM_YY), DateUtil.DATE_FORMAT_YYYY_MM_DD));
				instituteModel.setSessionStart(DateUtil.getDateString(DateUtil.getCalender(openingDate, DateUtil.DATE_FORMAT_DD_MMM_YY), DateUtil.DATE_FORMAT_YYYY_MM_DD));
				instituteModel.setSessionEnd(DateUtil.getDateString(DateUtil.getCalender(sessionEnd, DateUtil.DATE_FORMAT_DD_MMM_YY), DateUtil.DATE_FORMAT_YYYY_MM_DD));
				instituteModel.setNumberOfShift(String.valueOf(numberOfShift));
				instituteModel.setInstituteTypeUdvId(getUdvIdByCategory(CategoryEnum.INSTITUTE_TYPE.getName(), instituteType));
				instituteModel.setTotalBoys(String.valueOf(totalBoys));
				instituteModel.setTotalGirls(String.valueOf(totalGirls));
				instituteModel.setTotalEthnic(String.valueOf(totalEthnic));
				instituteModel.setTotalCsn(String.valueOf(totalCsn));
				instituteModel.setTotalStudent(String.valueOf(totalStudent));
				instituteModel.setToiletAvailability((toiletAvailablity.equalsIgnoreCase("Y") == true)? "true" : "false");
				instituteModel.setElectricity((electricity.equalsIgnoreCase("Y") == true)? "true" : "false");
				instituteModel.setNearestPublicSchool(nearestPublicSchool);
				instituteModel.setDistanceFromNearestPublicSchool(distanceFromNearestPublicSchool);
				instituteModel.setDistanceFromSchoolToBranchOffice(distanceFromSchoolToBranchOffice);
				
				
				// 1. Get branch and village location
				LocationModel branchModel = locationInfoService.getBranch(regionName, areaName, branchName);
				LocationModel villageModel = locationInfoService.getVillage(divisionName, districtName , village);
				//LocationModel branchModel = new LocationModel();
				//branchModel.setId("45482248-2578-loct-a213-7ebdfae2c349");
				//branchModel.setHierarchyId(">45482245-8937-loct-ae8b-adf386b8717c>45482248-2517-loct-bb39-57c62592c316>45482248-2578-loct-a213-7ebdfae2c349>");
				
				if(branchModel != null){
					instituteModel.setLocationId(branchModel.getId());
					instituteModel.setLocationHierarchy(branchModel.getHierarchyId());
				}
				if(villageModel != null){
					instituteModel.setGovtLocationId(villageModel.getId());
					instituteModel.setGovtLocationHierarchy(villageModel.getHierarchyId());
				}
				
				// location type
				String locationTypeUdvId = getUdvIdByCategory(CategoryEnum.LOCATION_TYPE.getName(), locationType);
				instituteModel.setLocationTypeUdvId(locationTypeUdvId);
				
				// 2. Save PO information
				String poId = savePO(poName, branchModel, locationTypeUdvId, poCategoryUdvId);
				instituteModel.setPoId(poId);
				
				//3. Set education type id
				//instituteModel.setEducationTypeModelList(getEducationTypeList("Primary School"));
				instituteModel.setEducationTypeModelList(educationTypeModels);
				
				//4. Set current grade id
				instituteModel.setCurrentGradeId(getCurrentGradeId(gradeCode));
				
				
				ResponseModel resInstituteModel = instituteService.create(instituteModel);
				System.out.println(((InstituteModel)resInstituteModel.getModel()).getName());
				System.out.println("======================================================================================= " + i++);
			}
    	}catch(Throwable t){
    		t.printStackTrace();
    	}
    }
	
	private void readTeacher(XSSFSheet teacherSheet) throws IOException {
    	Iterator<Row> rowIterator = teacherSheet.iterator();
    	UserModel teacherModel = null;
    	int i = 1;
    	try{    		
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				
				//Ignore heading row's value
				if("COM_NM".equals(IUtil.toString(row.getCell(0)))) {
					continue;
				}
				
				//Excel file's row end condition check
				if("".equals(IUtil.toString(row.getCell(0)))) {
					break;
				}
				
				if(i == 44){
					System.out.println("");
				}
					
				String instituteType = 		IUtil.toString(row.getCell(0));
				String regionName = 		IUtil.toString(row.getCell(1));
				String areaName	= 			IUtil.toString(row.getCell(2));
				String branchName = 		IUtil.toString(row.getCell(3));
				String districtName = 		IUtil.toString(row.getCell(4));
				String divisionName = 		IUtil.toString(row.getCell(5));
				
				int SLNO = 					(int)row.getCell(6).getNumericCellValue();
				String SLNAME =				IUtil.toString(row.getCell(7));
				
				String teacherName = 		IUtil.toString(row.getCell(8)); // not need now
				String dateOfBirth  = 		IUtil.toString(row.getCell(9));
				String religion = 			IUtil.toString(row.getCell(10));
				String email =				IUtil.toString(row.getCell(11));
				String mobile =			    IUtil.toString(row.getCell(12));
				String gender =	 			IUtil.toString(row.getCell(13));
				String education  =			IUtil.toString(row.getCell(14));
				String fatherName = 		IUtil.toString(row.getCell(15));
				String NID = 				IUtil.toString(row.getCell(16));
				String MRST  = 				IUtil.toString(row.getCell(17));
				String HUSB_NM  = 			IUtil.toString(row.getCell(18));
				String PRES_ADD = 			IUtil.toString(row.getCell(19));
				String PERM_ADD  = 			IUtil.toString(row.getCell(20));
				String JDATE  = 			IUtil.toString(row.getCell(21));
				String BG_NM =				IUtil.toString(row.getCell(22));
				
				// 1. Get institute
				ResponseModel insResModel = instituteService.findByNameAndCode(SLNAME, String.valueOf(SLNO));
				if(IUtil.isResponseModelNotBlank(insResModel)){
					InstituteModel instituteModel = (InstituteModel)insResModel.getModel();
					
					
					teacherModel = new UserModel();
					teacherModel.setFirstName(IUtil.getFirstName(teacherName));
					teacherModel.setMiddleName(IUtil.getMiddleName(teacherName));
					teacherModel.setLastName(IUtil.getLastName(teacherName));
					teacherModel.setUsername(IUtil.getUserName(teacherName));
					teacherModel.setPassword("brac123");
					teacherModel.setMobileNumber(mobile);
					teacherModel.setEmail(email);
					teacherModel.setGender(gender);
					teacherModel.setMaritalStatus((MRST.equalsIgnoreCase("Y") == true)? "true" : "false");
					teacherModel.setDateOfBirth(DateUtil.getDateString(DateUtil.getCalender(dateOfBirth, DateUtil.DATE_FORMAT_DD_MMM_YY), DateUtil.DATE_FORMAT_YYYY_MM_DD));
					teacherModel.setBracGraduate((BG_NM.equalsIgnoreCase("Y") == true)? "true" : "false");
					
					LocationModel branchModel = locationInfoService.getBranch(regionName, areaName, branchName);
					if(branchModel != null){
						teacherModel.setLocationId(branchModel.getId());
						teacherModel.setLocationHierarchy(branchModel.getHierarchyId());
					}
					teacherModel.setLocationTypeUdvId(instituteModel.getLocationTypeUdvId());
					teacherModel.setInstituteId(instituteModel.getId());
					String userCategoryUdvId = getUdvIdByCategory(CategoryEnum.USER_CATEGORY.getName(), "Teacher");
					teacherModel.setUserCategoryUdvId(userCategoryUdvId);
					
					teacherModel.setFatherName(fatherName);
					teacherModel.setHusbandName(HUSB_NM);
					teacherModel.setReligion(religion);
					teacherModel.setEducation(education);
					teacherModel.setNid(NID);
					teacherModel.setPermanentAddress(PERM_ADD);
					teacherModel.setPresentAddress(PRES_ADD);
					teacherModel.setJoiningDate(DateUtil.getDateString(DateUtil.getCalender(JDATE, DateUtil.DATE_FORMAT_DD_MMM_YY), DateUtil.DATE_FORMAT_YYYY_MM_DD));
					
					
					ResponseModel exTeacherResponseModel = userService.findOnlyUserByUsername(teacherModel.getUsername());
					if(IUtil.isResponseModelNotBlank(exTeacherResponseModel)){
						teacherModel.setUsername(teacherModel.getUsername() + "_" + instituteModel.getCode());
					}
					
					
					ResponseModel teacherResponseModel = userService.create(teacherModel);
					System.out.println(((UserModel)teacherResponseModel.getModel()).getUsername());
					System.out.println("======================================================================================= " + i++);
				}
			}
    	}catch(Throwable t){
    		t.printStackTrace();
    	}
    }
	
	
	public void readStudent(XSSFSheet studentSheet) throws IOException {
    	Iterator<Row> rowIterator = studentSheet.iterator();
    	StudentModel studentModel = null;
    	
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			
			//Ignore heading row's value
			if("SLNO".equals(IUtil.toString(row.getCell(0)))) {
				continue;
			}
			
			//Excel file's row end condition check
			if("".equals(IUtil.toString(row.getCell(0)))) {
				break;
			}
			
			
			int SLNO = 					(int)row.getCell(0).getNumericCellValue();
			String SLNAME = 			IUtil.toString(row.getCell(1));
			String COM_NM	=			IUtil.toString(row.getCell(2));
			String REG_NM =		 		IUtil.toString(row.getCell(3));
			String AREA_NM = 			IUtil.toString(row.getCell(4));
			String BR_NM = 				IUtil.toString(row.getCell(5));
			
			int ST_SLNO = 				(int)row.getCell(6).getNumericCellValue();//IUtil.toString(row.getCell(6));
			String STU_NM =				IUtil.toString(row.getCell(7)); // Student Name
			
			String MOBILE = 			IUtil.toString(row.getCell(8));
			String GENDER  = 			IUtil.toString(row.getCell(9));
			String PERM_ADD = 			IUtil.toString(row.getCell(10)); // parmanent address
			String B_DATE =				IUtil.toString(row.getCell(11));
			String RELIG =				IUtil.toString(row.getCell(12)); //not need now
			String RELIG_NM =	 		IUtil.toString(row.getCell(13)); //religion 
			String FAT_NAME  = 			IUtil.toString(row.getCell(14)); //father name
			String FAT_EDU = 			IUtil.toString(row.getCell(15));
			String FAT_OCCU = 			IUtil.toString(row.getCell(16));
			String FAT_OCCU1  = 		IUtil.toString(row.getCell(17));
			String MOT_NAME  = 			IUtil.toString(row.getCell(18));
			String MOT_EDU = 			IUtil.toString(row.getCell(19));
			String MOT_OCCU  = 			IUtil.toString(row.getCell(20));
			String MOT_OCCU1  = 		IUtil.toString(row.getCell(21));
			String GRADE =				IUtil.toString(row.getCell(22));
			int ROLL = 					(int)row.getCell(23).getNumericCellValue();//IUtil.toString(row.getCell(23));
			String CSN_TP =				IUtil.toString(row.getCell(24));
			String ECON_TP =			IUtil.toString(row.getCell(25));
			String FMB_NM =				IUtil.toString(row.getCell(26));
			String BSERV = 				IUtil.toString(row.getCell(27));
			String DROP_NM = 			IUtil.toString(row.getCell(28));
			String F_NAME =	 			IUtil.toString(row.getCell(29));
			
			// 1. Get institute
			ResponseModel insResModel = instituteService.findByNameAndCode(SLNAME, String.valueOf(SLNO));
			if(IUtil.isResponseModelNotBlank(insResModel)){
				InstituteModel instituteModel = (InstituteModel)insResModel.getModel();
				
				studentModel = new StudentModel();
				studentModel.setInstituteId(instituteModel.getId());
				studentModel.setLocationTypeUdvId(instituteModel.getLocationTypeUdvId());
				studentModel.setLocationId(instituteModel.getLocationId());
				studentModel.setLocationHierarchy(instituteModel.getLocationHierarchy());
				studentModel.setGradeId(instituteModel.getCurrentGradeId());
				
				studentModel.setStudentFirstName(IUtil.getFirstName(STU_NM));
				studentModel.setStudentMiddleName(IUtil.getMiddleName(STU_NM));
				studentModel.setStudentLastName(IUtil.getLastName(STU_NM));
				studentModel.setStudentId(String.valueOf(ST_SLNO));
				studentModel.setRoll(String.valueOf(ROLL));
				studentModel.setSex(GENDER);
				studentModel.setTypeOfCsnUdvId(getUdvIdByCategory(CategoryEnum.CSN_TYPE.getName(), CSN_TP));
				studentModel.setResidentialAddress(PERM_ADD);
				studentModel.setDateOfBirth(DateUtil.getDateString(DateUtil.getCalender(B_DATE, DateUtil.DATE_FORMAT_DD_MMM_YY), DateUtil.DATE_FORMAT_YYYY_MM_DD));
				studentModel.setReligion(RELIG_NM);
				studentModel.setGuardianMobile(MOBILE);
				studentModel.setFatherName(FAT_NAME);
				studentModel.setFatherEducationalAttainment(FAT_EDU);
				studentModel.setFatherOccupation(FAT_OCCU);
				studentModel.setMotherName(MOT_NAME);
				studentModel.setMotherEducationalAttainment(MOT_EDU);
				studentModel.setMotherOccupation(MOT_OCCU1);
				studentModel.setFamilyMembersInvolveWithBrac((FMB_NM.equalsIgnoreCase("Y") == true)? "true" : "false");
				studentModel.setFamilyMembersInvolveWithBracService(BSERV);
				studentModel.setDropout((DROP_NM.equalsIgnoreCase("Y") == true)? "true" : "false");
				
				ResponseModel studentResponseModel = studentService.create(studentModel);
				System.out.println(((StudentModel)studentResponseModel.getModel()).getRoll());
				System.out.println("======================================================================================= ");
			}
			
		}
    }
	
	/**
	 * @param poName
	 * @param hierarchyId
	 */
	private String savePO(String poName, LocationModel branchModel, String locationTypeUdvId, String poCategoryUdvId) {
		
		UserModel userModel = new UserModel();
		userModel.setFirstName(IUtil.getFirstName(poName));
		userModel.setMiddleName(IUtil.getMiddleName(poName));
		userModel.setLastName(IUtil.getLastName(poName));
		userModel.setUsername(IUtil.getUserName(poName));
		
		ResponseModel resModel = userService.findOnlyUserByUsername(userModel.getUsername());
		if(resModel.getSuccess().equalsIgnoreCase(RestUtil.SUCCESS) && resModel.getModel() != null){
			userModel = (UserModel)resModel.getModel();
		}else{
			userModel = new UserModel();
			userModel.setFirstName(IUtil.getFirstName(poName));
			userModel.setMiddleName(IUtil.getMiddleName(poName));
			userModel.setLastName(IUtil.getLastName(poName));
			userModel.setUsername(IUtil.getUserName(poName));
			//userModel.setUsername(poName);
			userModel.setPassword("brac123");
			//userModel.setFirstName(poName);
//			userModel.setUserCategoryUdvId(getUdvIdByCategory(CategoryEnum.USER_CATEGORY.getName(), IConstant.PO));
			userModel.setUserCategoryUdvId(poCategoryUdvId);
			userModel.setDropOut("false");
			userModel.setLocationTypeUdvId(locationTypeUdvId);
			if(branchModel != null){
				userModel.setLocationId(branchModel.getId());
				userModel.setLocationHierarchy(branchModel.getHierarchyId());
			}
			ResponseModel res = userService.create(userModel);
			userModel = (UserModel)res.getModel();
		}
		
		return userModel.getId();
	}
	
	
	private String getUdvIdByCategory(String category, String value){
		try{
			if(StringUtils.isNotBlank(value)){
				ResponseModel resModel = udvService.findByCategoryAndName(category, value);
				UdvModel udv = null;
				if(IUtil.isResponseModelNotBlank(resModel)){
					udv = (UdvModel)resModel.getModel();				
				}else{
					udv = new UdvModel();
					udv.setCategory(category);
					udv.setValue(value);
					ResponseModel res = udvService.create(udv);
					udv = (UdvModel)res.getModel();
				}
				return udv.getId();
			}
		}catch(Throwable t){
			t.printStackTrace();
		}
		return "";
	}
	
	private List<EducationTypeModel> getEducationTypeList(String educationTypeName){
		List<EducationTypeModel> educationTypeModels = new ArrayList<>();
		try{
			ResponseModel resModel = educationTypeService.findByName(educationTypeName);
			EducationTypeModel educationTypeModel = null;
			if(IUtil.isResponseModelNotBlank(resModel)){
				educationTypeModel = (EducationTypeModel)resModel.getModel();
				educationTypeModels.add(educationTypeModel);
			}
		}catch(Throwable t){
			t.printStackTrace();
		}
		return educationTypeModels;
	}
	
	private String getCurrentGradeId(String code){
		try{
			if(StringUtils.isNotBlank(code)){
				ResponseModel resModel = gradeService.findByCode(code);
				GradeModel grade = null;
				if(IUtil.isResponseModelNotBlank(resModel)){
					grade = (GradeModel)resModel.getModel();				
				}else{
					grade = new GradeModel();
					grade.setName(code);
					grade.setCode(code);
					ResponseModel res = gradeService.create(grade);
					grade = (GradeModel)res.getModel();
				}
				return grade.getId();
			}
		}catch(Throwable t){
			t.printStackTrace();
		}
		return "";
	}

	public XSSFSheet createExcelSheet(String filePath, String sheetName){
    	File excelFile = null;
    	FileInputStream fileInputStream = null;
    	XSSFSheet sheet = null;;
    	ClassLoader classLoader = getClass().getClassLoader();
    	excelFile = new File(classLoader.getResource(filePath).getFile());
		try {
			fileInputStream = new FileInputStream(excelFile);
			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
			sheet = workbook.getSheet(sheetName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sheet;
    }

}
