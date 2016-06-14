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

import com.okrur.st.jt.data.domain.GradePoint;
import com.okrur.st.jt.data.repository.GradePointRepository;
import com.okrur.st.jt.rest.model.GradePointModel;
import com.okrur.st.jt.rest.model.ResponseModel;
import com.okrur.st.jt.service.GradePointService;
import com.okrur.st.jt.util.RestUtil;

/**
 * @File GradePointServiceImp.java: GradePointServiceImp Implementation for GradePointService
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 29, 2015
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class GradePointServiceImp implements GradePointService{
	@Resource
	private EJBContext context;
	
	@Inject
	GradePointRepository gradePointRepository ;
	
	
	@Override
	public ResponseModel create(GradePointModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_GRADE_POINT, RestUtil.REQUEST_TYPE_CREATE);
		try{
			// TODO validation
			GradePoint gradePoint = new GradePoint();
			gradePoint = gradePoint.setGradePoint(model);
			gradePointRepository .create(gradePoint);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new GradePointModel(gradePoint));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel update(String id, GradePointModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_GRADE_POINT, RestUtil.REQUEST_TYPE_UPDATE);
		try{
			// TODO validation
			GradePoint gradePoint = new GradePoint();
			gradePoint = gradePointRepository.findById(id);
			gradePoint = gradePoint.setGradePoint(model);
			gradePointRepository .edit(gradePoint);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new GradePointModel(gradePoint));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_GRADE_POINT, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			List<GradePoint> list = gradePointRepository .findRange(startPosition, maxResult);
			List<GradePointModel> modelList = new ArrayList<GradePointModel>();
			for(GradePoint gradePoint : list){
				GradePointModel model = new GradePointModel(gradePoint);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.gradePointRepository .count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel findById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_GRADE_POINT, RestUtil.REQUEST_TYPE_DETAILS);
		try{
			GradePoint gradePoint = gradePointRepository .findById(id);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new GradePointModel(gradePoint));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel deleteById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_GRADE_POINT, RestUtil.REQUEST_TYPE_DELETE);
		try{
			GradePoint gradePoint = this.gradePointRepository .findById(id);
			gradePointRepository .remove(gradePoint);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, null);
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String letterGrade, String educationTypeId) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_GRADE_POINT, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			
			//set search criteria
			StringBuilder whereClause = new StringBuilder(); boolean needAnd = false;
			if(StringUtils.isNotBlank(letterGrade)){
				whereClause.append("t.letterGrade like '").append(letterGrade).append("%'");
				needAnd = true;
			}
			
			if(StringUtils.isNotBlank(educationTypeId)){
				if(needAnd){
					whereClause.append(" and ");
				}
				whereClause.append("t.educationTypeId =").append("'"+educationTypeId+"'");
			}
			
			List<GradePoint> list = gradePointRepository.findRange(startPosition, maxResult, whereClause.toString());
			List<GradePointModel> modelList = new ArrayList<GradePointModel>();
			for(GradePoint gradePoint : list){
				GradePointModel model = new GradePointModel(gradePoint);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.gradePointRepository .count(whereClause.toString())));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	
	@Override
	public ResponseModel findAllByEducationTypeId(String edicationTypeId) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_GRADE_POINT, RestUtil.REQUEST_TYPE_LIST);
		try{
			StringBuilder clause = new StringBuilder(); 
			clause.append("t.educationTypeId = ?1");
			Object[] params = {edicationTypeId};
			List<GradePoint> list = gradePointRepository.loadByClause(clause.toString(), params);
			
			List<GradePointModel> modelList = new ArrayList<GradePointModel>();
			for(GradePoint gradePoint : list){
				GradePointModel model = new GradePointModel(gradePoint);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(list.size()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

}
