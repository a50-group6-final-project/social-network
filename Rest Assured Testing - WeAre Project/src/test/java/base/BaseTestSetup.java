package base;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Random;

import static Utils.Endpoints.*;
import static Utils.JSONRequests.REGISTRATION_BODY;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class BaseTestSetup {
    public static HashSet<String> usedUsernames = new HashSet<>();
    public static HashSet<String> usedEmails = new HashSet<>();
    public static Cookies cookies;
    public static String currentUsername;
    public static String currentEmail;
    public static int currentUserId;
    private static Faker faker = new Faker();
    private static Random random = new Random();

    public static String generateUniqueUsername() {
        String username;
        do {
            username = faker.regexify("[a-zA-Z]{2,20}");
        } while (usedUsernames.contains(username));
        usedUsernames.add(username);
        return username;
    }

    public static String generateUniqueEmail() {
        String email;
        do {
            int number = random.nextInt(1000);
            email = "testaccount." + number + "@abv.bg";
        } while (usedEmails.contains(email));
        usedEmails.add(email);
        return email;
    }


    public static void isResponse200(Response response) {
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, "Incorrect status code.");
        if (statusCode == SC_OK) {
            System.out.println("The response is 200");
        }
    }



    @BeforeClass
    public void registerAndAuthenticate() {


        currentUsername = generateUniqueUsername();
        currentEmail = generateUniqueEmail();

        RestAssured.baseURI = BASE_URL;
        String body = REGISTRATION_BODY(currentUsername, currentEmail);

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


    @BeforeMethod
    public void authenticateAndFetchCookies() {
        RestAssured.baseURI = BASE_URL;

        Response response = RestAssured.given()
                .contentType("multipart/form-data")
                .multiPart("username", currentUsername)
                .multiPart("password", "Project.10")
                .when()
                .post(AUTHENTICATE_ENDPOINT);

        cookies = response.detailedCookies();
        int statusCodeAuthentication = response.getStatusCode();
        System.out.println("The status code is:" + statusCodeAuthentication);
    }

}