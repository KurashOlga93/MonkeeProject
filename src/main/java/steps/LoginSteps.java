package steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;

public class LoginSteps {

    SoftAssert softAssert = new SoftAssert();

    LoginPage loginPage;

    public LoginSteps() {
        this.loginPage = new LoginPage();
    }

    @Step("Login by user: {email}")
    public EntrySteps login(String email, String password, String url) {
        loginPage.openLoginPage(url)
                .login(email, password);
        return new EntrySteps();
    }

    @Step("Login by user: {email}")
    public LoginSteps loginWithError(String email, String password, String url) {
        loginPage.openLoginPage(url)
                .loginWithError(email, password);
        return this;
    }

    @Step("Login by user: {email} and Logout")
    public LoginSteps loginAndLogout(String email, String password, String url) {
        loginPage.openLoginPage(url)
                .login(email, password);
        loginPage.logout();
        return this;
    }

    @Step("Check validation message text for user field")
    public LoginSteps checkUserFieldValidation (String expectedResult) {
        Assert.assertEquals(loginPage.getUserFieldErrorMessageText(), expectedResult);
        return this;
    }

    @Step("Check validation message text for password field")
    public LoginSteps checkPasswordFieldValidation (String expectedResult) {
        Assert.assertEquals(loginPage.getPasswordFieldErrorMessageText(), expectedResult);
        return this;
    }

    @Step("Check alert message text for login fields")
    public LoginSteps checkAlertValidationText (String expectedResult) {
        Assert.assertEquals(loginPage.getErrorMessageAlertText(), expectedResult);
        return this;
    }

    @Step("Check login header text")
    public LoginSteps checkLoginHeaderText () {
        loginPage.getLoginHeaderText().shouldHave(Condition.text("Login"));
        return this;
    }
}
