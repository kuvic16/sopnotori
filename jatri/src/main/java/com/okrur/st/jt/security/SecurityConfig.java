/**
 * 
 */
package com.okrur.st.jt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.okrur.st.jt.data.enums.RightsEnum;
import com.okrur.st.jt.util.IConstant;

/**
 * @File SecurityConfig.java
 * @author shaiful islam palash | kuvic16@gmail.com
 * @CreationDate Jan 11, 2016
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled=true, proxyTargetClass=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
		AuthenticationProvider provider = new AppAuthProvider();
		auth.authenticationProvider(provider);
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
        	.csrf().disable()
            .authorizeRequests()
            	.antMatchers("/fonts/**").permitAll()
            	.antMatchers("/img/**").permitAll()
            	.antMatchers("/scripts/**").permitAll()
            	.antMatchers("/styles/**").permitAll()
            	
            	// right module	
            	.antMatchers(HttpMethod.POST, "/rest/right/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.USER_MANAGEMENT_RIGHT + "') and hasAuthority('" + RightsEnum.ADD + "')) ")
            	.antMatchers(HttpMethod.PUT, "/rest/right/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.USER_MANAGEMENT_RIGHT + "') and hasAuthority('" + RightsEnum.UPDATE + "')) ")
            	.antMatchers(HttpMethod.DELETE, "/rest/right/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.USER_MANAGEMENT_RIGHT + "') and hasAuthority('" + RightsEnum.DELETE + "')) ")
            	//.antMatchers(HttpMethod.GET, "/rest/right").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or hasAuthority('" + RightsEnum.USER_MANAGEMENT_RIGHT + "')")
            	
            	// role module	
            	.antMatchers(HttpMethod.POST, "/rest/role/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.USER_MANAGEMENT_ROLE + "') and hasAuthority('" + RightsEnum.ADD + "')) ")
            	.antMatchers(HttpMethod.PUT, "/rest/role/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.USER_MANAGEMENT_ROLE + "') and hasAuthority('" + RightsEnum.UPDATE + "')) ")
            	.antMatchers(HttpMethod.DELETE, "/rest/role/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.USER_MANAGEMENT_ROLE + "') and hasAuthority('" + RightsEnum.DELETE + "')) ")
            	//.antMatchers(HttpMethod.GET, "/rest/role").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or hasAuthority('" + RightsEnum.USER_MANAGEMENT_ROLE + "')")
            	
            	// user module	
            	.antMatchers(HttpMethod.POST, "/rest/user/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.USER_MANAGEMENT_USER + "') and hasAuthority('" + RightsEnum.ADD + "')) ")
            	.antMatchers(HttpMethod.PUT, "/rest/user/updateSettings").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.DASHBOARD + "')) ")
            	.antMatchers(HttpMethod.PUT, "/rest/user/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.USER_MANAGEMENT_USER + "') and hasAuthority('" + RightsEnum.UPDATE + "')) ")            	
            	.antMatchers(HttpMethod.DELETE, "/rest/user/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.USER_MANAGEMENT_USER + "') and hasAuthority('" + RightsEnum.DELETE + "')) ")
            	//.antMatchers(HttpMethod.GET, "/rest/user").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or hasAuthority('" + RightsEnum.USER_MANAGEMENT_USER + "')")
            	
            	// udv module	
            	.antMatchers(HttpMethod.POST, "/rest/udv/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.USER_MANAGEMENT_USER_DEFINED + "') and hasAuthority('" + RightsEnum.ADD + "')) ")
            	.antMatchers(HttpMethod.PUT, "/rest/udv/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.USER_MANAGEMENT_USER_DEFINED + "') and hasAuthority('" + RightsEnum.UPDATE + "')) ")
            	.antMatchers(HttpMethod.DELETE, "/rest/udv/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.USER_MANAGEMENT_USER_DEFINED + "') and hasAuthority('" + RightsEnum.DELETE + "')) ")
            	//.antMatchers(HttpMethod.GET, "/rest/udv").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or hasAuthority('" + RightsEnum.USER_MANAGEMENT_USER_DEFINED + "')")
            	
            	// organization module	
            	.antMatchers(HttpMethod.POST, "/rest/organization/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.ORGANIZATION_MANAGEMENT_ORGANIZATION + "') and hasAuthority('" + RightsEnum.ADD + "')) ")
            	.antMatchers(HttpMethod.PUT, "/rest/organization/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.ORGANIZATION_MANAGEMENT_ORGANIZATION + "') and hasAuthority('" + RightsEnum.UPDATE + "')) ")
            	.antMatchers(HttpMethod.DELETE, "/rest/organization/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.ORGANIZATION_MANAGEMENT_ORGANIZATION + "') and hasAuthority('" + RightsEnum.DELETE + "')) ")
            	//.antMatchers(HttpMethod.GET, "/rest/organization").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or hasAuthority('" + RightsEnum.ORGANIZATION_MANAGEMENT_ORGANIZATION + "')")
            	
            	// donor module	
            	.antMatchers(HttpMethod.POST, "/rest/donor/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.ORGANIZATION_MANAGEMENT_DONOR + "') and hasAuthority('" + RightsEnum.ADD + "')) ")
            	.antMatchers(HttpMethod.PUT, "/rest/donor/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.ORGANIZATION_MANAGEMENT_DONOR + "') and hasAuthority('" + RightsEnum.UPDATE + "')) ")
            	.antMatchers(HttpMethod.DELETE, "/rest/donor/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.ORGANIZATION_MANAGEMENT_DONOR + "') and hasAuthority('" + RightsEnum.DELETE + "')) ")
            	//.antMatchers(HttpMethod.GET, "/rest/donor").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or hasAuthority('" + RightsEnum.ORGANIZATION_MANAGEMENT_DONOR + "')")
            	
            	// education-type module	
            	.antMatchers(HttpMethod.POST, "/rest/education-type/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.EDUCATION_TYPE_MANAGEMENT_EDUCATION_TYPE + "') and hasAuthority('" + RightsEnum.ADD + "')) ")
            	.antMatchers(HttpMethod.PUT, "/rest/education-type/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.EDUCATION_TYPE_MANAGEMENT_EDUCATION_TYPE + "') and hasAuthority('" + RightsEnum.UPDATE + "')) ")
            	.antMatchers(HttpMethod.DELETE, "/rest/education-type/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.EDUCATION_TYPE_MANAGEMENT_EDUCATION_TYPE + "') and hasAuthority('" + RightsEnum.DELETE + "')) ")
            	//.antMatchers(HttpMethod.GET, "/rest/education-type").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or hasAuthority('" + RightsEnum.EDUCATION_TYPE_MANAGEMENT_EDUCATION_TYPE + "')")
            	
            	// grade-point module	
            	.antMatchers(HttpMethod.POST, "/rest/grade-point/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.EDUCATION_TYPE_MANAGEMENT_GRADING_POINT + "') and hasAuthority('" + RightsEnum.ADD + "')) ")
            	.antMatchers(HttpMethod.PUT, "/rest/grade-point/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.EDUCATION_TYPE_MANAGEMENT_GRADING_POINT + "') and hasAuthority('" + RightsEnum.UPDATE + "')) ")
            	.antMatchers(HttpMethod.DELETE, "/rest/grade-point/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.EDUCATION_TYPE_MANAGEMENT_GRADING_POINT + "') and hasAuthority('" + RightsEnum.DELETE + "')) ")
            	//.antMatchers(HttpMethod.GET, "/rest/grade-point").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or hasAuthority('" + RightsEnum.EDUCATION_TYPE_MANAGEMENT_GRADING_POINT + "')")
            	
            	// grade module	
            	.antMatchers(HttpMethod.POST, "/rest/grade/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.EDUCATION_TYPE_MANAGEMENT_GRADE + "') and hasAuthority('" + RightsEnum.ADD + "')) ")
            	.antMatchers(HttpMethod.PUT, "/rest/grade/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.EDUCATION_TYPE_MANAGEMENT_GRADE + "') and hasAuthority('" + RightsEnum.UPDATE + "')) ")
            	.antMatchers(HttpMethod.DELETE, "/rest/grade/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.EDUCATION_TYPE_MANAGEMENT_GRADE + "') and hasAuthority('" + RightsEnum.DELETE + "')) ")
            	//.antMatchers(HttpMethod.GET, "/rest/grade").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or hasAuthority('" + RightsEnum.EDUCATION_TYPE_MANAGEMENT_GRADE + "')")
            	
            	// institute module	
            	.antMatchers(HttpMethod.POST, "/rest/institute/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.INSTITUTE_MANAGEMENT_INSTITUTE + "') and hasAuthority('" + RightsEnum.ADD + "')) ")
            	.antMatchers(HttpMethod.PUT, "/rest/institute/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.INSTITUTE_MANAGEMENT_INSTITUTE + "') and hasAuthority('" + RightsEnum.UPDATE + "')) ")
            	.antMatchers(HttpMethod.DELETE, "/rest/institute/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.INSTITUTE_MANAGEMENT_INSTITUTE + "') and hasAuthority('" + RightsEnum.DELETE + "')) ")
            	//.antMatchers(HttpMethod.GET, "/rest/institute").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or hasAuthority('" + RightsEnum.INSTITUTE_MANAGEMENT_INSTITUTE + "')")
            	
            	// teacher module	
            	.antMatchers(HttpMethod.POST, "/rest/teacher/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.INSTITUTE_MANAGEMENT_TEACHER + "') and hasAuthority('" + RightsEnum.ADD + "')) ")
            	.antMatchers(HttpMethod.PUT, "/rest/teacher/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.INSTITUTE_MANAGEMENT_TEACHER + "') and hasAuthority('" + RightsEnum.UPDATE + "')) ")
            	.antMatchers(HttpMethod.DELETE, "/rest/teacher/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.INSTITUTE_MANAGEMENT_TEACHER + "') and hasAuthority('" + RightsEnum.DELETE + "')) ")
            	//.antMatchers(HttpMethod.GET, "/rest/teacher").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or hasAuthority('" + RightsEnum.INSTITUTE_MANAGEMENT_TEACHER + "')")
            	
            	// student module	
            	.antMatchers(HttpMethod.POST, "/rest/student/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.INSTITUTE_MANAGEMENT_STUDENT + "') and hasAuthority('" + RightsEnum.ADD + "')) ")
            	.antMatchers(HttpMethod.PUT, "/rest/student/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.INSTITUTE_MANAGEMENT_STUDENT + "') and hasAuthority('" + RightsEnum.UPDATE + "')) ")
            	.antMatchers(HttpMethod.DELETE, "/rest/student/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.INSTITUTE_MANAGEMENT_STUDENT + "') and hasAuthority('" + RightsEnum.DELETE + "')) ")
            	//.antMatchers(HttpMethod.GET, "/rest/student").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or hasAuthority('" + RightsEnum.INSTITUTE_MANAGEMENT_STUDENT + "')")
            	
            	// fee setup module	
            	.antMatchers(HttpMethod.POST, "/rest/fee/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.FEES_MANAGEMENT_FEE_SETUP + "') and hasAuthority('" + RightsEnum.ADD + "')) ")
            	.antMatchers(HttpMethod.PUT, "/rest/fee/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.FEES_MANAGEMENT_FEE_SETUP + "') and hasAuthority('" + RightsEnum.UPDATE + "')) ")
            	.antMatchers(HttpMethod.DELETE, "/rest/fee/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.FEES_MANAGEMENT_FEE_SETUP + "') and hasAuthority('" + RightsEnum.DELETE + "')) ")
            	//.antMatchers(HttpMethod.GET, "/rest/fee").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or hasAuthority('" + RightsEnum.FEES_MANAGEMENT_FEE_SETUP + "')")
            	
            	// fee collection module	
            	.antMatchers(HttpMethod.POST, "/rest/student-fee/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.FEES_MANAGEMENT_FEE_COLLECTION + "') and hasAuthority('" + RightsEnum.ADD + "')) ")
            	.antMatchers(HttpMethod.PUT, "/rest/student-fee/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.FEES_MANAGEMENT_FEE_COLLECTION + "') and hasAuthority('" + RightsEnum.UPDATE + "')) ")
            	.antMatchers(HttpMethod.DELETE, "/rest/student-fee/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.FEES_MANAGEMENT_FEE_COLLECTION + "') and hasAuthority('" + RightsEnum.DELETE + "')) ")
            	//.antMatchers(HttpMethod.GET, "/rest/student-fee").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or hasAuthority('" + RightsEnum.FEES_MANAGEMENT_FEE_COLLECTION + "')")
            	
            	// expenditure module	
            	.antMatchers(HttpMethod.POST, "/rest/expenditure/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.FINANCIAL_MANAGEMENT_EXPENDITURE + "') and hasAuthority('" + RightsEnum.ADD + "')) ")
            	.antMatchers(HttpMethod.PUT, "/rest/expenditure/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.FINANCIAL_MANAGEMENT_EXPENDITURE + "') and hasAuthority('" + RightsEnum.UPDATE + "')) ")
            	.antMatchers(HttpMethod.DELETE, "/rest/expenditure/**").access("hasAuthority('" + IConstant.ADMIN_ROLE + "') or (hasAuthority('" + RightsEnum.FINANCIAL_MANAGEMENT_EXPENDITURE + "') and hasAuthority('" + RightsEnum.DELETE + "')) ")
            	
            	
                .anyRequest().authenticated()
                .and()
        		.exceptionHandling().accessDeniedPage("/403.html")
                .and()
            .formLogin()
                .loginPage("/login.html")
            	.permitAll()
                .and()
            .logout()                                    
                .permitAll();
    }
}
