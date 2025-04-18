package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import elements.Button;
import elements.Input;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$x;

@Log4j2
@Getter
public class EntryPage extends BasePage {

    private static final SelenideElement ENTRY_AREA = $x("//*[@id='editable']");
    private static final SelenideElement BACK_TO_OVERVIEW_BUTTON = $x("//*[@id='back-to-overview']");
    private static final SelenideElement SAVE_ENTRY_BUTTON = $x("//*[@title='Save']");
    private static final SelenideElement ENTRY_TOOLBAR = $x("//*[@class='cke_top']");
    private static final SelenideElement DATE_TIME_FIELD = $x("//*[@class='ng-binding']");
    private static final SelenideElement TAG_INPUT = $x("//*[@id='new-tag']");
    private static final SelenideElement SAVE_TAG_BUTTON = $x("//*[@id='assign-new-tag']");

    public EntryPage() {
    }

    /**
     * Fill entry form on entry page.
     *
     * @param text the text
     * @return the entry page
     */
    public EntryPage fillEntryForm(String text) {
        ENTRY_AREA.click();
        ENTRY_TOOLBAR.shouldBe(Condition.visible);
        new Input("editable").writeTextToInput(text);
        log.info("Fill entry form with text: '{}'", text);
        return new EntryPage();
    }

    /**
     * Create tag on entry page.
     *
     * @param text the text
     * @return the entry page
     */
    public EntryPage createTag(String text) {
        TAG_INPUT.click();
        new Input("new-tag").writeTextToInput(text);
        new Button().click(SAVE_TAG_BUTTON);
        log.info("Create a tag with name: '{}'", text);
        return new EntryPage();
    }

    /**
     * Save entry on entry page.
     *
     * @return the entry list page
     */
    public EntryListPage saveEntry() {
        new Button().click(SAVE_ENTRY_BUTTON);
        new Button().click(BACK_TO_OVERVIEW_BUTTON);
        return new EntryListPage();
    }

    /**
     * Gets date and time when creating entry.
     *
     * @return the date and time when creating entry
     */
    public String getDateAndTimeWhenCreatingEntry() {
        String creatingData = DATE_TIME_FIELD.getText();
        log.info("Date and time of creating entry '{}'", creatingData);
        return creatingData;
    }
}