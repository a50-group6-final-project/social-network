package weare.api.testing.connection;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.ApproveRequest;
import org.testng.Assert;
import org.testng.annotations.Test;
import static Utils.Endpoints.BASE_URL;
import static Utils.Endpoints.RECEIVER_USER_ID_REQUEST_ENDPOINT;

public class GetUserRequestsTests extends BaseTestSetup {


    @Test
    public void getUserRequestTest() {


        authenticateAndFetchCookies(receiverUsername, "Password.10");
        RestAssured.baseURI = BASE_URL;

        Response response = RestAssured.given()
                .cookies(cookies)
                .pathParam("receiverUserId", receiverUserId)
                .when()
                .get(RECEIVER_USER_ID_REQUEST_ENDPOINT);

//
//        approveRequest=response.as(ApproveRequest.class);
//        Assert.assertNotNull(ApproveRequest.class);

        JsonPath jsonPath = response.jsonPath();
        int idRequest = jsonPath.getInt("[0].id");
        System.out.println("The ID is: " + idRequest);

        String responseBody = response.getBody().asString();
        isResponse200(response);
        System.out.println("The response body is: " + responseBody);
    }

}
