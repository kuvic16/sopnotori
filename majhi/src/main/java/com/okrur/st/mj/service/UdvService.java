package com.okrur.st.mj.service;

import com.okrur.st.mj.rest.model.ResponseModel;
import com.okrur.st.mj.rest.model.UdvModel;

/**
 * @File LinkedMapService.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 26, 2015
 */
public interface UdvService extends AbstractService<UdvModel>{
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String category, String value, String code);
	public ResponseModel categoryList();
	public ResponseModel udvList(String categoryNames);
	public ResponseModel udvList(String categoryName, String parentId);
	public ResponseModel findByName(String name);
	public ResponseModel findByCategoryAndName(String category, String name);
	public ResponseModel udvListByParentId(String parentId);
}
