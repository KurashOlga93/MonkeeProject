package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class LoginPage extends BasePage {

    public static final By USER_INPUT = By.id("login");
    public static final By PASSWORD_INPUT = By.id("password");
    public static final By LOGIN_BUTTON = By.xpath("//*[@type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public EntriesPage login(String username, String password) {
        driver.findElement(USER_INPUT).sendKeys(username);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new EntriesPage(driver);
    }


}
