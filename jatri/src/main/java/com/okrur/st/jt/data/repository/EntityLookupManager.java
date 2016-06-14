package com.okrur.st.jt.data.repository;

import com.okrur.st.jt.listener.CoreServletListener;

public class EntityLookupManager {
    private EntityLookupManager(){}

    @SuppressWarnings("rawtypes")
	public static boolean isLookupNeeded(Class clz){
        return CoreServletListener.getServletContext().getAttribute(clz.getSimpleName()) == null? true:
                (Boolean)CoreServletListener.getServletContext().getAttribute(clz.getSimpleName());
    }

    @SuppressWarnings("rawtypes")
	public static void setLookupNeeded(Class clz,boolean isLookupNeeded){
          CoreServletListener.getServletContext().setAttribute(clz.getSimpleName(), isLookupNeeded);
    }
}
