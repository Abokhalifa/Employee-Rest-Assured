package tests;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.XLUtils;

public class addEmployee {
	
	@Test(dataProvider = "emplyeeDataProviderExcel")
	void addEmployeeRecord(String FirstName , String LastName, String UserName, String Password, String Email)
	{
		RestAssured.baseURI = "http://restapi.demoqa.com";
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("Content-Type","application/json");
		
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", FirstName);
		requestParams.put("LastName", LastName);
		requestParams.put("UserName", UserName);
		requestParams.put("Password", Password);
		requestParams.put("Email", Email);
		
		httpRequest.body(requestParams.toString());
		
		Response response = httpRequest.request(Method.POST,"/customer/register");
		String responseBody = response.getBody().asString();
			
		
		Assert.assertEquals(responseBody.contains("OPERATION_SUCCESS"),true);
		
			

		
	}
	@DataProvider(name = "emplyeeDataProviderStatic")
	String[][] emplyeeDataProvider() throws IOException
	{
	
		
		
		String [][] emplyeeData = {{"Ahmed","Abokhalifa","A"+utilities.misc.generateRandomNumber(),"test123","aa@gg"+utilities.misc.generateRandomNumber()+".net"},
				                   {"Ahmed","Abokhalifa","A"+utilities.misc.generateRandomNumber(),"test123","aa@gg"+utilities.misc.generateRandomNumber()+".net"}};
		return emplyeeData;
		
	}
	
	@DataProvider(name = "emplyeeDataProviderExcel")
	String[][] emplyeeDataProviderExcel() throws IOException
	{
		String path = System.getProperty("user.dir")+"/src/test/resources/empData.xlsx";
		int rowCount = XLUtils.getRowCount(path, "Sheet1");
		int colCount = XLUtils.getCellCount(path, "Sheet1",1);
		String emplyeeData [][] = new String[rowCount][colCount];
		for (int i=1; i<=rowCount;i++)
		{
			for (int j =0 ;j<colCount;j++)
			{
				emplyeeData[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j)+utilities.misc.generateRandomNumber();
						
			}
			
			
		}
		
		
		
		//String [][] emplyeeData = {{"Ahmed","Abokhalifa","A"+generateRandomNumber(),"test123","aa@gg"+generateRandomNumber()+".net"},
				                  // {"Ahmed","Abokhalifa","A"+generateRandomNumber(),"test123","aa@gg"+generateRandomNumber()+".net"}};
		return emplyeeData;
		
	}
	

	
	
	
	
	

}
