package com.test.apirestas;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

/*
 	given()
    content type,set cookies,add auth,add param,set headers etc..
  
	when()
	get,post,put,delete
	
	then()
	validate status code,extract response,extract headers cookies rsponse body etc....
*/

public class HttpRequests {
	
	int id;
	
	@Test(priority=1)
	 void getUser() 
	{
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=1")
			
			
		.then()
			.statusCode(200)
			.body("page",equalTo(1))
			.log().all();
		
	}
	//@Test(priority=2)
	void getfstnameemail() {
		given()
		
		.when()
			.get("https://reqres.in/api/users?first_name=Eve")
		.then()
			.statusCode(200)
			.log().all();
		
	}
	@Test(priority=3)
	void createUsr() {
		
		HashMap in=new HashMap();
		in.put("email", "abc@abc.com");
		in.put("first_name", "test1");
		in.put("last_name", "roopa");
		in.put("avatar", "www.stanton-framii.com");
		
		id=given()
			.contentType("application/json")
			.body(in)
		
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
		
		/*
		 * .then() .statusCode(201) .log().all();
		 */
	}
	
	//@Test(priority=4,dependsOnMethods = {"createUsr"})
	void updateUser() {
		
		HashMap in=new HashMap();
		in.put("name", "uma");
		in.put("job", "coder");
		
		given()
			.contentType("application/json")
			.body(in)
		
		.when()
			.put("https://reqres.in/api/users/"+id)
			
		.then() 
			.statusCode(200) 
			.log().all();
		
		
	}

}
