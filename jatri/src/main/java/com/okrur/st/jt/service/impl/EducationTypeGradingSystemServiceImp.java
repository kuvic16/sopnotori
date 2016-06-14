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

import com.okrur.st.jt.data.domain.EducationTypeGradingSystem;
import com.okrur.st.jt.data.repository.EducationTypeGradingSystemRepository;
import com.okrur.st.jt.rest.model.EducationTypeGradingSystemModel;
import com.okrur.st.jt.rest.model.ResponseModel;
import com.okrur.st.jt.service.EducationTypeGradingSystemService;
import com.okrur.st.jt.util.RestUtil;

/**
 * @File EducationTypeGradingSystemServiceImp.java: EducationTypeGradingSystemServiceImp Implementation for EducationTypeGradingSystemService
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Dec 03, 2015
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class EducationTypeGradingSystemServiceImp implements EducationTypeGradingSystemService{
	@Resource
	private EJBContext context;
	
	@Inject
	EducationTypeGradingSystemRepository educationTypeGradingSystemRepository ;
	
	
	@Override
	public ResponseModel create(EducationTypeGradingSystemModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_EDUCATION_TYPE_GRADING_SYSTEM, RestUtil.REQUEST_TYPE_CREATE);
		try{
			// TODO validation
			EducationTypeGradingSystem educationTypeGradingSystem = new EducationTypeGradingSystem();
			educationTypeGradingSystem = educationTypeGradingSystem.setEducationTypeGradingSystem(model);
			educationTypeGradingSystemRepository .create(educationTypeGradingSystem);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new EducationTypeGradingSystemModel(educationTypeGradingSystem));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel update(String id, EducationTypeGradingSystemModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_EDUCATION_TYPE_GRADING_SYSTEM, RestUtil.REQUEST_TYPE_UPDATE);
		try{
			// TODO validation
			EducationTypeGradingSystem educationTypeGradingSystem = new EducationTypeGradingSystem();
			educationTypeGradingSystem = educationTypeGradingSystemRepository.findById(id);
			educationTypeGradingSystem = educationTypeGradingSystem.setEducationTypeGradingSystem(model);
			educationTypeGradingSystemRepository .edit(educationTypeGradingSystem);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new EducationTypeGradingSystemModel(educationTypeGradingSystem));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_EDUCATION_TYPE_GRADING_SYSTEM, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			List<EducationTypeGradingSystem> list = educationTypeGradingSystemRepository .findRange(startPosition, maxResult);
			List<EducationTypeGradingSystemModel> modelList = new ArrayList<EducationTypeGradingSystemModel>();
			for(EducationTypeGradingSystem educationTypeGradingSystem : list){
				EducationTypeGradingSystemModel model = new EducationTypeGradingSystemModel(educationTypeGradingSystem);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.educationTypeGradingSystemRepository .count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel findById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_EDUCATION_TYPE_GRADING_SYSTEM, RestUtil.REQUEST_TYPE_DELETE);
		try{
			EducationTypeGradingSystem educationTypeGradingSystem = educationTypeGradingSystemRepository .findById(id);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new EducationTypeGradingSystemModel(educationTypeGradingSystem));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel deleteById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_EDUCATION_TYPE_GRADING_SYSTEM, RestUtil.REQUEST_TYPE_DELETE);
		try{
			EducationTypeGradingSystem educationTypeGradingSystem = this.educationTypeGradingSystemRepository .findById(id);
			educationTypeGradingSystemRepository .remove(educationTypeGradingSystem);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, null);
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult,   String educationTypeId,  String gradingSystemId) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_EDUCATION_TYPE_GRADING_SYSTEM, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			
			//set search criteria
			StringBuilder whereClause = new StringBuilder(); boolean needAnd = false;
			if(StringUtils.isNotBlank(educationTypeId)){
				whereClause.append("t.educationTypeId like '").append(educationTypeId).append("%'");
				needAnd = true;
			}
			
			if(StringUtils.isNotBlank(gradingSystemId)){
				if(needAnd){
					whereClause.append(" and ");
				}
				whereClause.append("t.gradingSystemId like '").append(gradingSystemId).append("%'");
			}
			
			List<EducationTypeGradingSystem> list = educationTypeGradingSystemRepository .findRange(startPosition, maxResult, whereClause.toString());
			List<EducationTypeGradingSystemModel> modelList = new ArrayList<EducationTypeGradingSystemModel>();
			for(EducationTypeGradingSystem educationTypeGradingSystem : list){
				EducationTypeGradingSystemModel model = new EducationTypeGradingSystemModel(educationTypeGradingSystem);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.educationTypeGradingSystemRepository .count(whereClause.toString())));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

}
