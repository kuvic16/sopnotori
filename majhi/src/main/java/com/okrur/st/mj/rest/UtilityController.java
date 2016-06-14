package com.okrur.st.mj.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.okrur.st.mj.data.enums.CategoryEnum;
import com.okrur.st.mj.data.enums.RightsEnum;
import com.okrur.st.mj.rest.model.ResponseModel;
import com.okrur.st.mj.rest.model.RightModel;
import com.okrur.st.mj.rest.model.UdvModel;
import com.okrur.st.mj.service.RightService;
import com.okrur.st.mj.service.UdvService;
import com.okrur.st.mj.util.RestUtil;

/**
 * @File UtilityController.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Dec 30, 2015
 */
@Stateless
@Path("/util")
public class UtilityController {
	@EJB
	private UdvService udvService;
	
	@EJB
	private RightService rightService;

	@GET
	@Path("/udv")
	@Produces("application/json")
	public ResponseModel categoryList() {
		ResponseModel response = new ResponseModel();
		try{
			for (UdvModel udvModel : getUdvModelList()) {
				udvService.create(udvModel);
			}
			response.setSuccess(RestUtil.SUCCESS);
		}catch(Throwable t){
			response.setSuccess(RestUtil.ERROR);
		}
		return response;
	}
	
	@GET
	@Path("/rights")
	@Produces("application/json")
	public ResponseModel rightList() {
		ResponseModel response = new ResponseModel();
		try{
			for (RightModel udvModel : getRightModelList()) {
				rightService.create(udvModel);
			}
			response.setSuccess(RestUtil.SUCCESS);
		}catch(Throwable t){
			response.setSuccess(RestUtil.ERROR);
		}
		return response;
	}
	
