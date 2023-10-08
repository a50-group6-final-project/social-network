package weare.api.testing.users;

import Utils.Serializer;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Category;
import models.UserRegister;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.security.SecureRandom;

import static Utils.Endpoints.*;

public class RegisterUserTests extends BaseTestSetup {
    @Test
    public void registerUserSuccessfully () {
        UserRegister userRegister = generateUserRegisterModel();
        String bodyUserString = Serializer.convertObjectToJsonString(userRegister);

        Response response = RestAssured.given().baseUri(BASE_URL)
                .contentType("application/json")
                .body(bodyUserString)
                .when()
                .post(USERS_ENDPOINT)
                .then().log().body().extract().response();

        isResponse200(response);
        currentUsername = userRegister.username;

        String[] responseString = response.asString().split(" ");
        Assert.assertEquals(responseString[3], currentUsername);

        currentEmail = userRegister.email;
        currentUserId = Integer.parseInt(responseString[6]);
        System.out.println("Registered successfully!");
    }

    private UserRegister generateUserRegisterModel() {
        UserRegister userRegister = new UserRegister();
        userRegister.email = generateUniqueEmail();
        userRegister.username = "MrTest" + generateString(5);
        userRegister.password = "Project.10";
        userRegister.confirmPassword = "Project.10";

        Category category = new Category();
        category.id = 100;
        category.name = "All";
        userRegister.category = category;
        return userRegister;
    }


    private String generateString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            result.append(characters.charAt(randomIndex));
        }
        return result.toString();
    }

}
