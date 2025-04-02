package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class LoginPage extends BasePage {

    private static final SelenideElement USER_INPUT = $("#login");
    private static final SelenideElement PASSWORD_INPUT = $("#password");
    private static final SelenideElement LOGIN_BUTTON = $x("//*[@type='submit']");
    private static final SelenideElement USER_FIELD_ERROR_MESSAGE = $x("//*[@id=\"login\"]/following-sibling::div");
    public static final SelenideElement PASSWORD_FIELD_ERROR_MESSAGE = $x("//div[contains(@class, 'password')]/following-sibling::div[contains(@class, 'help-block')]");
    public static final SelenideElement ALERT_DANGER = $x("//*[@class='alert alert-danger']");
    public static final SelenideElement LOGOUT_BUTTON = $x("//*[text()='Logout']");


    public LoginPage() {
    }

    public LoginPage openLoginPage(String url){
        open(url);
        return this;
    }

    public LoginPage isOpened() {
        USER_INPUT.shouldBe(Condition.visible);
        return this;
    }

    public EntriesPage login(String username, String password) {
        isOpened();
        USER_INPUT.setValue(username);
        PASSWORD_INPUT.setValue(password);
        LOGIN_BUTTON.click();
        return new EntriesPage();
    }

    public LoginPage logout() {
        LOGOUT_BUTTON.click();
        return new LoginPage();
    }

//    public EntriesPage login(String username, String password) {
//        isOpened();
//        fillLoginForm()
//        return new EntriesPage();
//    }
//
//    public EntriesPage loginWithError(String username, String password) {
//        isOpened();
//        fillLoginForm()
//        return new EntriesPage();
//    }
//
//    метод возвра логин пейдж
//    fillLoginForm
//    USER_INPUT.setValue(username);
//        PASSWORD_INPUT.setValue(password);
//        LOGIN_BUTTON.click();

    public String getUserFieldErrorMessageText() {
       return USER_FIELD_ERROR_MESSAGE.getText();
    }

    public String getPasswordFieldErrorMessageText() {
        return PASSWORD_FIELD_ERROR_MESSAGE.getText();
    }

    public String getErrorMessageAlertText() {
        return ALERT_DANGER.getText();
    }
}
