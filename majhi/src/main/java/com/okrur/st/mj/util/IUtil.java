package com.okrur.st.mj.util;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.okrur.st.mj.rest.model.ResponseModel;

/**
 * @File IUtil.java: utility for emis 
 * @author shaiful islam palash | kuvic16@gmail.com
 * @CreationDate Nov 24, 2015
 */
public class IUtil {
	public static BigDecimal toBigDecimal(String number){
		try{
			return BigDecimal.valueOf(Double.parseDouble(number));
		}catch(Throwable t){}
		return null;
	}
	
	public static String toString(Object obj){
		try{
			if(obj != null){
				return obj.toString(); 
			}
		}catch(Throwable t){}
		return "";
	}
	
	public static double toDouble(String number){
		try{
			if(StringUtils.isNotBlank(number)){
				return Double.parseDouble(number);
			}
		}catch(Throwable t){}
		return 0;
	}

	public static boolean isNumeric(String str) {
		try {
			Double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static float toFloat(String number){
		try{
			if(StringUtils.isNotBlank(number)){
				return Float.parseFloat(number);
			}
		}catch(Throwable t){}
		return 0;
	}
	
	public static int toInteger(String number){
		try{
			if(StringUtils.isNotBlank(number)){
				return Integer.parseInt(number);
			}
		}catch(Throwable t){}
		return 0;
	}
	
	public static boolean toBoolean(String bool){
		try{
			if(StringUtils.isNotBlank(bool)){
				return Boolean.parseBoolean(bool);
			}
		}catch(Throwable t){}
		return false;
	}
	
	public static String toYesNo(int i){
		if(i==1) return "Yes";
		else if(i==0) return "No";
		else return "";
	}
	
	public static String toBooleanString(boolean bool){
		try{
			return Boolean.toString(bool);
		}catch(Throwable t){}
		return "false";
	}
	
	public static boolean isNotBlank(List<?> list){
        if(list != null && list.size()>0){
            return true;
        }
        return false;
    }
	
	public static boolean isResponseModelNotBlank(ResponseModel responseModel){
        if(responseModel != null && responseModel.getSuccess().equalsIgnoreCase(RestUtil.SUCCESS) && responseModel.getModel() != null){
            return true;
        }
        return false;
    }
	
	public static String getFirstName(String fullName){
		try{
			String[] names = fullName.split(" ");
			if(names.length > 0){
				return names[0];
			}
		}catch (Exception e) {}
		return "";
	}
	
	public static String getMiddleName(String fullName){
		try{
			String[] names = fullName.split(" ");
			if(names.length > 2){
				return names[1];
			}
		}catch (Exception e) {}
		return "";
	}
	
	public static String getLastName(String fullName){
		try{
			String[] names = fullName.split(" ");
			if(names.length == 2){
				return names[1];
			}else if(names.length > 2){
				return names[2];
			}
		}catch (Exception e) {}
		return "";
	}
	
	public static String getUserName(String fullName){
		try{
			String[] names = fullName.split(" ");
			if(names.length == 1){
				return names[0].toLowerCase();
			}else if(names.length >= 2){
				return (names[0] + "_" + names[1]).toLowerCase();
			}
		}catch (Exception e) {}
		return "";
	}
	
	public static String getMonth(int i) {
        String[] monthList = new String[12];
        monthList[0] = "January";
        monthList[1] = "February";
        monthList[2] = "March";
        monthList[3] = "April";
        monthList[4] = "May";
        monthList[5] = "June";
        monthList[6] = "July";
        monthList[7] = "August";
        monthList[8] = "September";
        monthList[9] = "October";
        monthList[10] = "November";
        monthList[11] = "December";    
        if(i>11) return "";
        return monthList[i];
    }
	
	public static void main(String[] args){
//		System.out.println(getUserName("Md Chowdhury asd"));
//		System.out.println(getMiddleName("Hosne begum"));
//		System.out.println(getLastName("Hosne begum"));
	}
}
