package weare.testing;

public class AdminPage extends BaseWeArePage {


    public AdminPage() {
        super(driver, "http://localhost:8081/login");
    }

    public void adminEditUserProfile() {

        actions.waitForElementClickable("//a[normalize-space(text())='GO TO admin zone']");
        actions.clickElement("//a[normalize-space(text())='GO TO admin zone']");

        actions.waitForElementClickable("//input[@value='View Users']");
        actions.clickElement("//input[@value='View Users']");

        actions.waitForElementClickable("//a[contains(@class,'btn btn-primary') and text()='See Profile']");
        actions.clickElement("//a[contains(@class,'btn btn-primary') and text()='See Profile']");

        actions.waitForElementVisible("//a[contains(text(), 'edit') and @class='nav-link']");
        actions.clickElement("//a[contains(text(), 'edit') and @class='nav-link']");

        actions.waitForElementClickable("//div[@class='col-8']/select[1]");
        actions.clickElement("//div[@class='col-8']/select[1]");

        actions.clickElement("//select[@id='category.id']/option[text()='Accountant']");

        actions.waitForElementVisible("//*[@id='profile-expertise']/div/div/div[2]/div/form/div/div[2]/div/button");
        actions.clickElement("//*[@id='profile-expertise']/div/div/div[2]/div/form/div/div[2]/div/button");

    }

}
