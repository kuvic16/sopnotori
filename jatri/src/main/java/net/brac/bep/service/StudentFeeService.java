package net.brac.bep.service;

import java.util.List;

import net.brac.bep.data.domain.StudentFee;
import net.brac.bep.rest.model.ResponseModel;
import net.brac.bep.rest.model.StudentFeeModel;

/**
 * @File StudentFeeService.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Jan 11, 2016
 */
public interface StudentFeeService extends AbstractService<StudentFeeModel>{
	public ResponseModel updateAll(List<StudentFeeModel> t);
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String feeId, String instituteId);
	public ResponseModel listByStudent(String studentRoll, String studentId, String instituteId, String gradeId, int year, int month);
	public ResponseModel listAllByStudent(String studentId, int year);
	public StudentFee findStudentFee(String studentId, String instituteId, String gradeId, int year, int month, String feeTypeUdvId, String feeCategoryUdvId);
}
