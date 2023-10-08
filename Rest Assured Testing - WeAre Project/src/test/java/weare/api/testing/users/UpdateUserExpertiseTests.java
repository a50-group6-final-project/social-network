package weare.api.testing.users;

import Utils.Serializer;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Utils.Endpoints.BASE_URL;
import static Utils.Endpoints.USERS_ENDPOINT;

public class UpdateUserExpertiseTests extends BaseTestSetup {

    @Test
    public void updateUserExpertise_Successful() {

        //update currentUserProfile.expertiseProfile
        currentUserProfile.expertiseProfile.category.id = 157;
        currentUserProfile.expertiseProfile.category.name = "Marketing Updated";
        currentUserProfile.expertiseProfile.availability = 33.66;

        currentUserProfile.expertiseProfile.skills.add(createdSkill);

        String bodyExpertiseProfileString = Serializer.convertObjectToJsonString(currentUserProfile.expertiseProfile);

        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .cookie("JSESSIONID", getJSESSIONIDCookie(currentUsername, "Project.10"))
                .body(bodyExpertiseProfileString)
                .when()
//                .put("/api/users/expertise/" + currentUserId);
                .post("/api/users/auth/" + currentUserId + "/expertise");

            System.out.println(response.asString());
            isResponse200(response);

    }
}
