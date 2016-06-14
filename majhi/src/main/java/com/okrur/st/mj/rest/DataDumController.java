package com.okrur.st.mj.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.okrur.st.mj.service.ExcelFileReaderService;

/**
 * @File DataDumController.java: DataDum rest api
 * @author Shaiful Islam Palash | kuvic16@gmail.com
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate April 04, 2016
 */
@Stateless
@Path("/datadum")
public class DataDumController {
	
	@Inject
	ExcelFileReaderService excelFileReaderSearvice;
	
	@GET
	@Path("/institute")
	@Produces("application/json")
	public void readInstituteExcel() {
		excelFileReaderSearvice.readInstituteExcel();
	}
	
	@GET
	@Path("/teacher")
	@Produces("application/json")
	public void readTeacherExcel() {
		excelFileReaderSearvice.readTeacherExcel();
	}
	
	@GET
	@Path("/student")
	@Produces("application/json")
	public void readStudentExcel() {
		excelFileReaderSearvice.readStudentExcel();
	}
}
