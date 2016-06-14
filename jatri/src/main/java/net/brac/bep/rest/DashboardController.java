package net.brac.bep.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import net.brac.bep.rest.model.DashboardModel;
import net.brac.bep.rest.model.ResponseModel;
import net.brac.bep.service.DashboardService;
import net.brac.bep.util.RestUtil;

/**
 * @File DashboardController.java: DashboardController rest api
 * @author Md. Shaiful Islam | kuvic16@gmail.com
 * @CreationDate May 04, 2016
 */
@Stateless
@Path("/dashboard")
public class DashboardController {
	@EJB
	private DashboardService dashboardService;

	@GET
	@Path("/stats")
	@Produces("application/json")
	public ResponseModel stats() {
		ResponseModel response = new ResponseModel(RestUtil.MODULE_DASHBOARD, RestUtil.REQUEST_TYPE_FIND);
		try{
			DashboardModel model = new DashboardModel();
			model.setTotalInstitute(dashboardService.countAllInstitute());
			model.setTotalStudent(dashboardService.countAllStudent());
			model.setTotalFemaleStudent(dashboardService.countAllFemaleStudent());
			model.setTotalDroppedStudent(dashboardService.countAllDroppedStudent());
			
			response.setResponse(RestUtil.SUCCESS, RestUtil.SUCCESSFUL_MESSAGE, model);
		}catch(Throwable t){
			response.setResponse(RestUtil.ERROR, RestUtil.UNEXPECTED_ERROR, null);
		}
		return response;
	}
}
