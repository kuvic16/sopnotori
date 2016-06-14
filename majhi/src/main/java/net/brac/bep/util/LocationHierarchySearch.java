/**
 * 
 */
package net.brac.bep.util;

import org.apache.commons.lang3.StringUtils;

import net.brac.bep.rest.model.FeeCollectionSearchResultModel;

/**
 * @File LocationHierarchySearch.java
 * @author shaiful islam palash | kuvic16@gmail.com
 * @CreationDate Mar 31, 2016
 */
public class LocationHierarchySearch {
	private boolean init;
	private String hierarchyId;
	private String locationType;
	private String instituteId;
	private String gradeId; 
	private String studentId;
	private String studentFeeId;
	private int year;
	private int month;
	private String loggedUserHierarchyId;
	private int startPos = 0;
	private int endPos = 0;
	private String poId;
	
	private String searchTypeId;
	private String searchType;
	private String nextSearchType;
	private String searchQuery;
	private String transactionSearchQuery;
	
	private boolean serachTransactionRequired;
	private FeeCollectionSearchResultModel feeCollectionSearchResultModel;
	
	public static String SEARCH_TYPE_REGION = "SEARCH_TYPE_REGION";
	public static String SEARCH_TYPE_AREA = "SEARCH_TYPE_AREA";
	public static String SEARCH_TYPE_BRANCH = "SEARCH_TYPE_BRANCH";
	public static String SEARCH_TYPE_INSTITUTE = "SEARCH_TYPE_INSTITUTE";
	public static String SEARCH_TYPE_GRADE = "SEARCH_TYPE_GRADE";
	public static String SEARCH_TYPE_STUDENT = "SEARCH_TYPE_STUDENT";
	public static String SEARCH_TYPE_STUDENT_FEE = "SEARCH_TYPE_STUDENT_FEE";
	public static String SEARCH_TYPE_STUDENT_FEE_HISTORY = "SEARCH_TYPE_STUDENT_FEE_HISTORY";
	
	public static String LABEL_REGION = "Region";
	public static String LABEL_AREA = "Area";
	public static String LABEL_BRANCH = "Branch";
	public static String LABEL_INSTITUTE = "Institute";
	public static String LABEL_GRADE = "Grade";
	public static String LABEL_STUDENT = "Student";
	public static String LABEL_STUDENT_FEE = "Student Fee";
	public static String LABEL_STUDENT_FEE_HISTORY = "Student Fee History";
	public static String LABEL_TOTAL_INSTITUTE = "Total Institute";
	public static String LABEL_TOTAL_STUDENT = "Total Student";
	public static String LABEL_TOTAL_FEE_AMOUNT = "Fee Amount";
	public static String LABEL_FEE_COLLECTED = "Fee Collected";
	public static String LABEL_TOTAL_FEE_DUE = "Due";
	public static String LABEL_TOTAL_GRADE = "Total Grade";
	public static String LABEL_TOTAL_FEE_TYPE = "Total Fee Type";
	public static String LABEL_STUDENT_ROLL = "Roll";
	public static String LABEL_MONTH = "Month";
	public static String LABEL_MANDATORY = "Mandatory";
	public static String LABEL_TOTAL_FEE_COLLECTED = "Total Fee Collected";
	public static String LABEL_COLLECTION_DATE = "Collection Date";
	
	
	
	public LocationHierarchySearch(){
	}
	
