package weare.testing;

import org.openqa.selenium.WebDriver;

public class RegisterPage extends BaseWeArePage {

    public RegisterPage(WebDriver driver) {
        super(driver, "home.page");
        this.driver = driver;
    }

    public void userRegister(String username, String password, String email) {
        navigateToPage();
        assertNavigatedUrl();

        actions.waitForElementVisible("(//a[text()='REGISTER'])[1]");
        actions.clickElement("(//a[text()='REGISTER'])[1]");

        actions.waitForElementVisible("//input[@id='name']");
        actions.typeValueInField(username, "//input[@id='name']");

        actions.waitForElementVisible("//input[@id='email']");
        actions.typeValueInField(email, "//input[@id='email']");

        actions.waitForElementVisible("//input[@id='password']");
        actions.typeValueInField(password, "//input[@id='password']");

        actions.waitForElementVisible("//input[@id='confirm']");
        actions.typeValueInField(password, "//input[@id='confirm']");

        actions.waitForElementClickable("//input[@type='submit' and @value='Register']");
        actions.clickElement("//input[@type='submit' and @value='Register']");
    }

    public void userRegisterWithProfessionalSelection(String username, String password, String email) {
        navigateToPage();
        assertNavigatedUrl();

        actions.waitForElementVisible("(//a[text()='REGISTER'])[1]");
        actions.clickElement("(//a[text()='REGISTER'])[1]");

        actions.waitForElementVisible("//input[@id='name']");
        actions.typeValueInField(username, "//input[@id='name']");

        actions.waitForElementVisible("//input[@id='email']");
        actions.typeValueInField(email, "//input[@id='email']");

        actions.waitForElementVisible("//input[@id='password']");
        actions.typeValueInField(password, "//input[@id='password']");

        actions.waitForElementVisible("//input[@id='confirm']");
        actions.typeValueInField(password, "//input[@id='confirm']");

        actions.waitForElementClickable("//div[@class='col-8']/select[1]");
        actions.clickElement("//div[@class='col-8']/select[1]");

        actions.clickElement("//select[@id='category.id']/option[text()='Accountant']");

        actions.waitForElementClickable("//input[@type='submit' and @value='Register']");
        actions.clickElement("//input[@type='submit' and @value='Register']");


    }


    public void adminRegister(String username, String password, String email) {
        navigateToPage();
        assertNavigatedUrl();

        actions.waitForElementVisible("(//a[text()='REGISTER'])[1]");
        actions.clickElement("(//a[text()='REGISTER'])[1]");

        actions.waitForElementVisible("//input[@id='name']");
        actions.typeValueInField(username, "//input[@id='name']");

        actions.waitForElementVisible("//input[@id='email']");
        actions.typeValueInField(email, "//input[@id='email']");

        actions.waitForElementVisible("//input[@id='password']");
        actions.typeValueInField(password, "//input[@id='password']");

        actions.waitForElementVisible("//input[@id='confirm']");
        actions.typeValueInField(password, "//input[@id='confirm']");

        actions.waitForElementClickable("//input[@type='submit' and @value='Register']");
        actions.clickElement("//input[@type='submit' and @value='Register']");
    }
}