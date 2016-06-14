/**
 * 
 */
package com.okrur.st.jt.service;

import com.okrur.st.jt.rest.model.LocationModel;

/**
 * @File LocationInfoService.java
 * @author shaiful islam palash | kuvic16@gmail.com
 * @CreationDate Apr 6, 2016
 */
public interface LocationInfoService {
	public LocationModel getBranch(String regionName, String areaName, String branchName);
	public LocationModel getVillage(String upazilaName, String unionName, String villageName);
}
