package weare.testing;

public class AdminPage extends BaseWeArePage {


    public AdminPage() {
        super(driver, "home.page");
    }

    public void adminEditUserIndustrySelection() {

        actions.waitForElementClickable("weAre.adminPage.gotoAdminZone");
        actions.clickElement("weAre.adminPage.gotoAdminZone");

        actions.waitForElementClickable("weAre.userPage.viewUsersButton");
        actions.clickElement("weAre.userPage.viewUsersButton");

        actions.waitForElementClickable("weAre.userPage.seeProfileButton");
        actions.clickElement("weAre.userPage.seeProfileButton");

        actions.waitForElementVisible("weAre.profilePage.editNavLink");
        actions.clickElement("weAre.profilePage.editNavLink");

        actions.waitForElementClickable("weAre.profilePage.categoryDropdown");
        actions.clickElement("weAre.profilePage.categoryDropdown");

        actions.clickElement("weAre.profilePage.selectAccountant");

        actions.waitForElementVisible("weAre.profilePage.saveButton");
        actions.clickElement("weAre.profilePage.saveButton");


    }

    public void adminDisableUser() {

        actions.waitForElementClickable("weAre.adminPage.gotoAdminZone");
        actions.clickElement("weAre.adminPage.gotoAdminZone");

        actions.waitForElementClickable("weAre.userPage.viewUsersButton");
        actions.clickElement("weAre.userPage.viewUsersButton");

        actions.waitForElementClickable("weAre.userPage.seeProfileButton");
        actions.clickElement("weAre.userPage.seeProfileButton");

        actions.waitForElementClickable("weAre.userPage.disableButton");
        actions.clickElement("weAre.userPage.disableButton");

    }

    public void adminEditUserPost() {

        actions.waitForElementClickable("weAre.adminPage.gotoAdminZone");
        actions.clickElement("weAre.adminPage.gotoAdminZone");

        actions.waitForElementClickable("//a[@href='/posts' and @class='nav-link']");
        actions.clickElement("//a[@href='/posts' and @class='nav-link']");


    }

    public void adminEditPersonalProfile() {
        actions.waitForElementClickable("weAre.adminPage.gotoAdminZone");
        actions.clickElement("weAre.adminPage.gotoAdminZone");

        actions.waitForElementClickable("weAre.userPage.viewUsersButton");
        actions.clickElement("weAre.userPage.viewUsersButton");

        actions.waitForElementClickable("weAre.userPage.seeProfileButton");
        actions.clickElement("weAre.userPage.seeProfileButton");

        actions.clickElement("//a[contains(@href, 'profile/editor')]");
        actions.waitForElementClickable("//div[@id='profile-personal']");
        actions.clearField("//input[@name='firstName']");
        actions.typeValueInField("Ana", "//input[@name='firstName']");
        actions.clearField("//input[@name='lastNAme']");
        actions.typeValueInField("Stoyanova", "//input[@name='lastNAme']");
        actions.clearField("//input[@name='email']");
        actions.typeValueInField("ana.stoyanova3557@abv.bg", "//input[@name='email']");

        actions.typeValueInField("01-01-1990", "//input[@name='birthYear']");

        actions.clickElement("//form[contains(@action,'/profile/personal')]//button[@type='submit']");
    }

}




