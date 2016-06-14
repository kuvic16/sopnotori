/**
 * 
 */
package com.okrur.st.jt.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * @File SecurityWebApplicationInitializer.java
 * @author shaiful islam palash | kuvic16@gmail.com
 * @CreationDate Jan 11, 2016
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer{
	public SecurityWebApplicationInitializer(){
		super(SecurityConfig.class);
	}
}
