package com.ibm.validation.liberty.bean;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

public class ApplicationMonitorInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Pattern(regexp="mbean|disabled")
	private String updateTrigger;
	
	@Pattern(regexp="false")
	private String dropinsEnabled;

	public String getUpdateTrigger() {
		return updateTrigger;
	}

	public void setUpdateTrigger(String updateTrigger) {
		this.updateTrigger = updateTrigger;
	}

	public String getDropinsEnabled() {
		return dropinsEnabled;
	}

	public void setDropinsEnabled(String dropinsEnabled) {
		this.dropinsEnabled = dropinsEnabled;
	}
	
}
