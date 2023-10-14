package weare.testing;

import com.github.javafaker.Faker;
import com.telerikacademy.testframework.CustomWebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

public class BaseTestSetup {
    public static RegisterPage registerPage;
    public static WebDriver driver;

    public static String generatedUsername;
    public static String generatedPassword;
    public static String generatedEmail;

    private static Faker faker = new Faker();

    @BeforeAll
    public static void setUp() {
        driver = CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver();
        registerPage = new RegisterPage(driver);


    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    public static void registerNewUser(int usernameLength) {
        generatedUsername = faker.regexify("[a-zA-Z]{" + usernameLength + "}");
        registerPage.userRegister(generatedUsername, generatedPassword, generatedEmail);
    }
    public static String generateRandomUsername(int length) {
        generatedUsername = faker.regexify("[a-zA-Z]{" + length + "}");
        return generatedUsername;
    }
    public static String generateRandomPassword() {
        generatedPassword = faker.internet().password(8, 14, true, true) + faker.number().digit() + faker.regexify("[!@#$%^&*()]");
        return generatedPassword;
    }

    public static String generateRandomEmail() {
        generatedEmail = faker.internet().emailAddress();
        return generatedEmail;
    }

}


