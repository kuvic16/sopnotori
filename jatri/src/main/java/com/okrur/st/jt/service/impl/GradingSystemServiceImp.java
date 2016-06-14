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

import com.okrur.st.jt.data.domain.GradingSystem;
import com.okrur.st.jt.data.repository.GradingSystemRepository;
import com.okrur.st.jt.rest.model.GradingSystemModel;
import com.okrur.st.jt.rest.model.ResponseModel;
import com.okrur.st.jt.service.GradingSystemService;
import com.okrur.st.jt.util.RestUtil;

/**
 * @File GradingSystemServiceImp.java: GradingSystemServiceImp Implementation for GradingSystemService
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 29, 2015
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class GradingSystemServiceImp implements GradingSystemService{
	@Resource
	private EJBContext context;
	
	@Inject
	GradingSystemRepository gradingSystemRepository ;
	
	
	@Override
	public ResponseModel create(GradingSystemModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_GRADING_SYSTEM, RestUtil.REQUEST_TYPE_CREATE);
		try{
			// TODO validation
			GradingSystem grade = new GradingSystem();
			grade = grade.setGradingSystem(model);
			gradingSystemRepository .create(grade);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new GradingSystemModel(grade));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel update(String id, GradingSystemModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_GRADING_SYSTEM, RestUtil.REQUEST_TYPE_UPDATE);
		try{
			// TODO validation
			GradingSystem grade = new GradingSystem();
			grade = gradingSystemRepository.findById(id);
			grade = grade.setGradingSystem(model);
			gradingSystemRepository .edit(grade);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new GradingSystemModel(grade));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_GRADING_SYSTEM, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			List<GradingSystem> list = gradingSystemRepository .findRange(startPosition, maxResult);
			List<GradingSystemModel> modelList = new ArrayList<GradingSystemModel>();
			for(GradingSystem grade : list){
				GradingSystemModel model = new GradingSystemModel(grade);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.gradingSystemRepository .count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel findById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_GRADING_SYSTEM, RestUtil.REQUEST_TYPE_DELETE);
		try{
			GradingSystem grade = gradingSystemRepository .findById(id);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new GradingSystemModel(grade));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel deleteById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_GRADING_SYSTEM, RestUtil.REQUEST_TYPE_DELETE);
		try{
			GradingSystem gradingSystem = this.gradingSystemRepository .findById(id);
			gradingSystemRepository .remove(gradingSystem);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, null);
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String name, String scale) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_GRADING_SYSTEM, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			
			//set search criteria
			StringBuilder whereClause = new StringBuilder(); boolean needAnd = false;
			if(StringUtils.isNotBlank(name)){
				whereClause.append("t.name like '").append(name).append("%'");
				needAnd = true;
			}
			
			if(StringUtils.isNotBlank(scale)){
				if(needAnd){
					whereClause.append(" and ");
				}
				whereClause.append("t.scale =").append(Float.parseFloat(scale)).append("");
			}
			
			List<GradingSystem> list = gradingSystemRepository .findRange(startPosition, maxResult, whereClause.toString());
			List<GradingSystemModel> modelList = new ArrayList<GradingSystemModel>();
			for(GradingSystem grade : list){
				GradingSystemModel model = new GradingSystemModel(grade);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.gradingSystemRepository .count(whereClause.toString())));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

}
