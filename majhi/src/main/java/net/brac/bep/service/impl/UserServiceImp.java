package net.brac.bep.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import net.brac.bep.data.domain.Udv;
import net.brac.bep.data.domain.User;
import net.brac.bep.data.repository.InstituteRepository;
import net.brac.bep.data.repository.UdvRepository;
import net.brac.bep.data.repository.UserRepository;
import net.brac.bep.rest.model.ResponseModel;
import net.brac.bep.rest.model.RoleModel;
import net.brac.bep.rest.model.UdvModel;
import net.brac.bep.rest.model.UserModel;
import net.brac.bep.rest.model.UserSettingsModel;
import net.brac.bep.security.SecurityUtil;
import net.brac.bep.service.RoleService;
import net.brac.bep.service.UserService;
import net.brac.bep.util.IConstant;
import net.brac.bep.util.IUtil;
import net.brac.bep.util.RestUtil;

/**
 * @File UserServiceImp.java: UserServiceImp Implementation for UserService
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 29, 2015
 */
@Stateless(name="userService")
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class UserServiceImp implements UserService{
		
	@Resource
	private EJBContext context;
	
	@Inject
	private UserRepository userRepository ;
	
	@Inject
	private UdvRepository udvRepository;
	
	@Inject
	private RoleService roleService;
	
	@Inject
	private InstituteRepository instituteRepository;
	
	@Override
	public ResponseModel create(UserModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_USER, RestUtil.REQUEST_TYPE_CREATE);
		Udv udv=null;
		//boolean teacher = userModel.isOnlyTeacher();
		if(model.isOnlyTeacher()){
			udv = udvRepository.findBy("value", "Teacher");
		}
		
		try{
			// TODO validation
			User user = new User().setUser(model);
			//get udv id of teacher
			if(udv!=null)
				user.setUserCategoryUdvId(udv.getId());
			
			
			/*SELECT COUNT(*) FROM users usr
			WHERE usr.username = 'razzak'*/
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT usr.username FROM users usr ")
			   .append("WHERE usr.username = '" + model.getUsername() + "'");
			
			List list = userRepository.loadsByNativeQuery(sql.toString());
				
			if(list.size()== 0){
				userRepository .create(user);
				response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new UserModel(user));
			}
			else
				response.setResponse(RestUtil.DUPLICATE, RestUtil.DUPLICATE_USER_NAME, null);
				
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		
		return response;
	}
	
	@Override
	public ResponseModel update(String id, UserModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_USER, RestUtil.REQUEST_TYPE_UPDATE);
		try{
			User user = userRepository.findById(id);
			if(user.getUsername().equalsIgnoreCase(model.getUsername())){
				userRepository .edit(user.setUser(model));
				response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new UserModel(user));
			}else{
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT usr.username FROM users usr ")
				   .append("WHERE usr.username = '" + model.getUsername() + "'");
				
				List list = userRepository.loadsByNativeQuery(sql.toString());
				if(list.size()== 0){
					userRepository .edit(user.setUser(model));
					response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new UserModel(user));
				}else{
					response.setResponse(RestUtil.DUPLICATE, RestUtil.DUPLICATE_USER_NAME, model);
				}
			}			
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	
	@Override
	public ResponseModel updateSettings(UserSettingsModel model) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_USER, RestUtil.REQUEST_TYPE_UPDATE);
		try{
			User user = userRepository.findById(model.getId());
			userRepository.edit(user.setUserSettings(model));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new UserSettingsModel(user));						
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_USER, RestUtil.REQUEST_TYPE_LIST);
		try{
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			List<User> list = userRepository .findRange(startPosition, maxResult);
			List<UserModel> modelList = new ArrayList<UserModel>();
			for(User user : list){
				UserModel model = new UserModel(user);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(this.userRepository .count()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel findById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_USER, RestUtil.REQUEST_TYPE_FIND);
		try{
			User user = userRepository .findById(id);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new UserModel(user));
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel findSettingsById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_USER, RestUtil.REQUEST_TYPE_FIND);
		try{
			User user = userRepository .findById(id);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, new UserSettingsModel(user));
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel deleteById(String id) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_USER, RestUtil.REQUEST_TYPE_DELETE);
		try{
			User user = this.userRepository .findById(id);
			userRepository .remove(user);
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, null);
		}catch(Throwable t){
			// TODO log the exception
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	

	
	@Override
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String username, String email, String mobileNumber, String userCategoryUdvId, String locationId, String locationHierarchy, String instituteId, String dropOut){
		ResponseModel response = new ResponseModel(RestUtil.MODULE_USER, RestUtil.REQUEST_TYPE_LIST);
		try{
			UserModel loggedUserModel = SecurityUtil.getCurrentLoggedUser();
			
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			
			boolean needWhere = true;
			boolean needAnd = false;
			boolean locationIncluded = false;
			StringBuilder sql = new StringBuilder();
						
			sql.append(" SELECT usr.id, usr.username, usr.mobile_number, usr.email, ud.value FROM users usr ");
			sql.append(" left join udv ud on (usr.user_category_udv_id = ud.id)");
			
			if(StringUtils.isNotBlank(username)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND "); 
				sql.append("usr.username like '%").append(username.trim()).append("%'");
				needAnd = true; needWhere = false;
			}
				
			if(StringUtils.isNotBlank(email)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND ");
				sql.append("usr.email LIKE'%").append(email.trim()).append("%'");
				needAnd = true; needWhere = false;
			}
			
			if(StringUtils.isNotBlank(mobileNumber)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND ");
				sql.append("usr.mobile_number LIKE '%").append(mobileNumber.trim()).append("%'");
				needAnd = true; needWhere = false;
			}
		
			if(StringUtils.isNotBlank(userCategoryUdvId)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND ");
				sql.append("usr.user_category_udv_id = '").append(userCategoryUdvId.trim()).append("'");
				needAnd = true; needWhere = false;
			}
			
			if(StringUtils.isNotBlank(instituteId)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND ");
				sql.append("usr.institute_id = '").append(instituteId.trim()).append("'");
				needAnd = true; needWhere = false;
			}
			
			if(StringUtils.isNotBlank(dropOut)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND ");
				sql.append("usr.drop_out = ").append(Boolean.parseBoolean(dropOut.trim())).append("");
				needAnd = true; needWhere = false;
			}
			
			
							
			if(StringUtils.isNotBlank(locationHierarchy)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND ");
				sql.append("usr.location_hierarchy LIKE '%").append(locationHierarchy.trim()).append("%'");
				needAnd = true; needWhere = false;
				locationIncluded = true;
			}
			
			
			if(!loggedUserModel.isAdmin() && StringUtils.isNotBlank(loggedUserModel.getLocationId())){
				if(!locationIncluded){
					if(needWhere) sql.append(" where ");
					if(needAnd) sql.append(" AND ");
					sql.append("usr.location_hierarchy LIKE '>%").append(loggedUserModel.getLocationId()).append(">%'");
					needAnd = true; needWhere = false;
				}
				
			}
			
			int totalSize = userRepository.countByJNQ(sql.toString());									
			sql.append(" limit ") .append(startPosition * maxResult).append(",").append(maxResult);
						
			@SuppressWarnings("rawtypes")
			List list = userRepository.loadsByNativeQuery(sql.toString());
			List<UserModel> modelList = new ArrayList<UserModel>();
			
			for(Object object : list){
				Object[] objs = (Object[])object;
				UserModel model = new UserModel();
								
				model.setId(IUtil.toString(objs[0]));
				model.setUsername(IUtil.toString(objs[1]));				
				model.setMobileNumber(IUtil.toString(objs[2]));				
				model.setEmail(IUtil.toString(objs[3]));				
				model.setUserCategoryUdvId(IUtil.toString(objs[4]));
				
				modelList.add(model);
			}
			
			response.setTotal(String.valueOf(totalSize));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);			
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel listAllTeacher(Integer startPosition, Integer maxResult, String username, String email, String mobileNumber, String userCategoryUdvId, String locationId, String locationHierarchy, String instituteId, String dropOut){
		ResponseModel response = new ResponseModel(RestUtil.MODULE_TEACHER, RestUtil.REQUEST_TYPE_LIST);
		try{
			UserModel loggedUserModel = SecurityUtil.getCurrentLoggedUser();
			
			if(startPosition == null){
				startPosition = 0;
			}
			
			if(maxResult == null){
				maxResult = 10;
			}
			
			boolean needWhere = true;
			boolean needAnd = false;
			boolean locationIncluded = false;
			StringBuilder sql = new StringBuilder();
						
			sql.append("SELECT usr.id, usr.username, usr.mobile_number, usr.email, ins.name FROM users usr join udv ud on (usr.user_category_udv_id = ud.id and ud.value='Teacher') ");
			sql.append("left join institute ins on (ins.id = usr.institute_id)");
			
			if(StringUtils.isNotBlank(username)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND "); 
				sql.append("usr.username like '%").append(username.trim()).append("%'");
				needAnd = true; needWhere = false;
			}
				
			if(StringUtils.isNotBlank(email)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND ");
				sql.append("usr.email LIKE'%").append(email.trim()).append("%'");
				needAnd = true; needWhere = false;
			}
			
			if(StringUtils.isNotBlank(mobileNumber)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND ");
				sql.append("usr.mobile_number LIKE '%").append(mobileNumber.trim()).append("%'");
				needAnd = true; needWhere = false;
			}
		
			if(StringUtils.isNotBlank(userCategoryUdvId) && !StringUtils.equals(userCategoryUdvId, "Teacher")){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND ");
				sql.append("usr.user_category_udv_id = '").append(userCategoryUdvId.trim()).append("'");
				needAnd = true; needWhere = false;
			}
			
			if(StringUtils.isNotBlank(instituteId)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND ");
				sql.append("usr.institute_id = '").append(instituteId.trim()).append("'");
				needAnd = true; needWhere = false;
			}
			
			if(StringUtils.isNotBlank(dropOut)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND ");
				sql.append("usr.drop_out = ").append(Boolean.parseBoolean(dropOut.trim())).append("");
				needAnd = true; needWhere = false;
			}
			
			
							
			if(StringUtils.isNotBlank(locationHierarchy)){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND ");
				sql.append("usr.location_hierarchy LIKE '%").append(locationHierarchy.trim()).append("%'");
				needAnd = true; needWhere = false;
				locationIncluded = true;
			}
			
			
			if(!loggedUserModel.isAdmin() && StringUtils.isNotBlank(loggedUserModel.getLocationId())){
				if(!locationIncluded){
					if(needWhere) sql.append(" where ");
					if(needAnd) sql.append(" AND ");
					sql.append("usr.location_hierarchy LIKE '>%").append(loggedUserModel.getLocationId()).append(">%'");
					needAnd = true; needWhere = false;
				}
				
			}
			
			if(loggedUserModel.isPo()){
				if(needWhere) sql.append(" where ");
				if(needAnd) sql.append(" AND ");
				sql.append("ins.po_id = '").append(loggedUserModel.getId()).append("'");
				needAnd = true; needWhere = false;
			}
			
			int totalSize = userRepository.countByJNQ(sql.toString());									
			sql.append(" limit ") .append(startPosition * maxResult).append(",").append(maxResult);
						
			@SuppressWarnings("rawtypes")
			List list = userRepository.loadsByNativeQuery(sql.toString());
			List<UserModel> modelList = new ArrayList<UserModel>();
			
			for(Object object : list){
				Object[] objs = (Object[])object;
				UserModel model = new UserModel();
								
				model.setId(IUtil.toString(objs[0]));
				model.setUsername(IUtil.toString(objs[1]));				
				model.setMobileNumber(IUtil.toString(objs[2]));				
				model.setEmail(IUtil.toString(objs[3]));				
				model.setUserCategoryUdvId(IUtil.toString(objs[4]));
				
				modelList.add(model);
			}
			
			response.setTotal(String.valueOf(totalSize));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	
	@Override
	public ResponseModel listAll() {
		/*ResponseModel response = new ResponseModel(RestUtil.MODULE_ORGANIZATION, RestUtil.REQUEST_TYPE_LIST);
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
		return response;*/
		return null;
	}
	
	@Override
	public ResponseModel listAllPo(String value, String locationHierarchy) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_USER, RestUtil.REQUEST_TYPE_LIST);
		try{
			UserModel loggedUserModel = SecurityUtil.getCurrentLoggedUser();
			
			StringBuilder sql = new StringBuilder();
			Object[] params = new Object[]{value,locationHierarchy};
			sql.append("select u from User u, Udv ud where ud.value = ?1 and u.userCategoryUdvId = ud.id and u.locationHierarchy = ?2");
			
			if(!loggedUserModel.getUsername().equalsIgnoreCase(IConstant.ADMIN_USER) && StringUtils.isNotBlank(loggedUserModel.getLocationId())){
				sql.append(" and u.locationHierarchy like '>%").append(loggedUserModel.getLocationId()).append(">%'");
			}
						
			List<User> list = new ArrayList<User>();
			list = userRepository.loadByQuery(sql.toString(), params);
	
			List<UserModel> modelList = new ArrayList<UserModel>();
			for(User user : list){
				UserModel model = new UserModel(user);
				modelList.add(model);
			}
			response.setTotal(String.valueOf(list.size()));
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, modelList);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}

	@Override
	public ResponseModel findByUsernamePassword(String username, String password) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_USER, RestUtil.REQUEST_TYPE_USER_LOGIN);
		try{
			String clause = "t.username = ?1 and t.password = ?2";
			Object[] params = new Object[]{username, password};
			
			List<User> list = new ArrayList<User>();
			list = userRepository.loadByClause(clause, params);	
			if(list != null && list.size() > 0){
				UserModel model = new UserModel(list.get(0));
				response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, model);
			}else{
				response.setResponse(RestUtil.ERROR, RestUtil.AUTHENTICATION_ERROR_MESSAGE, null);
			}
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	@Override
	public ResponseModel findByUsername(String username) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_USER, RestUtil.REQUEST_TYPE_USER_LOGIN);
		try{
			String clause = "t.username = ?1";
			Object[] params = new Object[]{username};
			
			List<User> list = new ArrayList<User>();
			list = userRepository.loadByClause(clause, params);	
			if(list != null && list.size() > 0){
				User user = list.get(0);
				
				// get user role & rights
				RoleModel roleModel = null;
				if(StringUtils.isNotBlank(user.getRoleId())){
					ResponseModel roleResponseModel = roleService.findById(user.getRoleId());
					roleModel = (RoleModel)roleResponseModel.getModel();
				}
				
				// get user category
				UdvModel categoryModel = null;
				if(StringUtils.isNotBlank(user.getRoleId())){
					Udv categoryUdv = udvRepository.findById(user.getUserCategoryUdvId());
					categoryModel = new UdvModel(categoryUdv);					
				}
				UserModel model = new UserModel(user, roleModel, categoryModel);
				
				response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, model);
			}else{
				response.setResponse(RestUtil.ERROR, RestUtil.AUTHENTICATION_ERROR_MESSAGE, null);
			}
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
	
	
	@Override
	public ResponseModel findOnlyUserByUsername(String username) {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_USER, RestUtil.REQUEST_TYPE_USER_LOGIN);
		try{
			String clause = "t.username = ?1";
			Object[] params = new Object[]{username};
			
			List<User> list = new ArrayList<User>();
			list = userRepository.loadByClause(clause, params);	
			if(list != null && list.size() > 0){
				User user = list.get(0);
				
				UserModel model = new UserModel(user);
				response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, model);
			}else{
				response.setResponse(RestUtil.ERROR, RestUtil.AUTHENTICATION_ERROR_MESSAGE, null);
			}
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}	
}
