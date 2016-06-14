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

import com.okrur.st.mj.data.domain.Grade;
import com.okrur.st.mj.data.repository.GradeRepository;
import com.okrur.st.mj.rest.model.GradeModel;
import com.okrur.st.mj.rest.model.ResponseModel;
import com.okrur.st.mj.service.GradeService;
import com.okrur.st.mj.util.RestUtil;

/**
 * @File GradeServiceImp.java: GradeServiceImp Implementation for GradeService
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 29, 2015
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class GradeServiceImp implements GradeService{
	@Resource
	private EJBContext context;
	
	@Inject
	GradeRepository gradeRepository ;
	
	
	@Override
	public ResponseModel create(GradeModel gradeModel) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_GRADE, RestUtil.REQUEST_TYPE_CREATE);
		try{
			Grade grade = new Grade();
			grade = grade.setGrade(gradeModel);
			
			gradeRepository.create(grade);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new GradeModel(grade));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel update(String id, GradeModel gradeModel) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_GRADE, RestUtil.REQUEST_TYPE_UPDATE);
		try{
			Grade grade = gradeRepository.findById(id);
			grade = grade.setGrade(gradeModel);
			
			gradeRepository.edit(grade);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new GradeModel(grade));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_GRADE, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			List<Grade> list = gradeRepository .findRange(startPosition, maxResult);
			List<GradeModel> modelList = new ArrayList<GradeModel>();
			for(Grade grade : list){
				GradeModel model = new GradeModel(grade);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.gradeRepository .count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel findById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_GRADE, RestUtil.REQUEST_TYPE_DELETE);
		try{
			Grade grade = gradeRepository .findById(id);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new GradeModel(grade));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel deleteById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_GRADE, RestUtil.REQUEST_TYPE_DELETE);
		try{
			Grade grade = this.gradeRepository .findById(id);
			gradeRepository .remove(grade);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, null);
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult,  String code,  String name) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_GRADE, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			
			StringBuilder sql = new StringBuilder();
			boolean needAnd = false;
			//boolean whereClause=false;
			sql.append("SELECT t FROM Grade t ");
		
			if (!StringUtils.isEmpty(name) || !StringUtils.isEmpty(code)) {
				sql.append(" WHERE ");
				//whereClause=true;
				if (StringUtils.isNotBlank(name)) {
					sql.append(" t.name LIKE '%").append(name.trim()).append("%'");
					needAnd = true;
				}

				if (StringUtils.isNotBlank(code)) {
					if (needAnd) {
						sql.append(" AND ");
					}
					sql.append(" t.code ='").append(code.trim()).append("'");
				}

			}
			
			sql.append(" ORDER BY t.name * 1, t.name ASC ");
			
			List<Grade> list = gradeRepository.loadByQueryAndRang(startPosition, maxResult,sql.toString());
			List<GradeModel> modelList = new ArrayList<GradeModel>();
			for(Grade grade : list){
				GradeModel model = new GradeModel(grade);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.gradeRepository.count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listAll() {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_GRADE, RestUtil.REQUEST_TYPE_LIST);
		try{
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT t FROM Grade t ");
			sql.append(" ORDER BY t.name * 1, t.name ASC ");
			
			List<Grade> list = gradeRepository.loadByQuery(sql.toString());
			List<GradeModel> modelList = new ArrayList<GradeModel>();
			for(Grade grade : list){
				GradeModel model = new GradeModel(grade);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.gradeRepository.count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel findAllByEducationTypeId(String educationTypeId) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_GRADE, RestUtil.REQUEST_TYPE_LIST);
		try{
			StringBuilder sql = new StringBuilder();
			Object[] params = new Object[]{educationTypeId};
			sql.append("select g from Grade g, EducationTypeGrade etg where etg.educationTypeId = ?1 and etg.gradeId = g.id ");
			
			List<Grade> list = gradeRepository.loadByQuery(sql.toString(), params);
			List<GradeModel> modelList = new ArrayList<GradeModel>();
			for(Grade grade : list){
				GradeModel model = new GradeModel(grade);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(list.size()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel findAllByInstituteId(String instituteId) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_GRADE, RestUtil.REQUEST_TYPE_LIST);
		try{
			StringBuilder sql = new StringBuilder();
			Object[] params = new Object[]{instituteId};
			sql.append("SELECT g FROM Grade g, EducationTypeGrade e, Institute i, InstituteEducationType ie");
			sql.append(" where i.id=?1");
			sql.append(" and ie.instituteId=i.id");
			sql.append(" and ie.educationTypeId = e.educationTypeId");
			sql.append(" and g.id=e.gradeId");
			sql.append(" ORDER BY g.name * 1, g.name ASC");
			//ORDER BY g.`name` ASC
			
			
			
			List<Grade> list = gradeRepository.loadByQuery(sql.toString(), params);
			List<GradeModel> modelList = new ArrayList<GradeModel>();
			for(Grade grade : list){
				GradeModel model = new GradeModel(grade);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(list.size()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel findByCode(String code) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_GRADE, RestUtil.REQUEST_TYPE_FIND);
		try {
			String clause = "t.code = ?1";
			Object[] params = new Object[] { code };

			List<Grade> list = new ArrayList<Grade>();
			list = gradeRepository.loadByClause(clause, params);
			if (list != null && list.size() > 0) {
				Grade grade = list.get(0);

				GradeModel model = new GradeModel(grade);
				response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, model);
			} else {
				response.setResponse(RestUtil.ERROR, RestUtil.AUTHENTICATION_ERROR_MESSAGE, null);
			}
		} catch (Throwable t) {
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

}
