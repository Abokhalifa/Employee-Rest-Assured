package tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.PreemptiveAuthSpec;
import io.restassured.specification.RequestSpecification;
import utilities.XLUtils;

public class authentication {
	
	@Test
	void authenticate()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com";
		PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
		authScheme.setUserName("ToolsQA");
		authScheme.setPassword("TestPassword");
		RestAssured.authentication= authScheme;	
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET,"/authentication/CheckForAuthentication");
		String responseBody = response.getBody().asString();
		System.out.println("Response body is: "+responseBody);
	    int statusCode = response.getStatusCode();
		assertEquals(statusCode, 200);
		
			

		
	}
	
	
	
	
	

}
