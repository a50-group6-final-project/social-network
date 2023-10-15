package api;

import Utils.Serializer;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import models.*;

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
    public  static Response updateExpertiseProfile(Cookies cookies, ExpertiseProfile expertiseProfile, int currentUserId){
        String bodyExpertiseProfileString = Serializer.convertObjectToJsonString(expertiseProfile);
        return RestAssured.given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .cookies(cookies)
                .body(bodyExpertiseProfileString)
                .when()
//                .put("/api/users/expertise/" + currentUserId);
                .post(UPDATE_ENDPOINT + currentUserId + "/expertise");
    }

    public static Response getUserById(String currentUsername, int currentUserId){
        return RestAssured.given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .queryParam("principal", currentUsername)
                .when()
                .get("/api/users/auth/" + currentUserId);
    }

    public static Response getProfilePosts(Cookies cookies, int currentUserId){
        Page page = new Page();
        page.size = 10;
        String bodyPageString = Serializer.convertObjectToJsonString(page);
        return RestAssured.given()
                .baseUri(BASE_URL)
                .cookies(cookies)
                .contentType("application/json")
                .pathParam("currentUserId", currentUserId)
                .body(bodyPageString)
                .when()
                .get("http://localhost:8081/api/users/{currentUserId}/posts");
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
}
