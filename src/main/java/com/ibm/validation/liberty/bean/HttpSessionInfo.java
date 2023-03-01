package com.ibm.validation.liberty.bean;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class HttpSessionInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Pattern(regexp="Strict", message = "cookieSameSite must be Strict")
	private String cookieSameSite;
	
	@Pattern(regexp="true", message = "cookieHttpOnly must be true")
	private String cookieHttpOnly;
	
	@NotNull(message = "cookieDomain must be defined")
	@NotEmpty(message = "cookieDomain must not be empty")
	private String cookieDomain;
	
	@Pattern(regexp="true", message = "cookieSecure must be true")
	private String cookieSecure;

	public String getCookieSameSite() {
		return cookieSameSite;
	}

	public void setCookieSameSite(String cookieSameSite) {
		this.cookieSameSite = cookieSameSite;
	}

	public String getCookieHttpOnly() {
		return cookieHttpOnly;
	}

	public void setCookieHttpOnly(String cookieHttpOnly) {
		this.cookieHttpOnly = cookieHttpOnly;
	}

	public String getCookieDomain() {
		return cookieDomain;
	}

	public void setCookieDomain(String cookieDomain) {
		this.cookieDomain = cookieDomain;
	}

	public String getCookieSecure() {
		return cookieSecure;
	}

	public void setCookieSecure(String cookieSecure) {
		this.cookieSecure = cookieSecure;
	}
	
}
