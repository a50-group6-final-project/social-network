package weare.api.testing.users;

import Utils.Serializer;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import weare.api.testing.skills.CreateSkillTests;

import static Utils.Endpoints.BASE_URL;
import static Utils.Endpoints.UPDATE_ENDPOINT;

public class UpdateUserExpertiseTests extends BaseUserSetup {

    @Test
    public void updateUserExpertise_Successful() {
        System.out.println("STOP");
        //update currentUserProfile.expertiseProfile
        currentUserProfile.expertiseProfile.category.id = 157;
        currentUserProfile.expertiseProfile.category.name = "Marketing Updated";
        currentUserProfile.expertiseProfile.availability = 33.66;

        String bodyExpertiseProfileString = Serializer.convertObjectToJsonString(currentUserProfile.expertiseProfile);

        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .cookie("JSESSIONID", getJSESSIONIDCookie(currentUsername, "Project.10"))
                .body(bodyExpertiseProfileString)
                .when()
//                .put("/api/users/expertise/" + currentUserId);
                .post(UPDATE_ENDPOINT + currentUserId + "/expertise");

            System.out.println(response.asString());
            isResponse200(response);

    }
}
