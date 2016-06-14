package net.brac.bep.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * 
 * @author shaiful islam palash <154166; kuvic16@gmail.com> Jersy restful
 *         request filter
 */
@Provider
public class AuthFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
//		System.out.println("I am here man!");
//		
//		requestContext.abortWith(
//                Response.status(Response.Status.UNAUTHORIZED)
//                        .entity("User is not authorized!")
//                        .build());
		//requestContext.abortWith(new Response());
		
		return;
	}

}
