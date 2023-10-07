package weare.api.testing.skills;

import Utils.Serializer;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Skill;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utils.Endpoints.BASE_URL;
import static Utils.Endpoints.SKILL_GET_ONE_ENDPOINT;

public class GetOneSkillTests extends BaseTestSetup {

    @Test
    public void getOneSkillByIdSuccessfully() {
        Response response = RestAssured.given().baseUri(BASE_URL)
                .cookie("JSESSIONID", getJSESSIONIDCookie("MrTestThree", "Project.10"))
                .queryParam("skillId", createdSkill.skillId)
                .when()
                .get(SKILL_GET_ONE_ENDPOINT)
                .then().log().body().extract().response();

        isResponse200(response);

        createdSkill = response.as(Skill.class);
        Assert.assertEquals(createdSkill.skill, skillToCreated.skill);
        Assert.assertEquals(createdSkill.category.id, skillToCreated.category.id);
    }


}
