package weare.testing;

import org.junit.jupiter.api.Test;

public class AccessToPublicPage extends BaseTestSetup {
    @Test
    public void accessToPublicSection() {
        // Initialize homePage
        publicPage = new PublicPage(driver);
        // Navigate to the page
        publicPage.navigateToPage();

        // Assert the presence of an element on the page
        PublicPage.assertElementPresent("(//a[text()='REGISTER'])[1]");
        PublicPage.assertElementPresent("(//a[@href='/login'])[1]");
        PublicPage.assertElementPresent("//a[@class='nav-link' and @href='/']");
        PublicPage.assertElementPresent("//a[@class='nav-link' and @href='/posts']");
        PublicPage.assertElementPresent("//a[@class='nav-link' and @href='/about-us']");
        PublicPage.assertElementPresent("//a[@id='v-pills-1-tab']");
    }
    @Test
    public void searchForAProfileByUsername() {
        // Initialize homePage
        publicPage = new PublicPage(driver);
        // Navigate to the page
        publicPage.navigateToPage();

        String username = "Borislav";
        // Access the public section with the initialized homePage
        publicPage.accessToPublicSection(username);
        // Assert the presence of an element on the page
        PublicPage.assertElementPresent("//h2[@class='mr-3 text-black']");

    }

    @Test
    public void searchForAProfileByInvalidUsername() {
        // Initialize homePage
        publicPage = new PublicPage(driver);
        // Navigate to the page
        publicPage.navigateToPage();

        String username = "Petar";
        // Access the public section with the initialized homePage
        publicPage.accessToPublicSection(username);
        // Assert the presence of an element on the page
        PublicPage.assertElementPresent("//h3[@class='mb-3 bread']");

    }
}
