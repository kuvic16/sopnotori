package com.okrur.st.jt.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.okrur.st.jt.data.domain.EducationTypeGrade;
import com.okrur.st.jt.data.domain.InstituteEducationType;
import com.okrur.st.jt.data.repository.InstituteEducationTypeRepository;
import com.okrur.st.jt.rest.model.InstituteEducationTypeModel;
import com.okrur.st.jt.rest.model.ResponseModel;
import com.okrur.st.jt.service.InstituteEducationTypeService;
import com.okrur.st.jt.util.RestUtil;

/**
 * @File InstituteEducationTypeServiceImp.java: InstituteEducationTypeServiceImp Implementation for InstituteEducationTypeService
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Jan 04, 2016
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class InstituteEducationTypeServiceImp implements InstituteEducationTypeService{
	
	@Inject
	InstituteEducationTypeRepository instituteEducationTypeRepository ;
	
	
	
	@Override
	public ResponseModel create(InstituteEducationTypeModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_INSTITUTE_EDUCATION_TYPE, RestUtil.REQUEST_TYPE_CREATE);
		try{
			// TODO validation
			InstituteEducationType instituteEducationType = new InstituteEducationType();
			instituteEducationType = instituteEducationType.setInstituteEducationType(model);
			instituteEducationTypeRepository .create(instituteEducationType);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new InstituteEducationTypeModel(instituteEducationType));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel update(String id, InstituteEducationTypeModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_INSTITUTE_EDUCATION_TYPE, RestUtil.REQUEST_TYPE_UPDATE);
		try{
			// TODO validation
			InstituteEducationType instituteEducationType = new InstituteEducationType();
			instituteEducationType = instituteEducationTypeRepository.findById(id);
			instituteEducationType.setInstituteEducationType(model);
			instituteEducationTypeRepository .edit(instituteEducationType);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new InstituteEducationTypeModel(instituteEducationType));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_INSTITUTE_EDUCATION_TYPE, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			List<InstituteEducationType> list = instituteEducationTypeRepository .findRange(startPosition, maxResult);
			List<InstituteEducationTypeModel> modelList = new ArrayList<InstituteEducationTypeModel>();
			for(InstituteEducationType institueEducationType : list){
				InstituteEducationTypeModel model = new InstituteEducationTypeModel(institueEducationType);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.instituteEducationTypeRepository .count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel findById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_INSTITUTE_EDUCATION_TYPE, RestUtil.REQUEST_TYPE_DELETE);
		try{
			InstituteEducationType institueEducationType = instituteEducationTypeRepository .findById(id);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new InstituteEducationTypeModel(institueEducationType));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel deleteById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_INSTITUTE_EDUCATION_TYPE, RestUtil.REQUEST_TYPE_DELETE);
		try{
			InstituteEducationType institute = this.instituteEducationTypeRepository .findById(id);
			instituteEducationTypeRepository .remove(institute);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, null);
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel deleteByEducationTypeId(String institutesId) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_INSTITUTE_EDUCATION_TYPE, RestUtil.REQUEST_TYPE_DELETE);
		try{
			String clause = "t.instituteId = ?1";
			Object[] params = {institutesId};
			
			List<InstituteEducationType> instituteEducationTypeList = this.instituteEducationTypeRepository .loadByClause(clause, params);
			if(instituteEducationTypeList != null){
				for(InstituteEducationType instituteEducationType : instituteEducationTypeList){
					deleteById(instituteEducationType.getId());
				}
			}
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, null);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String instituteId, String educationTypeId) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_INSTITUTE_EDUCATION_TYPE, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			
			//set search criteria
			StringBuilder whereClause = new StringBuilder(); boolean needAnd = false;
			if(StringUtils.isNotBlank(instituteId)){
				whereClause.append("t.instituteId like '").append(instituteId).append("%'");
				needAnd = true;
			}
			
			if(StringUtils.isNotBlank(educationTypeId)){
				if(needAnd){
					whereClause.append(" and ");
				}
				whereClause.append("t.educationTypeId like '").append(educationTypeId).append("%'");
			}
			
			List<InstituteEducationType> list = instituteEducationTypeRepository .findRange(startPosition, maxResult, whereClause.toString());
			List<InstituteEducationTypeModel> modelList = new ArrayList<InstituteEducationTypeModel>();
			for(InstituteEducationType instituteEducationType : list){
				InstituteEducationTypeModel model = new InstituteEducationTypeModel(instituteEducationType);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.instituteEducationTypeRepository .count(whereClause.toString())));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listAll() {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_INSTITUTE_EDUCATION_TYPE, RestUtil.REQUEST_TYPE_LIST);
		try{
			List<InstituteEducationType> list = instituteEducationTypeRepository.findAll();
			List<InstituteEducationTypeModel> modelList = new ArrayList<InstituteEducationTypeModel>();
			for(InstituteEducationType instituteEducationType : list){
				InstituteEducationTypeModel model = new InstituteEducationTypeModel(instituteEducationType);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.instituteEducationTypeRepository.count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
}
