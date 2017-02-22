package com.circleback.configuration;

public class PropertyConfiguration {
	//For REST Client
	public static final String CIRCLE_BACK_URL="https://api.circleback.com/service/sig-capture/scan";
	public static final String CACHE_CONTROL="no-cache";
	public static final String API_KEY="X-CB-ApiKey";
	public static final String API_KEY_VALUE="ZWU4OGFlOGYtNTc4Ni00ZjY0LWJjMGYtNzAxNTkyYjliNWZi";
	
	//For File Resource Paths
	public static final String SIG_CAPTURE_POST_REQUEST_FILE_PATH = "src/main/resources/SigCapturePostRequest_INPUT_FILE_TO_POST_REQUEST.json";
	public static final String SIG_CAPTURE_CONTACT_TO_MERGE_WITH_FILE_PATH = "src/main/resources/SigCaptureContact_INPUT_FILE_TO_MERGE_WITH.json";
	public static final String SIG_CAPTURE_CONTACT_OUTPUT_WITH_FILE_PATH = "src/main/resources/SigCaptureContact_FINAL_OUTPUT.json";
	public static final String SIG_CAPTURE_CONTACT_OUTPUT_REST_WITH_FILE_PATH = "src/main/resources/SigCaptureContact_REST_RESPONSE_OUTPUT.json";
}
