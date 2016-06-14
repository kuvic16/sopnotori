package com.okrur.st.jt.service;

import com.okrur.st.jt.rest.model.DonorModel;
import com.okrur.st.jt.rest.model.ResponseModel;

/**
 * @File DonorService.java: DonorService interface
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 25, 2015
 */
public interface DonorService extends AbstractService<DonorModel>{
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String name, String code, String hoEmail, String donorTypeUdvId);
	public ResponseModel listAll();
}
