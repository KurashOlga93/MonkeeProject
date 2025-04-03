package tests;

import com.codeborne.selenide.Condition;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Retry;

public class LoginTests extends BaseTest {

    @Test(description = "Login with valid parameters and check button in the next page", retryAnalyzer = Retry.class)
    public void loginTest() {
        loginSteps.login(USER, PASSWORD, LOGIN_URL);
        entryListPage.getCreateEntryButton().shouldBe(Condition.visible);
    }

    @Test(description = "Login with empty username and check error message text")
    public void loginWithEmptyUsernameTest() {
        loginSteps.loginWithError("", PASSWORD, LOGIN_URL);
        Assert.assertEquals(loginPage.getUserFieldErrorMessageText(), loginPage.getValidationMessageText());
    }

    @Test(description = "Login with empty password and check error message text")
    public void loginWithEmptyPasswordTest() {
        loginSteps.loginWithError(USER, "", LOGIN_URL);
        Assert.assertEquals(loginPage.getPasswordFieldErrorMessageText(), loginPage.getValidationMessageText());
    }

    @Test(description = "Login with empty fields and check error message text for both fields")
    public void loginWithEmptyFieldsTest() {
        loginSteps.loginWithError("", "", LOGIN_URL);
        softAssert.assertEquals(loginPage.getUserFieldErrorMessageText(), loginPage.getValidationMessageText());
        softAssert.assertEquals(loginPage.getPasswordFieldErrorMessageText(), loginPage.getValidationMessageText());
    }

    @Test(description = "Login with invalid fields and check error message alert text")
    public void loginWithInvalidDataFieldsTest() {
        loginSteps.loginWithError("dsgdsgghdsg", "fhdfhhfjhd", LOGIN_URL);
        Assert.assertEquals(loginPage.getErrorMessageAlertText(), "Login failed");
    }

    @Test(description = "Login with valid credentials and logout, check that user on the login page again")
    public void logoutTest() {
        loginSteps.loginAndLogout(USER, PASSWORD, LOGIN_URL);
        loginPage.getLoginHeaderText().shouldHave(Condition.text("Login"));
    }
}