	public LocationHierarchySearch(boolean init, String hierarchyId, String instituteId, String gradeId, String studentId, Integer year, Integer month, 
									String loggedUserHierarchy, String searchTypeId, String searchType, String poId) {
		this.setInit(init);
		this.setHierarchyId(hierarchyId);
		this.setInstituteId(instituteId);
		this.setGradeId(gradeId);
		this.setStudentId(studentId);
		this.setLoggedUserHierarchyId(loggedUserHierarchy);
		this.setSearchTypeId(searchTypeId);
		this.setSearchType(searchType);
		this.setLocationType(getLocationType(loggedUserHierarchy));
		//this.setStartPos(getStartPosition(locationType));
		//this.setEndPos(getEndPosition(locationType));
		if(year == null){
			this.setYear(DateUtil.getCurrentYear()); 
		}else{
			this.setYear(year);
		}
		if(month == null) {
			this.setMonth(DateUtil.getCurrentMonth() - 1);
		}else{
			this.setMonth(month);
		}
		this.setPoId(poId);
		
	}
	
	
	public String getSearchQuery(){
		String searchType = getSearchType();
		if(searchType.equalsIgnoreCase(SEARCH_TYPE_REGION) || searchType.equalsIgnoreCase(SEARCH_TYPE_AREA) || searchType.equalsIgnoreCase(SEARCH_TYPE_BRANCH)){
			searchQuery = "call search_fee_collection_by_location_hierarchy(" + getStartPosition(searchType) 
																			  + "," + getEndPosition(searchType) 
																			  + "," + getYear() 
																			  + "," + getMonth() 
																			  + ",'" + getSearchTypeId() + "'"
																			  + ")";
		}else if(searchType.equalsIgnoreCase(SEARCH_TYPE_INSTITUTE)){
			searchQuery = "call search_fee_collection_by_branch(" 
							+ getYear() 
							+ "," + getMonth() 
							+ ",'" + getSearchTypeId() + "'"
							+ ",'" + getPoId() + "'"
							+ ")";
		}else if(searchType.equalsIgnoreCase(SEARCH_TYPE_GRADE)){
			searchQuery = "call search_fee_collection_by_institute(" 
					+ getYear() 
					+ "," + getMonth() 
					+ ",'" + getSearchTypeId() + "'"
					+ ")";
		}else if(searchType.equalsIgnoreCase(SEARCH_TYPE_STUDENT)){
			searchQuery = "call search_fee_collection_by_grade(" 
					+ getYear() 
					+ "," + getMonth() 
					+ ",'" + getInstituteId() + "'"
					+ ",'" + getSearchTypeId() + "'"
					+ ")";
		}else if(searchType.equalsIgnoreCase(SEARCH_TYPE_STUDENT_FEE)){
			searchQuery = "call search_fee_collection_by_student(" 
					+ getYear() 
					+ "," + getMonth() 
					+ ",'" + getInstituteId() + "'"
					+ ",'" + getGradeId() + "'"
					+ ",'" + getSearchTypeId() + "'"
					+ ")";
		}else if(searchType.equalsIgnoreCase(SEARCH_TYPE_STUDENT_FEE_HISTORY)){
			searchQuery = "call search_fee_collection_by_student_fee(" 
					+ getYear() 
					+ "," + getMonth() 
					+ ",'" + getInstituteId() + "'"
					+ ",'" + getGradeId() + "'"
					+ ",'" + getStudentId() + "'"
					+ ",'" + getSearchTypeId() + "'"
					+ ")";
		}
		
		return searchQuery;
	}
	
	public String getTransactionSearchQuery(String searchResultId, String instituteId, String gradeId, String studentId) {
		String searchType = getSearchType();
		transactionSearchQuery = "call search_transaction_history_by_location_hierarchy(" + getYear() 
																			  + "," + getMonth() 
																			  + ",'" + searchType + "'"
																			  + ",'" + searchResultId + "'"
																			  + ",'" + instituteId + "'"
																			  + ",'" + gradeId + "'"
																			  + ",'" + studentId + "'"
																			  + ")";
		return transactionSearchQuery;
	}

	
	
