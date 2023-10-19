package api;

import utils.Serializer;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import models.SendRequest;

import static utils.Constants.APPLICATION_JSON;

public class ConnectionController {

    public static Response sendRequest(SendRequest sendRequestToUser, Cookies cookies, String senderUsername) {
        String bodySendRequest = Serializer.convertObjectToJsonString(sendRequestToUser);

        return RestAssured.given()
                .cookies(cookies)
                .header("name", senderUsername)
                .contentType(APPLICATION_JSON)
                .body(bodySendRequest)
                .when()
                .post("/api/auth/request");
    }

    public static Response getUserRequests(Cookies cookies, int receiverUserId){
        return RestAssured.given()
                .cookies(cookies)
                .pathParam("receiverUserId", receiverUserId)
                .when()
                .get("/api/auth/users/{receiverUserId}/request/");
    }

    public static Response approveRequest(Cookies cookies, int receiverUserId, int requestId){
        return  RestAssured.given()
                .cookies(cookies)
                .pathParam("receiverUserId", receiverUserId)
                .queryParam("requestId", requestId)
                .when()
                .post("/api/auth/users/{receiverUserId}/request/");
    }
}
