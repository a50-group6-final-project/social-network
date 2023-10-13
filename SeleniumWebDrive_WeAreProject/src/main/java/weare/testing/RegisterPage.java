package weare.testing;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BaseWeArePage {

    private static Faker faker = new Faker();
    private static String generatedUsername;
    private static String generatedPassword;
    private static String generatedEmail;

    public RegisterPage(WebDriver driver) {
        super(driver, "home.page");
        this.driver = driver;
    }

    public static String generateRandomUsername() {
        generatedUsername = faker.regexify("[a-zA-Z]{2,20}");
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

    public void userRegister() {
        navigateToPage();
        assertNavigatedUrl();

        actions.waitForElementVisible("(//a[text()='REGISTER'])[1]");
        actions.clickElement("(//a[text()='REGISTER'])[1]");

        actions.waitForElementVisible("//input[@id='name']");
        actions.typeValueInField(generatedUsername, "//input[@id='name']");

        actions.waitForElementVisible("//input[@id='email']");
        actions.typeValueInField(generatedEmail, "//input[@id='email']");

        actions.waitForElementVisible("//input[@id='password']");
        actions.typeValueInField(generatedPassword, "//input[@id='password']");

        actions.waitForElementVisible("//input[@id='confirm']");
        actions.typeValueInField(generatedPassword, "//input[@id='confirm']");

        actions.waitForElementClickable("//input[@type='submit' and @value='Register']");
        actions.clickElement("//input[@type='submit' and @value='Register']");
    }


}

    