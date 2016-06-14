package com.okrur.st.jt.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.okrur.st.jt.data.repository.InstituteRepository;
import com.okrur.st.jt.data.repository.StudentRepository;
import com.okrur.st.jt.rest.model.UserModel;
import com.okrur.st.jt.security.SecurityUtil;
import com.okrur.st.jt.service.DashboardService;

/**
 * @File DashboardServiceImp.java: DashboardServiceImp Implementation for GradeService
 * @author Md. Shaiful Islam | kuvic16@gmail.com
 * @CreationDate May 04, 2016
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class DashboardServiceImp implements DashboardService{
	@Resource
	private EJBContext context;
	
	@Inject
	private InstituteRepository instituteRepository;
	@Inject
	private StudentRepository studentRepository;
	
	/* (non-Javadoc)
	 * @see net.brac.bep.service.DashboardService#countAllSchool()
	 */
	@Override
	public int countAllInstitute() {
		try{
			UserModel loggedUserModel = SecurityUtil.getCurrentLoggedUser();
			StringBuilder sql = new StringBuilder();
			sql.append("select count(*) from institute ins ");
			
			if(loggedUserModel.isPo()){
				sql.append(" where ins.po_id = '").append(loggedUserModel.getId()).append("'");
			}else if(!loggedUserModel.isAdmin() && StringUtils.isNotBlank(loggedUserModel.getLocationId())){
				sql.append(" where ins.location_hierarchy LIKE '>%").append(loggedUserModel.getLocationId()).append(">%'");
			}
							
			@SuppressWarnings("rawtypes")
			List result = instituteRepository.loadsByNativeQuery(sql.toString());
			return Integer.valueOf(result.get(0).toString());			
		}catch(Throwable t){
			
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see net.brac.bep.service.DashboardService#countAllStudent()
	 */
	@Override
	public int countAllStudent() {
		try{
			return countStudent(null, null, null);			
		}catch(Throwable t){
			
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see net.brac.bep.service.DashboardService#countAllFemaleStudent()
	 */
	@Override
	public int countAllFemaleStudent() {
		return countStudent("F", null, null);
	}

	/* (non-Javadoc)
	 * @see net.brac.bep.service.DashboardService#countAllGraduateStudent()
	 */
	@Override
	public int countAllGraduateStudent() {
		return countStudent(null, true, null);
	}

	/* (non-Javadoc)
	 * @see net.brac.bep.service.DashboardService#countAllDroppedStudent()
	 */
	@Override
	public int countAllDroppedStudent() {
		return countStudent(null, null, true);
	}
	
	
	private int countStudent(String gender, Boolean graduate, Boolean dropped){
		try{
			UserModel loggedUserModel = SecurityUtil.getCurrentLoggedUser();
			StringBuilder sql = new StringBuilder();
			sql.append("select count(*) from student st join institute ins on (st.institute_id=ins.id)  ");
			
			boolean isAndNeed = false, isWhereNeed = true;
			if(loggedUserModel.isPo()){
				sql.append(" where ins.po_id = '").append(loggedUserModel.getId()).append("'");
				isAndNeed = true; isWhereNeed = false;
			}else if(!loggedUserModel.isAdmin() && StringUtils.isNotBlank(loggedUserModel.getLocationId())){
				sql.append(" where ins.location_hierarchy LIKE '>%").append(loggedUserModel.getLocationId()).append(">%'");
				isAndNeed = true; isWhereNeed = false;
			}
			
			if(StringUtils.isNotBlank(gender)){
				if(isWhereNeed){
					sql.append(" where ");
				}
				if(isAndNeed){
					sql.append(" and ");
				}
				sql.append(" st.sex = '").append(gender).append("'");
				isAndNeed = true; isWhereNeed = false;
			}
			
			if(graduate != null){
				if(isWhereNeed){
					sql.append(" where ");
				}
				if(isAndNeed){
					sql.append(" and ");
				}
				sql.append(" st.brac_graduate = ").append(graduate);
				isAndNeed = true; isWhereNeed = false;
			}
			
			if(dropped != null){
				if(isWhereNeed){
					sql.append(" where ");
				}
				if(isAndNeed){
					sql.append(" and ");
				}
				sql.append(" st.dropout = ").append(dropped);
				isAndNeed = true; isWhereNeed = false;
			}
							
			@SuppressWarnings("rawtypes")
			List result = studentRepository.loadsByNativeQuery(sql.toString());
			return Integer.valueOf(result.get(0).toString());			
		}catch(Throwable t){
			
		}
		return 0;
	}

}