	private List<UdvModel> getUdvModelList(){
		List<UdvModel> list = new ArrayList<UdvModel>();
		
		// designation
		UdvModel udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.DESIGNATION.getName());
		udvModel.setValue("Program manager");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.DESIGNATION.getName());
		udvModel.setValue("MIS Officer");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.DESIGNATION.getName());
		udvModel.setValue("Software engineer");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.DESIGNATION.getName());
		udvModel.setValue("Branch manager");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.DESIGNATION.getName());
		udvModel.setValue("Director");
		list.add(udvModel);
		
		// user cateogry
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.USER_CATEGORY.getName());
		udvModel.setValue("HO");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.USER_CATEGORY.getName());
		udvModel.setValue("PO");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.USER_CATEGORY.getName());
		udvModel.setValue("PM");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.USER_CATEGORY.getName());
		udvModel.setValue("BO");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.USER_CATEGORY.getName());
		udvModel.setValue("ICT");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.USER_CATEGORY.getName());
		udvModel.setValue("BEP");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.USER_CATEGORY.getName());
		udvModel.setValue("Teacher");
		list.add(udvModel);
		
		// location type
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.LOCATION_TYPE.getName());
		udvModel.setValue("Remote");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.LOCATION_TYPE.getName());
		udvModel.setValue("Char/Hoar");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.LOCATION_TYPE.getName());
		udvModel.setValue("CHT");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.LOCATION_TYPE.getName());
		udvModel.setValue("Coastal");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.LOCATION_TYPE.getName());
		udvModel.setValue("Urban");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.LOCATION_TYPE.getName());
		udvModel.setValue("Plain land");
		list.add(udvModel);
		
		
		// institute type
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.INSTITUTE_TYPE.getName());
		udvModel.setValue("BRAC Primary schools");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.INSTITUTE_TYPE.getName());
		udvModel.setValue("Urban schools");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.INSTITUTE_TYPE.getName());
		udvModel.setValue("Ethnic schools");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.INSTITUTE_TYPE.getName());
		udvModel.setValue("Boat/Shikka Tari schools");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.INSTITUTE_TYPE.getName());
		udvModel.setValue("School for marginal community");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.INSTITUTE_TYPE.getName());
		udvModel.setValue("Street children schools");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.INSTITUTE_TYPE.getName());
		udvModel.setValue("School for dropout children/Bridge school");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.INSTITUTE_TYPE.getName());
		udvModel.setValue("ESP/partner NGOs operated schools");
		list.add(udvModel);
		
		
		// donor type
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.DONOR_TYPE.getName());
		udvModel.setValue("SPA");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.DONOR_TYPE.getName());
		udvModel.setValue("Non SPA");
		list.add(udvModel);


		// Heads of account for fee type
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.HEAD_OF_ACCOUNTS.getName());
		udvModel.setValue("Student fee Recovery");
		ResponseModel fresModel = udvService.create(udvModel);
		UdvModel ftcsbs = (UdvModel)fresModel.getModel();
		if(ftcsbs == null){
			ResponseModel findRes = udvService.findByCategoryAndName(CategoryEnum.HEAD_OF_ACCOUNTS.getName(), "Student fee Recovery");
			ftcsbs = (UdvModel)findRes.getModel();
		}
		// fee type
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.FEE_TYPE.getName());
		udvModel.setValue("Admission fee");
		udvModel.setAltValue("10001");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.FEE_TYPE.getName());
		udvModel.setValue("Session fee");
		udvModel.setAltValue("10002");
		udvModel.setParentId(ftcsbs.getId());
		udvModel.setParentCategory(ftcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.FEE_TYPE.getName());
		udvModel.setValue("Monthly fee");
		udvModel.setAltValue("10004");
		udvModel.setParentId(ftcsbs.getId());
		udvModel.setParentCategory(ftcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.FEE_TYPE.getName());
		udvModel.setValue("Exam fee");
		udvModel.setAltValue("10005");
		udvModel.setParentId(ftcsbs.getId());
		udvModel.setParentCategory(ftcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.FEE_TYPE.getName());
		udvModel.setValue("Sports fee");
		udvModel.setAltValue("10006");
		udvModel.setParentId(ftcsbs.getId());
		udvModel.setParentCategory(ftcsbs.getCategory());
		list.add(udvModel);
		
		// fee category
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.FEE_CATEGORY.getName());
		udvModel.setValue("One time");
		udvModel.setAltValue("1");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.FEE_CATEGORY.getName());
		udvModel.setValue("Monthly");
		udvModel.setAltValue("12");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.FEE_CATEGORY.getName());
		udvModel.setValue("Yearly");
		udvModel.setAltValue("1");
		list.add(udvModel);
		
		// student type
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.STUDENT_TYPE.getName());
		udvModel.setValue("CSN");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.STUDENT_TYPE.getName());
		udvModel.setValue("Ethnic");
		list.add(udvModel);
		
		// csn type
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.CSN_TYPE.getName());
		udvModel.setValue("csn 1");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.CSN_TYPE.getName());
		udvModel.setValue("csn 2");
		list.add(udvModel);
		
		// ethnic community type
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.ETHNICITY_COMMUNITY.getName());
		udvModel.setValue("Chakma");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.ETHNICITY_COMMUNITY.getName());
		udvModel.setValue("Marma");
		list.add(udvModel);
		
		
		// student activity type
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.STUDENT_ACTIVITY_TYPE.getName());
		udvModel.setValue("Admitted");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.STUDENT_ACTIVITY_TYPE.getName());
		udvModel.setValue("Drop out");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.STUDENT_ACTIVITY_TYPE.getName());
		udvModel.setValue("Graduated");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.STUDENT_ACTIVITY_TYPE.getName());
		udvModel.setValue("Transferred");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.STAFF_GRADE.getName());
		udvModel.setValue("Grade I");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.STAFF_GRADE.getName());
		udvModel.setValue("Grade II");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.STAFF_GRADE.getName());
		udvModel.setValue("Grade III");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.STAFF_GRADE.getName());
		udvModel.setValue("Grade IV");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.STAFF_GRADE.getName());
		udvModel.setValue("Grade V");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.PROJECT_CODE.getName());
		udvModel.setValue("District");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.PROJECT_CODE.getName());
		udvModel.setValue("Upazila");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.PROJECT_CODE.getName());
		udvModel.setValue("Rural");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.PROJECT_CODE.getName());
		udvModel.setValue("Urban");
		list.add(udvModel);
		
		// Heads of account
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.HEAD_OF_ACCOUNTS.getName());
		udvModel.setValue("Teacher cost & Student books and supplies");
		ResponseModel resModel = udvService.create(udvModel);
		UdvModel tcsbs = (UdvModel)resModel.getModel();
		if(tcsbs == null){
			ResponseModel findRes = udvService.findByCategoryAndName(CategoryEnum.HEAD_OF_ACCOUNTS.getName(), "Teacher cost & Student books and supplies");
			tcsbs = (UdvModel)findRes.getModel();
		}
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Teachers Basic Training");
		udvModel.setAltValue("44701401");
		udvModel.setDescription("Primary school teachers basic training");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Refresher and Orientation Course");
		udvModel.setAltValue("44701402");
		udvModel.setDescription("Expenses for 1 day refreshers and 3 days Orientation cost of Primary teachers");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Teachers Salary");
		udvModel.setAltValue("44701403");
		udvModel.setDescription("Salary of Primary teachers");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Class Room Supplies");
		udvModel.setAltValue("44701471");
		udvModel.setDescription("Expenses of Signboard,black board,chair,table,calender,wallmatt,teacher's bag,trunk,jug,glass,tube light,fan,register khata,duster,attendance register,charts,chalk,lock,monthly collection receipts book and class room supplies");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Teachers Aids,Manuals & Supplies");
		udvModel.setAltValue("44701472");
		udvModel.setDescription("Expenses for teachers guide book,manual card, chart, teachers bag,exercise book,ball pen,clip file and folding file");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Students Books & Supplies");
		udvModel.setAltValue("44701473");
		udvModel.setDescription("Expenses for students books of Primary students,Cost of slate,slate pencil,color pencil,pencil sharpner,others story books,drawing sheet,activity card,report card & others supplies");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("School House Rent");
		udvModel.setAltValue("44701474");
		udvModel.setDescription("School house rent & maintenance expenses of Promary school");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("English Training");
		udvModel.setAltValue("44701475");
		udvModel.setDescription("Cost of English Training");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Resource Teachers");
		udvModel.setAltValue("44701476");
		udvModel.setDescription("Cost of Resource Teachers");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		// Heads of account
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.HEAD_OF_ACCOUNTS.getName());
		udvModel.setValue("Program Implementation & Management");
		resModel = udvService.create(udvModel);
		tcsbs = (UdvModel)resModel.getModel();
		if(tcsbs == null){
			ResponseModel findRes = udvService.findByCategoryAndName(CategoryEnum.HEAD_OF_ACCOUNTS.getName(), "Program Implementation & Management");
			tcsbs = (UdvModel)findRes.getModel();
		}
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Salary of PO");
		udvModel.setAltValue("44701201");
		udvModel.setDescription("Salary & other benefits of PO");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Salary of Branch Manager");
		udvModel.setAltValue("44701202");
		udvModel.setDescription("Salary & other benefits of Branch Manager");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("RSS(QA) Salary");
		udvModel.setAltValue("44701203");
		udvModel.setDescription("Salary & other benefits of RSS(QA)");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Salary of Regional Manager");
		udvModel.setAltValue("44701204");
		udvModel.setDescription("Salary & other benefits of Regional Manager");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Office Assistant");
		udvModel.setAltValue("44701205");
		udvModel.setDescription("Salary & other benefits of Office Assistant");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Officer Accounts");
		udvModel.setAltValue("44701206");
		udvModel.setDescription("Salary & other benefits of Officer Accounts");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Travelling & Transport Expenses");
		udvModel.setAltValue("44701207");
		udvModel.setDescription("Travelling expenses and motor cycle allowance of PO/BM/RSS(QA)/OA/BAP/RM & above staff");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Staff Training & Development");
		udvModel.setAltValue("44701208");
		udvModel.setDescription("Training expenses for PO,BM,RM & above staff");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Salary of Project Manager");
		udvModel.setAltValue("44701209");
		udvModel.setDescription("Salary & other benefits of Project Manager");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
	    udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Regional Office Cost");
		udvModel.setAltValue("44701301");
		udvModel.setDescription("Cost of office rent,utilities,maintenance & stationaries of regional office");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Branch Office Cost");
		udvModel.setAltValue("44701302");
		udvModel.setDescription("Cost of office rent,utilities,maintenance & stationaries of branch office");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Electricity Bill");
		udvModel.setAltValue("44701303");
		udvModel.setDescription("Cost of Electricity Bill");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("HO Management & Logistics Expenses");
		udvModel.setAltValue("44701424");
		udvModel.setDescription("7% of above expenses charged from HO for management staff and logistics support");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		
		// Heads of account
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.HEAD_OF_ACCOUNTS.getName());
		udvModel.setValue("Capital Expenditure");
		resModel = udvService.create(udvModel);
		tcsbs = (UdvModel)resModel.getModel();
		if(tcsbs == null){
			ResponseModel findRes = udvService.findByCategoryAndName(CategoryEnum.HEAD_OF_ACCOUNTS.getName(), "Capital Expenditure");
			tcsbs = (UdvModel)findRes.getModel();
		}
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Furniture & Fixture");
		udvModel.setAltValue("44717003");
		udvModel.setDescription("Cost of Furniture & Fixture");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Computer");
		udvModel.setAltValue("44717006");
		udvModel.setDescription("Cost of Computer");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Equipment");
		udvModel.setAltValue("44717007");
		udvModel.setDescription("Cost of Equipment");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		// Heads of account
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.HEAD_OF_ACCOUNTS.getName());
		udvModel.setValue("Others");
		resModel = udvService.create(udvModel);
		tcsbs = (UdvModel)resModel.getModel();
		if(tcsbs == null){
			ResponseModel findRes = udvService.findByCategoryAndName(CategoryEnum.HEAD_OF_ACCOUNTS.getName(), "Others");
			tcsbs = (UdvModel)findRes.getModel();
		}
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Donation");
		udvModel.setAltValue("44718581");
		udvModel.setDescription("Received from Donor Agency");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Advance to Staff Against Expenses");
		udvModel.setAltValue("44718023");
		udvModel.setDescription("Paid to staff against official expenses");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Advance to Staff Against Salary");
		udvModel.setAltValue("44718024");
		udvModel.setDescription("Paid to staff interest bearing loan against salary");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Advance to 3rd Party");
		udvModel.setAltValue("44718025");
		udvModel.setDescription("Advanced paid to 3rd party against material & services");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Depreciation");
		udvModel.setAltValue("44718212");
		udvModel.setDescription("Provision for depreciation on fixed Assets");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Accumulated Depreciation");
		udvModel.setAltValue("44718113");
		udvModel.setDescription("Cumulative Depreciation");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Outstanding Liabilities");
		udvModel.setAltValue("44718083");
		udvModel.setDescription("Accrued Expenses");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Outstanding Receivable");
		udvModel.setAltValue("44718084");
		udvModel.setDescription("Accrued Income");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Bank Interest Income/Expenses");
		udvModel.setAltValue("44718016");
		udvModel.setDescription("Bank Interest Income/Expenses");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Motor Cycle Loan Realised");
		udvModel.setAltValue("44718015");
		udvModel.setDescription("Motor Cycle Loan Realised from Staff");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Motorcycle");
		udvModel.setAltValue("44718001");
		udvModel.setDescription("Cost of Motorcycle");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Bi Cycle");
		udvModel.setAltValue("44718002");
		udvModel.setDescription("Cost of Bi Cycle");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Current Account with Field");
		udvModel.setAltValue("44718200");
		udvModel.setDescription("Current Account maintained with Field office at HO");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Current Account with HO");
		udvModel.setAltValue("44718120");
		udvModel.setDescription("Current Account maintained with HO at Area Office");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Capital Fund");
		udvModel.setAltValue("44718101");
		udvModel.setDescription("Accumulated surplus of income over general account");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("BRAC Contribution");
		udvModel.setAltValue("44718098");
		udvModel.setDescription("Contribution from BRAC");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.EXPENDITURE_TYPE.getName());
		udvModel.setValue("Fund Control");
		udvModel.setAltValue("44718048");
		udvModel.setDescription("Control fund at Head Office");
		udvModel.setParentId(tcsbs.getId());
		udvModel.setParentCategory(tcsbs.getCategory());
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.INCOME_INDICATOR.getName());
		udvModel.setValue("Admission fee");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.INCOME_INDICATOR.getName());
		udvModel.setValue("Session fee");
		list.add(udvModel);
		
		udvModel = new UdvModel();
		udvModel.setCategory(CategoryEnum.INCOME_INDICATOR.getName());
		udvModel.setValue("Exam fee");
		list.add(udvModel);
		
		return list;
	}
	
	private List<RightModel> getRightModelList(){
		List<RightModel> list = new ArrayList<RightModel>();
		RightsEnum[] rights = RightsEnum.values();
		for (RightsEnum e : rights) {
			RightModel rightModel = new RightModel();
			rightModel.setName(e.getCode());
			rightModel.setDescription(e.getName());
			list.add(rightModel);
		}		
		return list;
	}
}
