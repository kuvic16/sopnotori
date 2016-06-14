package com.okrur.st.jt.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Web application lifecycle listener.
 */
@WebListener()
public class CoreServletListener implements ServletContextListener,
        ServletContextAttributeListener, HttpSessionListener {
	
	private static Logger logger = (Logger) LoggerFactory.getLogger(CoreServletListener.class);

    public static ServletContext servletContext;

    public static ServletContext getServletContext() {
        return servletContext;
    }




    @Override
    public void contextInitialized(ServletContextEvent sce) {
        servletContext = sce.getServletContext();
//        System.out.println("relative path :  contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    	if (logger.isDebugEnabled()) {
    		logger.info("relative path :  contextDestroyed");
    	}
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        //System.out.println("relative path : attributeAdded");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        //System.out.println("relative path :  attributeRemoved  ");
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
//        System.out.println("relative path : attributeReplaced ");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
    	if (logger.isDebugEnabled()) {
    		logger.info("relative path :  sessionCreated ");
    	}
    }

    @SuppressWarnings("unchecked")
	@Override
    public void sessionDestroyed(HttpSessionEvent se) {
//        Users user = (Users) se.getSession().getAttribute(IConstant.SESSION_USER);
//        if (user != null) {
//            Set<Users> logins = (Set<Users>) se.getSession().getServletContext().getAttribute(IConstant.WHOS_ONLINE_USER);
//            logins.remove(user);
//        }
    }
}
