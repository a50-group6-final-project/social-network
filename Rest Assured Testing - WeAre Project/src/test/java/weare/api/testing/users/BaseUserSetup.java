package weare.api.testing.users;

import base.BaseTestSetup;
import models.Category;
import models.UserPersonal;
import models.UserProfile;
import models.UserRegister;
import org.testng.annotations.BeforeClass;
import weare.api.testing.skills.CreateSkillTests;

import java.security.SecureRandom;

public class BaseUserSetup extends BaseTestSetup {

    public UserRegister userToRegister = generateUserRegisterModel();
    public UserPersonal userPersonal;
    public UserProfile userProfile;
    public UserProfile[] userProfileList;

    @BeforeClass
    public void setup() {
        if (currentUsername == null) {
            currentUsername = userToRegister.username;
        }
        if (currentEmail == null) {
            currentEmail = userToRegister.email;
        }

        if (JSESSIONID == null) {
            JSESSIONID = getJSESSIONIDCookie(currentUsername, "Project.10");
        }
    }

    private static UserRegister generateUserRegisterModel() {
        UserRegister userRegister = new UserRegister();
        userRegister.email = generateUniqueEmail();
        userRegister.username = "MrTest" + generateString(5);
        userRegister.password = "Project.10";
        userRegister.confirmPassword = "Project.10";

        Category category = new Category();
        category.id = 100;
        category.name = "All";
        userRegister.category = category;
        return userRegister;
    }

    private static String generateString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            result.append(characters.charAt(randomIndex));
        }
        return result.toString();
    }

}
