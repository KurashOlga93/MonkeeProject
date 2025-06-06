package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import elements.Button;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class SettingsPage {

    private static final SelenideElement SETTINGS_BUTTON = $x("//*[text()='Settings']");
    private static final SelenideElement LANGUAGE_DROPDOWN = $("[name='selectLocale']");
    private static final SelenideElement SUBMIT_BUTTON = $x("//*[@type='submit']");
    private static final SelenideElement ALERT_SUCCESS = $x("//*[@class='alert alert-success']");

    public SettingsPage() {
    }

    /**
     * Is opened settings page.
     *
     * @return the settings page
     */
    public SettingsPage isOpened() {
        LANGUAGE_DROPDOWN.shouldBe(Condition.visible);
        return this;
    }

    /**
     * Go to settings page.
     *
     * @return the settings page
     */
    public SettingsPage goToSettingsPage() {
        new Button().click(SETTINGS_BUTTON);
        isOpened();
        return this;
    }

    /**
     * Change language settings page.
     *
     * @param language the language
     * @return the settings page
     */
    public SettingsPage changeLanguage(String language) {
        LANGUAGE_DROPDOWN.selectOption(language);
        new Button().click(SUBMIT_BUTTON);
        log.info("Language changed on: '{}'", language);
        return this;
    }

    /**
     * Gets alert success message text.
     *
     * @return the alert success message text
     */
    public String getAlertSuccessMessageText() {
        try {
            String alertSuccessText = ALERT_SUCCESS.getText();
            log.info("Alert success message text after changing language is: '{}'", alertSuccessText);
            return alertSuccessText;
        } catch (Exception e) {
            log.error("Failed to get alert success message.", e);
            return "";
        }
    }
}