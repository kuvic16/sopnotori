package net.brac.bep.service;

import net.brac.bep.rest.model.ResponseModel;
import net.brac.bep.rest.model.RightModel;

public interface RightService extends AbstractService<RightModel>{
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String name, String desc);
	public ResponseModel listAll();
}
