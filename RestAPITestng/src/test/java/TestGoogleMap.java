import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestGoogleMap {

	public static void main(String[] args) {
		//given all the input
		//when   the recourse
		//then the response validate
		
		 RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = "https://rahulshettyacademy.com";
				given().queryParam("key", "qaclick123")
				.header("Content-Type", "application/json")
				.body(Payload.addPlace())
				.when().post("maps/api/place/add/json")
				.then().log().all().assertThat().statusCode(200)
				.body("scope", equalTo("APP"))
				.header("Server", ("Apache/2.4.52 (Ubuntu)"));
	}

}
