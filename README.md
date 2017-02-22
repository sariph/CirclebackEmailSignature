# CirclebackEmailSignature

<b>Steps To Run:</b>

1. Run Main Method of CircleBackEmailSignature


<b>Change/Update the following Configuration if required in PropertyConfiguration.java</b>

1. Input File for POST Request: SigCapturePostRequest_INPUT_FILE_TO_POST_REQUEST.json

2. Input File To Merge with Response Contacts: SigCaptureContact_INPUT_FILE_TO_MERGE_WITH.json

3. Final Output File: SigCaptureContact_FINAL_OUTPUT.json

4. REST Response Output(Optional): SigCaptureContact_REST_RESPONSE_OUTPUT.json

5. URL To POST Request: https://api.circleback.com/service/sig-capture/scan

6. HTTP Header Cache Control: no-cache

7. HTTP Header for API Key: X-CB-ApiKey

8. HTTP Header for API Key's Value: ZWU4OGFlOGYtNTc4Ni00ZjY0LWJjMGYtNzAxNTkyYjliNWZi


<b>Assumptions:</b>

1. The emails are case sensitive so john@gmail.com and JOHN@gmail.com are considered different

2. The phone numbers should be an exact match and would not consider country code, for example 1-888-888-888 is not equivalent to 888-888-888


<b>Basic Program Steps Taken:</b>

1. Convert the given POST Request to Java Objects

2. Call the REST API with the POST Request and get a list of Signature Capture Responses

3. Extract a list of Signature Capture Contact Object from Signature Capture Response Object

4. Convert the given list of contact to merge from JSON to Java Objects

5. Merge the list of contacts from input and list of contacts from REST response

6. Write to a file



