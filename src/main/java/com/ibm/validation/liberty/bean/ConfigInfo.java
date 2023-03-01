package com.ibm.validation.liberty.bean;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

public class ConfigInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Pattern(regexp="polled|disabled")
	private String updateTrigger;
	private String monitorInterval;
	private String onError;
	

	public String getUpdateTrigger() {
		return updateTrigger;
	}

	public void setUpdateTrigger(String updateTrigger) {
		this.updateTrigger = updateTrigger;
	}

	public String getMonitorInterval() {
		return monitorInterval;
	}

	public void setMonitorInterval(String monitorInterval) {
		this.monitorInterval = monitorInterval;
	}

	public String getOnError() {
		return onError;
	}

	public void setOnError(String onError) {
		this.onError = onError;
	}	
	
}
