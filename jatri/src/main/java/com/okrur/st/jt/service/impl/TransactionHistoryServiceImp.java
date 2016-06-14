package com.okrur.st.jt.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.okrur.st.jt.data.domain.TransactionHistory;
import com.okrur.st.jt.data.repository.TransactionHistoryRepository;
import com.okrur.st.jt.rest.model.*;
import com.okrur.st.jt.security.SecurityUtil;
import com.okrur.st.jt.service.TransactionHistoryService;
import com.okrur.st.jt.service.UdvService;
import com.okrur.st.jt.util.DateUtil;
import com.okrur.st.jt.util.IConstant;
import com.okrur.st.jt.util.IUtil;
import com.okrur.st.jt.util.RestUtil;

/**
 * @File TransactionHistoryServiceImp.java: TransactionHistoryServiceImp Implementation for TransactionHistoryService
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Jan 11, 2016
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class TransactionHistoryServiceImp implements TransactionHistoryService{
	String poId=null;
	@Inject
	TransactionHistoryRepository transactionHistoryRepository ;

	@Inject
	private UdvService udvService;
	
	@Override
	public ResponseModel create(TransactionHistoryModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_TRANSACTION_HISTORY, RestUtil.REQUEST_TYPE_CREATE);
		try{
			// TODO validation
			TransactionHistory transactionHistory = new TransactionHistory().setTransactionHistory(model);
			transactionHistoryRepository .create(transactionHistory);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new TransactionHistoryModel(transactionHistory));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel update(String id, TransactionHistoryModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_TRANSACTION_HISTORY, RestUtil.REQUEST_TYPE_UPDATE);
		try{
			// TODO validation
			TransactionHistory transactionHistory = transactionHistoryRepository.findById(id);
			transactionHistoryRepository .edit(transactionHistory.setTransactionHistory(model));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new TransactionHistoryModel(transactionHistory));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_TRANSACTION_HISTORY, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			List<TransactionHistory> list = transactionHistoryRepository .findRange(startPosition, maxResult);
			List<TransactionHistoryModel> modelList = new ArrayList<TransactionHistoryModel>();
			for(TransactionHistory transactionHistory : list){
				TransactionHistoryModel model = new TransactionHistoryModel(transactionHistory);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.transactionHistoryRepository .count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel findById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_TRANSACTION_HISTORY, RestUtil.REQUEST_TYPE_DELETE);
		try{
			TransactionHistory transactionHistory = transactionHistoryRepository .findById(id);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new TransactionHistoryModel(transactionHistory));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel findTransactionDetailsById(Integer startPosition, Integer maxResult, String transaction_id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_TRANSACTION_HISTORY, RestUtil.REQUEST_TYPE_LIST);
		try{
			/*SELECT
			trnHis.id,
			(SELECT ud.`value` FROM udv ud WHERE ud.id = trnHis.fee_type_udv_id) Fee_Type,
			(SELECT ud.`value` FROM udv ud WHERE ud.id = trnHis.fee_category_udv_id) Fee_Category,
			(SELECT ins.`name` FROM institute ins WHERE ins.id = trnHis.institute_id) institute_Name,
			(SELECT grd.`name` FROM grade grd WHERE grd.id = trnHis.grade_id) Grade_Name,
			(SELECT CONCAT(COALESCE( std.first_name, ' '), ' ', COALESCE(std.middle_name,' '), ' ', COALESCE(std.last_name,' ')) 
				FROM student std WHERE std.id = trnHis.student_id) Student_Name,
			trnHis.amount, 
			trnHis.deposited,
			trnHis.`month`,
			trnHis.`year`
			
		FROM transaction_history trnHis 
		WHERE trnHis.transaction_id = '45922922-4631-tran-84aa-3fc4e85c72cf';*/
			
			boolean needAnd = false;
			boolean needWhere=false;
			boolean locationIncluded = false;
			
			StringBuilder sql = new StringBuilder();
						
			sql.append("SELECT trnHis.id, ")
			   .append("(SELECT ud.`value` FROM udv ud WHERE ud.id = trnHis.fee_type_udv_id) Fee_Type, ")
			   .append("(SELECT ud.`value` FROM udv ud WHERE ud.id = trnHis.fee_category_udv_id) Fee_Category, ")
			   .append("(SELECT ins.`name` FROM institute ins WHERE ins.id = trnHis.institute_id) institute_Name, ")
			   .append("(SELECT grd.`name` FROM grade grd WHERE grd.id = trnHis.grade_id) Grade_Name, ")
			   .append("(SELECT CONCAT(COALESCE( std.first_name, ' '), ' ', COALESCE(std.middle_name,' '), ' ', COALESCE(std.last_name,' ')) ")
			   .append("FROM student std WHERE std.id = trnHis.student_id) Student_Name, ")
			   .append("trnHis.amount,  ")
			   .append("trnHis.deposited, ")
			   .append("trnHis.`month`, ")
			   .append("trnHis.`year`, ")
			   .append("trnHis.collection_date, ")
			   .append("trnHis.total_deposited ")
			   .append("FROM transaction_history trnHis ")
			   .append("WHERE trnHis.transaction_id = '" + transaction_id + "'");

			needWhere = true;
			needAnd = true;
			
			//if(StringUtils.isNotBlank(transactionId)){
				//whereClause.append("t.transactionId like '").append(transactionId).append("%'");
				//needAnd = true;
			//}
			
			//if(StringUtils.isNotBlank(studentId)){
				if(needAnd){
					//whereClause.append(" and ");
				}
				//whereClause.append("t.studentId =").append(Float.parseFloat(studentId)).append("");
			//}
			
			
			//boolean adminTest = loggedUserModel.getUsername().equalsIgnoreCase(IConstant.ADMIN_USER);
			//adminTest=false;
			
			/*if(!loggedUserModel.getUsername().equalsIgnoreCase(IConstant.ADMIN_USER))
			{
				if(!needWhere)
					sql.append(" WHERE ");
				if(needAnd){
					sql.append(" AND ");
				}
				if(!locationIncluded)
					sql.append(" ins.location_hierarchy LIKE '%" + loggedUserModel.getLocationHierarchy() + "%'");
			}*/
			
			List totalList = transactionHistoryRepository.loadsByNativeQuery(sql.toString());
			
			sql.append(" limit ")
			   .append(startPosition * maxResult)
			   .append(",")
			   .append(maxResult);
			
			
			List list = transactionHistoryRepository.loadsByNativeQuery(sql.toString());
			List<TransactionHistoryModel> modelList = new ArrayList<TransactionHistoryModel>();
			
			System.out.println(list.size());
			for(Object object : list){
				Object[] objs = (Object[])object;
				TransactionHistoryModel model = new TransactionHistoryModel();
								
				model.setId(IUtil.toString(objs[0]));
				model.setFeeTypeUdvId(IUtil.toString(objs[1]));	
				model.setFeeCategoryUdvId(IUtil.toString(objs[2]));			
				model.setInstituteId(IUtil.toString(objs[3]));	
				model.setGradeId(IUtil.toString(objs[4]));				
				model.setStudentId(IUtil.toString(objs[5]));
				model.setAmount(IUtil.toString(objs[6]));
				model.setDeposited(IUtil.toString(objs[7]));
				model.setMonth(IUtil.toString(objs[8]));
				model.setYear(IUtil.toString(objs[9]));
				//Collection Date format: dd-mm-yyyy
				model.setCollectionDate(DateUtil.getDateString(DateUtil.getCalender(IUtil.toString(objs[10]))));
				model.setTotalDeposited(IUtil.toString(objs[11]));
							
				modelList.add(model);
				
			 //DateUtil.getDateString(DateUtil.getCalender(IUtil.toString(objs[10])));
			}
				
			response.setTotal(String.valueOf(totalList.size()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel deleteById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_TRANSACTION_HISTORY, RestUtil.REQUEST_TYPE_DELETE);
		try{
			TransactionHistory transactionHistory = this.transactionHistoryRepository .findById(id);
			transactionHistoryRepository .remove(transactionHistory);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, null);
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
		
	@Override
	public ResponseModel listAll(
									Integer startPosition, 
									Integer maxResult, 
									String locationHierarchy,
									String instituteId,
									String gradeId,
									String studentId,
									String collectionDate,
									String month
							    ) 
	{
		ResponseModel response = new ResponseModel(RestUtil.MODULE_TRANSACTION_HISTORY, RestUtil.REQUEST_TYPE_LIST);
		UserModel loggedUserModel = SecurityUtil.getCurrentLoggedUser();
		String PO=null;
		if(!loggedUserModel.getUsername().equalsIgnoreCase(IConstant.ADMIN_USER)){
			ResponseModel udvResponseModel = udvService.findById(loggedUserModel.getUserCategoryUdvId());
			UdvModel userTypeCategoryUdv = (UdvModel)udvResponseModel.getModel();
			PO = userTypeCategoryUdv.getValue();
		}
		if(!loggedUserModel.getUsername().equalsIgnoreCase(IConstant.ADMIN_USER) && PO.equals("PO")){
			poId = loggedUserModel.getId();
		}
		try{


			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			/*
			SELECT trn.id,
			(SELECT ins.`name` FROM institute ins WHERE ins.id = stu.institute_id) Institute_Name, 	
			CONCAT(COALESCE( stu.first_name, ' '), ' ', COALESCE(stu.middle_name,' '), ' ', COALESCE(stu.last_name,' ')) Student_Name,
			(SELECT grd.`name` FROM grade grd WHERE grd.id = stu.grade_id) Grade,
			trn.amount,
			trn.collection_date	
				
			FROM `transaction` trn , student stu , institute ins
			WHERE trn.student_id = stu.id 
			AND ins.id = stu.institute_id
			AND ins.location_hierarchy LIKE '%>45482245-2185-loct-a39a-92ea78311bdf>45482245-2298-loct-b273-3212c4e6cfd4>%'
			*/
			
			boolean needAnd = false;
			boolean needWhere=false;
			boolean locationIncluded = false;
			
			StringBuilder sql = new StringBuilder();
						
			sql.append("SELECT trn.id, ")
			   .append("(SELECT ins.`name` FROM institute ins WHERE ins.id = stu.institute_id) Institute_Name, ")
			   .append("CONCAT(COALESCE( stu.first_name, ' '), ' ', COALESCE(stu.middle_name,' '), ' ', COALESCE(stu.last_name,' ')) Student_Name, ")
			   .append("(SELECT grd.`name` FROM grade grd WHERE grd.id = stu.grade_id) Grade, ")
			   .append("trn.amount, ")
			   .append("trn.collection_date ")
			   .append("FROM `transaction` trn , student stu , institute ins ")
			   .append("WHERE trn.student_id = stu.id ")
			   .append("AND ins.id = stu.institute_id ");
			
			if(StringUtils.isNotBlank(poId)){
				needAnd = true;
				sql.append(" AND ");
				sql.append(" ins.po_id ='").append(poId).append("'");
			}
			

			needWhere = true;
			needAnd = true;
			
			
			if( 
					!StringUtils.isEmpty(instituteId) 	|| 
					!StringUtils.isEmpty(studentId) 	|| 
					!StringUtils.isEmpty(gradeId) 		|| 
					!StringUtils.isEmpty(collectionDate)||
					!StringUtils.isEmpty(month) 		||
					!StringUtils.isEmpty(locationHierarchy)
				)
			{
			
				if(StringUtils.isNotBlank(instituteId)){
					if(needAnd){
						sql.append(" AND ");
					}
					sql.append(" ins.id ='").append(instituteId).append("'");
					needAnd = true;
				}
				
				if(StringUtils.isNotBlank(gradeId)){
					if(needAnd){
						sql.append(" AND ");
					}
					sql.append(" stu.grade_id ='").append(gradeId).append("'");
					needAnd = true;
				}
				
				
				if(StringUtils.isNotBlank(studentId)){
					if(needAnd){
						sql.append(" AND ");
					}
					sql.append(" trn.student_id ='").append(studentId).append("'");
					needAnd = true;
				}
				
				if(StringUtils.isNotBlank(collectionDate)){
					if(needAnd){
						sql.append(" AND ");
					}
					sql.append(" trn.collection_date LIKE '%" + collectionDate + "%'");
					needAnd = true;
				}
				
				if(StringUtils.isNotBlank(month)){
					if(needAnd){
						sql.append(" AND ");
					}
					sql.append(" trn.month = " + month);
					needAnd = true;
				}
				
				if(StringUtils.isNotBlank(locationHierarchy)){
					if(needAnd){
						sql.append(" AND ");
					}
					sql.append(" ins.location_hierarchy LIKE '%" + locationHierarchy +"%'");
					needAnd = true;
					locationIncluded = true;
				}
			}
			//boolean adminTest = loggedUserModel.getUsername().equalsIgnoreCase(IConstant.ADMIN_USER);
			//adminTest=false;
			
			if(!loggedUserModel.getUsername().equalsIgnoreCase(IConstant.ADMIN_USER))
			{
				if(!needWhere)
					sql.append(" WHERE ");
				if(needAnd){
					sql.append(" AND ");
				}
				if(!locationIncluded)
					sql.append(" ins.location_hierarchy LIKE '%" + loggedUserModel.getLocationHierarchy() + "%'");
			}
			
			List totalList = transactionHistoryRepository.loadsByNativeQuery(sql.toString());
			
			sql.append(" limit ")
			   .append(startPosition * maxResult)
			   .append(",")
			   .append(maxResult);
			
			
			List list = transactionHistoryRepository.loadsByNativeQuery(sql.toString());
			List<TransactionModel> modelList = new ArrayList<TransactionModel>();
			
			System.out.println(list.size());
			for(Object object : list){
				Object[] objs = (Object[])object;
				TransactionModel model = new TransactionModel();
								
				model.setId(IUtil.toString(objs[0]));
				model.setInstituteName(IUtil.toString(objs[1]));	
				model.setStudentId(IUtil.toString(objs[2]));			
				model.setGrade(IUtil.toString(objs[3]));				
				model.setAmount(IUtil.toString(objs[4]));	
				//Collection Date format: dd-mm-yyyy
				model.setCollectionDate(DateUtil.getDateString(DateUtil.getCalender(IUtil.toString(objs[5]))));
							
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
	public ResponseModel listAll() 
	{
		ResponseModel response = new ResponseModel(RestUtil.MODULE_TRANSACTION_HISTORY, RestUtil.REQUEST_TYPE_LIST);
		try
		{
			List<TransactionHistory> list = transactionHistoryRepository.findAll();
			List<TransactionHistoryModel> modelList = new ArrayList<TransactionHistoryModel>();
			for(TransactionHistory transactionHistory : list)
			{
				TransactionHistoryModel model = new TransactionHistoryModel(transactionHistory);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.transactionHistoryRepository.count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}
		catch(Throwable t)
		{
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

}
