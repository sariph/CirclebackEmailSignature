package com.circleback.response;

import java.util.Arrays;

public class SigCaptureResult {
	private String user_id;
	private String message_id;
	private SigCaptureContact[] contacts;
	private String[] signaure_blocks;
	private String result_code;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getMessage_id() {
		return message_id;
	}
	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}
	public SigCaptureContact[] getContacts() {
		return contacts;
	}
	public void setContacts(SigCaptureContact[] contacts) {
		this.contacts = contacts;
	}
	public String[] getSignaure_blocks() {
		return signaure_blocks;
	}
	public void setSignaure_blocks(String[] signaure_blocks) {
		this.signaure_blocks = signaure_blocks;
	}
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	
	@Override
	public String toString() {
		return "SigCaptureResult [user_id=" + user_id + ", message_id=" + message_id + ", contacts="
				+ Arrays.toString(contacts) + ", signaure_blocks=" + Arrays.toString(signaure_blocks) + ", result_code="
				+ result_code + "]";
	}
}
