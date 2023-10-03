package weare.api.testing;

import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Utils.Endpoints.AUTHENTICATE_ENDPOINT;
import static Utils.Endpoints.BASE_URL;
import static base.BaseTestSetup.AssertResponse;

public class AuthenticateUserFetchCookie {

    String jSessionIdValue;

    @Test
    public void authenticateAndFetchCookies() {
        RestAssured.baseURI = BASE_URL;

        Response response = RestAssured.given()
                .contentType("multipart/form-data")
                .multiPart("username", "Grandma")
                .multiPart("password", "Project.10")
                .post(AUTHENTICATE_ENDPOINT);

   //I managed to fetch JSESSION but the status code is 302
        Cookies cookies = response.detailedCookies();
        System.out.println(cookies);
        Cookie jSessionIdCookie = cookies.get("JSESSIONID");
        jSessionIdValue = jSessionIdCookie.getValue();

        AssertResponse(response);

        System.out.println(response.getBody().asPrettyString());
    }
}





