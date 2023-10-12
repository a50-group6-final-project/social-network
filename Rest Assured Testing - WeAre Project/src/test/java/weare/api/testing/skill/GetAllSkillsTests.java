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

public class GetAllSkillsTests extends BaseTestSetup {

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
    public void newlyCreatedSkill_returned_whenGetAllSkills() {

        Response response = SkillController.getAllSkills(cookies);
        isResponse200(response);
        Skill[] skillsList = response.as(Skill[].class);
        Assert.assertTrue(skillsList.length > 0, "Skills list is empty");
        Assert.assertTrue(assertCreatedSkillIdIsPresent(skillsList, createdSkill.skillId));

    }

    @AfterClass
    public void tearDown() {
        Response response = SkillController.deleteSkill(createdSkill.skillId);
        isResponse200(response);
    }

    private boolean assertCreatedSkillIdIsPresent(Skill[] skillsList, int id) {
        for (Skill skill : skillsList) {
            if (skill.skillId == id) {
                return true;
            }
        }
        Assert.fail("Skill with id " + id + " was not found");
        return false;
    }
}

