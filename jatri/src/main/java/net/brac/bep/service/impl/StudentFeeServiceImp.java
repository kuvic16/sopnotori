package net.brac.bep.service.impl;

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

import net.brac.bep.data.domain.StudentFee;
import net.brac.bep.data.repository.StudentFeeRepository;
import net.brac.bep.rest.model.FeeModel;
import net.brac.bep.rest.model.ResponseModel;
import net.brac.bep.rest.model.StudentFeeModel;
import net.brac.bep.rest.model.StudentModel;
import net.brac.bep.rest.model.UdvModel;
import net.brac.bep.service.FeeService;
import net.brac.bep.service.StudentFeeService;
import net.brac.bep.service.StudentService;
import net.brac.bep.service.TransactionHistoryService;
import net.brac.bep.service.TransactionService;
import net.brac.bep.service.UdvService;
import net.brac.bep.util.IUtil;
import net.brac.bep.util.RestUtil;

/**
 * @File FeeStudentServiceImp.java: FeeStudentServiceImp Implementation for FeeStudentService
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Jan 11, 2016
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class StudentFeeServiceImp implements StudentFeeService{
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
		double totalDepositedAmount=0;
		try{
			// TODO validation
			
			/*for(StudentFeeModel model : studentFeeModelList){
				if(IUtil.toDouble(model.getPay())> 0){
					StudentFee studentFee = new StudentFee().setStudentFee(model);
					studentFeeRepository.edit(studentFee);
		
					totalDepositedAmount += studentFee.getDeposited();
				}
			}
			
			//Beacause student will same for all fee of a student so we take only any one row in this case i take 0'th row
			StudentFee studentFeeEntity = new StudentFee().setStudentFee(studentFeeModelList.get(0));
			//Set Transaction Model with necessary data from studentFee table
			TransactionModel transactionModel = new TransactionModel(studentFeeEntity);
			
			//============ Insert data to Transaction Table =====================
			transactionModel.setAmount(String.valueOf(totalDepositedAmount));
			transactionModel.setRemarks("Test Data");
			ResponseModel transactionResponseModel = transactionService.create(transactionModel);
			//===================================================================
			
			
			//=========== Insert data to TransactionHistory Table ================
			for(StudentFeeModel model: studentFeeModelList)
			{
				if(IUtil.toDouble(model.getPay())> 0){
					StudentFee studentFee = new StudentFee().setStudentFee(model);
					transactionModel = (TransactionModel) transactionResponseModel.getModel();
					TransactionHistoryModel transactionHistoryModel = new TransactionHistoryModel(studentFee);
					transactionHistoryModel.setTransactionId(transactionModel.getId());
					transactionHistoryService.create(transactionHistoryModel);
				}
			}
			//=====================================================================
*/			
			
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
								model.setMonth(i);
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

	/* (non-Javadoc)
	 * @see net.brac.bep.service.StudentFeeService#findStudentFee(java.lang.String, java.lang.String, java.lang.String, int, int, java.lang.String, java.lang.String)
	 */
	@Override
	public StudentFee findStudentFee(String studentId, String instituteId, String gradeId, int year, int month, String feeTypeUdvId, String feeCategoryUdvId) {
		String clause = "t.studentId = ?1 and t.instituteId = ?2 and t.gradeId = ?3 and t.year = ?4 and t.month = ?5 and t.feeTypeUdvId = ?6 and t.feeCategoryUdvId = ?7";
		Object[] params = {studentId, instituteId, gradeId, year, month, feeTypeUdvId, feeCategoryUdvId};
		List<StudentFee> list = studentFeeRepository.loadByClause(clause, params);
		if(list != null && list.size() > 0) return list.get(0);
		return null;
	}

}
