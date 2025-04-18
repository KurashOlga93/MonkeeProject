package pages;

import com.codeborne.selenide.SelenideElement;
import elements.Button;
import elements.Input;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import waiters.Waiter;

import static com.codeborne.selenide.Selenide.*;

@Getter
@Log4j2
public class LoginPage extends BasePage {

    private static final SelenideElement LOGIN_BUTTON = $x("//*[@type='submit']");
    private static final SelenideElement USER_FIELD_ERROR_MESSAGE = $x("//*[@id='login']/following-sibling::div");
    public static final SelenideElement PASSWORD_FIELD_ERROR_MESSAGE = $x("//*[@class='password-toggle-wrapper']/following-sibling::div");
    public static final SelenideElement ALERT_DANGER = $x("//*[@class='alert alert-danger']");
    public static final SelenideElement LOGOUT_BUTTON = $x("//*[text()='Logout']");
    private final SelenideElement createEntryButton = $x("//*[@id='create-entry']");
    private final SelenideElement loginHeaderText = $x("//*[@class='login__heading']");


    public LoginPage() {
    }

    /**
     * Open login page.
     *
     * @param url the url
     * @return the login page
     */
    public LoginPage openLoginPage(String url) {
        open(url);
        log.info("Open login page with URL '{}'", url);
        return this;
    }

    /**
     * Fill login form login page.
     *
     * @param username the username
     * @param password the password
     * @return the login page
     */
    public LoginPage fillLoginForm(String username, String password) {
        new Input("login").writeTextToInput(username);
        new Input("password").writeTextToInput(password);
        new Button().click(LOGIN_BUTTON);
        log.info("Login user with username: '{}'", username);
        return new LoginPage();
    }

    /**
     * Login entry list page.
     *
     * @param username the username
     * @param password the password
     * @return the entry list page
     */
    public EntryListPage login(String username, String password) {
        fillLoginForm(username, password);
        Waiter.waitForElementShouldBeVisible(createEntryButton);
        return new EntryListPage();
    }

    /**
     * Login with error login page.
     *
     * @param username the username
     * @param password the password
     * @return the login page
     */
    public LoginPage loginWithError(String username, String password) {
        fillLoginForm(username, password);
        return new LoginPage();
    }

    /**
     * Logout login page.
     *
     * @return the login page
     */
    public LoginPage logout() {
        new Button().click(LOGOUT_BUTTON);
        return new LoginPage();
    }


    /**
     * Gets user field error message text.
     *
     * @return the user field error message text
     */
    public String getUserFieldErrorMessageText() {
        try {
            String userFieldErrorMessage = USER_FIELD_ERROR_MESSAGE.getText();
            log.info("Error message text for user field is: '{}'", userFieldErrorMessage);
            return userFieldErrorMessage;
        } catch (Exception e) {
            log.error("Failed to get login field error message.", e);
            return "";
        }
    }

    /**
     * Gets password field error message text.
     *
     * @return the password field error message text
     */
    public String getPasswordFieldErrorMessageText() {
        try {
            String passwordFieldErrorMessage = PASSWORD_FIELD_ERROR_MESSAGE.getText();
            log.info("Error message text for password field is: '{}'", passwordFieldErrorMessage);
            return passwordFieldErrorMessage;
        } catch (Exception e) {
            log.error("Failed to get password field error message.", e);
            return "";
        }
    }

    /**
     * Gets error message alert text.
     *
     * @return the error message alert text
     */
    public String getErrorMessageAlertText() {
        try {
            String alertErrorMessageText = ALERT_DANGER.getText();
            log.info("Alert error message text for is: '{}'", alertErrorMessageText);
            return alertErrorMessageText;
        } catch (Exception e) {
            log.error("Failed to get alert error message.", e);
            return "";
        }
    }
}
