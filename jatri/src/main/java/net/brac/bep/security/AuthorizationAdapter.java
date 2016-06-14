/**
 * 
 */
package net.brac.bep.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * @File UserDetailsAdapter.java
 * @author shaiful islam palash | kuvic16@gmail.com
 * @CreationDate Jan 24, 2016
 */
public class AuthorizationAdapter implements GrantedAuthority{
	private static final long serialVersionUID = -945202485902011676L;
	
	private String code;
	
	public AuthorizationAdapter(String code) {
		this.code = code;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.GrantedAuthority#getAuthority()
	 */
	@Override
	public String getAuthority() {
		return code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	

}
