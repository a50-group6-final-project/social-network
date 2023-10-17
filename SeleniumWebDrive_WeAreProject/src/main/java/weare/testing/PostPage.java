package weare.testing;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

public class PostPage extends BaseWeArePage {

    public PostPage(WebDriver driver, String urlKey) {
        super(driver, urlKey);
    }

    public void update(String content, String visibility, int postId) {
        actions.waitForElementVisible(String.format(getUIMappingByKey("weAre.postPage.editButton"), postId));
        actions.clickElement(String.format(getUIMappingByKey("weAre.postPage.editButton"), postId));
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

    public void delete(int postId) {
        actions.waitForElementVisible(String.format(getUIMappingByKey("weAre.postPage.deleteButton"), postId));
        actions.clickElement(String.format(getUIMappingByKey("weAre.postPage.deleteButton"), postId));
        actions.waitForElementVisible("weAre.newPostPage.postVisibilityDropdown");
        actions.clickElement("weAre.newPostPage.postVisibilityDropdown");
        actions.clickElement("weAre.postPage.selectDeletePost");
        actions.clickElement("weAre.postPage.submitButton");


    }
}
