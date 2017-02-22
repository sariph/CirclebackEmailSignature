package com.circleback.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.circleback.configuration.PropertyConfiguration;
import com.circleback.request.SigCaptureRequests;
import com.circleback.response.SigCaptureResponse;

public class EmailSignatureRestClient{
	private static final Logger log = LoggerFactory.getLogger(EmailSignatureRestClient.class);
	
	public static List<SigCaptureResponse> callEmailSignatureApi(List<SigCaptureRequests> listOfSigCaptureRequests){
		List<SigCaptureResponse> listOfSigCaptureResponse = new ArrayList<SigCaptureResponse>();
		
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		RestTemplate restTemplate = restTemplateBuilder.build();
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setCacheControl(PropertyConfiguration.CACHE_CONTROL);
		httpHeaders.add(PropertyConfiguration.API_KEY, PropertyConfiguration.API_KEY_VALUE);

		for(SigCaptureRequests sigCaptureRequests: listOfSigCaptureRequests){
			HttpEntity<SigCaptureRequests> httpEntity = new HttpEntity<SigCaptureRequests>(sigCaptureRequests,httpHeaders);
			ResponseEntity<SigCaptureResponse> sigCaptureResponse = restTemplate.exchange(PropertyConfiguration.CIRCLE_BACK_URL, HttpMethod.POST, httpEntity, SigCaptureResponse.class);
			listOfSigCaptureResponse.add(sigCaptureResponse.getBody());
		}
		
		log.info("REST Client gets an output of List of SigCaptureResponse");
		return listOfSigCaptureResponse;
	}
}
