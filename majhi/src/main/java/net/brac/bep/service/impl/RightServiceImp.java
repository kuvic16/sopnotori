package net.brac.bep.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import net.brac.bep.data.domain.Right;
import net.brac.bep.data.repository.RightRepository;
import net.brac.bep.rest.model.ResponseModel;
import net.brac.bep.rest.model.RightModel;
import net.brac.bep.service.RightService;
import net.brac.bep.util.RestUtil;

@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class RightServiceImp implements RightService{
	@Resource
	private EJBContext context;
	
	@Inject
	RightRepository rightRepository;
	
	
	@Override
	public ResponseModel create(RightModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_RIGHT, RestUtil.REQUEST_TYPE_CREATE);
		try{
			// TODO validation
			if(rightRepository.isExist(model.getName())){
				response.setResponse(RestUtil.ERROR, RestUtil.ALREADY_EXIST_MESSAGE, null);
			}else{
				Right right = new Right().setRight(model);
				rightRepository.create(right);
				response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new RightModel(right));
			}
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel update(String id, RightModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_RIGHT, RestUtil.REQUEST_TYPE_UPDATE);
		try{
			// TODO validation
			Right right = new Right();
			right = rightRepository.findById(id);
			right = right.setRight(model);
			rightRepository.edit(right);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new RightModel(right));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_RIGHT, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			List<Right> list = rightRepository.findRange(startPosition, maxResult);
			List<RightModel> modelList = new ArrayList<RightModel>();
			for(Right right : list){
				RightModel model = new RightModel(right);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.rightRepository.count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel findById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_RIGHT, RestUtil.REQUEST_TYPE_DELETE);
		try{
			Right right = rightRepository.findById(id);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new RightModel(right));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel deleteById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_RIGHT, RestUtil.REQUEST_TYPE_DELETE);
		try{
			Right rights = this.rightRepository.findById(id);
			rightRepository.remove(rights);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, null);
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String name, String desc) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_RIGHT, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			
			//set search criteria
			StringBuilder whereClause = new StringBuilder(); boolean needAnd = false;
			if(StringUtils.isNotBlank(name)){
				whereClause.append("t.name like '%").append(name).append("%'");
				needAnd = true;
			}
			
			if(StringUtils.isNotBlank(desc)){
				if(needAnd){
					whereClause.append(" and ");
				}
				whereClause.append("t.description like '%").append(desc).append("%'");
			}
			
			List<Right> list = rightRepository.findRange(startPosition, maxResult, whereClause.toString());
			List<RightModel> modelList = new ArrayList<RightModel>();
			for(Right right : list){
				RightModel model = new RightModel(right);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.rightRepository.count(whereClause.toString())));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel listAll() {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_RIGHT, RestUtil.REQUEST_TYPE_LIST);
		try{
			List<Right> list = rightRepository.findAll();
			List<RightModel> modelList = new ArrayList<RightModel>();
			for(Right right : list){
				RightModel model = new RightModel(right);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.rightRepository.count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
}
