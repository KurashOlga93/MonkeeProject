package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertyReader;

import static com.codeborne.selenide.Selenide.$x;
public class LoginTests extends BaseTest {

    public static String USER = PropertyReader.getProperty("user");
    public static String PASSWORD = PropertyReader.getProperty("password");
    public static String LOGIN_URL = PropertyReader.getProperty("loginUrl");

    private static final SelenideElement CREATE_ENTRY_BUTTON = $x("//*[@id='create-entry']");
    private static final SelenideElement LOGIN_HEADER_TEXT = $x("//*[@class='login__heading']");

    @Test
    public void loginTest(){
        loginSteps.login(USER, PASSWORD, LOGIN_URL);
        CREATE_ENTRY_BUTTON.shouldBe(Condition.visible);
    }

    @Test
    public void loginWithEmptyUsernameTest(){
        loginSteps.login("", PASSWORD, LOGIN_URL);
        Assert.assertEquals(loginPage.getUserFieldErrorMessageText(), "Mandatory field");
    }

    @Test
    public void loginWithEmptyPasswordTest(){
        loginSteps.login(USER, "", LOGIN_URL);
        Assert.assertEquals(loginPage.getPasswordFieldErrorMessageText(), "Mandatory field");
    }

    @Test
    public void loginWithInvalidDataFieldsTest(){
        loginSteps.login("dsgdsgghdsg", "fhdfhhfjhd", LOGIN_URL);
        Assert.assertEquals(loginPage.getErrorMessageAlertText(), "Login failed");
    }

    @Test
    public void logoutTest(){
        loginSteps.loginAndLogout(USER, PASSWORD, LOGIN_URL);
        Assert.assertEquals(LOGIN_HEADER_TEXT.getText(), "Login");
    }

}
