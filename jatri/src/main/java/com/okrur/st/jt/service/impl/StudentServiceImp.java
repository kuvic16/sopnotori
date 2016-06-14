package com.okrur.st.jt.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.okrur.st.jt.data.domain.Student;
import com.okrur.st.jt.data.repository.StudentFeeRepository;
import com.okrur.st.jt.data.repository.StudentRepository;
import com.okrur.st.jt.rest.model.InstituteModel;
import com.okrur.st.jt.rest.model.ResponseModel;
import com.okrur.st.jt.rest.model.StudentModel;
import com.okrur.st.jt.rest.model.UserModel;
import com.okrur.st.jt.security.SecurityUtil;
import com.okrur.st.jt.service.InstituteService;
import com.okrur.st.jt.service.StudentService;
import com.okrur.st.jt.service.UserService;
import com.okrur.st.jt.util.IUtil;
import com.okrur.st.jt.util.RestUtil;

/**
 * @File StudentServiceImp.java: StudentServiceImp Implementation for StudentService
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 29, 2015
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class StudentServiceImp implements StudentService{
	@Resource
	private EJBContext context;
	
	@Inject
	StudentRepository studentRepository ;
	
	@Inject
	UserService userService;
	
	@Inject
	InstituteService instituteService;
	
	@Inject
	StudentFeeRepository studentFeeRepository;
	
	
	@Override
	public ResponseModel create(StudentModel studentModel) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_STUDENT, RestUtil.REQUEST_TYPE_CREATE);
		try{
			// TODO validation
			//grade = grade.setGrade(gradeModel);
			Student student = new Student();
			student.setStudent(studentModel);
			
			ResponseModel resModel = instituteService.findById(student.getInstituteId());
			
			if(resModel.getSuccess().equalsIgnoreCase(RestUtil.SUCCESS)){
				InstituteModel instituteModel = (InstituteModel )resModel.getModel();
				student.setLocationTypeUdvId(instituteModel.getLocationTypeUdvId());
				student.setLocationId(instituteModel.getLocationId());
				student.setLocationHierarchy(instituteModel.getLocationHierarchy());
				student.setLatitude(instituteModel.getLatitude());
				student.setLongitude(instituteModel.getLongitude());
				
				studentRepository.create(student);
				response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new StudentModel(student));
			}else{
				response.setResponse(RestUtil.ERROR, RestUtil.SCHOOL_NOT_FOUND_ERROR_MESSAGE, null);
			}
			
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel update(String id, StudentModel studentModel) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_STUDENT, RestUtil.REQUEST_TYPE_UPDATE);
		try{
			// TODO validation
			
			Student student = studentRepository.findById(id);
			student = student.setStudent(studentModel);
			ResponseModel resModel = instituteService.findById(student.getInstituteId());
			
			if(resModel.getSuccess().equalsIgnoreCase(RestUtil.SUCCESS)){
				InstituteModel instituteModel = (InstituteModel )resModel.getModel();
				student.setLocationTypeUdvId(instituteModel.getLocationTypeUdvId());
				student.setLocationId(instituteModel.getLocationId());
				student.setLocationHierarchy(instituteModel.getLocationHierarchy());
				student.setLatitude(instituteModel.getLatitude());
				student.setLongitude(instituteModel.getLongitude());
				
				studentRepository.edit(student);
				response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new StudentModel(student));
			}
			response.setResponse(RestUtil.ERROR, RestUtil.SCHOOL_NOT_FOUND_ERROR_MESSAGE, null);
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_STUDENT, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			List<Student> list = studentRepository .findRange(startPosition, maxResult);
			List<StudentModel> modelList = new ArrayList<StudentModel>();
			for(Student student : list){
				StudentModel model = new StudentModel(student);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.studentRepository .count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel findById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_STUDENT, RestUtil.REQUEST_TYPE_DELETE);
		try{
			Student student = studentRepository .findById(id);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new StudentModel(student));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel deleteById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_STUDENT, RestUtil.REQUEST_TYPE_DELETE);
		try{
			Student student = this.studentRepository .findById(id);
			int count;			
			StringBuilder sql = new StringBuilder();
			
			sql.append(" DELETE FROM student WHERE student.id = ")
			.append("'" + student.getId() + "'");
			count = studentRepository.removeByNativeQuery(sql.toString());	
			
			sql.setLength(0);
			
			sql.append(" DELETE FROM student_fee WHERE student_fee.student_id = ")
			.append("'" + student.getId() + "'");
			count = studentFeeRepository.removeByNativeQuery(sql.toString());	
			
			//studentRepository .remove(student);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, null);
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String fullName, String studentId, String gradeId, String sex, String waiver, String guardianMobile, 
									String dropout, String nameOfTransferredSchool, String instituteId, String typeOfEthnicityCommunityUdvId, String typeOfCsnUdvId,
									String locationTypeUdvId, String locationId, String locationHierarchy){
		ResponseModel response = new ResponseModel(RestUtil.MODULE_STUDENT, RestUtil.REQUEST_TYPE_LIST);
		try{
			UserModel loggedUserModel = SecurityUtil.getCurrentLoggedUser();
			
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			
			boolean needAnd = false;
			boolean needWhere= true;
			boolean locationIncluded = false;
			StringBuilder sql = new StringBuilder();
						
			sql.append("SELECT ")
			   .append("std.id, ")
			   .append("CONCAT(COALESCE( std.first_name, ' '), ' ', COALESCE(std.middle_name,' '), ' ', COALESCE(std.last_name,' ')) name, ")
			   .append("std.student_id roll_number, ")
			   .append("grd.name grade_name, ")
			   .append("ins.name institute_name, ")
			   .append("std.sex Gender, ")
			   .append("std.waiver, ")
			   .append("std.guardian_mobile, ")
			   .append("std.father_name, ")
			   .append("std.dropout, ")
			   .append("std.name_of_transferred_school ")
			   .append("FROM student std ")
			   .append(" join institute ins on (ins.id =  std.institute_id) ")
			   .append(" left join grade grd on (grd.id = std.grade_id) ");
			
			
			
			if(StringUtils.isNotBlank(locationTypeUdvId)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND "); 
				sql.append("std.location_type_udv_id = '" + locationTypeUdvId + "'");
				needAnd = true; needWhere = false;
			}
			
			if(StringUtils.isNotBlank(instituteId)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND ");
				sql.append("std.institute_id = '" + instituteId + "'");
				needAnd = true; needWhere = false;
			}
			
			if(StringUtils.isNotBlank(locationHierarchy)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND ");
				sql.append("std.location_hierarchy LIKE '%" + locationHierarchy + "%'");
				needAnd = true; needWhere = false;
				locationIncluded = true;
			}

			
			if(StringUtils.isNotBlank(fullName)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND ");
				
				sql.append("(CONCAT(std.first_name,' ', std.middle_name,' ',std.last_name) LIKE '%" + fullName + "%'" )
				.append(" OR CONCAT(std.first_name,' ', std.last_name) LIKE '%" +fullName+"%')");
				
				needAnd = true; needWhere = false;
			}
			
			if(StringUtils.isNotBlank(studentId)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND ");
				sql	.append("std.student_id = '" + studentId + "'");
				needAnd = true; needWhere = false;
			}
			
			if(StringUtils.isNotBlank(gradeId)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND ");
				sql	.append("std.grade_id = '" + gradeId + "'");
				needAnd = true; needWhere = false;
			}
			
			if(StringUtils.isNotBlank(sex)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND ");
				sql	.append("std.sex = '" + sex + "'");
				needAnd = true; needWhere = false;
			}
			
			if(StringUtils.isNotBlank(waiver)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND ");
				sql	.append("std.waiver = '" + waiver + "'");
				needAnd = true; needWhere = false;
			}
			
			if(StringUtils.isNotBlank(guardianMobile)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND ");
				sql	.append("std.guardian_mobile LIKE '%" + guardianMobile + "%'");
				needAnd = true; needWhere = false;
			}
			
			if(StringUtils.isNotBlank(dropout)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND ");
				sql	.append("std.dropout = " + Boolean.parseBoolean(dropout));
				needAnd = true; needWhere = false;
			}
			
			if(StringUtils.isNotBlank(typeOfEthnicityCommunityUdvId)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND ");
				sql	.append("std.type_of_ethnicity_community_udv_id = '" + typeOfEthnicityCommunityUdvId +"'" );
				needAnd = true; needWhere = false;
			}
			
			if(StringUtils.isNotBlank(typeOfCsnUdvId)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND ");
				sql	.append("std.type_of_csn_udv_id = '" + typeOfCsnUdvId +"'" );
				needAnd = true; needWhere = false;
			}
							
			if(StringUtils.isNotBlank(nameOfTransferredSchool)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND ");
				sql	.append("std.name_of_transferred_school = '" + nameOfTransferredSchool + "'");
				needAnd = true; needWhere = false;
			}				
	
		
			if(!loggedUserModel.isAdmin() && StringUtils.isNotBlank(loggedUserModel.getLocationId()) && !locationIncluded){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND ");
				sql.append(" std.location_hierarchy LIKE '>%" + loggedUserModel.getLocationId() + ">%'");
				needAnd = true; needWhere = false;
			}
			
			if(loggedUserModel.isPo()){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND ");
				sql.append("ins.po_id = '").append(loggedUserModel.getId()).append("'");
				needAnd = true; needWhere = false;
			}
			
			System.out.println(sql.toString());
			
			int totalSize = studentRepository.countByJNQ(sql.toString());
			
			sql.append(" limit ")
			   .append(startPosition * maxResult)
			   .append(",")
			   .append(maxResult);
			
			
			List list = studentRepository.loadsByNativeQuery(sql.toString());
			List<StudentModel> modelList = new ArrayList<StudentModel>();
			
			for(Object object : list){
				Object[] objs = (Object[])object;
				StudentModel model = new StudentModel();
								
				model.setId(IUtil.toString(objs[0]));
				model.setFullName(IUtil.toString(objs[1]));	
				model.setStudentId(IUtil.toString(objs[2]));			
				model.setGradeId(IUtil.toString(objs[3]));				
				model.setInstituteId(IUtil.toString(objs[4]));				
				model.setSex(IUtil.toString(objs[5]));
				model.setWaiver(IUtil.toString(objs[6]));
				model.setGuardianMobile(IUtil.toString(objs[7]));
				model.setFatherName(IUtil.toString(objs[8]));
				model.setDropout(IUtil.toString(objs[9]));
				model.setNameOfTransferredSchool(IUtil.toString(objs[10]));
				
				
				modelList.add(model);
			}
			
			response.setTotal(String.valueOf(totalSize));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listBy(String instituteId, String gradeId) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_STUDENT, RestUtil.REQUEST_TYPE_LIST);
		try{
			StringBuilder sql = new StringBuilder();
			sql.append(" select ")
			   .append(" std.id, ")
			   .append(" CONCAT(COALESCE( std.first_name, ' '), ' ', COALESCE(std.middle_name,' '), ' ', COALESCE(std.last_name,' ')) name, ")
			   .append(" std.student_id roll_number")
			   .append(" from student std ")
			   .append(" where std.institute_id = '").append(instituteId).append("'")
			   .append(" and std.grade_id = '").append(gradeId).append("'")
			   .append(" and std.dropout = 0");
			
			@SuppressWarnings("rawtypes")
			List list = studentRepository.loadsByNativeQuery(sql.toString());
			List<StudentModel> modelList = new ArrayList<StudentModel>();
			
			for(Object object : list){
				Object[] objs = (Object[])object;
				StudentModel model = new StudentModel();

				model.setId(IUtil.toString(objs[0]));
				model.setFullName(IUtil.toString(objs[1]));
				model.setStudentId(IUtil.toString(objs[2]));			
				
				modelList.add(model);
			}
			
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel findByRoll(String studentRoll) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_STUDENT, RestUtil.REQUEST_TYPE_DETAILS);
		try{
			StringBuilder clause = new StringBuilder();
			clause.append("t.studentId = ?1");
			Object[] params = new Object[]{studentRoll};
			
			List<Student> list = studentRepository.loadByClause(clause.toString(), params);
			StudentModel model = null;
			if(list != null && list.size()>0){
				model = new StudentModel(list.get(0));
			}
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, model);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

}
