package com.okrur.st.jt.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.okrur.st.jt.data.domain.StudentFee;
import com.okrur.st.jt.data.repository.StudentFeeRepository;
import com.okrur.st.jt.rest.model.FeeCollectionModel;
import com.okrur.st.jt.rest.model.FeeCollectionSearchResultModel;
import com.okrur.st.jt.rest.model.FeeModel;
import com.okrur.st.jt.rest.model.ResponseModel;
import com.okrur.st.jt.rest.model.StudentFeeModel;
import com.okrur.st.jt.rest.model.StudentModel;
import com.okrur.st.jt.rest.model.TransactionHistoryModel;
import com.okrur.st.jt.rest.model.TransactionModel;
import com.okrur.st.jt.rest.model.UdvModel;
import com.okrur.st.jt.rest.model.UserModel;
import com.okrur.st.jt.security.SecurityUtil;
import com.okrur.st.jt.service.FeeCollectionService;
import com.okrur.st.jt.service.FeeService;
import com.okrur.st.jt.service.StudentService;
import com.okrur.st.jt.service.TransactionHistoryService;
import com.okrur.st.jt.service.TransactionService;
import com.okrur.st.jt.service.UdvService;
import com.okrur.st.jt.util.DateUtil;
import com.okrur.st.jt.util.IUtil;
import com.okrur.st.jt.util.LocationHierarchySearch;
import com.okrur.st.jt.util.RestUtil;

