package weare.testing;

import org.openqa.selenium.WebDriver;

public class AllPostPage extends BaseWeArePage{

    public AllPostPage(WebDriver driver) {
        super(driver, "allPost.page");
    }

    public void updatePost(String content, String visibility) {
//        actions.waitForElementVisible("weAre.newPostPage.postVisibilityDropdown");
//        actions.clickElement("weAre.newPostPage.postVisibilityDropdown");
//        if (visibility.equals("Public")) {
//            actions.clickElement("weAre.newPostPage.selectPublic");
//        }else {
//            actions.clickElement("weAre.newPostPage.selectPrivate");
//        }
//        actions.waitForElementPresent("weAre.newPostPage.messageField");
//        actions.typeValueInField(content, "weAre.newPostPage.messageField");
//        actions.clickElement("weAre.newPostPage.submitButton");
    }
}
