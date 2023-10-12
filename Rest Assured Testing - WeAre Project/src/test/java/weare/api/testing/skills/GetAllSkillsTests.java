package weare.api.testing.skills;

import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Skill;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utils.Endpoints.BASE_URL;
import static Utils.Endpoints.SKILL_ENDPOINT;

public class GetAllSkillsTests extends BaseTestSetup {

    @Test
    public void newlyCreatedSkill_returned_whenGetAllSkills() {
        String cookie = getJSESSIONIDCookie("MrTestThree", "Project.10");

        Response response = RestAssured.given().baseUri(BASE_URL)
                .cookie("JSESSIONID", cookie)
                .queryParam("sorted", "true")
                .when()
                .get(SKILL_ENDPOINT)
                .then().extract().response();

        isResponse200(response);

        Skill[] skillsList = response.as(Skill[].class);

        Assert.assertTrue(skillsList.length > 0, "Skills list is empty");
        Assert.assertTrue(assertCreatedSkillIdIsPresent(skillsList, createdSkill.skillId));

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
