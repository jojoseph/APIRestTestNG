import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestGoogleMap {

	public static void main(String[] args) {
		//given all the input
		//when   the recourse
		//then the response validate
		
		// post
		 RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = "https://rahulshettyacademy.com";
			String  response = 	given().log().all().queryParam("key", "qaclick123")
				.header("Content-Type", "application/json")
				.body(Payload.addPlace())
				.when().post("maps/api/place/add/json")
				.then().assertThat().statusCode(200)
				.body("scope", equalTo("APP"))
				.header("Server", ("Apache/2.4.52 (Ubuntu)")).extract().response().asString();
			//System.out.println(response);
			JsonPath jsCreate = new JsonPath(response);  //Parse json
			String placeID = jsCreate.getString("place_id");
			System.out.println(placeID);
			
			//update place put
			String address = "70 winter walk, USA";
			given().log().all().queryParam("key", "qaclick123")
			.header("Content-Type", "application/json")
			.body("{\r\n"
					+ "\"place_id\":\""+ placeID + "\",\r\n"
					+ "\"address\":\""+ address +"\",\r\n"
					+ "\"key\":\"qaclick123\"\r\n"
					+ "}")
			.when().put("maps/api/place/update/json")
			.then().assertThat().log().all().statusCode(200)
			.body("msg", equalTo("Address successfully updated"));
			
			//Get Place  get
			String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
			.queryParam("place_id", placeID)
			.when().post("maps/api/place/get/json")
			.then().assertThat().statusCode(200).extract().response().asString();
			
			JsonPath jsgetPlace = new JsonPath(getPlaceResponse);  //Parse json
			String actualAddress = jsgetPlace.getString("address");
			System.out.println(actualAddress);
			
			//Cucumber, Junit, TestNG
	}

}
