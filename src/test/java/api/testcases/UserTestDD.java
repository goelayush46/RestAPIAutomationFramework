package api.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.UserPayload;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class UserTestDD {

	@Test(priority=1,dataProvider="AllData",dataProviderClass=DataProviders.class)
	public void TestCreateUser(String UserID, String username, String fname, String lname, String email,String pwd,String phone) {
		UserPayload userPayload=new UserPayload();
		userPayload.setId(Integer.parseInt(UserID));
		userPayload.setUsername(username);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(email);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phone);
		
		Response response=UserEndPoints.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=3,dataProvider="UserNameData",dataProviderClass=DataProviders.class)
	public void TestDeleteUser(String userName)
	{
		Response response=UserEndPoints.deleteUser(userName);
		System.out.println("Delete User Data");
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	@Test(priority=2,dataProvider="UserNameData",dataProviderClass=DataProviders.class)
	public void TestGetUserData(String userName)
	{
		Response response=UserEndPoints.getUser(userName);
		System.out.println("Read User Data");
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
