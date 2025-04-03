package tests;

import com.codeborne.selenide.Condition;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Retry;

public class LoginTests extends BaseTest {

    @Test(retryAnalyzer = Retry.class)
    public void loginTest() {
        loginSteps.login(USER, PASSWORD, LOGIN_URL);
        entryListPage.getCreateEntryButton().shouldBe(Condition.visible);
    }

    @Test
    public void loginWithEmptyUsernameTest() {
        loginSteps.loginWithError("", PASSWORD, LOGIN_URL);
        Assert.assertEquals(loginPage.getUserFieldErrorMessageText(), "Mandatory field");
    }

    @Test
    public void loginWithEmptyPasswordTest() {
        loginSteps.loginWithError(USER, "", LOGIN_URL);
        Assert.assertEquals(loginPage.getPasswordFieldErrorMessageText(), "Mandatory field");
    }

    @Test
    public void loginWithInvalidDataFieldsTest() {
        loginSteps.loginWithError("dsgdsgghdsg", "fhdfhhfjhd", LOGIN_URL);
        Assert.assertEquals(loginPage.getErrorMessageAlertText(), "Login failed");
    }

    @Test
    public void logoutTest() {
        loginSteps.loginAndLogout(USER, PASSWORD, LOGIN_URL);
        loginPage.getLoginHeaderText().shouldHave(Condition.text("Login"));
    }
}