package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static tests.ITestConstants.password;
import static tests.ITestConstants.username;

public class LoginTest extends BaseTest {

    @Test(description = "Login with empty username field and check validation.")
    public void loginWithEmptyUsernameTest(){
        loginSteps.loginAndWaitForPageOpened(username, password);
        Assert.assertEquals(driver.getCurrentUrl(), "https://monkkee.com/app/#/entries");
    }
}
