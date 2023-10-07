package weare.api.testing.users;

import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Utils.Endpoints.BASE_URL;
import static Utils.Endpoints.USERS_ENDPOINT;
import static Utils.JSONRequests.REGISTRATION_BODY_TEMPLATE;


public class RegistrationTest extends BaseTestSetup {


    @Test
    public void registerUser_successful() {



        currentUsername = generateUniqueUsername();
        currentEmail = generateUniqueEmail();

        RestAssured.baseURI = BASE_URL;
        String body = String.format(REGISTRATION_BODY_TEMPLATE, currentEmail, currentUsername);

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(body)
                .when()
                .post(USERS_ENDPOINT);

        System.out.println(response.asString());
        isResponse200(response);

        String responseString = response.getBody().asString();

        int nameStartIndex = responseString.indexOf("name ") + 5;
        int nameEndIndex = responseString.indexOf(" and id");
        String username = responseString.substring(nameStartIndex, nameEndIndex);

        int idStartIndex = responseString.indexOf("id ") + 3;
        int idEndIndex = responseString.indexOf(" was created");
        String idString = responseString.substring(idStartIndex, idEndIndex);
        int id = Integer.parseInt(idString);
        currentUsername = responseString.substring(nameStartIndex, nameEndIndex);
        currentUserId = Integer.parseInt(idString);

        System.out.println("Registered successfully!");

        System.out.println("Username: " + username);
        System.out.println("ID: " + id);

    }

}








