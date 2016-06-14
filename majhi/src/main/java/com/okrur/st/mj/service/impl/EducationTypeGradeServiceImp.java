package com.okrur.st.mj.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.okrur.st.mj.data.domain.EducationTypeGrade;
import com.okrur.st.mj.data.repository.EducationTypeGradeRepository;
import com.okrur.st.mj.rest.model.EducationTypeGradeModel;
import com.okrur.st.mj.rest.model.ResponseModel;
import com.okrur.st.mj.service.EducationTypeGradeService;
import com.okrur.st.mj.util.RestUtil;

/**
 * @File EducationTypeGradeServiceImp.java: EducationTypeGradeServiceImp Implementation for EducationTypeGradeService
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Dec 03, 2015
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class EducationTypeGradeServiceImp implements EducationTypeGradeService{
	@Resource
	private EJBContext context;
	
	@Inject
	EducationTypeGradeRepository educationTypeGradeRepository ;
	
	
	@Override
	public ResponseModel create(EducationTypeGradeModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_EDUCATION_TYPE_GRADE, RestUtil.REQUEST_TYPE_CREATE);
		try{
			EducationTypeGrade educationTypeGrade = new EducationTypeGrade();
			educationTypeGrade = educationTypeGrade.setEducationTypeGrade(model);
			
			String clause = "t.educationTypeId = ?1 and t.gradeId = ?2";
			Object[] params = {educationTypeGrade.getEducationTypeId(), educationTypeGrade.getGradeId()};
			List<EducationTypeGrade> list = educationTypeGradeRepository.loadByClause(clause, params);
			if( ! (list != null && list.size()>0) ){
				educationTypeGradeRepository .create(educationTypeGrade);
			}
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new EducationTypeGradeModel(educationTypeGrade));
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel createList(List<EducationTypeGradeModel> educationTypeGradeModelList) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_EDUCATION_TYPE_GRADE, RestUtil.REQUEST_TYPE_CREATE);
		try{
			for(EducationTypeGradeModel educationTypeGradeModel : educationTypeGradeModelList){
				create(educationTypeGradeModel);				
			}
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, null);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel update(String id, EducationTypeGradeModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_EDUCATION_TYPE_GRADE, RestUtil.REQUEST_TYPE_UPDATE);
		try{
		
			// TODO validation
			EducationTypeGrade educationTypeGrade = new EducationTypeGrade();
			educationTypeGrade = educationTypeGradeRepository.findById(id);
			educationTypeGrade = educationTypeGrade.setEducationTypeGrade(model);
			educationTypeGradeRepository .edit(educationTypeGrade);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new EducationTypeGradeModel(educationTypeGrade));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_EDUCATION_TYPE_GRADE, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			List<EducationTypeGrade> list = educationTypeGradeRepository .findRange(startPosition, maxResult);
			List<EducationTypeGradeModel> modelList = new ArrayList<EducationTypeGradeModel>();
			for(EducationTypeGrade educationTypeGrade : list){
				EducationTypeGradeModel model = new EducationTypeGradeModel(educationTypeGrade);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.educationTypeGradeRepository .count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel findById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_EDUCATION_TYPE_GRADE, RestUtil.REQUEST_TYPE_DELETE);
		try{
			EducationTypeGrade educationTypeGrade = educationTypeGradeRepository .findById(id);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new EducationTypeGradeModel(educationTypeGrade));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel deleteById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_EDUCATION_TYPE_GRADE, RestUtil.REQUEST_TYPE_DELETE);
		try{
			EducationTypeGrade educationTypeGrade = this.educationTypeGradeRepository .findById(id);
			educationTypeGradeRepository .remove(educationTypeGrade);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, null);
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String educationTypeId, String gradeId) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_EDUCATION_TYPE_GRADE, RestUtil.REQUEST_TYPE_LIST);
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
			
			if(StringUtils.isNotBlank(gradeId)){
				if(needAnd){
					whereClause.append(" and ");
				}
				whereClause.append("t.gradeId =").append(gradeId).append("");
			}
			
			List<EducationTypeGrade> list = educationTypeGradeRepository .findRange(startPosition, maxResult, whereClause.toString());
			List<EducationTypeGradeModel> modelList = new ArrayList<EducationTypeGradeModel>();
			for(EducationTypeGrade educationTypeGrade : list){
				EducationTypeGradeModel model = new EducationTypeGradeModel(educationTypeGrade);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.educationTypeGradeRepository .count(whereClause.toString())));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel deleteByEducationTypeId(String educationTypeId) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_EDUCATION_TYPE_GRADE, RestUtil.REQUEST_TYPE_DELETE);
		try{
			String clause = "t.educationTypeId = ?1";
			Object[] params = {educationTypeId};
			
			List<EducationTypeGrade> educationTypeGradeList = this.educationTypeGradeRepository .loadByClause(clause, params);
			if(educationTypeGradeList != null){
				for(EducationTypeGrade educationTypeGrade : educationTypeGradeList){
					deleteById(educationTypeGrade.getId());
				}
			}
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, null);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

}
