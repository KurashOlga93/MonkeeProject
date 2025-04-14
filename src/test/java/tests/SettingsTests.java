package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class SettingsTests extends BaseTest {

    @Test(description = "Go to Settings, change language and check success alert")
    public void changeLanguageTest() {
        loginSteps.login(USER, PASSWORD, LOGIN_URL);
        settingsSteps.goToSettingsAndChangeLanguage("Portuguese","Seu idioma foi alterado com sucesso");
    }

    @AfterMethod(description = "Change language and check success alert")
    public void returnEnglishLanguage() {
        settingsSteps.changeLanguage("English","Your language has been changed successfully");
    }
}
