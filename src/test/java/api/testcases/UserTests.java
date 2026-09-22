package api.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.UserPayload;
import io.restassured.response.Response;


public class UserTests {
	Faker faker;
	UserPayload userPayload;
	public static Logger logger;
	
	@BeforeClass
	public void generateTestData() {
		faker=new Faker();
		userPayload=new UserPayload();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		logger=LogManager.getLogger("Test Data Generated");
	}
	@Test(priority=1)
	public void testCreateUser() {
		Response response=UserEndPoints.createUser(userPayload);
		response.then().log().all();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 201);
	}

}
