package com.ibm.validation.liberty.bean;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

public class LDAPRegistryInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Pattern(regexp="true")
	private String sslEnabled;

	public String getSslEnabled() {
		return sslEnabled;
	}

	public void setSslEnabled(String sslEnabled) {
		this.sslEnabled = sslEnabled;
	}
	
}
