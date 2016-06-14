package net.brac.bep.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import net.brac.bep.util.IUtil;
import org.apache.commons.lang3.StringUtils;
import net.brac.bep.data.domain.Udv;
import net.brac.bep.data.enums.CategoryEnum;
import net.brac.bep.data.repository.UdvRepository;
import net.brac.bep.rest.model.UdvModel;
import net.brac.bep.rest.model.ResponseModel;
import net.brac.bep.service.UdvService;
import net.brac.bep.util.RestUtil;

/**
 * @File LinkedMapServiceImp.java: LinkedMapServiceImp Implementation for UdvService
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 29, 2015
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class UdvServiceImp implements UdvService{
	@Resource
	private EJBContext context;
	
	@Inject
	UdvRepository udvRepository ;
	
	
	@Override
	public ResponseModel create(UdvModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_UDV, RestUtil.REQUEST_TYPE_CREATE);
		try{
			// TODO validation
			if(udvRepository.isExist(model.getCategory(), model.getValue())){
				response.setResponse(RestUtil.ERROR, RestUtil.ALREADY_EXIST_MESSAGE, null);
			}else{
				Udv linkedMap = new Udv().setUdv(model);
				udvRepository .create(linkedMap);
				response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new UdvModel(linkedMap));
			}
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel update(String id, UdvModel udvModel) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_UDV, RestUtil.REQUEST_TYPE_UPDATE);
		try{
			// TODO validation
			Udv linkedMap =udvRepository.findById(id);
			udvRepository .edit(linkedMap.setUdv(udvModel));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new UdvModel(linkedMap));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_UDV, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			List<Udv> list = udvRepository .findRange(startPosition, maxResult);
			List<UdvModel> modelList = new ArrayList<UdvModel>();
			for(Udv linkedMap : list){
				UdvModel model = new UdvModel(linkedMap);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.udvRepository .count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel findById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_UDV, RestUtil.REQUEST_TYPE_DELETE);
		try{
			Udv udv = udvRepository .findById(id);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new UdvModel(udv));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel deleteById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_UDV, RestUtil.REQUEST_TYPE_DELETE);
		try{
			Udv linkedMap = this.udvRepository .findById(id);
			udvRepository .remove(linkedMap);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, null);
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String category, String value, String altValue) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_UDV, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			
			//set search criteria
			StringBuilder whereClause = new StringBuilder(); boolean needAnd = false;
			if(StringUtils.isNotBlank(value)){
				whereClause.append("t.value like '%").append(value).append("%'");
				needAnd = true;
			}
			
			if(StringUtils.isNotBlank(category)){
				if(needAnd){
					whereClause.append(" and ");
				}
				whereClause.append("t.category like '%").append(category).append("%'");
			}
			
			if(StringUtils.isNotBlank(altValue)){
				if(needAnd){
					whereClause.append(" and ");
				}
				whereClause.append("t.altValue like '%").append(altValue.trim()).append("%'");
			}
			
			List<Udv> list = udvRepository .findRange(startPosition, maxResult, whereClause.toString());
			List<UdvModel> modelList = new ArrayList<UdvModel>();
			for(Udv linkedMap : list){
				UdvModel model = new UdvModel(linkedMap);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.udvRepository .count(whereClause.toString())));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel categoryList() {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_UDV, RestUtil.REQUEST_TYPE_LIST);
		try{
			Object[] categoryList = CategoryEnum.categoryList();
			response.setTotal(String.valueOf(categoryList.length));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, categoryList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel udvList(String categoryNames) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_UDV, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(StringUtils.isNotBlank(categoryNames)){
				String[] categories = categoryNames.split(",");
				Object[] params = new Object[categories.length];
				StringBuilder clause = new StringBuilder();
				
				boolean orNeeded = false;
				for(int i=0; i<categories.length; i++){
					if(orNeeded){
						clause.append(" or ");
					}
					clause.append("t.category = ?").append(i+1);
					params[i] = categories[i].trim();
					orNeeded = true;
				}
				
				List<Udv> list = udvRepository.findAll(clause.toString(), params);
				List<UdvModel> modelList = new ArrayList<UdvModel>();
				for(Udv udv : list){
					UdvModel model = new UdvModel(udv);
					modelList.add(model);
				}
				response.setTotal(String.valueOf(list.size()));
				response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
			}
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel findByName(String name) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_UDV, RestUtil.REQUEST_TYPE_FIND);
		try{
			String clause = "t.value = ?1";
			Object[] params = {name};
			List<Udv> list = udvRepository.loadByClause(clause, params);
			
			Udv udv = null;
			if(list != null && list.size()>0){
				udv = (Udv)list.get(0);
			}
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new UdvModel(udv));
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see net.brac.bep.service.UdvService#findByCategoryAndName(java.lang.String, java.lang.String)
	 */
	@Override
	public ResponseModel findByCategoryAndName(String category, String name) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_UDV, RestUtil.REQUEST_TYPE_FIND);
		try{
			String clause = "t.category=?1 and t.value = ?2";
			Object[] params = {category, name};
			List<Udv> list = udvRepository.loadByClause(clause, params);
			
			Udv udv = null;
			if(list != null && list.size()>0){
				udv = (Udv)list.get(0);
			}
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new UdvModel(udv));
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see net.brac.bep.service.UdvService#udvList(java.lang.String, java.lang.String)
	 */
	@Override
	public ResponseModel udvList(String categoryName, String parentId) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_UDV, RestUtil.REQUEST_TYPE_FIND);
		try{
			String clause = "t.category=?1 and t.parentId = ?2";
			Object[] params = {categoryName, parentId};
			List<Udv> list = udvRepository.loadByClause(clause, params);
			
			List<UdvModel> modelList = new ArrayList<UdvModel>();
			for(Udv udv : list){
				UdvModel model = new UdvModel(udv);
				modelList.add(model);
			}
			
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel udvListByParentId(String parentId) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_UDV, RestUtil.REQUEST_TYPE_FIND);
		try{
			StringBuilder sql = new StringBuilder();
			UdvModel model;
			List<UdvModel> modelList = new ArrayList<UdvModel>();

			sql.append("SELECT udv.id, udv.`value` ")
					.append("FROM udv udv ")
					.append(" WHERE udv.parent_id = '" + parentId + "' ORDER BY udv.`value`;");
			List rawQueryExecutionList = udvRepository.loadsByNativeQuery(sql.toString());

			for (Object object : rawQueryExecutionList) {
				Object[] objs = (Object[]) object;
				model = new UdvModel();
				model.setId(IUtil.toString(objs[0]));
				model.setValue(IUtil.toString(objs[1]));
				modelList.add(model);
			}
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	
}
