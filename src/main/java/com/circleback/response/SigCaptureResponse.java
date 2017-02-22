package com.circleback.response;

import java.util.Arrays;

public class SigCaptureResponse {
	private String request_id;
	private String contact_count;
	private SigCaptureResult[] results;
	
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	public String getContact_count() {
		return contact_count;
	}
	public void setContact_count(String contact_count) {
		this.contact_count = contact_count;
	}
	public SigCaptureResult[] getResults() {
		return results;
	}
	public void setResults(SigCaptureResult[] results) {
		this.results = results;
	}
	
	@Override
	public String toString() {
		return "SigCaptureResponse [request_id=" + request_id + ", contact_count=" + contact_count + ", results="
				+ Arrays.toString(results) + "]";
	}
}
