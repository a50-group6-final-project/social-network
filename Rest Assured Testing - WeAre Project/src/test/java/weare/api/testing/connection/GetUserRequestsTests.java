package weare.api.testing.connection;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.ApproveRequest;
import org.testng.annotations.Test;
import static Utils.Endpoints.BASE_URL;

public class GetUserRequestsTests extends BaseTestSetup {

    @Test
    public void getUserRequestTest() {


        authenticateAndFetchCookies(receiverUsername, "Password.10");
        RestAssured.baseURI = BASE_URL;

        Response response = RestAssured.given()
                .cookies(cookies)
                .pathParam("receiverUserId", receiverUserId)
                .when()
                .get("/api/auth/users/{receiverUserId}/request/");

//         approveRequest=response.as(ApproveRequest.class);
        JsonPath jsonPath = response.jsonPath();
        int idRequest = jsonPath.getInt("[0].id");
        System.out.println("The ID is: " + idRequest);

        System.out.println("The ID is: " + idRequest);

        String responseBody = response.getBody().asString();
        isResponse200(response);
        System.out.println("The response body is: " + responseBody);
    }

}
