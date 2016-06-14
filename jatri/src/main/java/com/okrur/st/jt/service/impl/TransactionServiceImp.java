package com.okrur.st.jt.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.okrur.st.jt.data.domain.Transaction;
import com.okrur.st.jt.data.repository.TransactionRepository;
import com.okrur.st.jt.rest.model.ResponseModel;
import com.okrur.st.jt.rest.model.TransactionModel;
import com.okrur.st.jt.service.TransactionService;
import com.okrur.st.jt.util.RestUtil;

/**
 * @File TransactionServiceImp.java: TransactionServiceImp Implementation for TransactionService
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Jan 11, 2016
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class TransactionServiceImp implements TransactionService{
	
	@Inject
	TransactionRepository transactionRepository ;
	
	
	@Override
	public ResponseModel create(TransactionModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_TRANSACTION, RestUtil.REQUEST_TYPE_CREATE);
		try{
			// TODO validation
			Transaction transaction = new Transaction().setTransaction(model);
			transactionRepository .create(transaction);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new TransactionModel(transaction));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel update(String id, TransactionModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_TRANSACTION, RestUtil.REQUEST_TYPE_UPDATE);
		try{
			// TODO validation
			Transaction transaction = transactionRepository.findById(id);
			transactionRepository .edit(transaction.setTransaction(model));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new TransactionModel(transaction));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_TRANSACTION, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			List<Transaction> list = transactionRepository .findRange(startPosition, maxResult);
			List<TransactionModel> modelList = new ArrayList<TransactionModel>();
			for(Transaction transaction : list){
				TransactionModel model = new TransactionModel(transaction);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.transactionRepository .count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel findById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_TRANSACTION, RestUtil.REQUEST_TYPE_DELETE);
		try{
			Transaction transaction = transactionRepository .findById(id);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new TransactionModel(transaction));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel deleteById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_TRANSACTION, RestUtil.REQUEST_TYPE_DELETE);
		try{
			Transaction transaction = this.transactionRepository .findById(id);
			transactionRepository .remove(transaction);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, null);
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String studentId, String amount) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_TRANSACTION, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			
			//set search criteria
			StringBuilder whereClause = new StringBuilder(); 
			boolean needAnd = false;
			
			if(StringUtils.isNotBlank(studentId)){
				whereClause.append("t.studentId like '").append(studentId).append("%'");
				needAnd = true;
			}
			
			if(StringUtils.isNotBlank(amount)){
				if(needAnd){
					whereClause.append(" and ");
				}
				whereClause.append("t.amount =").append(Float.parseFloat(amount)).append("");
			}
			
			List<Transaction> list = transactionRepository.findRange(startPosition, maxResult, whereClause.toString());
			List<TransactionModel> modelList = new ArrayList<TransactionModel>();
			
			for(Transaction transaction : list){
				TransactionModel model = new TransactionModel(transaction);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.transactionRepository .count(whereClause.toString())));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listAll() 
	{
		ResponseModel response = new ResponseModel(RestUtil.MODULE_TRANSACTION, RestUtil.REQUEST_TYPE_LIST);
		try
		{
			List<Transaction> list = transactionRepository.findAll();
			List<TransactionModel> modelList = new ArrayList<TransactionModel>();
			for(Transaction transaction : list)
			{
				TransactionModel model = new TransactionModel(transaction);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.transactionRepository.count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}
		catch(Throwable t)
		{
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

}
