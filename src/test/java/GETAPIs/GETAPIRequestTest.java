package GETAPIs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GETAPIRequestTest {

	RequestSpecification request;
	
	//Non BDD Approach:
	@BeforeTest
	public void setup() {
		RestAssured.baseURI = "https://gorest.co.in";
		request = RestAssured.given();
		request.header("Authorization", "Bearer b4d39753152999ba3609d3fd11033dd1630e6e80c5da95b6b10b467dd2656a0b");
		
	}
	
	@Test(priority=1)
	public void getAllUserAPITest() {

		// Request:
		Response response = request.get("/public/v2/users/");

		// =========================
		// Status Code:
		int statusCode = response.statusCode();
		System.out.println("Status Code : " + statusCode);

		// Verification point:
		Assert.assertEquals(statusCode, 200);

		// Status Message:
		String statusMesg = response.statusLine();
		System.out.println(statusMesg);

		// fetch the body:
		response.prettyPrint();

		// fetch response header:
		String contentType = response.header("Content-Type");
		System.out.println("Print contentType from the header : " + contentType);

		System.out.println("---------------------------------------------------");
		// fetch all response headers:
		List<Header> headersList = response.headers().asList();
		System.out.println(headersList.size());

		for (Header h : headersList) {
			System.out.println(h.getName() + ":" + h.getValue());
		}
	}

	@Test(priority=2)
	public void getAllUsersWithQueryParameterAPITest() {

		// Request
		request.queryParam("name", "Krishna");
		request.queryParam("status", "active");

		Response response = request.get("/public/v2/users/");

		// =========================
		// Status Code:
		int statusCode = response.statusCode();
		System.out.println("Status Code : " + statusCode);

		// Verification point:
		Assert.assertEquals(statusCode, 200);

		// Status Message:
		String statusMesg = response.statusLine();
		System.out.println(statusMesg);

		// fetch the body:
		response.prettyPrint();

	}

	@Test(priority=3)
	public void getAllUsersWithQueryParameter_withHashMap_APITest() {

		// Request
		RestAssured.baseURI = "https://gorest.co.in";
		RequestSpecification request = RestAssured.given();

		Map<String, String> queryParamMap = new HashMap<String, String>();
		queryParamMap.put("status", "inactive");
		queryParamMap.put("gender", "male");

		request.header("Authorization", "Bearer b4d39753152999ba3609d3fd11033dd1630e6e80c5da95b6b10b467dd2656a0b");
		Response response = request.get("/public/v2/users/");

		// =========================
		// Status Code:
		int statusCode = response.statusCode();
		System.out.println("Status Code : " + statusCode);

		// Verification point:
		Assert.assertEquals(statusCode, 200);

		// Status Message:
		String statusMesg = response.statusLine();
		System.out.println(statusMesg);

		// fetch the body:
		response.prettyPrint();

	}

}
