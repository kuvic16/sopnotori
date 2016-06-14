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

import com.okrur.st.mj.data.domain.EducationType;
import com.okrur.st.mj.data.repository.EducationTypeRepository;
import com.okrur.st.mj.rest.model.EducationTypeGradeModel;
import com.okrur.st.mj.rest.model.EducationTypeModel;
import com.okrur.st.mj.rest.model.GradeModel;
import com.okrur.st.mj.rest.model.ResponseModel;
import com.okrur.st.mj.service.EducationTypeGradeService;
import com.okrur.st.mj.service.EducationTypeService;
import com.okrur.st.mj.util.IUtil;
import com.okrur.st.mj.util.RestUtil;

/**
 * @File EducationTypeServiceImp.java: EducationTypeServiceImp Implementation for EducationTypeService
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 29, 2015
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class EducationTypeServiceImp implements EducationTypeService{
	@Resource
	private EJBContext context;
	
	@Inject
	EducationTypeRepository educationTypeRepository ;
	
	@Inject
	private EducationTypeGradeService educationTypeGradeService;
	
	
	@Override
	public ResponseModel create(EducationTypeModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_EDUCATION_TYPE, RestUtil.REQUEST_TYPE_CREATE);
		try{
			// TODO validation
			
			EducationType educationType = new EducationType();
			educationType = educationType.setEducationType(model);
			educationTypeRepository .create(educationType);
			createEducationTypeGrade(educationType.getId(), model.getGradeModelList());			
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new EducationTypeModel(educationType));
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel update(String id, EducationTypeModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_EDUCATION_TYPE, RestUtil.REQUEST_TYPE_UPDATE);
		try{
			
			EducationType educationType = new EducationType();
			educationType = educationTypeRepository.findById(id);
			educationType = educationType.setEducationType(model);
			educationTypeRepository.edit(educationType);
			
			// educationtypegrade
			ResponseModel egsDeleteModel = educationTypeGradeService.deleteByEducationTypeId(educationType.getId());
			if(egsDeleteModel.getSuccess().equalsIgnoreCase(RestUtil.SUCCESS)){
				createEducationTypeGrade(educationType.getId(), model.getGradeModelList());
			}
			
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new EducationTypeModel(educationType));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	private void createEducationTypeGrade(String educationTypeId, List<GradeModel> gradeModelList){
		if(StringUtils.isNoneBlank(educationTypeId) && gradeModelList != null && gradeModelList.size()>0){
			for(GradeModel gradeModel : gradeModelList){
				EducationTypeGradeModel educationTypeGradeModel = new EducationTypeGradeModel();
				educationTypeGradeModel.setEducationTypeId(educationTypeId);
				educationTypeGradeModel.setGradeId(gradeModel.getId());
				educationTypeGradeService.create(educationTypeGradeModel);
			}
		}
	}

	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_EDUCATION_TYPE, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			List<EducationType> list = educationTypeRepository .findRange(startPosition, maxResult);
			List<EducationTypeModel> modelList = new ArrayList<EducationTypeModel>();
			for(EducationType educationType : list){
				EducationTypeModel model = new EducationTypeModel(educationType);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.educationTypeRepository .count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel findById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_EDUCATION_TYPE, RestUtil.REQUEST_TYPE_DELETE);
		try{
			EducationType educationType = educationTypeRepository .findById(id);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new EducationTypeModel(educationType));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel deleteById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_EDUCATION_TYPE, RestUtil.REQUEST_TYPE_DELETE);
		try{
			EducationType educationType = this.educationTypeRepository .findById(id);
			educationTypeRepository .remove(educationType);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, null);
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String name, String scale) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_EDUCATION_TYPE, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			
			/*
			 * 
				select et.id, et.`name`, et.scale, 
				(	SELECT COUNT(*) 
					FROM grade_point gp 
					WHERE gp.education_type_id = et.id ) as 'Total Grading Point',
				( SELECT COUNT(*)
					FROM grade g, education_type_grade etg
					WHERE etg.education_type_id = et.id 
					AND   etg.grade_id = g.id) as 'Total Grade'
				FROM education_type et;
			* 
			*/
			
			
			boolean needAnd = false,needWhere=false;
			StringBuilder sql = new StringBuilder();
			
			sql.append("SELECT et.id, et.name, et.scale,")
			   .append(" (SELECT COUNT(*) FROM grade_point gp")
			   .append(" WHERE gp.education_type_id = et.id ) 'Total_Grading_Point',")
			   .append(" (SELECT COUNT(*)")
			   .append(" FROM grade g, education_type_grade etg")
			   .append(" WHERE etg.education_type_id = et.id")
			   .append(" AND etg.grade_id = g.id) as 'Total_Grade'")
			   .append(" FROM education_type et");
			   
			if(!StringUtils.isEmpty(name) || !StringUtils.isEmpty(scale)){
				sql.append(" WHERE ");
				needWhere=true;
				if(StringUtils.isNotBlank(name)){
					sql.append(" et.name like '%").append(name).append("%'");
					needAnd = true;
				}
				
				if(StringUtils.isNotBlank(scale)){
					if(!needWhere) {
						sql.append(" WHERE ");
						needWhere = true;
					}
					if(needAnd){
						sql.append(" AND ");
					}
					sql.append(" et.scale =").append(Float.parseFloat(scale)).append("");
				}
			}
			List totalList = educationTypeRepository.loadsByNativeQuery(sql.toString());
			sql.append(" limit ")
			   .append(startPosition * maxResult)
			   .append(",")
			   .append(maxResult);
			
			
			List list = educationTypeRepository.loadsByNativeQuery(sql.toString());
			List<EducationTypeModel> modelList = new ArrayList<EducationTypeModel>();
			
			System.out.println(list.size());
			for(Object object : list){
				Object[] objs = (Object[])object;
				EducationTypeModel model = new EducationTypeModel();
								
				model.setId(IUtil.toString(objs[0]));
				model.setName(IUtil.toString(objs[1]));				
				model.setScale(Float.parseFloat(objs[2].toString()));				
				model.setTotalGradingPoint(IUtil.toString(objs[3]));				
				model.setTotalGrade(IUtil.toString(objs[4]));
				
				modelList.add(model);
			}
			
			response.setTotal(String.valueOf(totalList.size()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listAll() {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_EDUCATION_TYPE, RestUtil.REQUEST_TYPE_LIST);
		try{
			List<EducationType> list = educationTypeRepository.findAll();
			List<EducationTypeModel> modelList = new ArrayList<EducationTypeModel>();
			for(EducationType educationType : list){
				EducationTypeModel model = new EducationTypeModel(educationType);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.educationTypeRepository.count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){	
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel findAllByInstituteId(String instituteId) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_EDUCATION_TYPE, RestUtil.REQUEST_TYPE_LIST);
		try{
			StringBuilder sql = new StringBuilder();
			Object[] params = new Object[]{instituteId};
			sql.append("select e from EducationType e, InstituteEducationType iet where iet.instituteId = ?1 and iet.educationTypeId = e.id ");
			
			List<EducationType> list = educationTypeRepository.loadByQuery(sql.toString(), params);
			List<EducationTypeModel> modelList = new ArrayList<EducationTypeModel>();
			for(EducationType educationType : list){
				EducationTypeModel model = new EducationTypeModel(educationType);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(list.size()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see net.brac.bep.service.AbstractService#findByName(java.lang.String)
	 */
	@Override
	public ResponseModel findByName(String name) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_EDUCATION_TYPE, RestUtil.REQUEST_TYPE_FIND);
		try{
			String clause = "t.name = ?1";
			Object[] params = new Object[]{name};
			
			List<EducationType> list = new ArrayList<EducationType>();
			list = educationTypeRepository.loadByClause(clause, params);	
			if(list != null && list.size() > 0){
				EducationType educationType = list.get(0);
				EducationTypeModel model = new EducationTypeModel(educationType);
				response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, model);
			}else{
				response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
			}
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

}
