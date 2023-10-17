package weare.testing;


import org.openqa.selenium.WebDriver;

public class PrivatePage extends BaseWeArePage{

    public PrivatePage(WebDriver driver) {
        super(driver, "http://localhost:8081/");
        this.driver = driver;
    }
    public static void LoginUser(String username, String password) {

        actions.waitForElementVisible("weAre.privatePage.loginNavLink");
        actions.clickElement("weAre.privatePage.loginNavLink");
        actions.waitForElementVisible("weAre.privatePage.username");
        actions.typeValueInField(username, "weAre.privatePage.username");
        actions.waitForElementVisible("weAre.privatePage.password");
        actions.typeValueInField(password, "weAre.privatePage.password");
        actions.clickElement("weAre.privatePage.loginButton");
    }
    public static void updatePersonalInformation(String firstname, String lastname) {

        actions.waitForElementVisible("weAre.privatePage.personalProfileButton");
        actions.clickElement("weAre.privatePage.personalProfileButton");
        actions.waitForElementVisible("weAre.privatePage.editButton");
        actions.clickElement("weAre.privatePage.editButton");
        actions.waitForElementVisible("weAre.privatePage.firstname");
        actions.typeValueInField(firstname, "weAre.privatePage.firstname");
        actions.waitForElementVisible("weAre.privatePage.lastname");
        actions.typeValueInField(lastname, "weAre.privatePage.lastname");
        actions.waitForElementVisible("weAre.privatePage.calendar");
        actions.typeValueInField("11/12/2023", "weAre.privatePage.calendar");
        actions.waitForElementVisible("weAre.privatePage.gender");
        actions.clickElement("weAre.privatePage.gender");
        actions.waitForElementVisible("weAre.privatePage.city");
        actions.clickElement("weAre.privatePage.city");
        actions.clickElement("weAre.privatePage.updateButtonOne");
    }

    public static void updateWorkspaceBusiness() {

        actions.waitForElementVisible("weAre.privatePage.personalProfileButton");
        actions.clickElement("weAre.privatePage.personalProfileButton");
        actions.waitForElementVisible("weAre.privatePage.editButton");
        actions.clickElement("weAre.privatePage.editButton");
        actions.clickElement("weAre.privatePage.professionalCategory");
        actions.clickElement("weAre.privatePage.updateButtonTwo");
    }

    public static void updateServiceAndWeeklyAvailability(String skill) {

        actions.waitForElementVisible("weAre.privatePage.personalProfileButton");
        actions.clickElement("weAre.privatePage.personalProfileButton");
        actions.waitForElementVisible("weAre.privatePage.editButton");
        actions.clickElement("weAre.privatePage.editButton");
        actions.waitForElementVisible("weAre.privatePage.skill");
        actions.typeValueInField(skill, "weAre.privatePage.skill");
        actions.waitForElementVisible("weAre.privatePage.weeklyAvailability");
        actions.typeValueInField("1.1", "weAre.privatePage.weeklyAvailability");
        actions.clickElement("weAre.privatePage.updateButtonThree");
    }

    public static void updatePersonalInfoAndSafety() {

        actions.waitForElementVisible("weAre.privatePage.personalProfileButton");
        actions.clickElement("weAre.privatePage.personalProfileButton");
        actions.waitForElementVisible("weAre.privatePage.editButton");
        actions.clickElement("weAre.privatePage.editButton");
        actions.clickElement("weAre.privatePage.picturePrivacy");
        actions.clickElement("weAre.privatePage.updateButtonFour");
    }
}
