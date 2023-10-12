package weare.api.testing.skills;

import Utils.ModelGenerator;
import Utils.Serializer;
import api.SkillController;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Skill;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Utils.Endpoints.BASE_URL;
import static Utils.Endpoints.SKILL_GET_ONE_ENDPOINT;

public class GetOneSkillTests extends BaseTestSetup {

    @BeforeClass
    public void setup() {
        if(!isRegistered){

            currentUsername = generateUniqueUsername();
            currentEmail = generateUniqueEmail();
            register(currentUsername, currentEmail);
            authenticateAndFetchCookies(currentUsername, currentEmail);

            isRegistered = true;
        }

        skillToCreated = ModelGenerator.generateSkillModel(155);
        createdSkill = SkillController.createSkill(cookies, skillToCreated).as(Skill.class);
    }
    @Test
    public void getOneSkillByIdSuccessfully() {
        Response response = SkillController.getOneSkillById(cookies, createdSkill.skillId);

        isResponse200(response);

        createdSkill = response.as(Skill.class);
        Assert.assertEquals(createdSkill.skill, skillToCreated.skill);
        Assert.assertEquals(createdSkill.category.id, skillToCreated.category.id);
    }

    @AfterClass
    public void tearDown() {
        Response response = SkillController.deleteSkill(createdSkill.skillId);
        isResponse200(response);
    }
}
