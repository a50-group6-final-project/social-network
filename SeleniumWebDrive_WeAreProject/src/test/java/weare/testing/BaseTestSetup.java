package weare.testing;

import com.github.javafaker.Faker;
import com.telerikacademy.testframework.CustomWebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

public class BaseTestSetup {
    protected static RegisterPage registerPage;
    protected static WebDriver driver;
    protected static AdminPage adminPage;

    protected static LoginPage loginPage;
    protected static String generatedUsername;
    protected static String generatedPassword;
    protected static String generatedEmail;
    protected static String generatedAdminUsername;
    protected static String generatedLetterPassword;

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


    public static String generateRandomUsername(int length) {
        generatedUsername = faker.regexify("[a-zA-Z]{" + length + "}");
        return generatedUsername;
    }

    public static String generateRandomUsernameWithAdmin(int length) {
        generatedUsername = faker.regexify("[a-zA-Z]{" + length + "}");
        generatedAdminUsername = "admin" + generatedUsername;

        return generatedAdminUsername;
    }

    public static String generateRandomPassword(int length) {
        String randomPassword = faker.lorem().characters(length - 2);
        String randomDigit = faker.number().digit();
        String specialChar = faker.regexify("[!@#$%^&*()]");

        generatedPassword = randomPassword + randomDigit + specialChar;
        return generatedPassword;
    }

    public static String generateLetterPassword(int length) {
        generatedLetterPassword = faker.lorem().characters(length);
        return generatedLetterPassword;
    }


    public static String generateRandomEmail() {
        generatedEmail = faker.internet().emailAddress();
        return generatedEmail;
    }


}


