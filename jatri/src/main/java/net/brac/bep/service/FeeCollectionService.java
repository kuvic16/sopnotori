package net.brac.bep.service;

import java.util.List;

import net.brac.bep.rest.model.ResponseModel;
import net.brac.bep.rest.model.StudentFeeModel;

/**
 * @File StudentFeeService.java
 * @author Shaiful Islam Palash | kuvic16@gmail.com
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Jan 11, 2016
 */
public interface FeeCollectionService extends AbstractService<StudentFeeModel>{
	public ResponseModel hierarchySearch(boolean init, String hierarchyId, String institueId, String gradeId, String studentId, Integer year, Integer month, String searchTypeId, String searchType);
	public ResponseModel updateAll(List<StudentFeeModel> t);
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String feeId, String instituteId);
	public ResponseModel listByStudent(String studentRoll, String studentId, String instituteId, String gradeId, int year, int month);
	public ResponseModel listAllByStudent(String studentId, int year);
}
