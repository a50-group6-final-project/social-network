package weare.api.testing.users;

import Utils.Serializer;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.UserRegister;
import org.testng.annotations.Test;
import static Utils.Endpoints.BASE_URL;
import static Utils.Endpoints.USERS_ENDPOINT;
import static Utils.JSONRequests.REGISTRATION_BODY_TEMPLATE;


public class RegistrationTest extends BaseTestSetup {


    @Test
    public void registerUser_successful() {

        currentUsername = generateUniqueUsername();
        currentEmail = generateUniqueEmail();
        UserRegister userRegisterBody = new UserRegister();

        userRegisterBody.username=currentUsername;
        userRegisterBody.email=currentEmail;
        userRegisterBody.password="Project.10";
        userRegisterBody.confirmPassword="Project.10";

        RestAssured.baseURI = BASE_URL;

        String bodyRegistration = Serializer.convertObjectToJsonString(userRegisterBody);

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(bodyRegistration)
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








