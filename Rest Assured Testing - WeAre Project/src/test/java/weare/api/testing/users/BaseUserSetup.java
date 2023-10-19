package weare.api.testing.users;

import utils.ModelGenerator;
import base.BaseTestSetup;
import models.UserPersonal;
import models.UserProfile;
import org.testng.annotations.BeforeClass;

public class BaseUserSetup extends BaseTestSetup {

    public UserPersonal userPersonal;
    public UserProfile userProfile;
    public UserProfile[] userProfileList;
    public UserPersonal currentUserPersonalProfile;

    @BeforeClass
    public void setupClass() {
        if (userToRegister == null) {
            userToRegister = ModelGenerator.generateUserRegisterModel();
        }
        if (currentUsername == null) {
            currentUsername = userToRegister.username;
        }
        if (currentEmail == null) {
            currentEmail = userToRegister.email;
        }
    }
}