/**
 * @File FeeCollectionServiceImp.java: Fee Collection
 * @author Shaiful Islam Palash| kuvic16@gmail.com
 * @CreationDate Jan 11, 2016
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class FeeCollectionServiceImp implements FeeCollectionService{
	@Resource
	private EJBContext context;
	
	@Inject
	StudentFeeRepository studentFeeRepository ;
	
	@EJB
	private FeeService feeService;
	
	@EJB
	private UdvService udvService;
	
	@Inject
	private TransactionService transactionService;
	
	@Inject
	private TransactionHistoryService transactionHistoryService;
	
	@Inject
	private StudentService studentService;
	
	
	@Override
	public ResponseModel create(StudentFeeModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_STUDENT_FEE, RestUtil.REQUEST_TYPE_CREATE);
		try{
			// TODO validation
			StudentFee studentFee = new StudentFee().setStudentFee(model);
			studentFeeRepository .create(studentFee);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new StudentFeeModel(studentFee));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel update(String id, StudentFeeModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_STUDENT_FEE, RestUtil.REQUEST_TYPE_UPDATE);
		try{
			// TODO validation
			StudentFee studentFee = studentFeeRepository.findById(id);
			studentFeeRepository.edit(studentFee.setStudentFee(model));
			
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new StudentFeeModel(studentFee));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel updateAll(List<StudentFeeModel> studentFeeModelList) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_STUDENT_FEE, RestUtil.REQUEST_TYPE_CREATE);
		double totalDepositedAmount=0, payAmount=0;
		try{
			for(StudentFeeModel model : studentFeeModelList){
				payAmount = IUtil.toDouble(model.getPay()); 
				if(payAmount> 0){
					totalDepositedAmount += payAmount;
				}
			}
			
			//Beacause student will same for all fee of a student so we take only any one row in this case i take 0'th row
			StudentFee studentFeeEntity = new StudentFee().setStudentFee(studentFeeModelList.get(0));
			//Set Transaction Model with necessary data from studentFee table
			TransactionModel transactionModel = new TransactionModel(studentFeeEntity);			
			//============ Insert data to Transaction Table =====================
			transactionModel.setAmount(String.valueOf(totalDepositedAmount));
			transactionModel.setRemarks("");
			ResponseModel transactionResponseModel = transactionService.create(transactionModel);
			transactionModel = (TransactionModel) transactionResponseModel.getModel();
			//===================================================================
			
			
			//=========== Insert data to TransactionHistory Table ================
			for(StudentFeeModel model: studentFeeModelList)
			{
				if(IUtil.toDouble(model.getPay())> 0){
					StudentFee studentFee = studentFeeRepository.findById(model.getId());
					studentFee = studentFee.setStudentFee(model);
					studentFeeRepository.edit(studentFee);
					
					TransactionHistoryModel transactionHistoryModel = new TransactionHistoryModel(studentFee, model.getPay());
					transactionHistoryModel.setTransactionId(transactionModel.getId());
					transactionHistoryService.create(transactionHistoryModel);
				}
			}
			//=====================================================================
			
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, null);
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}


	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_STUDENT_FEE, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			List<StudentFee> list = studentFeeRepository .findRange(startPosition, maxResult);
			List<StudentFeeModel> modelList = new ArrayList<StudentFeeModel>();
			for(StudentFee studentFee : list){
				StudentFeeModel model = new StudentFeeModel(studentFee);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.studentFeeRepository .count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel findById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_STUDENT_FEE, RestUtil.REQUEST_TYPE_DETAILS);
		try{
			StudentFee studentFee = studentFeeRepository .findById(id);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new StudentFeeModel(studentFee));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel deleteById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_STUDENT_FEE, RestUtil.REQUEST_TYPE_DELETE);
		try{
			StudentFee studentFee = this.studentFeeRepository .findById(id);
			studentFeeRepository .remove(studentFee);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, null);
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String feeId, String instituteId) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_STUDENT_FEE, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			
			//set search criteria
			StringBuilder whereClause = new StringBuilder(); boolean needAnd = false;
			if(StringUtils.isNotBlank(feeId)){
				whereClause.append("t.feeId like '").append(feeId).append("%'");
				needAnd = true;
			}
			
			if(StringUtils.isNotBlank(instituteId)){
				if(needAnd){
					whereClause.append(" and ");
				}
				whereClause.append("t.instituteId like '").append(instituteId).append("%'");
			}
			
			List<StudentFee> list = studentFeeRepository .findRange(startPosition, maxResult, whereClause.toString());
			List<StudentFeeModel> modelList = new ArrayList<StudentFeeModel>();
			for(StudentFee studentFee : list){
				StudentFeeModel model = new StudentFeeModel(studentFee);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.studentFeeRepository .count(whereClause.toString())));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listByStudent(String studentRoll, String studentId, String instituteId, String gradeId, int year, int month) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_STUDENT_FEE, RestUtil.REQUEST_TYPE_LIST);
		try{
			// Get studentId, instituteId and gradeId base on Student Roll no.
			 if(StringUtils.isNotBlank(studentRoll)){
				ResponseModel responseStudentModel = studentService.findByRoll(studentRoll);
				if(responseStudentModel.getModel() != null){
					StudentModel studentModel = (StudentModel)responseStudentModel.getModel();
					
					studentId = studentModel.getId();
					instituteId = studentModel.getInstituteId();
					gradeId = studentModel.getGradeId();
				}
			}
			// Get data from FeeSetup table
			ResponseModel responseFeeModel = feeService.listBy(instituteId, gradeId, Integer.valueOf(year));
			
			if(!responseFeeModel.getSuccess().equalsIgnoreCase(RestUtil.SUCCESS) || responseFeeModel.getModel() == null){
				response.setResponse(RestUtil.ERROR, "Fee is not setup yet. Please contact system admin", null);
				return response;
			}else{
				// All the fees that applicable to the specific student.
				List<FeeModel> feeList = (List<FeeModel>)responseFeeModel.getModel();
				
				if(feeList.size() > 0){					
					for(FeeModel fee : feeList){
					  //ResponseModel = Rows					 // get udv table data 
						ResponseModel responseFeeCategory = udvService.findById(fee.getFeeCategoryUdvId());
						// get udv model for response
						UdvModel feeCategoryUdv = (UdvModel)responseFeeCategory.getModel();
						// get AltValue of udv table (monthly fee value 12)
						int feeSize = Integer.parseInt(feeCategoryUdv.getAltValue());
						
						// check whether rows exist in Student Fee table of specific feeId and StudentId
						String clause = "t.feeId=?1 and t.studentId=?2";
						Object[] params = {fee.getId(), studentId};
						List<StudentFee> list = studentFeeRepository.loadByClause(clause, params);
						// if not have any row then create rows
							for(int i=list.size(); i<feeSize; i++){
								StudentFeeModel model = new StudentFeeModel();
								model.setFeeId(fee.getId());
								model.setFeeTypeUdvId(fee.getFeeTypeUdvId());
								model.setFeeCategoryUdvId(fee.getFeeCategoryUdvId());
								model.setInstituteId(instituteId);
								model.setGradeId(gradeId);
								model.setYear(year);
								model.setStudentId(studentId);
								model.setMandatory(String.valueOf(fee.isMandatory()));
								model.setAmount(IUtil.toString(fee.getAmount()));
								
								if(feeSize == 12){
									model.setMonth(i);
								}else{
									model.setMonth(999);
								}
								create(model);
							}
						
					}
				}
				
				// list Student Fee by student,year,grade
				String clause = "t.studentId=?1 and t.instituteId=?2 and t.gradeId=?3 and t.year=?4 and t.amount != t.deposited";
				Object[] params = {studentId, instituteId, gradeId, year};
				List<StudentFee> studentEntityList = studentFeeRepository.loadByClause(clause, params);
				List<StudentFeeModel> modelList = new ArrayList<StudentFeeModel>();
				
				for(StudentFee studentFee : studentEntityList){
					// get fee category name from udv table
					ResponseModel udvResponseModel = udvService.findById(studentFee.getFeeCategoryUdvId());
					UdvModel feeCategoryUdv = (UdvModel)udvResponseModel.getModel();
					String feeCategoryName = feeCategoryUdv.getValue();
					
					// get AltValue of udv table (monthly fee value 12)
					int altValue = Integer.parseInt(feeCategoryUdv.getAltValue());
					
					if(altValue != 12 || (altValue == 12 && (Integer.valueOf(studentFee.getMonth()) <= Integer.valueOf(month)))){
						// get fee type name and category name from udv table
						udvResponseModel = udvService.findById(studentFee.getFeeTypeUdvId());
						UdvModel feeTypeUdv = (UdvModel)udvResponseModel.getModel();
						String feeTypeName = feeTypeUdv.getValue();

						StudentFeeModel model = new StudentFeeModel(studentFee, feeTypeName, feeCategoryName);
						modelList.add(model);
					}
			
				}
				//response.setTotal(String.valueOf(studentEntityList.size()));
				response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
			}
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	
	@Override
	public ResponseModel listAllByStudent(String studentId, int year) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_STUDENT_FEE, RestUtil.REQUEST_TYPE_LIST);
		
		try{
				// list Student Fee by studentId, year
				String clause = "t.studentId=?1 and t.year=?2";
				Object[] params = {studentId, year};
				List<StudentFee> studentEntityList = studentFeeRepository.loadByClause(clause, params);
				List<StudentFeeModel> modelList = new ArrayList<StudentFeeModel>();
				
				for(StudentFee studentFee : studentEntityList){
					
					// get fee category name from udv table
					ResponseModel udvResponseModel = udvService.findById(studentFee.getFeeCategoryUdvId());
					UdvModel udvModel = (UdvModel)udvResponseModel.getModel();
					String feeCategoryName = udvModel.getValue();
					
					udvResponseModel = udvService.findById(studentFee.getFeeTypeUdvId());
					UdvModel feeTypeUdv = (UdvModel)udvResponseModel.getModel();
					String feeTypeName = feeTypeUdv.getValue();

					StudentFeeModel model = new StudentFeeModel(studentFee, feeTypeName, feeCategoryName);
					modelList.add(model);
				}
				
				response.setTotal(String.valueOf(studentEntityList.size()));
				response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
			
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	/**
	 * to understand clearly please check deeply LocationHierarchySearch.java and related stored procedures
	 * @param init
	 * @param hierarchyId
	 * @param instituteId
	 * @param gradeId
	 * @param studentId
	 * @param year
	 * @param month
	 * @param searchTypeId
	 * @param searchType
	 * @return
	 */
	@Override
	public ResponseModel hierarchySearch(boolean init, String hierarchyId, String instituteId, String gradeId, String studentId, Integer year, Integer month, String searchTypeId, String searchType) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_FEE_COLLECTION, RestUtil.REQUEST_TYPE_SEARCH);		
		try{
			UserModel loggedUserModel = SecurityUtil.getCurrentLoggedUser();
			String loggedUserHierarchy = loggedUserModel.getLocationHierarchy();
			String poId = "";
			if(loggedUserModel.isPo()){
				poId = loggedUserModel.getId();
			}
			LocationHierarchySearch locationHierarchySearch = new LocationHierarchySearch(init, hierarchyId, instituteId, gradeId, studentId, year, month, loggedUserHierarchy, searchTypeId, searchType, poId);
			
			StringBuilder jql = new StringBuilder();
			jql.append(locationHierarchySearch.getSearchQuery());
			
			@SuppressWarnings("rawtypes")
			List resultList = studentFeeRepository.loadsByNativeQuery(jql.toString());
			List<FeeCollectionModel> feeCollectionModels = new ArrayList<>();
			if (IUtil.isNotBlank(resultList)) {
				for(int i=0; i<resultList.size(); i++){
					FeeCollectionModel model = new FeeCollectionModel();
					
					Object[] listObject = (Object[]) resultList.get(i);
					
					if (listObject[0] != null) {
	                	model.setSearchResultName(listObject[0].toString());
	                	if(locationHierarchySearch.getSearchType().equalsIgnoreCase("SEARCH_TYPE_STUDENT_FEE_HISTORY")){
	                		model.setSearchResultName(DateUtil.formatDateString(listObject[0].toString()));
						}
	                }
					
					if (listObject[1] != null) {
	                	model.setSearchResultId(listObject[1].toString());
	                }
					
					//[COMPLEXITY]: to understand the value please check related stored procedure
					if (listObject[2] != null) {
						model.setTotalPattern1(IUtil.toDouble(listObject[2].toString())); 
						if(locationHierarchySearch.getSearchType().equalsIgnoreCase("SEARCH_TYPE_STUDENT_FEE") || locationHierarchySearch.getSearchType().equalsIgnoreCase("SEARCH_TYPE_STUDENT_FEE_HISTORY")){
							int monthId = IUtil.toInteger(listObject[2].toString());
							model.setTotalPattern1(IUtil.getMonth(monthId));
						} 	
	                }
					if (listObject[3] != null) {
	                	model.setTotalPattern2(IUtil.toDouble(listObject[3].toString()));
	                	if(locationHierarchySearch.getSearchType().equalsIgnoreCase("SEARCH_TYPE_STUDENT_FEE")){
							int yesNoId = IUtil.toInteger(listObject[3].toString());
							model.setTotalPattern2(IUtil.toYesNo(yesNoId));
						}
	                }
					if (listObject[4] != null) {
	                	model.setTotalPattern3(IUtil.toDouble(listObject[4].toString()));
	                }
					
					if(locationHierarchySearch.isSerachTransactionRequired()){
						String getTotalPaidQuery = locationHierarchySearch.getTransactionSearchQuery(model.getSearchResultId(), instituteId, gradeId, studentId);
						model.setTotalPattern4(this.getTotalPaidBySearchTypeId(getTotalPaidQuery));
						model.setTotalPattern5(model.getTotalPattern3() - model.getTotalPattern4());
					}else{
						// only for transaction history
						if (listObject[5] != null) {
		                	model.setTotalPattern4(IUtil.toDouble(listObject[5].toString()));
		                	model.setTotalPattern5(Double.parseDouble(model.getTotalPattern2().toString()) - model.getTotalPattern3());
		                }
					}
					
					model.setNextSearchType(locationHierarchySearch.getNextSearchType());
					feeCollectionModels.add(model);
				}	            
	        }
			
			FeeCollectionSearchResultModel resultModel = locationHierarchySearch.getFeeCollectionSearchResultModel();
			resultModel.setFeeCollectionModels(feeCollectionModels);
			
			response.setTotal(String.valueOf(feeCollectionModels.size()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, resultModel);			
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	private double getTotalPaidBySearchTypeId(String query){
		try{
			StringBuilder jql = new StringBuilder();
			jql.append(query);
			
			@SuppressWarnings("rawtypes")
			List resultList = studentFeeRepository.loadsByNativeQuery(jql.toString());
			if (IUtil.isNotBlank(resultList)) {
				for(int i=0; i<resultList.size(); i++){
					Object obj = resultList.get(i);
					if (obj != null) {
	                	return IUtil.toDouble(obj.toString());
	                }
				}	            
	        }
		}catch(Throwable t){
			t.printStackTrace();
		}
		return 0.0;
	}

}
