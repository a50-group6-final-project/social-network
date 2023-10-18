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

    public void adminEditUserPost(){

        actions.waitForElementClickable("weAre.adminPage.gotoAdminZone");
        actions.clickElement("weAre.adminPage.gotoAdminZone");

        actions.waitForElementClickable("//a[@href='/posts' and @class='nav-link']");
        actions.clickElement("//a[@href='/posts' and @class='nav-link']");






    }

}
