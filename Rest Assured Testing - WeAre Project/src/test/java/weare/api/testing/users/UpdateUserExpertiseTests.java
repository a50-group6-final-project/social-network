package weare.api.testing.users;

import Utils.Serializer;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Category;
import models.ExpertiseProfile;
import models.Skill;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static Utils.Endpoints.BASE_URL;
import static Utils.Endpoints.UPDATE_ENDPOINT;

public class UpdateUserExpertiseTests extends BaseUserSetup {

    @Test
    public void updateUserExpertise_Successful() {
        int categoryId = 157;
        String categoryName = "Marketing Updated";
        double availability = 33.66;
        ExpertiseProfile update = updateExpertiseProfile(categoryId, categoryName, availability);

        String bodyExpertiseProfileString = Serializer.convertObjectToJsonString(update);

        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .cookie("JSESSIONID", getJSESSIONIDCookie(currentUsername, "Project.10"))
                .body(bodyExpertiseProfileString)
                .when()
                .post(UPDATE_ENDPOINT + currentUserId + "/expertise");

        isResponse200(response);


        currentUserProfile.expertiseProfile = response.as(ExpertiseProfile.class);
        Assert.assertEquals(currentUserProfile.expertiseProfile.category.id, categoryId, "Category id is not updated!");
        Assert.assertEquals(currentUserProfile.expertiseProfile.category.name, categoryName, "Category name is not updated!");
        Assert.assertEquals(currentUserProfile.expertiseProfile.availability, availability, "Availability is not updated!");
    }


    private ExpertiseProfile updateExpertiseProfile(int categoryId, String categoryName, double availability, Skill[] skills) {
        ExpertiseProfile expertiseProfile = new ExpertiseProfile();
        expertiseProfile.category = new Category();
        expertiseProfile.category.id = categoryId;
        expertiseProfile.category.name = categoryName;
        expertiseProfile.availability = availability;
        expertiseProfile.skills.addAll(List.of(skills));

        return expertiseProfile;
    }
    private ExpertiseProfile updateExpertiseProfile(int categoryId, String categoryName, double availability) {
        ExpertiseProfile expertiseProfile = new ExpertiseProfile();
        expertiseProfile.category = new Category();
        expertiseProfile.category.id = categoryId;
        expertiseProfile.category.name = categoryName;
        expertiseProfile.availability = availability;

        return expertiseProfile;
    }
}
