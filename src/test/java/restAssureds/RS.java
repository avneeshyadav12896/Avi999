package restAssureds;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payload;

public class RS {

	String placeId;
	String addressNew;
	@Test(priority = 1)
	public void addPlace()
	{
		//given - all inout detiails
		//when - submit the API, resourse and http methods
		//then - validate the response
		
	/*	RestAssured.baseURI="https://rahulshettyacademy.com";
		given().log().all().queryParam("keyS", "qaclick123").header("Content-Type","application/json")
		.body(Payload.addPlaceBody())
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).body("status", equalTo("OK"))
		.header("Server", "Apache/2.4.52 (Ubuntu)");
		*/
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123")
				.header("Content-Type","application/json")
		.body(Payload.addPlaceBody())
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).body("status", equalTo("OK"))
		.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		JsonPath js=new JsonPath(response);//for parsing json
		placeId = js.getString("place_id");
		System.out.println(placeId);

	    }
	
	
	    @Test(priority = 2)
		public void updatePlace()
		{
	    addressNew="Azamgarh, India";
		given().log().all().queryParam("key", "qaclick123")
		.header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+addressNew+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		.when().put("/maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200)
		.body("msg", equalTo("Address successfully updated"));
		}
	    
	    
		@Test(priority = 3)
		public void getPlace()
		{
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", placeId)
		.when().get("/maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200)
		.extract().response().asString();
		JsonPath jS=new JsonPath(getPlaceResponse);
		String  actualAddress= jS.getString("address");
		System.out.println(actualAddress);
		Assert.assertEquals(addressNew, actualAddress);	
		}
		
		
		@Test(priority = 4)
		public void deletePlace()
		{
			
		}
}
