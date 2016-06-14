package com.okrur.st.jt.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.okrur.st.jt.rest.model.ResponseModel;
import com.okrur.st.jt.service.FinancialReportService;
import com.okrur.st.jt.service.UtilityService;
import com.okrur.st.jt.service.impl.FinancialReportServiceImp;

/**
 * @File ReportController.java: ReportController rest api
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate 10 May, 2016
 */
@Stateless
@Path("/report")
public class FinancialReportController {
	
	@Inject
	private FinancialReportService financialReportService;
	
	@Inject
	private UtilityService utilityService;

	@GET
	@Path("/columnlist")
	@Produces("application/json")
	public ResponseModel getColumnHeaderNames(
			@QueryParam("headsOfAccountUdvId") String headsOfAccountUdvId,
			@QueryParam("year") String year)
	{
		return financialReportService.getColumnsNameByHeadOfAccount(headsOfAccountUdvId,year);
	}

	@GET
	@Produces("application/json")
	public ResponseModel getColumnValues(
								 @QueryParam("start") Integer startPosition, 
								 @QueryParam("max") Integer maxResult, 
								 @QueryParam("expenditureTypeUdvId") String expenditureTypeUdvId,
								 @QueryParam("headsOfAccountUdvId") String headsOfAccountUdvId,
								 @QueryParam("locationId") String locationId,
								 @QueryParam("locationHierarchy") String locationHierarchy,
        						 @QueryParam("year") Integer year,
        						 @QueryParam("month") Integer month,
        						 @QueryParam("totalNumberOfColumn") Integer totalNumberOfColumn
        						)
	{
		return financialReportService.listAll(startPosition, maxResult,
				expenditureTypeUdvId, headsOfAccountUdvId,
				locationId,
				locationHierarchy, year, month,totalNumberOfColumn);
	}

}
