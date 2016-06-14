package net.brac.bep.util;

/**
 * @author Mithun
 * @author Nadir
 */
public class IConstant {

	public static String DEFAULT_CREATOR_UPADTER = "emis";
	public static String RECORD_ID = "recordId";
	public static String RECORD_NAME = "recordName";
	public static String INFO = "INFO";
	public static String WARN = "WARN";
	public static String ERROR = "ERROR";
	public static String FATAL = "FATAL";
	public static String SUCCESS = "SUCCESS";
	public static String ENGINESELECTALL = "ENGINESELECTALL";
	public static String SESSION_USER = "SESSION_USER";
	public static String WHOS_ONLINE_USER = "WHOS_ONLINE_USER";
	public static String SESSION_FORM = "SESSION_FORM";
	public static String SESSION_USER_ID = "SESSION_USER_ID";
	public static String SESSION_IS_LOGIN_SSO = "SESSION_IS_LOGIN_SSO";
	public static String TO_HOME = "TO_HOME";
	public static String BACK_TO_LOGIN = "BACK_TO_LOGIN";
	public static String SOLVER = "SOLVER";
	public static String BARANGAY = "BARANGAY";
	public static String MUNICIPALITY = "MUNICIPALITY";
	public static String PROVINCE = "PROVINCE";
	public static String SCHOOLTYPE = "SCHOOLTYPE";
	public static String SESSION = "SESSION";
	public static String COUNTRY = "COUNTRY";
	public static String EMAIL_PATTERN = "[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]";
	public static String DATE_FORMAT = "yyyy-MM-dd";

	public static String SMS_FORMAT_SINGLE = "S";
	public static String SMS_FORMAT_MULTIPLE = "M";
	public static String ADD_OPERATION = "ADD";
	public static String UPDATE_OPERATION = "UPDATE";
	public static String DELETE_OPERATION = "DELETE";
	public static String MIGRATE_OPERATION = "MIGRATE";
	public static String START_ENGINE_OPERATION = "START ENGINE";
	public static String STOP_ENGINE_OPERATION = "STOP ENGINE";
	public static String RUN_SCHEDULE_JOB_OPERATION = "RUN_SCHEDULE_JOB";
	public static String PLACE = "PLACE";
	public static String DIVISION = "Division";
	public static String DISTRICT = "District";
	public static String REGION = "Region";
	public static String UPAZILA = "Upazila";
	public static String UNION = "Union";
	public static String VILLAGE = "Village";
	public static String AREA = "Area";
	public static String BRANCH = "Branch";
	
	public static String FORM_DATA_CONFIRMATION = "FORM_DATA_CONFIRMATION";

	public static String HIERARCHY_SEPARATOR = ">";
	public static String PLACE_GEOCODE_WPID_SEPARATOR = "-";

	public static final String CURRENT_USER_ID = "curUserID";
	public static final String CURRENT_USER_NAME = "curUserName";
	
	public static final String HO = "HO";
	public static final String RM = "RM";
	public static final String BM = "BM";
	public static final String PO = "PO";
	public static final String SK = "SK";
	public static final String SS = "SS";
	public static final String USER_CATEGORY = "USER_CATEGORY";
	public static final String VISIT_CATEGORY = "VISIT_CATEGORY";
	public static final String VISIT_SUBCATEGORY = "VISIT_SUBCATEGORY";
	public static final String PLACE_HIERARCHY_SEPARATOR = ">";
	public static final String VISIT = "VISIT";
	public static final String ELIGIBLE_VISIT = "ELIGIBLE_VISIT";
	public static final String ALL_VISIT = "ALL_VISIT";
    
	public static final String WS_USERNAME = "ws_user";
    public static final String WS_PASSWORD = "ws_password";
    public static final String WS_USERNAME_VALUE = "ict-4d";
    public static final String WS_PASSWORD_VALUE = "Br@c-t4D";
    
    public static final String REQUESTER = "requester";
    public static final String DEATH_CATEGORY = "DEATH_CATEGORY";
    public static final String DEATH_REASON = "DEATH_REASON";
    public static final String DEATH_REASON_MOTHER = "DEATH_REASON_MOTHER";
    public static final String DEATH_PLACE = "DEATH_PLACE";
    
	//public static final String BRACHUB_BASEURI = "http://172.25.100.98:8080/brachub/bips";
	public static final String BRACHUB_BASEURI = "http://localhost/brachub/bips";
	
	public static final int PAGE_SIZE = 20;
	public static final int HIERARCHY_LENGTH = 1000;
	
	public static final String ADMIN_USER = "admin";
	public static final String ADMIN_PASS = "1";
	public static final String ADMIN_ROLE = "ADMIN";
	
	public static final String AUTH_USERNAME = "AUTH_USERNAME";
	public static final String AUTH_USERID = "AUTH_USERID";
	
	public static final String ATTACHMENT_TEMP = "TEMP";
	public static final String ATTACHMENT_EXPF = "EXPF";
	
	public static enum SMS_SEPARATOR {

		SPACE("SPACE", " "), COMMA("COMMA", ",");

		public final String name;
		public final String value;

		SMS_SEPARATOR(String name, String value) {
			this.name = name;
			this.value = value;
		}

		@Override
		public String toString() {
			return this.name;
		}
	}
}
