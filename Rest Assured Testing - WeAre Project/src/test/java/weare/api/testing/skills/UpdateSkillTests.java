package weare.api.testing.skills;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Skill;
import org.testng.annotations.Test;

import static Utils.Endpoints.*;

public class UpdateSkillTests extends BaseTestSetup{


    @Test
    public void updateSkillSuccessfully(){
        String cookie = getJSESSIONIDCookie("MrTestThree", "Project.10");
        System.out.println("UPDATE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Response response = RestAssured.given().baseUri(BASE_URL)
                .cookie("JSESSIONID", cookie)
                .queryParam("skill", createdSkill.skill + " Updated")
                .queryParam("skillId", createdSkill.skillId)
                .when()
                .put(SKILL_UPDATE_ENDPOINT)
                .then().log().body().extract().response();

        isResponse200(response);
    }
}
