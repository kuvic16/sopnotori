/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.okrur.st.jt.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.okrur.st.jt.data.domain.InstituteEducationType;
import com.okrur.st.jt.data.repository.InstituteEducationTypeRepository;
import com.okrur.st.jt.data.repository.TransactionHistoryRepository;
import com.okrur.st.jt.rest.model.InstituteEducationTypeModel;
import com.okrur.st.jt.rest.model.InstituteModel;
import com.okrur.st.jt.rest.model.LocationModel;
import com.okrur.st.jt.rest.model.UserModel;
import com.okrur.st.jt.service.LocationInfoService;


/**
 *
 * @author 154427
 */

@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class Read_Excel_Files{
   
	@Inject
	TransactionHistoryRepository transactionHistoryRepository;
	
	@Inject
	InstituteEducationTypeRepository instituteEducationTypeRepository;
	
	@Inject
	LocationInfoService locationInfoService;
	
	
	public String getRelationId(String nativeSql){
		String relationId;
		List list = transactionHistoryRepository.loadsByNativeQuery(nativeSql);
		Object objectList = list.get(0);
		Object[] objectArray = (Object[])objectList;
		relationId= IUtil.toString(objectArray[0]);
		return relationId;
	}
  
    public void readInstitute(XSSFSheet instituteSheet) throws IOException {
    	Iterator<Row> rowIterator = instituteSheet.iterator();
    	InstituteModel instituteModel=null;
    	List<InstituteModel> modelList = new ArrayList<InstituteModel>();
    	
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
			
			String name = 		 		IUtil.toString(row.getCell(0));
			String regionName = 		IUtil.toString(row.getCell(1));
			String areaName	= 			IUtil.toString(row.getCell(2));
			String branchName = 		IUtil.toString(row.getCell(3));
			String districtName = 		IUtil.toString(row.getCell(4));
			String divisionName = 		IUtil.toString(row.getCell(5));
			
			String SLNO = 				IUtil.toString(row.getCell(6));
			String SLNAME =				IUtil.toString(row.getCell(7));
			
			String serialTp = 			IUtil.toString(row.getCell(8)); // not need now
			String gradeId  = 			IUtil.toString(row.getCell(9));
			String openingDate = 		IUtil.toString(row.getCell(10));
			String sessionEnd =			IUtil.toString(row.getCell(11));
			String numberOfShift =		IUtil.toString(row.getCell(12));
			String poId =	 			IUtil.toString(row.getCell(13));
			String donorId  = 			IUtil.toString(row.getCell(14));
			String location = 			IUtil.toString(row.getCell(15));
			String village = 			IUtil.toString(row.getCell(16));
			String union  = 			IUtil.toString(row.getCell(17));
			String thana  = 			IUtil.toString(row.getCell(18));
			String totalBoys = 			IUtil.toString(row.getCell(19));
			String totalGirls  = 		IUtil.toString(row.getCell(20));
			String totalStudent  = 		IUtil.toString(row.getCell(21));
			String totalEthnic =		IUtil.toString(row.getCell(22));
			String totalCsn = 			IUtil.toString(row.getCell(23));
			String toiletAvailablity =	IUtil.toString(row.getCell(24));
			String electricity =		IUtil.toString(row.getCell(25));
			String nearestPublicSchool =IUtil.toString(row.getCell(26));
			String distanceFromNearestPublicSchool = IUtil.toString(row.getCell(27));
			

			instituteModel = new InstituteModel();
			instituteModel.setName(name);
			instituteModel.setOpeningDate(openingDate);
			instituteModel.setSessionStart(openingDate);
			instituteModel.setSessionEnd(sessionEnd);
			instituteModel.setNumberOfShift(numberOfShift);
			
			
			LocationModel branchModel = locationInfoService.getBranch(regionName, areaName, branchName);
			System.out.println(branchModel.getHierarchyId());
			
			
			/*
			SELECT eduTypeGrade.education_type_id FROM education_type_grade eduTypeGrade , grade grd
			WHERE eduTypeGrade.grade_id = grd.id
			AND grd.`name` = 'NURSERY'
			*/
			
//			StringBuilder sql = new StringBuilder();
//			sql.append("SELECT eduTypeGrade.education_type_id FROM education_type_grade eduTypeGrade , grade grd ")
//				.append("WHERE eduTypeGrade.grade_id = grd.id ")
//				.append("AND grd.`name` = 'NURSERY'");
//									
//			String strId = getRelationId(sql.toString());
//			
//			InstituteEducationTypeModel model = new InstituteEducationTypeModel();
//			model.setEducationTypeId(strId);
//			model.setInstituteId(instituteModel.getId());
//			
//			InstituteEducationType instituteEducationType = new InstituteEducationType();
//			instituteEducationType = instituteEducationType.setInstituteEducationType(model);
//			instituteEducationTypeRepository .create(instituteEducationType);
			
			 //  .append("WHERE ins.id = '" + fee.getInstituteId() + "'");
			
			//instituteModel.setInstituteTypeUdvId(instituteTypeUdvId);
			//instituteModel.set
		}
    }
    
    public void readStudent(XSSFSheet studentSheet) throws IOException {
    	Iterator<Row> rowIterator = studentSheet.iterator();
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
			
			
			String SLNO = 		 		IUtil.toString(row.getCell(0));
			String SLNAME = 			IUtil.toString(row.getCell(1));
			String COM_NM	=			IUtil.toString(row.getCell(2));
			String REG_NM =		 		IUtil.toString(row.getCell(3));
			String AREA_NM = 			IUtil.toString(row.getCell(4));
			String BR_NM = 				IUtil.toString(row.getCell(5));
			
			String ST_SLNO = 			IUtil.toString(row.getCell(6));
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
			String ROLL = 				IUtil.toString(row.getCell(23));
			String CSN_TP =				IUtil.toString(row.getCell(24));
			String ECON_TP =			IUtil.toString(row.getCell(25));
			String FMB_NM =				IUtil.toString(row.getCell(26));
			String BSERV = 				IUtil.toString(row.getCell(27));
			String DROP_NM = 			IUtil.toString(row.getCell(28));
			String F_NAME =	 			IUtil.toString(row.getCell(29));
			
		}
    }
    
    public void readUser(XSSFSheet userSheet) throws IOException {
    	Iterator<Row> rowIterator = userSheet.iterator();
    	UserModel userModel=null;
    	List<UserModel> modelList = new ArrayList<UserModel>();
    	
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
			
			String instituteName = 		IUtil.toString(row.getCell(0));
			String regionName = 		IUtil.toString(row.getCell(1));
			String areaName	= 			IUtil.toString(row.getCell(2));
			String branchName = 		IUtil.toString(row.getCell(3));
			String districtName = 		IUtil.toString(row.getCell(4));
			String divisionName = 		IUtil.toString(row.getCell(5));
			
			String SLNO = 				IUtil.toString(row.getCell(6));
			String SLNAME =				IUtil.toString(row.getCell(7));
			
			String TCH_NM = 			IUtil.toString(row.getCell(8)); // not need now
			String B_DATE  = 			IUtil.toString(row.getCell(9));
			String RELIG = 				IUtil.toString(row.getCell(10));
			String EMIL =				IUtil.toString(row.getCell(11));
			String MOBILE =				IUtil.toString(row.getCell(12));
			String GENDER =	 			IUtil.toString(row.getCell(13));
			String EDU  = 				IUtil.toString(row.getCell(14));
			String FAT_NAME = 			IUtil.toString(row.getCell(15));
			String NID = 				IUtil.toString(row.getCell(16));
			String MRST  = 				IUtil.toString(row.getCell(17));
			String HUSB_NM  = 			IUtil.toString(row.getCell(18));
			String PRES_ADD = 			IUtil.toString(row.getCell(19));
			String PERM_ADD  = 			IUtil.toString(row.getCell(20));
			String JDATE  = 			IUtil.toString(row.getCell(21));
			String BG_NM =				IUtil.toString(row.getCell(22));
			String F_NAME = 			IUtil.toString(row.getCell(23));
			
			

			userModel = new UserModel();
			userModel.setUsername(TCH_NM);
			userModel.setEmail(EMIL);
			userModel.setMobileNumber(MOBILE);
			userModel.setGender(GENDER);
			
			
			
		}
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
    
    
    public void readExcelFile(){ 
    	/*File excelFile;
    	FileInputStream fileInputStream;*/
    	XSSFSheet instituteSheet, studentSheet, teacherSheet;
    	instituteSheet = createExcelSheet("Excel_Files/School_Information.xlsx", "School_Information" );
    	//studentSheet = createExcelSheet("Excel_Files/Student_Information.xlsx", "Student_Information" );
    	//teacherSheet = createExcelSheet("Excel_Files/Teachers_Information.xlsx", "Teachers_Information");
    	
    	try {
			readInstitute(instituteSheet);
			//readStudent(studentSheet);
    		//readUser(teacherSheet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
