package com.ibm.validation.liberty.bean;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

public class WebAppSecurityInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Pattern(regexp="false")
	private String displayAuthenticationRealm;

	public String getDisplayAuthenticationRealm() {
		return displayAuthenticationRealm;
	}

	public void setDisplayAuthenticationRealm(String displayAuthenticationRealm) {
		this.displayAuthenticationRealm = displayAuthenticationRealm;
	}
	
}
