package weare.testing;

import org.openqa.selenium.WebDriver;

public class PublicPage extends BaseWeArePage {

    public PublicPage(WebDriver driver) {
        super(driver, "home.page");
        this.driver = driver;
    }
    public void accessToPublicSection(String username) {

        actions.waitForElementVisible("weAre.publicPage.searchUser");
        actions.typeValueInField(username, "weAre.publicPage.searchUser");
        actions.clickElement("weAre.publicPage.searchButton");
    }
}