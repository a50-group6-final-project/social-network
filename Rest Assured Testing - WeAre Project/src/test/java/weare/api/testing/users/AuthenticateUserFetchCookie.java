package weare.api.testing.users;

import Utils.ModelGenerator;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Utils.Constants.*;
import static Utils.Endpoints.AUTHENTICATE_ENDPOINT;
import static Utils.Endpoints.BASE_URL;

public class AuthenticateUserFetchCookie extends BaseUserSetup {


    @BeforeClass
    public void setup() {
        if (isRegistered == false) {
            userToRegister = ModelGenerator.generateUserRegisterModel();
            register(userToRegister);
            isRegistered = true;
        }
    }

    @Test
    public void CookiesFetched_When_AuthenticateAndFetchCookies() {
        RestAssured.baseURI = BASE_URL;
        Response response = RestAssured.given()
                .contentType(CONTENT_TYPE_MULTIPART_FORM_DATA)
                .multiPart(FIELD_USERNAME, userToRegister.username)
                .multiPart(FIELD_PASSWORD, userToRegister.password)
                .when()
                .post(AUTHENTICATE_ENDPOINT);

        cookies = response.detailedCookies();
        int statusCodeAuthentication = response.getStatusCode();
        Assert.assertEquals(statusCodeAuthentication, 302);
        System.out.println(String.format(STATUS_CODE_MESSAGE_FORMAT, statusCodeAuthentication));
    }
}





