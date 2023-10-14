package api;

import Utils.Serializer;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import models.Page;
import models.SearchUser;
import models.UserPersonal;
import models.UserRegister;

import static Utils.Constants.APPLICATION_JSON;
import static Utils.Endpoints.*;

public class UserController {

    public static Response registerUser(UserRegister user){
         String bodyUserString = Serializer.convertObjectToJsonString(user);

        return RestAssured.given().baseUri(BASE_URL)
                .contentType("application/json")
                .body(bodyUserString)
                .when()
                .post(USERS_ENDPOINT)
                .then().log().body().extract().response();
    }

    public static Response getUsers(Page page){
        String bodyPageString = Serializer.convertObjectToJsonString(page);

        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body(bodyPageString)
                .when()
                .post(GET_USERS_BY_NAME_ENDPOINT);
    }
    public static Response getUserByName(Cookies cookies, SearchUser user){
        String bodyGetUserByName = Serializer.convertObjectToJsonString(user);
        return RestAssured.given()
                .cookies(cookies)
                .contentType(APPLICATION_JSON)
                .body(bodyGetUserByName)
                .when()
                .post(GET_USERS_BY_NAME_ENDPOINT);
    }
    public static Response updatePersonalProfile(Cookies cookies, UserPersonal userPersonal, int currentUserId){
        String bodyUpdatedPersonalProfileString = Serializer.convertObjectToJsonString(userPersonal);
        return RestAssured.given()
                .cookies(cookies)
                .contentType("application/json")
                .pathParam("currentUserId", currentUserId)
                .body(bodyUpdatedPersonalProfileString)
                .when()
                .post("/api/users/auth/{currentUserId}/personal");
    }

    public static Response getUserById(String currentUsername, int currentUserId){
        return RestAssured.given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .queryParam("principal", currentUsername)
                .when()
                .get("/api/users/auth/" + currentUserId);
    }

    public static Cookies authenticatedAndFetchCookies(){
        RestAssured.baseURI = BASE_URL;
        Response response = RestAssured.given()
                .contentType("multipart/form-data")
                .multiPart("username", "currentUsername")
                .multiPart("password", "Project.10")
                .when()
                .post(AUTHENTICATE_ENDPOINT);

        return response.detailedCookies();
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

    public static Response autheticated(){
        return null;
    }
}
