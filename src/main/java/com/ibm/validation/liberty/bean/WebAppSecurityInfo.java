package com.ibm.validation.liberty.bean;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class WebAppSecurityInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Pattern(regexp="false", message="displayAuthenticationRealm must be set to false")
	private String displayAuthenticationRealm;
	
	@Pattern(regexp="false", message="sameSiteCookie must be set to Strict")
	private String sameSiteCookie;
	
	@NotNull(message="ssoDomainNames must be set")
	@NotEmpty(message="ssoDomainNames must not be empty")
	private String ssoDomainNames;
	
	@Pattern(regexp="true", message="setCookieSecureFlag must be set to true")
	private String setCookieSecureFlag;
	
	@Pattern(regexp="true", message="ssoRequiresSSL must be set to true")
	private String ssoRequiresSSL;
	
	@Pattern(regexp="^(?!LtpaToken2$)", message="ssoCookieName must not be LtpaToken2")
	private String ssoCookieName;
	
	@Pattern(regexp="true", message="useOnlyCustomCookieName must be set to true")
	private String useOnlyCustomCookieName;
	
	@Pattern(regexp="true", message="httpOnlyCookies must be set to true")
	private String httpOnlyCookies;
	
	@Pattern(regexp="true", message="trackLoggedOutSSOCookies must be set to true")
	private String trackLoggedOutSSOCookies;
	
	public String getDisplayAuthenticationRealm() {
		return displayAuthenticationRealm;
	}

	public void setDisplayAuthenticationRealm(String displayAuthenticationRealm) {
		this.displayAuthenticationRealm = displayAuthenticationRealm;
	}

	public String getSameSiteCookie() {
		return sameSiteCookie;
	}

	public void setSameSiteCookie(String sameSiteCookie) {
		this.sameSiteCookie = sameSiteCookie;
	}

	public String getSsoDomainNames() {
		return ssoDomainNames;
	}

	public void setSsoDomainNames(String ssoDomainNames) {
		this.ssoDomainNames = ssoDomainNames;
	}

	public String getSetCookieSecureFlag() {
		return setCookieSecureFlag;
	}

	public void setSetCookieSecureFlag(String setCookieSecureFlag) {
		this.setCookieSecureFlag = setCookieSecureFlag;
	}

	public String getSsoRequiresSSL() {
		return ssoRequiresSSL;
	}

	public void setSsoRequiresSSL(String ssoRequiresSSL) {
		this.ssoRequiresSSL = ssoRequiresSSL;
	}

	public String getSsoCookieName() {
		return ssoCookieName;
	}

	public void setSsoCookieName(String ssoCookieName) {
		this.ssoCookieName = ssoCookieName;
	}

	public String getUseOnlyCustomCookieName() {
		return useOnlyCustomCookieName;
	}

	public void setUseOnlyCustomCookieName(String useOnlyCustomCookieName) {
		this.useOnlyCustomCookieName = useOnlyCustomCookieName;
	}

	public String getHttpOnlyCookies() {
		return httpOnlyCookies;
	}

	public void setHttpOnlyCookies(String httpOnlyCookies) {
		this.httpOnlyCookies = httpOnlyCookies;
	}

	public String getTrackLoggedOutSSOCookies() {
		return trackLoggedOutSSOCookies;
	}

	public void setTrackLoggedOutSSOCookies(String trackLoggedOutSSOCookies) {
		this.trackLoggedOutSSOCookies = trackLoggedOutSSOCookies;
	}
	
}
