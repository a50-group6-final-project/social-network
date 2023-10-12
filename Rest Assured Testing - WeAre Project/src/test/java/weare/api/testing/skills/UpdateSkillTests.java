package weare.api.testing.skills;

import Utils.ModelGenerator;
import api.SkillController;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Skill;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Utils.Endpoints.*;

public class UpdateSkillTests extends BaseTestSetup {

    @BeforeClass
    public void setup() {
        if (!isRegistered) {

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
    public void updateSkillSuccessfully() {
        Response response = SkillController.updateOneSkill(cookies, createdSkill);
        isResponse200(response);
    }

    @AfterClass
    public void tearDown() {
        Response response = SkillController.deleteSkill(createdSkill.skillId);
        isResponse200(response);
    }
}
