package com.okrur.st.mj.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import com.okrur.st.mj.rest.model.ExpenditureModel;
import com.okrur.st.mj.rest.model.FileUploadForm;
import com.okrur.st.mj.rest.model.ResponseModel;
import com.okrur.st.mj.service.ExpenditureService;
import com.okrur.st.mj.service.UtilityService;
import com.okrur.st.mj.util.ConfigurationUtil;

/**
 * @File ExpenditureController.java: ExpenditureController rest api
 * @author Md. Shaiful Islam Palash | kuvic16@gmail.com
 * @CreationDate April 20, 2016
 */
@Stateless
@Path("/expenditure")
public class ExpenditureController {
	@EJB
	private ExpenditureService expenditureService;
	@EJB
	private UtilityService utilityService;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseModel create(ExpenditureModel model) {
		return expenditureService.create(model);
	}

	@DELETE
	@Path("/{id}")
	public ResponseModel deleteById(@PathParam("id") String id) {
		return expenditureService.deleteById(id);
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public ResponseModel findById(@PathParam("id") String id) {
		return expenditureService.findById(id);
	}

	
	@GET
	@Produces("application/json")
	public ResponseModel listAll(@QueryParam("start") Integer startPosition, 
								 @QueryParam("max") Integer maxResult, 
								 @QueryParam("expenditureTypeUdvId") String expenditureTypeUdvId,
								 @QueryParam("headsOfAccountUdvId") String headsOfAccountUdvId,
								 @QueryParam("locationHierarchy") String locationHierarchy,
        						 @QueryParam("year") Integer year,
        						 @QueryParam("month") Integer month){
		
		return expenditureService.listAll(startPosition, maxResult, expenditureTypeUdvId, headsOfAccountUdvId, locationHierarchy, year, month);
		
	}

	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public ResponseModel update(@PathParam("id") String id, ExpenditureModel model){
		return expenditureService.update(id, model);
	}
	
	@POST
	@Path("/uploadFile")
	@Produces("application/json")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ResponseModel uploadFile(@MultipartForm FileUploadForm form) {
		String directory = ConfigurationUtil.config().getString("document.docDirectory");
		return utilityService.uploadFile(form, directory);
	}
}
