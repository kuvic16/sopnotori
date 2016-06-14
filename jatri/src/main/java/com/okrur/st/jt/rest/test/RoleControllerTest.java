package com.okrur.st.jt.rest.test;

import static com.eclipsesource.restfuse.Assert.assertOk;

import org.junit.Rule;
import org.junit.runner.RunWith;

import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.MediaType;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.Header;
import com.eclipsesource.restfuse.annotation.HttpTest;

@RunWith(HttpJUnitRunner.class)
public class RoleControllerTest {
	
	@Rule
	public Destination destination = new Destination(this, "http://localhost:8080/emis/rest/role");

	@Context
	private Response response;
	
	@HttpTest (method = Method.GET, path="/")
	public void listAllTest() {
		System.out.println(response.getBody());
		assertOk(response);
		//com.eclipsesource.restfuse.Assert.assertAccepted( response );
	}
	
//	@HttpTest (
//			method = Method.POST, 
//			path="/", 
//			type=MediaType.APPLICATION_JSON,
//			headers={ @Header(name = "Content-Type", value = "application/json;charset=utf-8")},
//			content = "{\"name\":\"po\",\"description\":\"Test\"}"
//		)
//	//@Poll( times = 10000, interval = 1000 )
//	public void createTest() {
//		System.out.println(response.getBody());
//		assertOk(response);
//	}

}
