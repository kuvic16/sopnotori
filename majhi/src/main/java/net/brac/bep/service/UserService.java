package net.brac.bep.service;

import net.brac.bep.rest.model.ResponseModel;
import net.brac.bep.rest.model.UserModel;
import net.brac.bep.rest.model.UserSettingsModel;

/**
 * @File UserService.java: User related all service 
 * @author shaiful islam palash | kuvic16@gmail.com
 * @CreationDate Nov 25, 2015
 */
public interface UserService extends AbstractService<UserModel>{
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String username, String email, String mobileNumber, String userCategoryUdvId, String locationId, String locationHierarchy, String instituteId, String dropOut);
	public ResponseModel listAllTeacher(Integer startPosition, Integer maxResult, String username, String email, String mobileNumber, String userCategoryUdvId, String locationId, String locationHierarchy, String instituteId, String dropOut);
	public ResponseModel listAll();
	public ResponseModel listAllPo(String po, String locationHierarchy);
	public ResponseModel findByUsernamePassword(String username, String password);
	public ResponseModel findByUsername(String username);
	public ResponseModel findOnlyUserByUsername(String username);	
	public ResponseModel updateSettings(UserSettingsModel t);
	public ResponseModel findSettingsById(String id);
//	public ResponseModel getLoggedUser();
	/*public ResponseModel createByTeacher(UserModel userModel, String teacher);*/
	
}
