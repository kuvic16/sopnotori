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

import com.okrur.st.mj.data.domain.Organization;
import com.okrur.st.mj.data.repository.OrganizationRepository;
import com.okrur.st.mj.rest.model.OrganizationModel;
import com.okrur.st.mj.rest.model.ResponseModel;
import com.okrur.st.mj.service.OrganizationService;
import com.okrur.st.mj.util.RestUtil;

/**
 * @File OrganizationServiceImp.java: OrganizationServiceImp Implementation for OrganizationService
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 29, 2015
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class OrganizationServiceImp implements OrganizationService{
	@Resource
	private EJBContext context;
	
	@Inject
	OrganizationRepository organizationRepository ;
	
	
	@Override
	public ResponseModel create(OrganizationModel organizationModel) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_ORGANIZATION, RestUtil.REQUEST_TYPE_CREATE);
		try{
			Organization organization = new Organization();
			organization = organization.setOrganization(organizationModel);
			
			organizationRepository.create(organization);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new OrganizationModel(organization));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel update(String id, OrganizationModel organizationModel) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_ORGANIZATION, RestUtil.REQUEST_TYPE_UPDATE);
		try{
			Organization organization = organizationRepository.findById(id);
			organization = organization.setOrganization(organizationModel);
			
			organizationRepository.edit(organization);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new OrganizationModel(organization));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_ORGANIZATION, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			List<Organization> list = organizationRepository .findRange(startPosition, maxResult);
			List<OrganizationModel> modelList = new ArrayList<OrganizationModel>();
			for(Organization organization : list){
				OrganizationModel model = new OrganizationModel(organization);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.organizationRepository .count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel findById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_ORGANIZATION, RestUtil.REQUEST_TYPE_DELETE);
		try{
			Organization organization = organizationRepository .findById(id);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new OrganizationModel(organization));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel deleteById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_ORGANIZATION, RestUtil.REQUEST_TYPE_DELETE);
		try{
			Organization organization = this.organizationRepository .findById(id);
			organizationRepository .remove(organization);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, null);
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String name, String code, String emailId) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_ORGANIZATION, RestUtil.REQUEST_TYPE_LIST);
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
				whereClause.append("t.name like '%").append(name.trim()).append("%'");
				needAnd = true;
			}
			
			if(StringUtils.isNotBlank(code)){
				if(needAnd){
					whereClause.append(" and ");
				}
				whereClause.append("t.code like '%").append(code.trim()).append("%'");
			}
			
			if(StringUtils.isNotBlank(emailId)){
				if(needAnd){
					whereClause.append(" and ");
				}
				whereClause.append("t.emailId like '%").append(emailId.trim()).append("%'");
			}
			
			List<Organization> list = organizationRepository .findRange(startPosition, maxResult, whereClause.toString());
			List<OrganizationModel> modelList = new ArrayList<OrganizationModel>();
			for(Organization organization : list){
				OrganizationModel model = new OrganizationModel(organization);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.organizationRepository .count(whereClause.toString())));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listAll() {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_ORGANIZATION, RestUtil.REQUEST_TYPE_LIST);
		try{
			List<Organization> list = organizationRepository.findAll();
			List<OrganizationModel> modelList = new ArrayList<OrganizationModel>();
			for(Organization organization : list){
				OrganizationModel model = new OrganizationModel(organization);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.organizationRepository.count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

}
