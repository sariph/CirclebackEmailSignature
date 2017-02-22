package com.circleback.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SigCaptureHeaderAddress {
	String name;

	@JsonProperty(required = true)
	String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
