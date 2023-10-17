package weare.testing;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LatestPostsPage extends BaseWeArePage{
    public LatestPostsPage(WebDriver driver) {
        super(driver, "latestPosts.page");
    }

    public void assertPostsCount(int count) {
        int listSize = driver.findElements(By.xpath("//div[@class='col-md-12']//child::a[contains(@href, '/posts/')]")).size();
        if(listSize != count) {
            Assertions.fail("Posts count is not as expected. Actual count: " + listSize + ". Expected count: " + count);
        }
    };

}
