package br.ufrn.imd.utravel.security;

import java.io.IOException;
import java.security.Principal;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import io.jsonwebtoken.Claims;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter{

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String header = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		
		if ((header == null) || (!header.startsWith("Bearer "))) {
			throw new NotAuthorizedException("Authorization header fault.");
		}
		
		String token = header.substring("Bearer".length()).trim();
		
		try {
			Claims claims = JWTUtil.decode(token);
			
			if (claims == null) {
				throw new Exception();
			}
			
			System.out.println("Testando");
			changeRequestContext(requestContext, claims.getSubject());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void changeRequestContext(ContainerRequestContext requestContext, String login) {
		final SecurityContext currentSecurityContext = requestContext.getSecurityContext();
		requestContext.setSecurityContext(new SecurityContext() {

			@Override
			public Principal getUserPrincipal() {				
				return new Principal() {
					@Override
					public String getName() {
						return login;
					}
				};
			}

			@Override
			public boolean isUserInRole(String role) {
				return true;
			}

			@Override
			public boolean isSecure() {
				return currentSecurityContext.isSecure();
			}

			@Override
			public String getAuthenticationScheme() {
				return "Bearer";
			}
		});
	}
}
