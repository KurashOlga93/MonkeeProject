package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

abstract class BasePage {

    WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(30));

    public BasePage() {
    }

}