	public String getSearchType() {
		if(isInit()){
			if(locationType == ""){
				searchType = SEARCH_TYPE_REGION;
			}else if(locationType.equalsIgnoreCase(IConstant.REGION)){
				searchType = SEARCH_TYPE_AREA;
			}else if(locationType.equalsIgnoreCase(IConstant.AREA)){
				searchType = SEARCH_TYPE_BRANCH;
			}else if(locationType.equalsIgnoreCase(IConstant.BRANCH)){
				searchType = SEARCH_TYPE_INSTITUTE;
			}
		}
		return searchType;
	}
	
	public String getNextSearchType() {
		if(searchType.equalsIgnoreCase(SEARCH_TYPE_REGION)){
			nextSearchType = SEARCH_TYPE_AREA;
		}else if(searchType.equalsIgnoreCase(SEARCH_TYPE_AREA)){
			nextSearchType = SEARCH_TYPE_BRANCH;
		}else if(searchType.equalsIgnoreCase(SEARCH_TYPE_BRANCH)){
			nextSearchType = SEARCH_TYPE_INSTITUTE;
		}else if(searchType.equalsIgnoreCase(SEARCH_TYPE_INSTITUTE)){
			nextSearchType = SEARCH_TYPE_GRADE;
		}else if(searchType.equalsIgnoreCase(SEARCH_TYPE_GRADE)){
			nextSearchType = SEARCH_TYPE_STUDENT;
		}else if(searchType.equalsIgnoreCase(SEARCH_TYPE_STUDENT)){
			nextSearchType = SEARCH_TYPE_STUDENT_FEE;
		}else if(searchType.equalsIgnoreCase(SEARCH_TYPE_STUDENT_FEE)){
			nextSearchType = SEARCH_TYPE_STUDENT_FEE_HISTORY;
		}
		return nextSearchType;
	}
	
	
	public static int getStartPosition(String searchType){
		if(searchType.equalsIgnoreCase(SEARCH_TYPE_REGION)) return 2;
		if(searchType.equalsIgnoreCase(SEARCH_TYPE_AREA)) return 39;
		if(searchType.equalsIgnoreCase(SEARCH_TYPE_BRANCH)) return 76;
		return -1;
	}
	
	public static int getEndPosition(String searchType){
		if(searchType.equalsIgnoreCase(SEARCH_TYPE_REGION)) return 36;
		if(searchType.equalsIgnoreCase(SEARCH_TYPE_AREA)) return 36;
		if(searchType.equalsIgnoreCase(SEARCH_TYPE_BRANCH)) return 36;
		return -1;
	}
	
	public static String getLocationType(String hierarchy){
		if(StringUtils.isNotBlank(hierarchy)){
			String[] hierarchys = hierarchy.split(">");
			if(hierarchys.length == 4){
				return IConstant.BRANCH;
			}else if(hierarchys.length == 3){
				return IConstant.AREA;
			}else if(hierarchys.length == 2){
				return IConstant.REGION;
			}
		}
		return "";
	}

	public boolean isInit() {
		return init;
	}

	public void setInit(boolean init) {
		this.init = init;
	}

	public String getHierarchyId() {
		return hierarchyId;
	}

