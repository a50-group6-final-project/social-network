package weare.api.testing.skill;

import Utils.ModelGenerator;
import api.SkillController;
import base.BaseTestSetup;
import io.restassured.response.Response;
import models.Skill;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateSkillTests extends BaseTestSetup {

    @BeforeClass
    public void setup() {

        if (!isRegistered) {
            currentUsername = generateUniqueUsername();
            currentEmail = generateUniqueEmail();
            register(currentUsername, currentEmail);
            authenticateAndFetchCookies(currentUsername, currentEmail);
            isRegistered = true;
        }
    }

    @Test
    public void createSkillSuccessfully() {
        skillToCreated = ModelGenerator.generateSkillModel(155);

        Response response = SkillController.createSkill(cookies, skillToCreated);
        isResponse200(response);

        createdSkill = response.as(Skill.class);
        Assert.assertEquals(createdSkill.skill, skillToCreated.skill);
        Assert.assertEquals(createdSkill.category.id, skillToCreated.category.id);
    }

    @AfterClass
    public void tearDown() {
        SkillController.deleteSkill(createdSkill.skillId);
    }
}
