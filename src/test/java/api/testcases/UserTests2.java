package api.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints2;
import api.payload.UserPayload;
import io.restassured.response.Response;


public class UserTests2 {
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
		Response response=UserEndPoints2.createUser(userPayload);
		response.then().log().all();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		logger.info("Create User Executed");
	}
	
	@Test(priority=2)
	public void testGetUser() {
		Response response=UserEndPoints2.getUser(this.userPayload.getUsername());
		response.then().log().all();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		logger.info("Get User Executed");
	}
	
	@Test(priority=3)
	public void testUpdateUser() {
		userPayload.setFirstName(faker.name().firstName());
		Response response=UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		Response responsePostUpdate=UserEndPoints2.getUser(this.userPayload.getUsername());
		responsePostUpdate.then().log().all();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		logger.info("Update User Executed");
	}

	@Test(priority=4)
	public void testDeleteUser() {
		Response response=UserEndPoints2.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		logger.info("Delete User Executed");
	}
	
}
