package com.okrur.st.jt.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.okrur.st.jt.data.domain.Income;
import com.okrur.st.jt.data.domain.Udv;
import com.okrur.st.jt.data.enums.CategoryEnum;
import com.okrur.st.jt.data.repository.IncomeRepository;
import com.okrur.st.jt.data.repository.UdvRepository;
import com.okrur.st.jt.rest.model.IncomeDetailsModel;
import com.okrur.st.jt.rest.model.IncomeIndicatorModel;
import com.okrur.st.jt.rest.model.IncomeModel;
import com.okrur.st.jt.rest.model.IncomeReportModel;
import com.okrur.st.jt.rest.model.ResponseModel;
import com.okrur.st.jt.rest.model.UserModel;
import com.okrur.st.jt.security.SecurityUtil;
import com.okrur.st.jt.service.IncomeService;
import com.okrur.st.jt.service.UtilityService;
import com.okrur.st.jt.util.DateUtil;
import com.okrur.st.jt.util.IConstant;
import com.okrur.st.jt.util.IUtil;
import com.okrur.st.jt.util.RestUtil;

/**
 * @File IncomeServiceImp.java: IncomeServiceImp Implementation for IncomeService
 * @author Md. Shaiful Islam | kuvic16@gmail.com
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate 24 April, 2016
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class IncomeServiceImp implements IncomeService{
	
	@Inject
	IncomeRepository incomeRepository;
	@Inject
	UtilityService utilityService;
	@Inject
	private UdvRepository udvRepository;

	
	@Override
	public ResponseModel create(IncomeModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_INCOME, RestUtil.REQUEST_TYPE_CREATE);
		try{
			if(IUtil.toDouble(model.getTargetAmount()) < IUtil.toDouble(model.getDepositAmount())){
				response.setResponse(RestUtil.ERROR, "Deposit amount is greater than Target amount", null);
				return response;
			}
			if(!isAlreadyExist(model.getLocationHierarchy(), model.getIndicatorUdvId(), model.getMonth(), model.getYear())){
				Income income = new Income();
				income = income.setIncome(model);
				income.setSubmittedByUsername(SecurityUtil.getCurrentUserName());
				incomeRepository.create(income);
				response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new IncomeModel(income));
			}else{
				response.setResponse(RestUtil.ERROR, RestUtil.ALREADY_EXIST_MESSAGE, null);
			}
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	private boolean isAlreadyExist(String locationHierarchy, String indicatorUdvId, String month, String year){
		try{
			String clause = "t.locationHierarchy = ?1 and t.indicatorUdvId = ?2 and t.month = ?3 and t.year = ?4";
			Object[] params = {locationHierarchy, indicatorUdvId, Integer.valueOf(month), Integer.valueOf(year)};
			List<Income> list = incomeRepository.loadByClause(clause, params);
			if(list != null && list.size() > 0){ 
				return true;
			}
		}catch(Throwable t){
			t.printStackTrace();
		}
		return false;
	}


	@Override
	public ResponseModel update(String id, IncomeModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_INCOME, RestUtil.REQUEST_TYPE_UPDATE);
		try{
			if(IUtil.toDouble(model.getTargetAmount()) < IUtil.toDouble(model.getDepositAmount())){
				response.setResponse(RestUtil.ERROR, "Deposit amount is greater than Target amount", null);
				return response;
			}
			if(!isAlreadyExist(id, model.getLocationHierarchy(), model.getIndicatorUdvId(), model.getMonth(), model.getYear())){
				Income income = incomeRepository.findById(id);
				income = income.setIncome(model);
				incomeRepository.edit(income);
				response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new IncomeModel(income));
			}else{
				response.setResponse(RestUtil.ERROR, RestUtil.ALREADY_EXIST_MESSAGE, null);
			}
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	private boolean isAlreadyExist(String incomeId, String locationHierarchy, String indicatorUdvId, String month, String year){
		try{
			String clause = "t.id != ?1 and t.locationHierarchy = ?2 and t.indicatorUdvId = ?3 and t.month = ?4 and t.year = ?5";
			Object[] params = {incomeId, locationHierarchy, indicatorUdvId, Integer.valueOf(month), Integer.valueOf(year)};
			List<Income> list = incomeRepository.loadByClause(clause, params);
			if(list != null && list.size() > 0){ 
				return true;
			}
		}catch(Throwable t){
			t.printStackTrace();
		}
		return false;
	}

	@Override
	public ResponseModel deleteById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_INCOME, RestUtil.REQUEST_TYPE_DELETE);
		try{
			Income income = this.incomeRepository.findById(id);
			incomeRepository.remove(income);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, null);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel findById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_INCOME, RestUtil.REQUEST_TYPE_DETAILS);
		try{
			Income income = incomeRepository.findById(id);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new IncomeModel(income));
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult) {
		return null;
	}

	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String locationHierarchy, 
									String indicatorUdvId, String month, String year, String verified) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_INCOME, RestUtil.REQUEST_TYPE_LIST);
		try{
			UserModel loggedUserModel = SecurityUtil.getCurrentLoggedUser();
			
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			
			boolean needAnd = false;
			boolean needWhere = true;
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT inc.id, ")
			   .append("ud.value, ")
			   .append("inc.total_institute, ")
			   .append("inc.total_student, ")
			   .append("inc.target_amount, ")
			   .append("inc.deposit_amount, ")
			   .append("inc.`month`, ")
			   .append("inc.`year`, ")
			   .append("inc.submitted_by_username, ")
			   .append("inc.verified ")
			   .append("FROM income inc JOIN udv ud ON inc.indicator_udv_id = ud.id ");
			   			
			if(!loggedUserModel.getUsername().equalsIgnoreCase(IConstant.ADMIN_USER) && !StringUtils.isNotBlank(loggedUserModel.getLocationId())){
				response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
				return response;
			}else if(!loggedUserModel.getUsername().equalsIgnoreCase(IConstant.ADMIN_USER) && StringUtils.isNotBlank(loggedUserModel.getLocationId())){
				sql.append(" where inc.location_hierarchy LIKE '>%").append(loggedUserModel.getLocationId()).append(">%'");
				needAnd = true;
				needWhere = false;
			}else if(loggedUserModel.getUsername().equalsIgnoreCase(IConstant.ADMIN_USER)){
				needWhere = true;
			}else{
				response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
				return response;
			}
							
			
			if(StringUtils.isNotBlank(indicatorUdvId)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" and ");
				
				sql.append("inc.indicator_udv_id = '").append(indicatorUdvId.trim()).append("'");
				needAnd = true; needWhere = false;
			}
			
			if(StringUtils.isNotBlank(locationHierarchy)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" and ");
				
				sql.append("inc.location_hierarchy LIKE '>%").append(locationHierarchy.trim()).append(">%'");
				needAnd = true; needWhere = false;
			}
			
			if(StringUtils.isNotBlank(year)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" and ");
				
				sql.append("inc.year = ").append(year).append("");
				needAnd = true; needWhere = false;
			}
			
			if(StringUtils.isNotBlank(month)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" and ");
				
				sql.append("inc.month = ").append(month).append("");
				needAnd = true; needWhere = false;
			}
			
			if(StringUtils.isNotBlank(verified)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND ");
				
				sql	.append("inc.verified = " + Boolean.parseBoolean(verified));
				needAnd = true; needWhere = false;
			}
			
			int total = incomeRepository.countByJNQ(sql.toString());
			
			sql.append(" limit ")
			   .append(startPosition * maxResult)
			   .append(",")
			   .append(maxResult);
			
			
			@SuppressWarnings("rawtypes")
			List list = incomeRepository.loadsByNativeQuery(sql.toString());
			List<IncomeModel> modelList = new ArrayList<IncomeModel>();
			
			DecimalFormat df = new DecimalFormat("#");
			df.setMaximumFractionDigits(1);
			
			
			for(Object object : list){
				Object[] objs = (Object[])object;
				IncomeModel model = new IncomeModel();
								
				model.setId(IUtil.toString(objs[0]));
				model.setIndicatorUdvName(IUtil.toString(objs[1]));				
				model.setTotalInstitute(df.format(IUtil.toDouble(objs[2].toString())));
				model.setTotalStudent(df.format(IUtil.toDouble(objs[3].toString())));
				model.setTargetAmount(df.format(IUtil.toDouble(objs[4].toString())));
				model.setDepositAmount(df.format(IUtil.toDouble(objs[5].toString())));
				
				model.setMonth(IUtil.toString(objs[6]));
				model.setYear(IUtil.toString(objs[7]));
				model.setSubmittedByUsername(IUtil.toString(objs[8]));
				model.setVerified(IUtil.toString(objs[9]));
				modelList.add(model);
			}
			
			response.setTotal(String.valueOf(total));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}


	@Override
	public ResponseModel report(String locationHierarchy, String indicatorUdvId, String monthParam, String yearParam,String verified) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_INCOME, RestUtil.REQUEST_TYPE_LIST);
		try{
			List<IncomeIndicatorModel> indicatorModelList = new ArrayList<IncomeIndicatorModel>();
			
			int months = DateUtil.getCurrentMonth();
			if(StringUtils.isNotBlank(monthParam)){
				months = IUtil.toInteger(monthParam) + 1;
			}
			
			int year = DateUtil.getCurrentYear();
			if(StringUtils.isNotBlank(yearParam)){
				year = IUtil.toInteger(yearParam);
			}
			
			if(StringUtils.isNotBlank(indicatorUdvId)){
				Udv udv = udvRepository.findById(indicatorUdvId);
				IncomeIndicatorModel indicatorModel = new IncomeIndicatorModel();
				indicatorModel.setId(udv.getId());
				indicatorModel.setName(udv.getValue());
				indicatorModel.setIncomeDetailsModels(getIncomeDetailsListByIndicator(indicatorModel.getId(), months, year, locationHierarchy, verified));
				indicatorModelList.add(indicatorModel);				
			}else{
				StringBuilder sql = new StringBuilder();
				sql.append(" SELECT ")
				   .append(" u.id, ")
				   .append(" u.`value`")
				   .append(" FROM udv u")
				   .append(" where u.category = '").append(CategoryEnum.INCOME_INDICATOR.getName()).append("'");
				
				@SuppressWarnings("rawtypes")
				List udvlist = udvRepository.loadsByNativeQuery(sql.toString());
				for(Object object : udvlist){
					Object[] objs = (Object[])object;
					IncomeIndicatorModel indicatorModel = new IncomeIndicatorModel();
					indicatorModel.setId(IUtil.toString(objs[0]));
					indicatorModel.setName(IUtil.toString(objs[1]));
					indicatorModel.setIncomeDetailsModels(getIncomeDetailsListByIndicator(indicatorModel.getId(), months, year, locationHierarchy, verified));
					indicatorModelList.add(indicatorModel);
				}
			}
			
			List<String> monthList = new ArrayList<>();
			for(int i=0; i<months; i++){
				monthList.add(IUtil.getMonth(i));
			}
			if(months > 1)
				monthList.add(IUtil.getMonth(0) + " - " + IUtil.getMonth(months-1));
			
			IncomeReportModel reportModel = new IncomeReportModel();
			reportModel.setIndicatorModels(indicatorModelList);
			reportModel.setMonths(monthList);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, reportModel);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}	
	
	
	private List<IncomeDetailsModel> getIncomeDetailsListByIndicator(String indicatorUdvId, int months, int year, String locationHierarchy, String verified){
		List<IncomeDetailsModel> incomeDetailsModelList = new ArrayList<>();
		try{
			for(int i=0; i<months; i++){
				IncomeDetailsModel incomeDetailsModel = getIncomeDetailsByIndicator(indicatorUdvId, i, year, locationHierarchy, verified);
				incomeDetailsModelList.add(incomeDetailsModel);
			}
			
			if(months > 1){
				IncomeDetailsModel incomeDetailsModel = getTotalIncomeDetailsByIndicator(indicatorUdvId, 0, months-1, year, locationHierarchy, verified);
				incomeDetailsModelList.add(incomeDetailsModel);
			}
			
		}catch(Throwable t){
			t.printStackTrace();
		}
		return incomeDetailsModelList;
	}
	
	private IncomeDetailsModel getIncomeDetailsByIndicator(String indicatorUdvId, int month, int year, String locationHierarchy, String verified){
		IncomeDetailsModel incomeDetailsModel = new IncomeDetailsModel();
		try{
			UserModel loggedUserModel = SecurityUtil.getCurrentLoggedUser();
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT ")
			   .append(" sum(i.total_institute), ")
			   .append(" sum(i.target_amount), ")
			   .append(" sum(i.deposit_amount), ")
			   .append(" i.`month`, ")
			   .append(" i.`year` ")
			   .append(" FROM income i")
			   .append(" where i.indicator_udv_id='").append(indicatorUdvId).append("'")
			   .append(" and i.month=").append(month)
			   .append(" and i.year=").append(year);
			
			if(!loggedUserModel.getUsername().equalsIgnoreCase(IConstant.ADMIN_USER) && StringUtils.isNotBlank(loggedUserModel.getLocationId())){
				sql.append(" and i.location_hierarchy LIKE '>%").append(loggedUserModel.getLocationId()).append(">%'");
			}
							
			if(StringUtils.isNotBlank(locationHierarchy)){
				sql.append(" and i.location_hierarchy LIKE '>%").append(locationHierarchy.trim()).append(">%'");				
			}
			
			if(StringUtils.isNotBlank(verified)){
				sql	.append(" and i.verified = " + Boolean.parseBoolean(verified));
			}
			
			
			@SuppressWarnings("rawtypes")
			List list = incomeRepository.loadsByNativeQuery(sql.toString());
			
			DecimalFormat df = new DecimalFormat("#");
			df.setMaximumFractionDigits(1);
			for(Object object : list){
				Object[] objs = (Object[])object;
				IncomeDetailsModel model = new IncomeDetailsModel();
								
				model.setTotalInstitute(df.format(IUtil.toDouble(objs[0].toString())));
				model.setTargetAmount(df.format(IUtil.toDouble(objs[1].toString())));
				model.setDepositAmount(df.format(IUtil.toDouble(objs[2].toString())));
				model.setPercentAmount(df.format(getPercent(IUtil.toDouble(objs[1].toString()), IUtil.toDouble(objs[2].toString()))));
				model.setMonth(IUtil.toString(objs[3]));
				model.setYear(IUtil.toString(objs[4]));
				
				incomeDetailsModel = model;
			}
			return incomeDetailsModel;
		}catch(Throwable t){
			//t.printStackTrace();
		}
		return incomeDetailsModel;
	}
	
	private IncomeDetailsModel getTotalIncomeDetailsByIndicator(String indicatorUdvId, int minMonth, int maxMonth, int year, String locationHierarchy, String verified){
		IncomeDetailsModel incomeDetailsModel = new IncomeDetailsModel();
		try{
			UserModel loggedUserModel = SecurityUtil.getCurrentLoggedUser();
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT ")
			   .append(" sum(i.total_institute), ")
			   .append(" sum(i.target_amount), ")
			   .append(" sum(i.deposit_amount), ")
			   .append(" i.`year` ")
			   .append(" FROM income i")
			   .append(" where i.indicator_udv_id='").append(indicatorUdvId).append("'")
			   .append(" and (i.month >=").append(minMonth)
			   .append(" or i.month <=").append(maxMonth).append(")")
			   .append(" and i.year=").append(year);
			
			if(!loggedUserModel.getUsername().equalsIgnoreCase(IConstant.ADMIN_USER) && StringUtils.isNotBlank(loggedUserModel.getLocationId())){
				sql.append(" and i.location_hierarchy LIKE '>%").append(loggedUserModel.getLocationId()).append(">%'");
			}
							
			if(StringUtils.isNotBlank(locationHierarchy)){
				sql.append(" and i.location_hierarchy LIKE '>%").append(locationHierarchy.trim()).append(">%'");				
			}
			
			if(StringUtils.isNotBlank(verified)){
				sql	.append(" and i.verified = " + Boolean.parseBoolean(verified));
			}
			
			
			@SuppressWarnings("rawtypes")
			List list = incomeRepository.loadsByNativeQuery(sql.toString());
			
			DecimalFormat df = new DecimalFormat("#");
			df.setMaximumFractionDigits(1);
			for(Object object : list){
				Object[] objs = (Object[])object;
				IncomeDetailsModel model = new IncomeDetailsModel();
								
				model.setTotalInstitute(df.format(IUtil.toDouble(objs[0].toString())));
				model.setTargetAmount(df.format(IUtil.toDouble(objs[1].toString())));
				model.setDepositAmount(df.format(IUtil.toDouble(objs[2].toString())));
				model.setPercentAmount(df.format(getPercent(IUtil.toDouble(objs[1].toString()), IUtil.toDouble(objs[2].toString()))));
				model.setYear(IUtil.toString(objs[3]));
				
				incomeDetailsModel = model;
			}
			return incomeDetailsModel;
		}catch(Throwable t){
			//t.printStackTrace();
		}
		return incomeDetailsModel;
	}
	
	private double getPercent(Double target, Double deposit){
		try{
			return (deposit * 100)/target;
		}catch(Throwable t){
			
		}
		return 0.0;
	}
}
