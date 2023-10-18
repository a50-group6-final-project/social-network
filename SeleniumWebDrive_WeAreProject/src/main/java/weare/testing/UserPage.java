package weare.testing;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import weare.models.UserPersonal;

public class UserPage extends BaseWeArePage {
    public UserPage(WebDriver driver, String urlKey) {
        super(driver, urlKey);
    }

    public void editPersonalProfile(UserPersonal userPersonal) {
        actions.clickElement("//a[contains(@href, 'profile/editor')]");
        actions.waitForElementClickable("//div[@id='profile-personal']");
        actions.clearField("//input[@name='firstName']");
        actions.typeValueInField(userPersonal.firstName, "//input[@name='firstName']");
        actions.clearField("//input[@name='lastNAme']");
        actions.typeValueInField(userPersonal.lastName, "//input[@name='lastNAme']");
        actions.clearField("//input[@name='email']");
        actions.typeValueInField(userPersonal.email, "//input[@name='email']");

        actions.typeValueInField("01-01-1990", "//input[@name='birthYear']");

        actions.clickElement("//form[contains(@action,'/profile/personal')]//button[@type='submit']");
    }

    public void sendConnectionRequest() {
        actions.clickElement("weAre.userPage.connectButton");
    }

    public void approveConnectionRequest() {
        actions.clickElement("weAre.userPage.newRequestsButton");
        actions.waitForElementVisible("weAre.userPage.approveButton");
        actions.clickElement("weAre.userPage.approveButton");
    }

    public void disconnectFromUser() {
        actions.clickElement("weAre.userPage.disconnectButton");
    }

    public void assertNamesArePresent(String firstName, String lastName) {
        try {
            actions.assertElementPresent(String.format("//p[text()='%s %s']", firstName, lastName));
        } catch (Exception e) {
            Assertions.fail("Names are not present.");
        }
    }

    public void assertBirthDateIsPresent(String birthDate) {
        try {
            actions.assertElementPresent(String.format("//p[text()='%s']", birthDate));
        } catch (Exception e) {
            Assertions.fail("Birth date is not present.");
        }
    }

    public void assertEmailIsPresent(String email) {
        try {
            actions.assertElementPresent(String.format("//p[text()='%s']", email));
        } catch (Exception e) {
            Assertions.fail("Birth date is not present.");
        }
    }

    public void assertHasOneFriend() {
        try {
            actions.assertElementPresent("weAre.userPage.oneFriend");
        } catch (Exception e) {
            Assertions.fail("User has no friends.");
        }
    }

    public void assertHasNoFriends() {
        try {
            actions.assertElementPresent("weAre.userPage.noFriends");
        } catch (Exception e) {
            Assertions.fail("User has friends.");
        }
    }
}
