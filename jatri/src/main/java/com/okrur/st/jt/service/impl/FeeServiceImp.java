package com.okrur.st.jt.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.okrur.st.jt.data.domain.Fee;
import com.okrur.st.jt.data.domain.Institute;
import com.okrur.st.jt.data.domain.StudentFee;
import com.okrur.st.jt.data.repository.FeeRepository;
import com.okrur.st.jt.data.repository.StudentFeeRepository;
import com.okrur.st.jt.rest.model.FeeModel;
import com.okrur.st.jt.rest.model.ResponseModel;
import com.okrur.st.jt.rest.model.UdvModel;
import com.okrur.st.jt.rest.model.UserModel;
import com.okrur.st.jt.security.SecurityUtil;
import com.okrur.st.jt.service.FeeService;
import com.okrur.st.jt.service.InstituteService;
import com.okrur.st.jt.service.StudentFeeService;
import com.okrur.st.jt.service.UdvService;
import com.okrur.st.jt.util.IUtil;
import com.okrur.st.jt.util.RestUtil;

/**
 * @File FeeServiceImp.java: FeeServiceImp Implementation for FeeService
 * @author Md. Shaiful Islam Palash | kuvic16@gmail.com
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 29, 2015
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class FeeServiceImp implements FeeService{
	
	@Inject
	FeeRepository feeRepository;
	
	@Inject
	private StudentFeeService studentFeeService; 
	
	@EJB
	private UdvService udvService;
	
	@Inject
	StudentFeeRepository studentFeeRepository ;
	
	@Inject
	private InstituteService instituteService;
	
	@Override
	public ResponseModel create(FeeModel feeModel) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_FEE, RestUtil.REQUEST_TYPE_CREATE);
		try{
			Fee fee = new Fee();
			fee = fee.setFee(feeModel);
			if(this.isExist(null, fee.getFeeTypeUdvId(), fee.getInstituteId(), fee.getGradeId(), fee.getYear())){
				response.setResponse(RestUtil.ERROR, RestUtil.ALREADY_EXIST_MESSAGE, null);
				return response;
			}
			feeRepository.create(fee);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new FeeModel(fee));
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel apply() {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_FEE, RestUtil.REQUEST_TYPE_FEE_APPLY);
		try{
			UserModel loggedUserModel = SecurityUtil.getCurrentLoggedUser();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select f.id, ")
			   .append("f.fee_type_udv_id, ")
			   .append("f.fee_category_udv_id, ")
			   .append("u.alt_value, ")
			   .append("f.institute_id, ")
			   .append("f.grade_id, ")
			   .append("f.year, ")
			   .append("f.amount, ")
			   .append("f.mandatory ")
			   .append("from fee f join institute i on (f.institute_id = i.id) join udv u on (f.fee_category_udv_id = u.id)");
			   
			if(loggedUserModel.isPo()){
				sql.append(" where i.po_id = '").append(loggedUserModel.getId()).append("'");
			}else if(!loggedUserModel.isAdmin() && StringUtils.isNotBlank(loggedUserModel.getLocationHierarchy())){
				sql.append(" where i.location_hierarchy LIKE '%").append(loggedUserModel.getLocationHierarchy()).append("%'");
			} 

			@SuppressWarnings("rawtypes")
			List feeList = feeRepository.loadsByNativeQuery(sql.toString());
			for(Object object : feeList){
				Object[] feeObject = (Object[])object;
				
				boolean isMandatory = Boolean.parseBoolean(IUtil.toString(feeObject[8]));
				if(isMandatory){
					int feeSize = Integer.parseInt(IUtil.toString(feeObject[3]));
					
					StringBuilder studentSql = new StringBuilder();
					studentSql.append("select s.id")
						 .append(" from student s where s.institute_id='").append(IUtil.toString(feeObject[4])).append("'")
						 .append(" and s.grade_id='").append(IUtil.toString(feeObject[5])).append("'");
					
					@SuppressWarnings("rawtypes")
					List studentList = feeRepository.loadsByNativeQuery(studentSql.toString());
					for(Object stObject : studentList){
						String studentId = (String)stObject;
						for(int i=0; i<feeSize; i++){
							StudentFee entity = new StudentFee();
							entity.setFeeId(IUtil.toString(feeObject[0]));
							entity.setFeeTypeUdvId(IUtil.toString(feeObject[1]));
							entity.setFeeCategoryUdvId(IUtil.toString(feeObject[2]));
							entity.setInstituteId(IUtil.toString(feeObject[4]));
							entity.setGradeId(IUtil.toString(feeObject[5]));
							entity.setYear(Integer.parseInt(IUtil.toString(feeObject[6])));
							entity.setStudentId(IUtil.toString(studentId));
							entity.setAmount(IUtil.toDouble(IUtil.toString(feeObject[7])));
							entity.setMandatory(Boolean.parseBoolean(IUtil.toString(feeObject[8])));
							entity.setMonth(i);
							if(feeSize == 12){
								entity.setMonth(i);
							}else{
								entity.setMonth(999);
							}
							
							StudentFee sFee = studentFeeService.findStudentFee(entity.getStudentId(), entity.getInstituteId(), entity.getGradeId(), entity.getYear(), entity.getMonth(), entity.getFeeTypeUdvId(), entity.getFeeCategoryUdvId());
							if(sFee != null){
								sFee.setAmount(IUtil.toDouble(IUtil.toString(feeObject[7])));
								studentFeeRepository.edit(sFee);
							}else{
								studentFeeRepository.create(entity);
							}
						}
					}
				}
			}
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, "Applied to all student");
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel engine(FeeModel feeModel) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_FEE, RestUtil.REQUEST_TYPE_FEE_SETUP_ENGINE);
		try{
			List<Institute> institutes = instituteService.findAll(feeModel.getLocationTypeUdvId(), feeModel.getLocationHierarchy(), feeModel.getGradeId());
			int count = 0;
			for(Institute institute : institutes){
				try{
					Fee fee = new Fee();
					fee = fee.setFee(feeModel);
					fee.setInstituteId(institute.getId());
					fee.setGradeId(institute.getCurrentGradeId());
					if(!this.isExist(null, fee.getFeeTypeUdvId(), fee.getInstituteId(), fee.getGradeId(), fee.getYear() )){
						feeRepository.create(fee);
						count = count + 1;
					}					
				}catch(Throwable t){
					
				}
			}
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, count);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel update(String id, FeeModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_FEE, RestUtil.REQUEST_TYPE_UPDATE);
		try{
			if(this.isExist(id, model.getFeeTypeUdvId(), model.getInstituteId(), model.getGradeId(), Integer.valueOf(model.getYear()))){
				response.setResponse(RestUtil.ERROR, RestUtil.ALREADY_EXIST_MESSAGE, null);
				return response;
			}
			Fee fee = new Fee();
			fee = feeRepository.findById(id);
			fee = fee.setFee(model);
			feeRepository.edit(fee);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new FeeModel(fee));
			
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_FEE, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			List<Fee> list = feeRepository .findRange(startPosition, maxResult);
			List<FeeModel> modelList = new ArrayList<FeeModel>();
			for(Fee fee : list){
				// get fee category name from udv table
				ResponseModel udvResponseModel = udvService.findById(fee.getFeeCategoryUdvId());
				UdvModel feeCategoryUdv = (UdvModel)udvResponseModel.getModel();
				String feeCategoryName = feeCategoryUdv.getValue();
				
				// get fee type name from udv table
				udvResponseModel = udvService.findById(fee.getFeeTypeUdvId());
				UdvModel feeTypeUdv = (UdvModel)udvResponseModel.getModel();
				String feeTypeName = feeTypeUdv.getValue();
				
				FeeModel model = new FeeModel(fee, feeTypeName, feeCategoryName);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.feeRepository .count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel findById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_FEE, RestUtil.REQUEST_TYPE_DELETE);
		
		//ResponseModel responseLoggedUserModel = userService.getLoggedUser();
		UserModel loggedUserModel = SecurityUtil.getCurrentLoggedUser();
		
		
		try{
			Fee fee = feeRepository .findById(id);
			FeeModel feeModel = new FeeModel(fee);
			
			/*SELECT ins.location_id, ins.location_hierarchy FROM institute ins 
			  WHERE ins.id = '45507939-9365-inst-a56c-6c47c24da65f'*/
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ins.location_id, ins.location_hierarchy FROM institute ins ")
			   .append("WHERE ins.id = '" + fee.getInstituteId() + "'");
			
			List list = feeRepository.loadsByNativeQuery(sql.toString());
			Object objectList = list.get(0);
			Object[] objectArray = (Object[])objectList;
			feeModel.setLocationId(IUtil.toString(objectArray[0]));
			feeModel.setLocationHierarchy(IUtil.toString(objectArray[1]));	
						
			list = null;
			sql = null;
			
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, feeModel);
			
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel deleteById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_FEE, RestUtil.REQUEST_TYPE_DELETE);
		try{
			Fee fee = this.feeRepository .findById(id);
			feeRepository .remove(fee);
			
			/*DELETE FROM student_fee WHERE student_fee.fee_id = '45760553-7585-Fee-b1d7-4047c438b6c1'*/
			StringBuilder sql = new StringBuilder();
			sql.append(" DELETE FROM student_fee WHERE student_fee.fee_id = ")
			   .append("'" + fee.getId() + "'");
			
			studentFeeRepository.removeByNativeQuery(sql.toString());	
			
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, null);
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String feeTypeUdvId, String feeCategoryUdvId, String instituteId, String code, String gradeId , 
								int year, String amount, String mandatory ){
		
		ResponseModel response = new ResponseModel(RestUtil.MODULE_FEE, RestUtil.REQUEST_TYPE_LIST);
		UserModel loggedUserModel = SecurityUtil.getCurrentLoggedUser();
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			
			boolean needAnd = false;
			boolean needWhere=true;
			
			StringBuilder sql = new StringBuilder();
			sql.append("select f.id, ")
			   .append("f.code, ")
			   .append("typUdv.value fee_type, ")
			   .append("ctgUdv.value category, ")
			   .append("i.name institue_name, ")
			   .append("g.name grade_name, ")
			   .append("f.year, ")
			   .append("f.amount, ")
			   .append("f.mandatory ")
			   
			   .append(" from fee f join institute i on (f.institute_id=i.id)")
			   .append(" join udv ctgUdv on (f.fee_category_udv_id = ctgUdv.id)")
			   .append(" join udv typUdv on (f.fee_type_udv_id = typUdv.id)")
			   .append(" join grade g on (f.grade_id = g.id)");
			
			
				
			if(StringUtils.isNotBlank(feeTypeUdvId)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" and ");
				
				sql.append("f.fee_type_udv_id = '" + feeTypeUdvId + "'");
				needAnd = true; needWhere = false;
			}
			
			if(StringUtils.isNotBlank(feeCategoryUdvId)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" and ");
				
				sql.append("f.fee_category_udv_id = '" + feeCategoryUdvId + "'");
				needAnd = true; needWhere = false;
			}
			
			if(StringUtils.isNotBlank(instituteId)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" and ");
				
				sql.append("f.institute_id = '" + instituteId + "'");
				needAnd = true; needWhere = false;
			}
			
			if(StringUtils.isNotBlank(code)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" and ");
				
				sql.append("f.code = '" + code + "'");
				needAnd = true; needWhere = false;
			}
			
			if(StringUtils.isNotBlank(gradeId)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" and ");
				
				sql.append("f.grade_id = '" + gradeId + "'");
				needAnd = true; needWhere = false;
			}
			
			if(year > 0){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" and ");
				
				sql.append("f.year = " + year + "");
				needAnd = true; needWhere = false;
			}
			
			if(StringUtils.isNotBlank(amount)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" and ");
				
				sql.append("f.amount = " + amount + "");
				needAnd = true; needWhere = false;
			}
			
			if(StringUtils.isNotBlank(mandatory)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" and ");
				
				sql.append("f.mandatory = " + Boolean.parseBoolean(mandatory )+"");
				needAnd = true; needWhere = false;
			}
		
			if(!loggedUserModel.isAdmin() && StringUtils.isNotBlank(loggedUserModel.getLocationId())){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" and ");
				
				sql.append("i.location_hierarchy LIKE '%").append(loggedUserModel.getLocationHierarchy()).append("%'");
				needAnd = true; needWhere = false;
			}
			
			if(loggedUserModel.isPo()){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" and ");
				
				sql.append("i.po_id = '").append(loggedUserModel.getId()).append("'");
				needAnd = true; needWhere = false;
			}
			
			
			int totalSize = feeRepository.countByJNQ(sql.toString());
			
			sql.append(" limit ")
			   .append(startPosition * maxResult)
			   .append(",")
			   .append(maxResult);
			
			
			@SuppressWarnings("rawtypes")
			List list = feeRepository.loadsByNativeQuery(sql.toString());
			
			List<FeeModel> modelList = new ArrayList<FeeModel>();
			for(Object object : list){
				Object[] objs = (Object[])object;
				FeeModel model = new FeeModel();
								
				model.setId(IUtil.toString(objs[0]));
				model.setCode(IUtil.toString(objs[1]));	
				model.setFeeTypeName(IUtil.toString(objs[2]));			
				model.setGradeId(IUtil.toString(objs[3]));				
				model.setFeeCategoryName(IUtil.toString(objs[4]));				
				model.setInstituteId(IUtil.toString(objs[5]));
				model.setYear(IUtil.toString(objs[6]));
				model.setAmount(IUtil.toString(objs[7]));
				model.setMandatory(IUtil.toString(objs[8]));
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
		ResponseModel response = new ResponseModel(RestUtil.MODULE_FEE, RestUtil.REQUEST_TYPE_LIST);
		try{
			List<Fee> list = feeRepository.findAll();
			List<FeeModel> modelList = new ArrayList<FeeModel>();
			for(Fee fee : list){
				FeeModel model = new FeeModel(fee);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.feeRepository.count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listBy(String instituteId, String gradeId, int year) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_FEE, RestUtil.REQUEST_TYPE_LIST);
		try{
			String clause = "t.instituteId=?1 and t.gradeId=?2 and t.year=?3";
			Object[] params = {instituteId, gradeId, year};
			
			List<Fee> list = feeRepository.loadByClause(clause, params);
			List<FeeModel> modelList = new ArrayList<FeeModel>();
			for(Fee fee : list){
				FeeModel model = new FeeModel(fee);
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
	 * @see net.brac.bep.service.FeeService#callStoredProcedure()
	 */
	@Override
	public ResponseModel callStoredProcedure() {
		StringBuilder jql = new StringBuilder();
		jql.append("CALL emis.test('asd','riad')");
		
		List object = feeRepository.loadsByNativeQuery(jql.toString());
		for (Object obj : object) {
			Object[] cols = (Object[])obj;
			String name = String.valueOf(cols[0]);
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see net.brac.bep.service.FeeService#isExist(java.lang.String, java.lang.String, java.lang.String, int)
	 */
	@Override
	public boolean isExist(String feeId, String feeTypeUdvId, String instituteId, String gradeId, int year) {
		try{
			String clause = "t.feeTypeUdvId=?1 and t.instituteId=?2 and t.gradeId=?3 and t.year=?4";
			if(StringUtils.isNotBlank(feeId)){
				clause += " and t.id != '" + feeId + "'";
			}
			Object[] params = {feeTypeUdvId, instituteId, gradeId, year};
			List<Fee> list = feeRepository.loadByClause(clause, params);
			if(list != null && list.size() > 0)
				return true;
		}catch(Throwable t){
			
		}
		return false;
	}

}
