package weare.testing;

import com.telerikacademy.pages.BasePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class AccessToPrivatePage extends BaseTestSetup{
    @BeforeEach
    public void LoginUser() {
        //Initialize homePage
        privatePage = new PrivatePage(driver);
        // Navigate to the page
        privatePage.navigateToPage();
        String username = "BeastTest";
        String password = "Project.10";

        // Access the private section
        privatePage.LoginUser(username, password);
        LoginPage.assertElementPresent("//a[@href='/logout' and text()='LOGOUT']");
    }

    @Test
    public void UpdatePersonalInformation() {
        String firstname = "Borislav";
        String lastname = "Borisov";

        // Access the private section
        privatePage.updatePersonalInformation(firstname, lastname);
    }

    @Test
    public void UpdateWorkspaceBusiness(){
        // Access the private section
        privatePage.updateWorkspaceBusiness();

        BasePage.assertElementPresent("//span[text()='Accountant']");
    }

    @Test
    public void UpdateServiceAndWeeklyAvailability(){
        String skill = "Quality Assurance";
        // Access the private section
        privatePage.updateServiceAndWeeklyAvailability(skill);

        BasePage.assertElementPresent("//span[text()='Quality Assurance']");
        BasePage.assertElementPresent("//span[@class='icon-hand-o-up']");
        BasePage.assertElementPresent("//span[@class='icon-clock-o']");
    }

    @Test
    public void UpdatePersonalInfoAndSafety() {
        // Access the private section
        privatePage.updatePersonalInfoAndSafety();
    }
}