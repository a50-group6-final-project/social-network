package weare.testing;

import org.openqa.selenium.WebDriver;

public class AdminPage extends BaseWeArePage{


    public AdminPage(WebDriver driver, String urlKey) {
        super(driver, urlKey);
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
