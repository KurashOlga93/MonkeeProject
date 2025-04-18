package steps;

import io.qameta.allure.Step;
import org.testng.Assert;
import pages.SettingsPage;

public class SettingsSteps {

    SettingsPage settingsPage;

    public SettingsSteps() {
        this.settingsPage = new SettingsPage();
    }

    @Step("Go to settings page, change language and check alert")
    public SettingsSteps goToSettingsAndChangeLanguage(String language, String expectedResult) {
        settingsPage.goToSettingsPage()
                .changeLanguage(language);
        Assert.assertEquals(settingsPage.getAlertSuccessMessageText(), expectedResult);
        return new SettingsSteps();
    }

    @Step("Change language and check success alert")
    public SettingsSteps changeLanguage(String language, String expectedResult) {
        settingsPage.changeLanguage(language);
        Assert.assertEquals(settingsPage.getAlertSuccessMessageText(), expectedResult);
        return new SettingsSteps();
    }
}
