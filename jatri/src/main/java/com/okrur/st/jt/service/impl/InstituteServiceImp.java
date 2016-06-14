package com.okrur.st.jt.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.okrur.st.jt.data.domain.Institute;
import com.okrur.st.jt.data.repository.InstituteRepository;
import com.okrur.st.jt.data.repository.UserRepository;
import com.okrur.st.jt.rest.model.EducationTypeModel;
import com.okrur.st.jt.rest.model.InstituteEducationTypeModel;
import com.okrur.st.jt.rest.model.InstituteModel;
import com.okrur.st.jt.rest.model.ResponseModel;
import com.okrur.st.jt.rest.model.UdvModel;
import com.okrur.st.jt.rest.model.UserModel;
import com.okrur.st.jt.security.SecurityUtil;
import com.okrur.st.jt.service.InstituteEducationTypeService;
import com.okrur.st.jt.service.InstituteService;
import com.okrur.st.jt.service.UdvService;
import com.okrur.st.jt.service.UserService;
import com.okrur.st.jt.util.IConstant;
import com.okrur.st.jt.util.IUtil;
import com.okrur.st.jt.util.RestUtil;

/**
 * @File InstituteServiceImp.java: InstituteServiceImp Implementation for InstituteService
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 29, 2015
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class InstituteServiceImp implements InstituteService{
	@Resource
	private EJBContext context;
	
	@Inject
	InstituteRepository instituteRepository ;
	@EJB
	UserRepository userRepository;
	
	@EJB
	private InstituteEducationTypeService instituteEducationTypeService;
	
	@Inject
	private UserService userService;
	
	@Inject
	private UdvService udvService;
	
	@Override
	public ResponseModel create(InstituteModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_INSTITUTE, RestUtil.REQUEST_TYPE_CREATE);
		try{
			// TODO validation
			Institute institute = new Institute().setInstitute(model);		
			instituteRepository .create(institute);
			createInstituteEducationType(institute.getId(),model.getEducationTypeModelList());
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new InstituteModel(institute));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel update(String id, InstituteModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_INSTITUTE, RestUtil.REQUEST_TYPE_UPDATE);
		try{
			// TODO validation
			Institute institue = new Institute();
			institue = instituteRepository.findById(id);
			institue = institue.setInstitute(model);
			instituteRepository .edit(institue);
			
			// Delete InstituteEducationType Start
			ResponseModel ietDeleteModel = instituteEducationTypeService.deleteByEducationTypeId(institue.getId());
			if(ietDeleteModel.getSuccess().equalsIgnoreCase(RestUtil.SUCCESS)){
				createInstituteEducationType(institue.getId(), model.getEducationTypeModelList());
			}
			// Delete InstituteEducationType End 
			
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new InstituteModel(institue));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_INSTITUTE, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			List<Institute> list = instituteRepository .findRange(startPosition, maxResult);
			List<InstituteModel> modelList = new ArrayList<InstituteModel>();
			for(Institute grade : list){
				InstituteModel model = new InstituteModel(grade);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.instituteRepository .count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel findById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_INSTITUTE, RestUtil.REQUEST_TYPE_DELETE);
		try{
			Institute institute = instituteRepository .findById(id);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new InstituteModel(institute));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel deleteById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_INSTITUTE, RestUtil.REQUEST_TYPE_DELETE);
		try{
			Institute institute = this.instituteRepository .findById(id);
			instituteRepository .remove(institute);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, null);
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String name, String code, String instituteTypeUdvId, String educationTypeId, String poId, String locationTypeUdvId, String projectCodeUdvId, String locationId, String locationHierarchy){
		ResponseModel response = new ResponseModel(RestUtil.MODULE_INSTITUTE, RestUtil.REQUEST_TYPE_LIST);
		try{
			
			UserModel loggedUserModel = SecurityUtil.getCurrentLoggedUser();
			String PO=null;
			
			 if(!loggedUserModel.getUsername().equalsIgnoreCase(IConstant.ADMIN_USER)){
				ResponseModel udvResponseModel = udvService.findById(loggedUserModel.getUserCategoryUdvId());
				UdvModel userTypeCategoryUdv = (UdvModel)udvResponseModel.getModel();
				PO = userTypeCategoryUdv.getValue();
			 }
			 if(!loggedUserModel.getUsername().equalsIgnoreCase(IConstant.ADMIN_USER) && PO.equals("PO")){
			    poId = loggedUserModel.getId();
			}
			
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			
			boolean needAnd = false;
			boolean locationIncluded = false;
							
			boolean whereClause=false;
			StringBuilder sql = new StringBuilder();
			
			sql.append("SELECT DISTINCT ins.id, ins.name, ins.code, ")
			   .append("(SELECT GROUP_CONCAT(edt.name SEPARATOR ' , ') ")
			   .append("FROM education_type edt, institute_education_type inedt ")
			   .append("WHERE inedt.education_type_id = edt.id ")
			   .append("AND inedt.institute_id = ins.id ");
			
			if(!StringUtils.isEmpty(educationTypeId))
				sql.append("AND inedt.education_type_id = '" + educationTypeId + "' " );
			   
			sql.append("GROUP BY inedt.institute_id) as Education_Types, ")
			   .append("ins.session_start, ")
			   .append("ins.session_end, ")
			   .append("(SELECT  usr.username ")
			   .append("FROM users usr ")
			   .append("WHERE usr.id = ins.po_id) AS User_Name , ")
			   .append("ins.total_student ");
			
			if (StringUtils.isEmpty(educationTypeId)){
				sql.append(" FROM institute ins ");
			}
			else {
				whereClause = true;
				needAnd = true;
				sql.append(" FROM institute ins , institute_education_type insEduType , education_type eduType ")
						.append(" WHERE ins.id = insEduType.institute_id ")
						.append(" AND eduType.id = '" + educationTypeId + "'")
						.append(" AND insEduType.education_type_id = eduType.id ");
			}
					
			
			
			if( 
					!StringUtils.isEmpty(name) || 
					!StringUtils.isEmpty(code) || 
					!StringUtils.isEmpty(instituteTypeUdvId) || 
					!StringUtils.isEmpty(poId) ||
					!StringUtils.isEmpty(locationTypeUdvId) ||
					!StringUtils.isEmpty(projectCodeUdvId) ||
					!StringUtils.isEmpty(educationTypeId)||
					//!StringUtils.isEmpty(locationId) ||
					!StringUtils.isEmpty(locationHierarchy)
				)
			{
				
				if(!whereClause){
					sql.append("WHERE ");
					whereClause=true;
				}
				//set search criteria
				if(StringUtils.isNotBlank(name)){
					if(needAnd){
						sql.append(" AND ");
					}
					sql.append("ins.name like '%").append(name).append("%'");
					needAnd = true;
				}
				
				if(StringUtils.isNotBlank(code)){
					if(needAnd){
						sql.append(" AND ");
					}
					sql.append("ins.code like '%").append(code).append("%'");
					needAnd = true;
				}
				
				if(StringUtils.isNotBlank(instituteTypeUdvId)){
					if(needAnd){
						sql.append(" AND ");
					}
					sql.append("ins.institute_type_udv_id ='").append(instituteTypeUdvId).append("'");
					needAnd = true;
				}
				
				/*if(StringUtils.isNotBlank(educationTypeId)){
					if(needAnd){
						sql.append(" AND ");
					}
					sql.append("ins.institute_type_udv_id like '").append(educationTypeId).append("'");
					needAnd = true;
				}*/
								
				if(StringUtils.isNotBlank(poId)){
					if(needAnd){
						sql.append(" AND ");
					}
					sql.append("ins.po_id = '").append(poId.trim()).append("'");
					needAnd = true;
				}
				
				if(StringUtils.isNotBlank(locationTypeUdvId)){
					if(needAnd){
						sql.append(" AND ");
					}
					sql.append("ins.location_type_udv_id = '").append(locationTypeUdvId).append("'");
					needAnd = true;
				}
				
				if(StringUtils.isNotBlank(projectCodeUdvId)){
					if(needAnd){
						sql.append(" AND ");
					}
					sql.append("ins.project_code_udv_id = '").append(projectCodeUdvId).append("'");
					needAnd = true;
				}
				
				/*if(StringUtils.isNotBlank(locationId)){
					if(needAnd){
						sql.append(" AND ");
					}
					sql.append("ins.location_id like '%").append(locationId).append("%'");
					needAnd = true;
				}*/
				
				if(StringUtils.isNotBlank(locationHierarchy)){
					if(needAnd){
						sql.append(" AND ");
					}
					sql.append(" ins.location_hierarchy like '%").append(locationHierarchy.trim()).append("%'");
					needAnd = true;
					locationIncluded = true;
				}
			
			}
			
			if(!loggedUserModel.getUsername().equalsIgnoreCase(IConstant.ADMIN_USER) && StringUtils.isNotBlank(loggedUserModel.getLocationId())){
				if(!whereClause){
					sql.append(" WHERE ");
				}
				
				if(!locationIncluded){
					if(needAnd){
						sql.append(" AND ");
					}
					sql.append(" ins.location_hierarchy like '>%").append(loggedUserModel.getLocationId()).append(">%'");
				}
			}
			
			int totalSize = instituteRepository.countByJNQ(sql.toString());
			
			sql.append(" limit ")
			   .append(startPosition * maxResult)
			   .append(",")
			   .append(maxResult);
			
			
			List list = instituteRepository.loadsByNativeQuery(sql.toString());
			List<InstituteModel> modelList = new ArrayList<InstituteModel>();
			
			System.out.println(list.size());
			for(Object object : list){
				Object[] objs = (Object[])object;
				InstituteModel model = new InstituteModel();
				model.setId(IUtil.toString(objs[0]));
				model.setName(IUtil.toString(objs[1]));				
				model.setCode(IUtil.toString(objs[2]));				
				model.setEducationTypeName(IUtil.toString(objs[3]));				
				model.setSessionStart(IUtil.toString(objs[4]));
				model.setSessionEnd(IUtil.toString(objs[5]));
				model.setPoId(IUtil.toString(objs[6])); // at actually set the name
				model.setTotalStudent(IUtil.toString(objs[7]));
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
	public ResponseModel listAll() {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_INSTITUTE, RestUtil.REQUEST_TYPE_LIST);
		try{
			UserModel loggedUserModel = SecurityUtil.getCurrentLoggedUser();
			StringBuilder whereClause= new StringBuilder();
			List<Institute> list;
			Object[] params = {"%>" + loggedUserModel.getLocationId() + ">%"};
			
			if(!loggedUserModel.getUsername().equalsIgnoreCase(IConstant.ADMIN_USER))
			{
				whereClause.append("t.locationHierarchy LIKE ?1");
				list = instituteRepository.findAll(whereClause.toString(), params);
			}
			else
			{
				list = instituteRepository.findAll();
			}
			
			List<InstituteModel> modelList = new ArrayList<InstituteModel>();
			for(Institute institute : list){
				InstituteModel model = new InstituteModel(institute);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.instituteRepository.count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listAll(String locationHierarchy ) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_INSTITUTE, RestUtil.REQUEST_TYPE_LIST);
		try{
			UserModel loggedUserModel = SecurityUtil.getCurrentLoggedUser();
			
			StringBuilder whereClause= new StringBuilder();
			List<Institute> list;
			Object[] params = {"%" + locationHierarchy + "%"};
						
			whereClause.append("t.locationHierarchy LIKE ?1");
			if(loggedUserModel.isPo()){
				whereClause.append(" and t.poId LIKE '").append(loggedUserModel.getId()).append("'");
			}
			
			list = instituteRepository.findAll(whereClause.toString(), params);
		
			List<InstituteModel> modelList = new ArrayList<InstituteModel>();
			for(Institute institute : list){
				InstituteModel model = new InstituteModel(institute);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.instituteRepository.count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	private void createInstituteEducationType(String instituteId, List<EducationTypeModel> educationTypeModelList){
		if(StringUtils.isNoneBlank(instituteId) && educationTypeModelList != null && educationTypeModelList.size()>0){
			for(EducationTypeModel educationTypeModel : educationTypeModelList){
				InstituteEducationTypeModel instituteEducationTypeModel = new InstituteEducationTypeModel();
				instituteEducationTypeModel.setInstituteId(instituteId);
				instituteEducationTypeModel.setEducationTypeId(educationTypeModel.getId());
				instituteEducationTypeService.create(instituteEducationTypeModel);
			}
		}
	}

	
	@Override
	public ResponseModel findByNameAndCode(String name, String code) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_INSTITUTE, RestUtil.REQUEST_TYPE_FIND);
		try{
			String clause = "t.name = ?1 and t.code= ?2";
			Object[] params = new Object[]{name, code};
			
			List<Institute> list = new ArrayList<Institute>();
			list = instituteRepository.loadByClause(clause, params);	
			if(list != null && list.size() > 0){
				Institute institute = list.get(0);
				
				InstituteModel model = new InstituteModel(institute);
				response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, model);
			}else{
				response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
			}
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public List<Institute> findAll(String locationTypeUdvId, String locationHierarchy, String gradeId) {
		try{
			UserModel loggedUserModel = SecurityUtil.getCurrentLoggedUser();
			StringBuilder whereClause= new StringBuilder();			
			boolean andNeed = false, whereNeed = true;
			
			whereClause.append("Select t from Institute t ");
			if(StringUtils.isNotBlank(locationHierarchy)){
				if(whereNeed) whereClause.append(" where ");
				whereClause.append(" t.locationHierarchy LIKE '%").append(locationHierarchy).append("%'");
				andNeed = true; whereNeed = false;
			}else if(!loggedUserModel.isAdmin()){
				if(whereNeed) whereClause.append(" where ");
				whereClause.append(" t.locationHierarchy LIKE '%").append(loggedUserModel.getLocationHierarchy()).append("%'");
				andNeed = true; whereNeed = false;
			}
			
			if(loggedUserModel.isPo()){
				if(whereNeed) whereClause.append(" where ");
				if(andNeed) whereClause.append(" and ");
				whereClause.append(" t.poId = '").append(loggedUserModel.getId()).append("'");
				andNeed = true; whereNeed = false;
			}
			
			if(StringUtils.isNotBlank(locationTypeUdvId)){
				if(whereNeed) whereClause.append(" where ");
				if(andNeed) whereClause.append(" and ");
				whereClause.append(" t.locationTypeUdvId = '").append(locationTypeUdvId).append("'");
				andNeed = true; whereNeed = false;
			}
			
			if(StringUtils.isNotBlank(gradeId)){
				if(whereNeed) whereClause.append(" where ");
				if(andNeed) whereClause.append(" and ");
				whereClause.append(" t.currentGradeId = '").append(gradeId).append("'");
				andNeed = true; whereNeed = false;
			}
			
			return instituteRepository.findAll(whereClause.toString());				
		}catch(Throwable t){
			
		}
		return null;
	}
	
	
	
}
