package base;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import models.*;
import org.testng.annotations.BeforeSuite;

import java.util.HashSet;
import java.util.Random;

import static Utils.Endpoints.*;
import static Utils.JSONRequests.REGISTRATION_BODY_TEMPLATE;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class BaseTestSetup {
    public static HashSet<String> usedUsernames = new HashSet<>();
    public static HashSet<String> usedEmails = new HashSet<>();
    public static Cookies cookies;
    public static Cookies cookiesSender;
    public static String currentUsername;
    public static String currentEmail;
    public static int currentUserId;
    public static int postId;
    public static int userId;
    public static int commentId;

    public static int idRequest ;
    public static String senderUsername;
    public static String receiverUsername;
    public static String receiverEmail;
    public static int senderUserId;
    public static String postCreatorUsername;
    public static int receiverUserId;
    public static EditPost editPost;
    //public static EditC editPost;

    public static Boolean isRegistered = false;

    public static ApproveRequest approveRequest;
    public static SendRequest sendRequestToUser;
    public static PostModel createPost;
    public static CommentModel createComment;
    public static Skill skillToCreated;
    public static Skill createdSkill;
    public static String JSESSIONID;
    public static Page page;
    public static UserProfile currentUserProfile;
    public static UserPersonal currentUserPersonalProfile;

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

    public static String generateUniqueContentPost() {
        return faker.lorem().characters(10, 50);
    }

    public static void isResponse200(Response response) {
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, "Incorrect status code.");
        if (statusCode == SC_OK) {
            System.out.println("The response is 200");
        }
    }

    @BeforeSuite
    public void setup() {
        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);

        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);
    }


    public void register(String username, String email) {

        currentUsername = username;
        currentEmail = email;

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


    public void authenticateAndFetchCookies(String username, String password) {
        RestAssured.baseURI = BASE_URL;

        Response response = RestAssured.given()
                .contentType("multipart/form-data")
                .multiPart("username", username)
                .multiPart("password", "Project.10")
                .when()
                .post(AUTHENTICATE_ENDPOINT);

        cookies = response.detailedCookies();
        int statusCodeAuthentication = response.getStatusCode();
        System.out.println("The status code is:" + statusCodeAuthentication);
    }

    public String getJSESSIONIDCookie(String username, String password) {
        try {
            String JSESSIONID = RestAssured.given()
                    .baseUri(BASE_URL)
                    .contentType("multipart/form-data")
                    .multiPart("username", username)
                    .multiPart("password", password)
                    .when()
                    .post(AUTHENTICATE_ENDPOINT)
                    .getCookie("JSESSIONID");
            return JSESSIONID;
        } catch (Exception e) {
            throw new RuntimeException("Could not authenticate user");
        }
    }


}