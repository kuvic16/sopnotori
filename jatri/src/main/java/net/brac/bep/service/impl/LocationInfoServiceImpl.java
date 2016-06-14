/**
 * 
 */
package net.brac.bep.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.brac.bep.rest.model.LocationModel;
import net.brac.bep.service.LocationInfoService;
import net.brac.bep.util.IUtil;


/**
 * @File LocationInfoServiceImpl.java
 * @author shaiful islam palash | kuvic16@gmail.com
 * @CreationDate Apr 6, 2016
 */


@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class LocationInfoServiceImpl implements LocationInfoService {
	@Resource
	private EJBContext context;
	
	//sample URL: http://brachub.brac.net:8080/brachub/bips/location/getLocationsByCategory/BRANCH?hierarchyName=Rajshahi>NATORE&name=GOPALPUR(LALPUR)
	public static String BRANCH_URL = "http://brachub.brac.net:8080/brachub/bips/location/getLocationsByCategory/BRANCH";
	public static String VILLAGE_URL = "http://brachub.brac.net:8080/brachub/bips/location/getLocationsByCategory/VILLAGE";
	//public static String BRANCH_URL = "http://172.26.4.65/brachub/bips/location/getLocationsByCategory/BRANCH";
	//public static String VILLAGE_URL = "http://172.26.4.65/brachub/bips/location/getLocationsByCategory/VILLAGE";

	/* (non-Javadoc)
	 * @see net.brac.bep.service.LocationInfoService#getBranch(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public LocationModel getBranch(String regionName, String areaName, String branchName) {
		LocationModel branchModel = null;
		try {
			List<LocationModel> locationModelList= new ArrayList<LocationModel>();
			ClientRequest request = new ClientRequest(BRANCH_URL);
			request.queryParameter("hierarchyName", regionName + ">" + areaName);
			request.queryParameter("name", branchName);
			request.accept("application/json");
			ClientResponse<String> response = request.get(String.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(response.getEntity().getBytes())));

			String jsonString = br.readLine();
			if(!jsonString.isEmpty()) {
				GsonBuilder builder = new GsonBuilder();
				Gson gson = builder.create();
				LocationModel locationModel = gson.fromJson(jsonString, LocationModel.class);
				locationModelList = locationModel.getListOfLocation();
			}
			
			if(IUtil.isNotBlank(locationModelList)){
				branchModel = locationModelList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return branchModel;
	}

	/* (non-Javadoc)
	 * @see net.brac.bep.service.LocationInfoService#getVillage(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public LocationModel getVillage(String upazilaName, String unionName, String villageName) {
		LocationModel villageModel = null;
		try {
			List<LocationModel> locationModelList= new ArrayList<LocationModel>();
			ClientRequest request = new ClientRequest(VILLAGE_URL);
			request.queryParameter("hierarchyName", upazilaName + ">" + unionName);
			request.queryParameter("name", villageName);
			request.accept("application/json");
			ClientResponse<String> response = request.get(String.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(response.getEntity().getBytes())));

			String jsonString = br.readLine();
			if(!jsonString.isEmpty()) {
				GsonBuilder builder = new GsonBuilder();
				Gson gson = builder.create();
				LocationModel locationModel = gson.fromJson(jsonString, LocationModel.class);
				locationModelList = locationModel.getListOfLocation();
			}
			
			if(IUtil.isNotBlank(locationModelList)){
				villageModel = locationModelList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return villageModel;
	}

}

