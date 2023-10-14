package weare.api.testing.users;

import Utils.DataGenerator;
import Utils.ModelGenerator;
import base.BaseTestSetup;
import models.Category;
import models.UserPersonal;
import models.UserProfile;
import models.UserRegister;
import org.testng.annotations.BeforeClass;

import java.security.SecureRandom;

public class BaseUserSetup extends BaseTestSetup {

    public UserPersonal userPersonal;
    public UserProfile userProfile;
    public UserProfile[] userProfileList;
    public UserPersonal currentUserPersonalProfile;

    @BeforeClass
    public void setupClass() throws InterruptedException {
        if(userToRegister == null){
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
