package weare.api.testing.skills;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Utils.Endpoints.*;

public class DeleteSkillTests  extends BaseTestSetup{

    @Test
    public void deleteSkillSuccessfully(){
        Response response = RestAssured.given().baseUri(BASE_URL)
                .queryParam("skillId", createdSkill.skillId)
                .when()
                .put(SKILL_DELETE_ENDPOINT)
                .then().log().body().extract().response();

        isResponse200(response);
    }
}
