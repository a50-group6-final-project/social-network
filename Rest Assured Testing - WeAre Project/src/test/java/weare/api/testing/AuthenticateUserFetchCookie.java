package weare.api.testing;

import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Utils.Endpoints.AUTHENTICATE_ENDPOINT;
import static Utils.Endpoints.BASE_URL;

public class AuthenticateUserFetchCookie extends BaseTestSetup {


    @Test
    public void authenticateAndFetchCookies() {
        RestAssured.baseURI = BASE_URL;

        Response response = RestAssured.given()
                .contentType("multipart/form-data")
                .multiPart("username", "currentUsername")
                .multiPart("password", "Project.10")
                .when()
                .post(AUTHENTICATE_ENDPOINT);

        //I managed to fetch cookies but the status code is 302
        cookies = response.detailedCookies();
        int statusCodeAuthentication = response.getStatusCode();
        System.out.println("The status code is:" + statusCodeAuthentication);


        System.out.println(response.getBody().asPrettyString());
    }
}





