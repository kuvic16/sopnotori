package com.okrur.st.mj.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.okrur.st.mj.data.domain.Donor;
import com.okrur.st.mj.data.repository.DonorRepository;
import com.okrur.st.mj.rest.model.DonorModel;
import com.okrur.st.mj.rest.model.ResponseModel;
import com.okrur.st.mj.service.DonorService;
import com.okrur.st.mj.util.IUtil;
import com.okrur.st.mj.util.RestUtil;

/**
 * @File DonorServiceImp.java: DonorServiceImp Implementation for DonorService
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Dec 14, 2015
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class DonorServiceImp implements DonorService{
	
	@Inject
	DonorRepository donorRepository ;
	
	
	@Override
	public ResponseModel create(DonorModel donorModel) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_DONOR, RestUtil.REQUEST_TYPE_CREATE);
		try{
			// TODO validation
			Donor donor = new Donor();
			donor = donor.setDonor(donorModel);
			donorRepository .create(donor);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new DonorModel(donor));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel update(String id, DonorModel donorModel) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_DONOR, RestUtil.REQUEST_TYPE_UPDATE);
		try{
			// TODO validation
			Donor donor = donorRepository.findById(id);
			donor = donor.setDonor(donorModel);
			donorRepository .edit(donor);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new DonorModel(donor));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_DONOR, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			List<Donor> list = donorRepository .findRange(startPosition, maxResult);
			List<DonorModel> modelList = new ArrayList<DonorModel>();
			for(Donor donor : list){
				DonorModel model = new DonorModel(donor);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.donorRepository .count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel findById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_DONOR, RestUtil.REQUEST_TYPE_DELETE);
		try{
			Donor donor = donorRepository .findById(id);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new DonorModel(donor));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel deleteById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_DONOR, RestUtil.REQUEST_TYPE_DELETE);
		try{
			Donor donor = this.donorRepository .findById(id);
			donorRepository .remove(donor);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, null);
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String name, String code, String hoEmail, String donorTypeUdvId) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_DONOR, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			
			//set search criteria
			 
			boolean needAnd = false;
			StringBuilder sql = new StringBuilder();
			sql.append("select donerTable.id, donerTable.name, donerTable.code, donerTable.ho_email, udvTable.value from donor donerTable, udv udvTable")
			   .append(" where donerTable.donor_type_udv_id = udvTable.id ");
			needAnd = true;
			
			if(StringUtils.isNotBlank(name)){
				if(needAnd){
					sql.append(" and ");
				}
				sql.append("donerTable.name like '%").append(name.trim()).append("%'");
				needAnd = true;
			}
			
			if(StringUtils.isNotBlank(code)){
				if(needAnd){
					sql.append(" and ");
				}
				sql.append("donerTable.code like '%").append(code.trim()).append("%'");
				needAnd = true;
			}
			
			if(StringUtils.isNotBlank(hoEmail)){
				if(needAnd){
					sql.append(" and ");
				}
				sql.append("donerTable.ho_email like '%").append(hoEmail.trim()).append("%'");
				needAnd = true;
			}
			
			if(StringUtils.isNotBlank(donorTypeUdvId)){
				if(needAnd){
					sql.append(" and ");
				}
				sql.append("donerTable.donor_type_udv_id like '%").append(donorTypeUdvId.trim()).append("%'");
				needAnd = true;
			}
			
			List totalList = donorRepository.loadsByNativeQuery(sql.toString());
			
			sql.append(" limit ")
			   .append(startPosition * maxResult)
			   .append(",")
			   .append(maxResult);
			
			/*List<Donor> list = donorRepository.findRange(startPosition, maxResult, whereClause.toString());
			List<DonorModel> modelList = new ArrayList<DonorModel>();
			
			for(Donor donor : list){
				DonorModel model = new DonorModel(donor);
				modelList.add(model);
			}*/
			
			List list = donorRepository.loadsByNativeQuery(sql.toString());
			List<DonorModel> modelList = new ArrayList<DonorModel>();
			
			System.out.println(list.size());
			for(Object object : list){
				Object[] objs = (Object[])object;
				DonorModel model = new DonorModel();
								
				model.setId(IUtil.toString(objs[0]));
				model.setName(IUtil.toString(objs[1]));				
				model.setCode(IUtil.toString(objs[2]));				
				model.setHoEmail(IUtil.toString(objs[3]));				
				model.setDonorTypeUdvId(IUtil.toString(objs[4]));
				
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
		ResponseModel response = new ResponseModel(RestUtil.MODULE_DONOR, RestUtil.REQUEST_TYPE_LIST);
		try
		{
			List<Donor> list = donorRepository.findAll();
			List<DonorModel> modelList = new ArrayList<DonorModel>();
			for(Donor donor : list)
			{
				DonorModel model = new DonorModel(donor);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.donorRepository.count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}
		catch(Throwable t)
		{
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

}
