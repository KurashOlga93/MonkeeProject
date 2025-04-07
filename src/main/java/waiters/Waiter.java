package waiters;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Waiter {

    public static void waitForElementShouldBeVisible (SelenideElement locator) {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(locator));
    }
}
