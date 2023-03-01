package com.ibm.validation.liberty.bean;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

public class IncludeInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Pattern(regexp="IGNORE")
	private String onConflict;

	public String getOnConflict() {
		return onConflict;
	}

	public void setOnConflict(String onConflict) {
		this.onConflict = onConflict;
	}
}
