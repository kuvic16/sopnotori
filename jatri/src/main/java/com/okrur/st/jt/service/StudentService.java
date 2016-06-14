package com.okrur.st.jt.service;

import com.okrur.st.jt.rest.model.ResponseModel;
import com.okrur.st.jt.rest.model.StudentModel;

/**
 * @File StudentService.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 26, 2015
 */
public interface StudentService extends AbstractService<StudentModel>{
	public ResponseModel listAll(
									Integer startPosition, 
									Integer maxResult, 
									String fullName,
									String studentId,// roll number
									String gradeId,
									String sex,
									String waiver,
									String guardianMobile, 
									String dropout,
									String nameOfTransferredSchool,
									String instituteId,
									String typeOfEthnicityCommunityUdvId,
									String typeOfCsnUdvId,
									String locationTypeUdvId,
									String locationId,
									String locationHierarchy
								);
	public ResponseModel listBy(String instituteId, String gradeId);
	public ResponseModel findByRoll(String studentRoll);
}
