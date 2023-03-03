package com.ibm.validation.liberty.bean;

import java.io.Serializable;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class SSLInfo implements Serializable {
	private static final long serialVersionUID = 3398615252471910825L;

	private String id;
	
	@Pattern(regexp="true", message="clientAuthentication must be set to true")
	private String clientAuthentication;
	
	@Pattern(regexp="true", message="verifyHostname must be set to true")
	private String verifyHostname;
	
	@NotNull(message="enabledCiphers must not be null")
	@NotEmpty(message="enabledCiphers must not be empty")
	private String enabledCiphers;
	
	@Pattern(regexp="true", message="enforceCipherOrder must be set to true")
	private String enforceCipherOrder;
	
	@Pattern(regexp="false", message="trustDefaultCerts must be set to false")
	private String trustDefaultCerts;
	
	@Pattern(regexp="TLSv1.2|TLSv1.3", message="protocol must be set to either TLSv1.2 or TLSv1.3")
	private String protocol;
	
	@Pattern(regexp="HIGH", message="securityLevel must be set to HIGH")
	private String securityLevel;
	
	@AssertTrue
	private boolean clientAuthSupported;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getSecurityLevel() {
		return securityLevel;
	}
	public void setSecurityLevel(String securityLevel) {
		this.securityLevel = securityLevel;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public boolean isClientAuthSupported() {
		return clientAuthSupported;
	}
	public void setClientAuthSupported(boolean clientAuthSupported) {
		this.clientAuthSupported = clientAuthSupported;
	}
	public String getClientAuthentication() {
		return clientAuthentication;
	}
	public void setClientAuthentication(String clientAuthentication) {
		this.clientAuthentication = clientAuthentication;
	}
	public String getVerifyHostname() {
		return verifyHostname;
	}
	public void setVerifyHostname(String verifyHostname) {
		this.verifyHostname = verifyHostname;
	}
	public String getEnabledCiphers() {
		return enabledCiphers;
	}
	public void setEnabledCiphers(String enabledCiphers) {
		this.enabledCiphers = enabledCiphers;
	}
	public String getEnforceCipherOrder() {
		return enforceCipherOrder;
	}
	public void setEnforceCipherOrder(String enforceCipherOrder) {
		this.enforceCipherOrder = enforceCipherOrder;
	}
	public String getTrustDefaultCerts() {
		return trustDefaultCerts;
	}
	public void setTrustDefaultCerts(String trustDefaultCerts) {
		this.trustDefaultCerts = trustDefaultCerts;
	}
}