	public void setHierarchyId(String hierarchyId) {
		this.hierarchyId = hierarchyId;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(String instituteId) {
		this.instituteId = instituteId;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentFeeId() {
		return studentFeeId;
	}

	public void setStudentFeeId(String studentFeeId) {
		this.studentFeeId = studentFeeId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getLoggedUserHierarchyId() {
		return loggedUserHierarchyId;
	}

	public void setLoggedUserHierarchyId(String loggedUserHierarchyId) {
		this.loggedUserHierarchyId = loggedUserHierarchyId;
	}

	public int getStartPos() {
		return startPos;
	}

	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}

	public int getEndPos() {
		return endPos;
	}

	public void setEndPos(int endPos) {
		this.endPos = endPos;
	}

	public String getSearchTypeId() {
		if(searchTypeId == null) return "";
		return searchTypeId;
	}

	public void setSearchTypeId(String searchTypeId) {
		this.searchTypeId = searchTypeId;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public void setNextSearchType(String nextSearchType) {
		this.nextSearchType = nextSearchType;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}
	
	public void setTransactionSearchQuery(String transactionSearchQuery) {
		this.transactionSearchQuery = transactionSearchQuery;
	}
	
	public boolean isSerachTransactionRequired() {
		serachTransactionRequired = true;
		if(searchType.equalsIgnoreCase(SEARCH_TYPE_STUDENT_FEE_HISTORY)){
			serachTransactionRequired = false;
		}
		return serachTransactionRequired;
	}

	public void setSerachTransactionRequired(boolean serachTransactionRequired) {
		this.serachTransactionRequired = serachTransactionRequired;
	}
	
	public String getPoId() {
		return poId;
	}

	public void setPoId(String poId) {
		this.poId = poId;
	}

	public FeeCollectionSearchResultModel getFeeCollectionSearchResultModel() {
		feeCollectionSearchResultModel = new FeeCollectionSearchResultModel();
		feeCollectionSearchResultModel.setMonth(IUtil.getMonth(getMonth()));
		feeCollectionSearchResultModel.setYear(String.valueOf(getYear()));
		
		if(searchType.equalsIgnoreCase(SEARCH_TYPE_REGION)){
			feeCollectionSearchResultModel.setField1Label(LABEL_REGION);
			feeCollectionSearchResultModel.setField2Label(LABEL_TOTAL_INSTITUTE);
			feeCollectionSearchResultModel.setField3Label(LABEL_TOTAL_STUDENT);
			feeCollectionSearchResultModel.setField4Label(LABEL_TOTAL_FEE_AMOUNT);
			feeCollectionSearchResultModel.setField5Label(LABEL_FEE_COLLECTED);
			feeCollectionSearchResultModel.setField6Label(LABEL_TOTAL_FEE_DUE);
		}else if(searchType.equalsIgnoreCase(SEARCH_TYPE_AREA)){
			feeCollectionSearchResultModel.setField1Label(LABEL_AREA);
			feeCollectionSearchResultModel.setField2Label(LABEL_TOTAL_INSTITUTE);
			feeCollectionSearchResultModel.setField3Label(LABEL_TOTAL_STUDENT);
			feeCollectionSearchResultModel.setField4Label(LABEL_TOTAL_FEE_AMOUNT);
			feeCollectionSearchResultModel.setField5Label(LABEL_FEE_COLLECTED);
			feeCollectionSearchResultModel.setField6Label(LABEL_TOTAL_FEE_DUE);
		}else if(searchType.equalsIgnoreCase(SEARCH_TYPE_BRANCH)){
			feeCollectionSearchResultModel.setField1Label(LABEL_BRANCH);
			feeCollectionSearchResultModel.setField2Label(LABEL_TOTAL_INSTITUTE);
			feeCollectionSearchResultModel.setField3Label(LABEL_TOTAL_STUDENT);
			feeCollectionSearchResultModel.setField4Label(LABEL_TOTAL_FEE_AMOUNT);
			feeCollectionSearchResultModel.setField5Label(LABEL_FEE_COLLECTED);
			feeCollectionSearchResultModel.setField6Label(LABEL_TOTAL_FEE_DUE);
		}else if(searchType.equalsIgnoreCase(SEARCH_TYPE_INSTITUTE)){
			feeCollectionSearchResultModel.setField1Label(LABEL_INSTITUTE);
			feeCollectionSearchResultModel.setField2Label(LABEL_TOTAL_STUDENT);
			feeCollectionSearchResultModel.setField3Label(LABEL_TOTAL_GRADE);
			feeCollectionSearchResultModel.setField4Label(LABEL_TOTAL_FEE_AMOUNT);
			feeCollectionSearchResultModel.setField5Label(LABEL_FEE_COLLECTED);
			feeCollectionSearchResultModel.setField6Label(LABEL_TOTAL_FEE_DUE);
		}else if(searchType.equalsIgnoreCase(SEARCH_TYPE_GRADE)){
			feeCollectionSearchResultModel.setField1Label(LABEL_GRADE);
			feeCollectionSearchResultModel.setField2Label(LABEL_TOTAL_STUDENT);
			feeCollectionSearchResultModel.setField3Label(LABEL_TOTAL_FEE_TYPE);
			feeCollectionSearchResultModel.setField4Label(LABEL_TOTAL_FEE_AMOUNT);
			feeCollectionSearchResultModel.setField5Label(LABEL_FEE_COLLECTED);
			feeCollectionSearchResultModel.setField6Label(LABEL_TOTAL_FEE_DUE);
		}else if(searchType.equalsIgnoreCase(SEARCH_TYPE_STUDENT)){
			feeCollectionSearchResultModel.setField1Label(LABEL_STUDENT);
			feeCollectionSearchResultModel.setField2Label(LABEL_STUDENT_ROLL);
			feeCollectionSearchResultModel.setField3Label(LABEL_TOTAL_FEE_TYPE);
			feeCollectionSearchResultModel.setField4Label(LABEL_TOTAL_FEE_AMOUNT);
			feeCollectionSearchResultModel.setField5Label(LABEL_FEE_COLLECTED);
			feeCollectionSearchResultModel.setField6Label(LABEL_TOTAL_FEE_DUE);
		}else if(searchType.equalsIgnoreCase(SEARCH_TYPE_STUDENT_FEE)){
			feeCollectionSearchResultModel.setField1Label(LABEL_STUDENT_FEE);
			feeCollectionSearchResultModel.setField2Label(LABEL_MONTH);
			feeCollectionSearchResultModel.setField3Label(LABEL_MANDATORY);
			feeCollectionSearchResultModel.setField4Label(LABEL_TOTAL_FEE_AMOUNT);
			feeCollectionSearchResultModel.setField5Label(LABEL_FEE_COLLECTED);
			feeCollectionSearchResultModel.setField6Label(LABEL_TOTAL_FEE_DUE);
		}else if(searchType.equalsIgnoreCase(SEARCH_TYPE_STUDENT_FEE_HISTORY)){
			feeCollectionSearchResultModel.setField1Label(LABEL_COLLECTION_DATE);
			feeCollectionSearchResultModel.setField2Label(LABEL_MONTH);
			feeCollectionSearchResultModel.setField3Label(LABEL_TOTAL_FEE_AMOUNT);
			feeCollectionSearchResultModel.setField4Label(LABEL_TOTAL_FEE_COLLECTED);
			feeCollectionSearchResultModel.setField5Label(LABEL_FEE_COLLECTED);
			feeCollectionSearchResultModel.setField6Label(LABEL_TOTAL_FEE_DUE);
		}
		return feeCollectionSearchResultModel;
	}

	public void setFeeCollectionSearchResultModel(FeeCollectionSearchResultModel feeCollectionSearchResultModel) {
		this.feeCollectionSearchResultModel = feeCollectionSearchResultModel;
	}

	public static void main(String[] args){
//		String a = ">45482245-2530-loct-ad22-e84a3673d740>45482245-4962-loct-9b71-0284782976ed>45482245-5133-loct-ad43-cff89cf22523>";
//		System.out.println(a.substring(1, 37)); // region
//		System.out.println(a.substring(38, 74)); // area
//		System.out.println(a.substring(75, 111)); // branch
		
		System.out.println(getLocationType(">45482245-2530-loct-ad22-e84a3673d740>"));
		
		LocationHierarchySearch loc = new LocationHierarchySearch(true, "", "", "", "", 0, 0, ">45482245-2530-loct-ad22-e84a3673d740>", "", "","");
		System.out.println(loc.getSearchQuery());
		System.out.println(loc.getNextSearchType());
	}
	
	
}
