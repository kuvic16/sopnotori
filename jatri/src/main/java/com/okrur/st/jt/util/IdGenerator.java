package com.okrur.st.jt.util;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.okrur.st.jt.data.domain.PersistableEntity;

public class IdGenerator implements IdentifierGenerator { 
	
	  /** 
	   * @see org.hibernate.id.IdentifierGenerator#generate(org.hibernate.engine.SessionImplementor, java.lang.Object) 
	   */ 
	  public Serializable generate(SessionImplementor session, Object obj) throws HibernateException { 
		  String component = "udef";
		  if (obj instanceof PersistableEntity) {
			  try {
				  component = ((PersistableEntity) obj).getObjCode();
				  if(StringUtils.isNotBlank(component) && component.length() > 4){
					  component = component.substring(0, 4);
				  }
			  } catch (Exception e) 
			  {
				  component="uspt";
			  }
		  }
		  return doStuff(component);
	 }
	  
	 public static String generate(String code) {
		 if (code == null || code.length() != 4) {
			 code = "cust";
		 }
		 return doStuff(code);
	 }
	 
	 public static String generateHouseholdId() {
		 String code = "hhid";
		 return doStuff(code);
	 }
	 
	 public static String generateBeneficiaryId() {
		 String code = "bfid";
		 return doStuff(code);
	 }
	 
	 
	 private static String doStuff(String code) {
		 UUID uuid = UUID.randomUUID();
		 String time = new Date().getTime() + "";
		 return time.substring(1, 9) + "-" + time.substring(9,13) + "-" + code + uuid.toString().substring(18);   
	 }
	 
	 /**
	  * Generate a two-part ID where the first part is a date in millis and the second part is a unique id
	  * @return
	  */
	 public static String generateTimeID() {
		 String pt1 = Long.toString(APPDate.now().getTimeInMillis());
		 UUID uuid = UUID.randomUUID();
		 String pt2 = uuid.toString().substring(18);
		 return pt1 + pt2;
	 }	
} 