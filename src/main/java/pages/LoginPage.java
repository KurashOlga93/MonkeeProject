package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import waiters.Waiter;

import static com.codeborne.selenide.Selenide.*;
@Getter
@Log4j2
public class LoginPage extends BasePage {

    private static final SelenideElement USER_INPUT = $("#login");
    private static final SelenideElement PASSWORD_INPUT = $("#password");
    private static final SelenideElement LOGIN_BUTTON = $x("//*[@type='submit']");
    private static final SelenideElement USER_FIELD_ERROR_MESSAGE = $x("//*[@id='login']/following-sibling::div");
    public static final SelenideElement PASSWORD_FIELD_ERROR_MESSAGE = $x("//*[@class='password-toggle-wrapper']/following-sibling::div");
    public static final SelenideElement ALERT_DANGER = $x("//*[@class='alert alert-danger']");
    public static final SelenideElement LOGOUT_BUTTON = $x("//*[text()='Logout']");
    private final SelenideElement createEntryButton = $x("//*[@id='create-entry']");
    private final SelenideElement loginHeaderText = $x("//*[@class='login__heading']");
    private final String validationMessageText = "Mandatory field";


    public LoginPage() {
    }

    public LoginPage openLoginPage(String url){
        open(url);
        log.info("Open login page with URL '{}'", url);
        return this;
    }

    public LoginPage isOpened() {
        USER_INPUT.shouldBe(Condition.visible);
        return this;
    }

    public LoginPage fillLoginForm(String username, String password) {
        isOpened();
        USER_INPUT.setValue(username);
        PASSWORD_INPUT.setValue(password);
        LOGIN_BUTTON.click();
        log.info("Login user with username: '{}'", username);
        return new LoginPage();
    }

    public EntryListPage login(String username, String password) {
        isOpened();
        fillLoginForm(username, password);
        Waiter.waitForElementShouldBeVisible(createEntryButton);
        return new EntryListPage();
    }

    public LoginPage loginWithError(String username, String password) {
      isOpened();
      fillLoginForm(username, password);
      return new LoginPage();
  }

    public LoginPage logout() {
        LOGOUT_BUTTON.click();
        return new LoginPage();
    }

    public String getUserFieldErrorMessageText() {
        String userFieldErrorMessage = USER_FIELD_ERROR_MESSAGE.getText();
        log.info("Error message text for user field is: '{}'", userFieldErrorMessage);
        return userFieldErrorMessage;
    }

    public String getPasswordFieldErrorMessageText() {
        String passwordFieldErrorMessage = PASSWORD_FIELD_ERROR_MESSAGE.getText();
        log.info("Error message text for password field is: '{}'", passwordFieldErrorMessage);
        return passwordFieldErrorMessage;
    }

    public String getErrorMessageAlertText() {
        String alertErrorMessageText = ALERT_DANGER.getText();
        log.info("Alert error message text for is: '{}'", alertErrorMessageText);
        return alertErrorMessageText;

    }
}
