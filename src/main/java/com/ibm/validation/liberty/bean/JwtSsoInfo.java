package com.ibm.validation.liberty.bean;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class JwtSsoInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message="cookieName must be set and may not be JWT")
	@NotEmpty(message="cookieName must not be JWT")
	@Pattern(regexp="^(?!JWT$).*$", message="cookieName must not be JWT")
	private String cookieName;

	public String getCookieName() {
		return cookieName;
	}

	public void setCookieName(String cookieName) {
		this.cookieName = cookieName;
	}
}
