package weare.testing;

import org.openqa.selenium.WebDriver;

public class LoginPage extends BaseWeArePage {

    public LoginPage(WebDriver driver) {
        super(driver, "http://localhost:8081/login");
    }

    public static void loginUser(String username, String password) {


        actions.waitForElementVisible("//a[@class='nav-link' and @href = '/login']");
        actions.clickElement("//a[@class='nav-link' and @href = '/login']");

        actions.waitForElementVisible("//input[@id='username']");
        actions.typeValueInField(username, "//input[@id='username']");
        actions.waitForElementVisible("//input[@id='password']");
        actions.typeValueInField(password, "//input[@id='password']");

        actions.clickElement("//input[@value='Login']");
    }
}
