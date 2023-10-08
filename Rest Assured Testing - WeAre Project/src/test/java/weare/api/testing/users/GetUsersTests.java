package weare.api.testing.users;

import Utils.Serializer;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Page;
import models.UserProfile;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utils.Endpoints.BASE_URL;
import static Utils.Endpoints.GET_USERS_BY_NAME_ENDPOINT;

public class GetUsersTests extends BaseTestSetup {


    @Test
    public void getUsers_Successful() {

        Page page = generatePageModel(1000);
        String bodyPageString = Serializer.convertObjectToJsonString(page);

        Response response = RestAssured
                .given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body(bodyPageString)
                .when()
                .post(GET_USERS_BY_NAME_ENDPOINT);

        //to create Page model
        isResponse200(response);

        UserProfile[] userProfileList = response.then().extract().as(UserProfile[].class);

        Assert.assertTrue(userProfileList.length > 0, "Users list is empty");
        Assert.assertTrue(assertUserIsPresented(userProfileList, currentUsername));

        UserProfile userProfile = returnUserProfile(userProfileList, currentUsername);
       //set currentUserProfile
        currentUserProfile = userProfile;

    }

    private boolean assertUserIsPresented(UserProfile[] userProfileList, String username) {
        for (UserProfile userProfile : userProfileList) {
            if (userProfile.username.equals(username)) {
                return true;
            }
        }
        Assert.fail("User with username " + username + " was not found");
        return false;
    }
    private UserProfile returnUserProfile(UserProfile[] userProfileList, String username) {
        for (UserProfile userProfile : userProfileList)
            if (userProfile.username.equals(username)) {
                return userProfile;
            }
        return null;
    }

    private Page generatePageModel(int size) {
        Page page = new Page();
        page.size = size;
        return page;
    }
}
