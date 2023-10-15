package weare.api.testing.users;

import Utils.ModelGenerator;
import api.UserController;
import io.restassured.response.Response;
import models.Page;
import models.UserProfile;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetUsersTests extends BaseUserSetup {


    @Test()
    public void getUsers_Successful() {
        Page page = ModelGenerator.generatePageModel(1500);
        Response response = UserController.getUsers(page);

        isResponse200(response);

        userProfileList = response.then().extract().as(UserProfile[].class);
        Assert.assertTrue(userProfileList.length > 0, "Users list is empty");
        Assert.assertTrue(assertUserIsPresented(userProfileList, currentUsername));

        userProfile = returnUserProfile(userProfileList, currentUsername);
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
}
