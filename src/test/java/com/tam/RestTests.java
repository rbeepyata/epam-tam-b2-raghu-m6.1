package com.tam;

import java.util.List;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.testng.annotations.Test;

public class RestTests {
	@Test
	public void testStatusCodeForUsers() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		
		Response response = RestAssured.when().get("/users");
		System.out.println("Request = " + "https://jsonplaceholder.typicode.com/users");
		System.out.println("Status Code = " + response.getStatusCode());
		
		response.then().statusCode(200);
		//RestAssured.when().get("/users/").then().statusCode(200); // For one line simple verification
	}

	@Test
	public void testContentTypeResponseHeader() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		
		Response response = RestAssured.when().get("/users");
		System.out.println("Request = " + "https://jsonplaceholder.typicode.com/users");
		System.out.println("Content Type = " + response.getContentType());
	
		response.then().contentType("application/json; charset=utf-8");
		//RestAssured.when().get("/users").then().contentType("application/json; charset=utf-8"); // For one line simple verification
	}

	@Test
	public void testResponseContent() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		RequestSpecification request = RestAssured.given();
		Response response = request.get("/users");

		JsonPath jsonPathEvaluator = response.jsonPath();
		System.out.println("Request = " + "https://jsonplaceholder.typicode.com/users");
		System.out.println("Printing list of users in the response...");
		List<String> list = jsonPathEvaluator.getList("username");
		for (String user : list) {
			System.out.print(" " + user);
		}
		System.out.println();
		System.out.println("Number of users = " + list.size());
		Assert.assertTrue("Response has an array of 10 users", list.size() == 10);
	}
}
