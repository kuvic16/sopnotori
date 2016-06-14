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

import com.okrur.st.mj.data.domain.Role;
import com.okrur.st.mj.data.repository.RoleRepository;
import com.okrur.st.mj.rest.model.ResponseModel;
import com.okrur.st.mj.rest.model.RoleModel;
import com.okrur.st.mj.service.RoleService;
import com.okrur.st.mj.util.RestUtil;

@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class RoleServiceImp implements RoleService{
	@Resource
	private EJBContext context;
	
	@Inject
	RoleRepository roleRepository;
	
	
	@Override
	public ResponseModel create(RoleModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_ROLE, RestUtil.REQUEST_TYPE_CREATE);
		try{
			// TODO validation
			Role role = new Role().setRole(model);
			roleRepository.create(role);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new RoleModel(role));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel update(String id, RoleModel roleModel) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_ROLE, RestUtil.REQUEST_TYPE_UPDATE);
		try{
			// TODO validation
			Role role = roleRepository.findById(id);
			role = role.setRoleForEdit(role, roleModel);
			roleRepository.edit(role);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new RoleModel(role));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_ROLE, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			List<Role> list = roleRepository.findRange(startPosition, maxResult);
			List<RoleModel> modelList = new ArrayList<RoleModel>();
			for(Role role : list){
				RoleModel model = new RoleModel(role);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.roleRepository.count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel findById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_ROLE, RestUtil.REQUEST_TYPE_FIND);
		try{
			Role role = roleRepository.findById(id);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new RoleModel(role, true));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel deleteById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_ROLE, RestUtil.REQUEST_TYPE_DELETE);
		try{
			Role role = this.roleRepository.findById(id);
			roleRepository.remove(role);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, null);
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String name, String desc) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_ROLE, RestUtil.REQUEST_TYPE_LIST);
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
			
			List<Role> list = roleRepository.findRange(startPosition, maxResult, whereClause.toString());
			List<RoleModel> modelList = new ArrayList<RoleModel>();
			for(Role role : list){
				RoleModel model = new RoleModel(role, false);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(roleRepository.count(whereClause.toString())));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listAll() {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_ROLE, RestUtil.REQUEST_TYPE_LIST);
		try{
			List<Role> list = roleRepository.findAll();
			List<RoleModel> modelList = new ArrayList<RoleModel>();
			for(Role role : list){
				RoleModel model = new RoleModel(role);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.roleRepository.count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
}
