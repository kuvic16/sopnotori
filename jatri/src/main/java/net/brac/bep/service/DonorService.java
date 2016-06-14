package net.brac.bep.service;

import net.brac.bep.rest.model.DonorModel;
import net.brac.bep.rest.model.ResponseModel;

/**
 * @File DonorService.java: DonorService interface
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 25, 2015
 */
public interface DonorService extends AbstractService<DonorModel>{
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String name, String code, String hoEmail, String donorTypeUdvId);
	public ResponseModel listAll();
}
