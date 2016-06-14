package com.okrur.st.jt.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.okrur.st.jt.data.domain.StudentActivity;
import com.okrur.st.jt.data.repository.StudentActivityRepository;
import com.okrur.st.jt.rest.model.ResponseModel;
import com.okrur.st.jt.rest.model.StudentActivityModel;
import com.okrur.st.jt.service.StudentActivityService;
import com.okrur.st.jt.util.RestUtil;

/**
 * @File StudentActivityServiceImp.java: StudentActivityServiceImp Implementation for StudentActivityService
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 29, 2015
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class StudentActivityServiceImp implements StudentActivityService{

	@Inject
	StudentActivityRepository studentActivityRepository ;
	
	
	@Override
	public ResponseModel create(StudentActivityModel studentActivityModel) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_STUDENT_ACTIVITY, RestUtil.REQUEST_TYPE_CREATE);
		try{
			// TODO validation
			StudentActivity studentActivity = new StudentActivity(studentActivityModel);
			studentActivityRepository .create(studentActivity);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new StudentActivityModel(studentActivity));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel update(String id, StudentActivityModel studentActivityModel) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_STUDENT_ACTIVITY, RestUtil.REQUEST_TYPE_UPDATE);
		try{
			// TODO validation
			StudentActivity studentActivity = new StudentActivity(studentActivityModel);
			studentActivityRepository .edit(studentActivity);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new StudentActivityModel(studentActivity));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_STUDENT_ACTIVITY, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			List<StudentActivity> list = studentActivityRepository .findRange(startPosition, maxResult);
			List<StudentActivityModel> modelList = new ArrayList<StudentActivityModel>();
			for(StudentActivity studentActivity : list){
				StudentActivityModel model = new StudentActivityModel(studentActivity);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.studentActivityRepository .count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel findById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_STUDENT_ACTIVITY, RestUtil.REQUEST_TYPE_DELETE);
		try{
			StudentActivity studentActivity = studentActivityRepository .findById(id);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new StudentActivityModel(studentActivity));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel deleteById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_STUDENT_ACTIVITY, RestUtil.REQUEST_TYPE_DELETE);
		try{
			StudentActivity studentActivity = this.studentActivityRepository .findById(id);
			studentActivityRepository .remove(studentActivity);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, null);
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String instituteId, String result) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_STUDENT_ACTIVITY, RestUtil.REQUEST_TYPE_LIST);
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
			
			if(StringUtils.isNotBlank(result)){
				if(needAnd){
					whereClause.append(" and ");
				}
				whereClause.append("t.result like '").append(result).append("%'");
			}
			
			List<StudentActivity> list = studentActivityRepository .findRange(startPosition, maxResult, whereClause.toString());
			List<StudentActivityModel> modelList = new ArrayList<StudentActivityModel>();
			for(StudentActivity studentActivity : list){
				StudentActivityModel model = new StudentActivityModel(studentActivity);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.studentActivityRepository .count(whereClause.toString())));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

}
