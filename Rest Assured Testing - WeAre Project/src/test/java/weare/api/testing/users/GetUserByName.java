package weare.api.testing.users;

import Utils.Serializer;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.SearchUser;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utils.Constants.APPLICATION_JSON;
import static Utils.Endpoints.BASE_URL;
import static Utils.Endpoints.GET_USERS_BY_NAME_ENDPOINT;
import static Utils.JSONRequests.GET_USER_BY_NAME_BODY;
import static org.testng.Assert.assertEquals;

public class GetUserByName extends BaseTestSetup {


    @Test
    public void getUserByName_Successful() {

        SearchUser byName=new SearchUser();
        byName.index=0;
        byName.next=true;
        byName.searchParam1="";
        byName.searchParam2="Lili Ivanova";
        byName.size=1;

        String bodyGetUserByName = Serializer.convertObjectToJsonString(byName);

        currentUsername= generateUniqueUsername();
        currentEmail = generateUniqueEmail();

        register(currentUsername, currentEmail);

        authenticateAndFetchCookies(currentUsername, "Project.10");
        RestAssured.baseURI = BASE_URL;


        Response response = RestAssured.given()
                .cookies(cookies)
                .contentType(APPLICATION_JSON)
                .body(bodyGetUserByName)
                .when()
                .post(GET_USERS_BY_NAME_ENDPOINT);

        System.out.println(response.asString());

       isResponse200(response);

        String username = response.jsonPath().getString("[0].username");
        int userId = response.jsonPath().getInt("[0].userId");

        Assert.assertEquals(username, "Bo", "Username does not match.");
        Assert.assertEquals(userId, 779, "UserId does not match.");


    }
}