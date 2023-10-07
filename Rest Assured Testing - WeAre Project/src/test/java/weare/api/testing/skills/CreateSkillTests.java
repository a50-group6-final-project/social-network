package weare.api.testing.skills;

import Utils.Serializer;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Category;
import models.Skill;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utils.Endpoints.*;

public class CreateSkillTests extends BaseTestSetup {

    @Test
    public void createSkillSuccessfully() {
        skillToCreated = generateSkillModel();
        String body = Serializer.convertObjectToJsonString(skillToCreated);

        Response response = RestAssured.given().baseUri(BASE_URL)
                .cookie("JSESSIONID", getJSESSIONIDCookie("MrTestThree", "Project.10"))
                .contentType("application/json")
                .body(body)
                .when()
                .post(SKILL_CREATE_ENDPOINT)
                .then().log().body().extract().response();

        isResponse200(response);

        createdSkill = response.as(Skill.class);

        Assert.assertEquals(createdSkill.skill, skillToCreated.skill);
        Assert.assertEquals(createdSkill.category.id, skillToCreated.category.id);
    }

    public static Skill generateSkillModel() {
        Category category = new Category();
        category.id = 155;

        Skill skill = new Skill();
        skill.category = category;
        skill.skill = "Test Skill" + System.currentTimeMillis();
        skill.skillId = 0;
        return skill;
    }
}
