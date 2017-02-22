package com.circleback.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.circleback.configuration.PropertyConfiguration;
import com.circleback.request.SigCaptureRequests;
import com.circleback.response.SigCaptureContact;
import com.circleback.response.SigCaptureResponse;
import com.circleback.response.SigCaptureResult;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CircleBackEmailSignatureApplication {
	private static final Logger log = LoggerFactory.getLogger(CircleBackEmailSignatureApplication.class);

	public static void main(String[] args) {
		log.info("Start of Program");
		// Convert the given Post Requests to Java Objects
		List<SigCaptureRequests> listOfSigCaptureRequests =runSigCapturePostRequestJsonToJavaObject();

		// Call the REST API with the Post Request and get a list of Responses
		List<SigCaptureResponse> listOfSigCaptureResponse = EmailSignatureRestClient.callEmailSignatureApi(listOfSigCaptureRequests);

		// Extract a list of Signature Capture Contact Object from Signature Capture Response Object
		List<SigCaptureContact> listOfSigCaptureContact =extractSigCaptureContactFromResponse(listOfSigCaptureResponse);

		// Convert the given list of contact to merge from JSON to Java Objects
		List<SigCaptureContact> listOfSigCaptureContactToMergeWith =runSigCaptureContactJsonToJavaObject();

		// Merge the Contacts
		log.info("Merge the Contacts");
		List<SigCaptureContact> mergedListOfSigCaptureContact = MergeSigCaptureContacts.mergeSigCaptureContactList(listOfSigCaptureContact, listOfSigCaptureContactToMergeWith);

		// Write to File
		log.info("Write to File");
		
		// Write Objects to File
		runConvertToJsonAndWriteToFileTemp(listOfSigCaptureContact);
		
		//Write Objects to File
		runConvertToJsonAndWriteToFile(mergedListOfSigCaptureContact);
		log.info("End of Program");
	}

	private static List<SigCaptureRequests> runSigCapturePostRequestJsonToJavaObject() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			List<SigCaptureRequests> listOfSigCaptureRequests = mapper.readValue(
					new File(PropertyConfiguration.SIG_CAPTURE_POST_REQUEST_FILE_PATH),
					mapper.getTypeFactory().constructCollectionType(List.class, SigCaptureRequests.class));
			return listOfSigCaptureRequests;
		} catch (JsonGenerationException jsonGenerationException) {
			jsonGenerationException.printStackTrace();
		} catch (JsonMappingException jsonMappingException) {
			jsonMappingException.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}

		return Arrays.asList(new SigCaptureRequests());
	}

	private static List<SigCaptureContact> extractSigCaptureContactFromResponse(
			List<SigCaptureResponse> listOfSigCaptureResponse) {
		List<SigCaptureContact> listOfSigCaptureContact = new ArrayList<SigCaptureContact>();
		for (SigCaptureResponse sigCaptureResponse : listOfSigCaptureResponse) {
			List<SigCaptureResult> listOfSigCaptureResult = new ArrayList<SigCaptureResult>();
			if (sigCaptureResponse.getResults() != null) {
				listOfSigCaptureResult = Arrays.asList(sigCaptureResponse.getResults());
			}
			for (SigCaptureResult sigCaptureResult : listOfSigCaptureResult) {
				List<SigCaptureContact> listOfSigCaptureContactTemp = new ArrayList<SigCaptureContact>();
				if (sigCaptureResult.getContacts() != null) {
					listOfSigCaptureContactTemp = Arrays.asList(sigCaptureResult.getContacts());
				}
				listOfSigCaptureContact.addAll(listOfSigCaptureContactTemp);
			}
		}
		return listOfSigCaptureContact;
	}

	private static List<SigCaptureContact> runSigCaptureContactJsonToJavaObject() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			List<SigCaptureContact> listOfSigCaptureContact = mapper.readValue(
					new File(PropertyConfiguration.SIG_CAPTURE_CONTACT_TO_MERGE_WITH_FILE_PATH),
					mapper.getTypeFactory().constructCollectionType(List.class, SigCaptureContact.class));
			return listOfSigCaptureContact;
		} catch (JsonGenerationException jsonGenerationException) {
			jsonGenerationException.printStackTrace();
		} catch (JsonMappingException jsonMappingException) {
			jsonMappingException.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		return Arrays.asList(new SigCaptureContact());
	}

	private static void runConvertToJsonAndWriteToFile(List<SigCaptureContact> mergedListOfSigCaptureContact) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File(PropertyConfiguration.SIG_CAPTURE_CONTACT_OUTPUT_WITH_FILE_PATH),
					mergedListOfSigCaptureContact);
		} catch (JsonGenerationException jsonGenerationException) {
			jsonGenerationException.printStackTrace();
		} catch (JsonMappingException jsonMappingException) {
			jsonMappingException.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	private static void runConvertToJsonAndWriteToFileTemp(List<SigCaptureContact> mergedListOfSigCaptureContact) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File(PropertyConfiguration.SIG_CAPTURE_CONTACT_OUTPUT_REST_WITH_FILE_PATH),
					mergedListOfSigCaptureContact);
		} catch (JsonGenerationException jsonGenerationException) {
			jsonGenerationException.printStackTrace();
		} catch (JsonMappingException jsonMappingException) {
			jsonMappingException.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
}
