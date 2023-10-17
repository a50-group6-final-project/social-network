package weare.testing;

import org.openqa.selenium.WebDriver;

public class PostPage extends BaseWeArePage {

    public PostPage(WebDriver driver, String urlKey) {
        super(driver, urlKey);
    }

    public void update(String content, String visibility, int postId) {
        actions.waitForElementVisible("//a[contains(@href, 'editor/" + postId + "')]");
        actions.clickElement("//a[contains(@href, 'editor/113')]");
        actions.waitForElementVisible("weAre.newPostPage.postVisibilityDropdown");
        actions.clickElement("weAre.newPostPage.postVisibilityDropdown");
        if (visibility.equals("Public")) {
            actions.clickElement("weAre.newPostPage.selectPublic");
        } else {
            actions.clickElement("weAre.newPostPage.selectPrivate");
        }
        actions.waitForElementPresent("weAre.newPostPage.messageField");
        actions.clearField("weAre.newPostPage.messageField");
        actions.typeValueInField(content, "weAre.newPostPage.messageField");
        actions.clickElement("weAre.newPostPage.submitButton");
    }
}
