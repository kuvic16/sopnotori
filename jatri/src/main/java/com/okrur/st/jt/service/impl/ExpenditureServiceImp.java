package com.okrur.st.jt.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.okrur.st.jt.data.domain.Expenditure;
import com.okrur.st.jt.data.repository.ExpenditureRepository;
import com.okrur.st.jt.rest.model.ExpenditureModel;
import com.okrur.st.jt.rest.model.ResponseModel;
import com.okrur.st.jt.rest.model.UdvModel;
import com.okrur.st.jt.rest.model.UserModel;
import com.okrur.st.jt.security.SecurityUtil;
import com.okrur.st.jt.service.ExpenditureService;
import com.okrur.st.jt.service.UtilityService;
import com.okrur.st.jt.util.ConfigurationUtil;
import com.okrur.st.jt.util.IConstant;
import com.okrur.st.jt.util.IUtil;
import com.okrur.st.jt.util.RestUtil;

/**
 * @File ExpenditureServiceImp.java: ExpenditureServiceImp Implementation for expenditureService
 * @author Md. Shaiful Islam Palash | kuvic16@gmail.com
 * @CreationDate 24 April, 2016
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class ExpenditureServiceImp implements ExpenditureService{
	
	@Inject
	ExpenditureRepository expenditureRepository;
	@EJB
	UtilityService utilityService;

	/* (non-Javadoc)
	 * @see net.brac.bep.service.AbstractService#create(java.lang.Object)
	 */
	@Override
	public ResponseModel create(ExpenditureModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_EXPENDITURE, RestUtil.REQUEST_TYPE_CREATE);
		try{
			Expenditure expenditure = new Expenditure();
			expenditure = expenditure.setExpenditure(model);
			
			//rename temporary attachment file
			if(StringUtils.isNotBlank(expenditure.getDocName())){
				String directory = ConfigurationUtil.config().getString("document.docDirectory");
				String oldFilePath = directory +  expenditure.getDocName();
				String newFileName = expenditure.getDocName().replace(IConstant.ATTACHMENT_TEMP, IConstant.ATTACHMENT_EXPF);
				String newFilePath = directory +  newFileName;
				if(utilityService.renameFile(oldFilePath, newFilePath)){
					expenditure.setDocName(newFileName);
				}
			}
			expenditureRepository.create(expenditure);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new ExpenditureModel(expenditure));
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see net.brac.bep.service.AbstractService#update(java.lang.String, java.lang.Object)
	 */
	@Override
	public ResponseModel update(String id, ExpenditureModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_EXPENDITURE, RestUtil.REQUEST_TYPE_UPDATE);
		try{
			Expenditure expenditure = expenditureRepository.findById(id);
			
			
			//rename temporary attachment file
			String directory = ConfigurationUtil.config().getString("document.docDirectory");
			if(
					(StringUtils.isNotBlank(model.getDocName()) && StringUtils.isNotBlank(expenditure.getDocName()) && !model.getDocName().equalsIgnoreCase(expenditure.getDocName()))
					||
					(StringUtils.isNotBlank(model.getDocName()) && StringUtils.isBlank(expenditure.getDocName()))
					
			){
				String oldFilePath = directory +  model.getDocName();
				String newFileName = model.getDocName().replace(IConstant.ATTACHMENT_TEMP, IConstant.ATTACHMENT_EXPF);
				String newFilePath = directory +  newFileName;
				if(utilityService.renameFile(oldFilePath, newFilePath)){
					model.setDocName(newFileName);
				}
			}
			
			expenditure = expenditure.setExpenditure(model);
			expenditureRepository.edit(expenditure);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new ExpenditureModel(expenditure));
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see net.brac.bep.service.AbstractService#deleteById(java.lang.String)
	 */
	@Override
	public ResponseModel deleteById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_EXPENDITURE, RestUtil.REQUEST_TYPE_DELETE);
		try{
			Expenditure expenditure = this.expenditureRepository.findById(id);
			expenditureRepository.remove(expenditure);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, null);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see net.brac.bep.service.AbstractService#findById(java.lang.String)
	 */
	@Override
	public ResponseModel findById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_EXPENDITURE, RestUtil.REQUEST_TYPE_DETAILS);
		try{
			Expenditure expenditure = expenditureRepository.findById(id);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new ExpenditureModel(expenditure));
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see net.brac.bep.service.AbstractService#listAll(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult) {
		return null;
	}

	/* (non-Javadoc)
	 * @see net.brac.bep.service.ExpenditureService#listAll(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, int, int)
	 */
	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String expenditureTypeUdvId, String headsOfAccountUdvId, String locationHierarchy, Integer year, Integer month) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_EXPENDITURE, RestUtil.REQUEST_TYPE_LIST);
		try{
			UserModel loggedUserModel = SecurityUtil.getCurrentLoggedUser();
			
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			
			boolean needAnd = false;
			boolean needWhere = false;
			StringBuilder sql = new StringBuilder();
			sql.append("select exp.id, udv.value, exp.amount, exp.year, exp.month, exp.doc_name, loc.name")
			   .append(" from expenditure exp join udv udv on (exp.expenditure_type_udv_id = udv.id) ")
			   .append(" join location loc on (exp.location_id = loc.id) ");
			
			if(!loggedUserModel.getUsername().equalsIgnoreCase(IConstant.ADMIN_USER) && !StringUtils.isNotBlank(loggedUserModel.getLocationId())){
				response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
				return response;
			}else if(!loggedUserModel.getUsername().equalsIgnoreCase(IConstant.ADMIN_USER) && StringUtils.isNotBlank(loggedUserModel.getLocationId())){
				sql.append(" where exp.location_hierarchy LIKE '>%").append(loggedUserModel.getLocationId()).append(">%'");
				needAnd = true;
				needWhere = false;
			}else if(loggedUserModel.getUsername().equalsIgnoreCase(IConstant.ADMIN_USER)){
				needWhere = true;
			}else{
				response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
				return response;
			}
							
			
			if(StringUtils.isNotBlank(expenditureTypeUdvId)){
				if(needWhere){
					sql.append(" where ");
					needWhere = false;
				}
				if(needAnd){
					sql.append(" and ");
				}
				sql.append("exp.expenditure_type_udv_id = '").append(expenditureTypeUdvId.trim()).append("'");
				needAnd = true;
			}
			
			if(StringUtils.isNotBlank(headsOfAccountUdvId)){
				if(needWhere){
					sql.append(" where ");
					needWhere = false;
				}
				if(needAnd){
					sql.append(" and ");
				}
				sql.append("exp.heads_of_account_udv_id = '").append(headsOfAccountUdvId.trim()).append("'");
				needAnd = true;
			}
			
			if(StringUtils.isNotBlank(locationHierarchy)){
				if(needWhere){
					sql.append(" where ");
					needWhere = false;
				}
				if(needAnd){
					sql.append(" and ");
				}
				sql.append("exp.location_hierarchy LIKE '>%").append(locationHierarchy.trim()).append(">%'");
				needAnd = true;
			}
			
			if(year != null){
				if(needWhere){
					sql.append(" where ");
					needWhere = false;
				}
				if(needAnd){
					sql.append(" and ");
				}
				sql.append("exp.year = ").append(year).append("");
				needAnd = true;
			}
			
			if(month != null){
				if(needWhere){
					sql.append(" where ");
					needWhere = false;
				}
				if(needAnd){
					sql.append(" and ");
				}
				sql.append("exp.month = ").append(month).append("");
				needAnd = true;
			}
			
			int total = expenditureRepository.countByJNQ(sql.toString());
			
			sql.append(" limit ")
			   .append(startPosition * maxResult)
			   .append(",")
			   .append(maxResult);
			
			
			@SuppressWarnings("rawtypes")
			List list = expenditureRepository.loadsByNativeQuery(sql.toString());
			List<ExpenditureModel> modelList = new ArrayList<ExpenditureModel>();
			
			for(Object object : list){
				Object[] objs = (Object[])object;
				ExpenditureModel model = new ExpenditureModel();
								
				model.setId(IUtil.toString(objs[0]));
				model.setExpenditureTypeName(IUtil.toString(objs[1]));				
				model.setAmount(IUtil.toDouble(String.valueOf(objs[2])));
				model.setYear(IUtil.toInteger(String.valueOf(objs[3])));
				model.setMonth(IUtil.toInteger(String.valueOf(objs[4])));
				model.setDocName(IUtil.toString(objs[5]));
				model.setLocationName(IUtil.toString(objs[6]));
				
				modelList.add(model);
			}
			
			response.setTotal(String.valueOf(total));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}	
}
