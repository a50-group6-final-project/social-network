package weare.testing;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PostTests extends BaseTestSetup {
    static NewPostPage newPostPage;
    @BeforeAll
    public static void setup() {
        String username = BaseTestSetup.generateRandomUsername(6);
        String password = BaseTestSetup.generateRandomPassword(10);
        String email = BaseTestSetup.generateRandomEmail();

        registerPage.userRegister(username, password, email);

        LoginPage.loginUser(username, password);
        LoginPage.assertElementPresent("//a[@href='/logout' and text()='LOGOUT']");
        newPostPage = new NewPostPage(driver);
    }

    @BeforeEach
    public void beforeEach(){
        newPostPage.navigateToPage();
    }
    @Test
    public void createPublicPost_withValidInput(){
        newPostPage.navigateToPage();
        newPostPage.createPost("Test Title", "Test Content", "Public");


    }

}
