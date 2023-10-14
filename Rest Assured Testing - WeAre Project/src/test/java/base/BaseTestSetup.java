package base;

import Utils.DataGenerator;
import Utils.ModelGenerator;
import api.UserController;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import models.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.util.HashSet;
import java.util.Random;

import static Utils.Endpoints.*;
import static Utils.JSONRequests.REGISTRATION_BODY_TEMPLATE;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class BaseTestSetup {

    public static Cookies cookies;
    public static Cookies cookiesSender;
    public static String currentUsername;
    public static String currentEmail;
    public static int currentUserId;
    public static int postId;
    public static int userId;
    public static int commentId;
    public static CommentModel createdComment;
    public static int idRequest ;
    public static String senderUsername;
    public static String receiverUsername;
    public static String receiverEmail;
    public static int senderUserId;
    public static String postCreatorUsername;
    public static int receiverUserId;
    public static PostModel createdPost;
    public static PostModel editPost;

    public static Boolean isRegistered = false;
    public static Boolean isRegisteredTwoUsers = false;
    public static Boolean isDeletedPost = true;
    public static UserRegister userToRegister;
    public static ApproveRequest approveRequest;
    public static SendRequest sendRequestToUser;
    public static PostModel createPost;
    public static CommentModel createComment;
    public static Boolean isCommentDeleted = true;
    public static Skill skillToCreated;
    public static Skill createdSkill;
    public static String JSESSIONID;
    public static Page page;
    public static UserProfile currentUserProfile;
    public static UserPersonal currentUserPersonalProfile;



    public static void isResponse200(Response response) {
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, SC_OK, "Incorrect status code.");
        if (statusCode == SC_OK) {
            System.out.println("The response is 200");
        }
    }

    @BeforeClass
    public void setup() throws InterruptedException {
        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);

        if(userToRegister == null){
            userToRegister = ModelGenerator.generateUserRegisterModel();
        }
    }


    public void register(UserRegister userToRegister) {
        currentUsername = userToRegister.username;
        currentEmail = userToRegister.email;
        Response response = UserController.registerUser(userToRegister);
        authenticateAndFetchCookies(currentUsername, currentEmail);

        isResponse200(response);
        isRegistered = true;
        String[] responseString = response.asString().split(" ");
        Assert.assertEquals(responseString[3], currentUsername);

        currentUserId = Integer.parseInt(responseString[6]);
        System.out.println("Registered successfully!");
        System.out.println("Username: " + currentUsername);
        System.out.println("ID: " + currentUserId);
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