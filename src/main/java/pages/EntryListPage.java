package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import elements.Button;
import elements.Input;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
@Getter
@NoArgsConstructor
public class EntryListPage extends BasePage {

    private static final SelenideElement SEARCH_BUTTON = $x("//*[@title='Search']");
    private static final SelenideElement FIRST_ENTRY_CHECKBOX = $x("(//*[@type='checkbox'])[2]");
    private static final SelenideElement ALL_ENTRIES_CHECKBOX = $x("(//*[@type='checkbox'])[1]");
    private static final SelenideElement DELETE_ENTRIES_BUTTON = $x("//*[@id='delete-entries']");
    private static final SelenideElement createEntryButton = $x("//*[@id='create-entry']");
    private static final ElementsCollection entriesList = $$x("//*[contains(@class, 'entries__entry-container')]");
    private static final SelenideElement TAG_FIELD = $x("//*[@class='tag ng-binding']");
    private final SelenideElement entriesBody = $x("//*[@class=' entries__body']");
    private final SelenideElement firstEntryBody = $x("(//*[@class=' entries__body'])[1]");
    private final SelenideElement entryCreatedDate = $x("//div[@ng-attr-title='{{entry.fullDate}}']");
    private final SelenideElement entryTag = $x("//*[@class='entries__tags']");

    /**
     * Open entry page.
     *
     * @return the entry page
     */
    public EntryPage openEntryPage() {
        new Button().click(createEntryButton);
        log.info("Click 'Create new entry' button for open entry page");
        return new EntryPage();
    }

    /**
     * Check entries list size.
     *
     * @param size the size
     */
    public void checkEntriesListSize(int size) {
        entriesList.shouldHave(CollectionCondition.size(size));
        log.info("Actual entries list size is '{}'", size);
    }

    /**
     * Delete first entry from entry list page.
     *
     * @return the entry list page
     */
    public EntryListPage deleteFirstEntry() {
        FIRST_ENTRY_CHECKBOX.click();
        DELETE_ENTRIES_BUTTON.shouldBe(Condition.enabled);
        new Button().click(DELETE_ENTRIES_BUTTON);
        switchTo().alert().accept();
        DELETE_ENTRIES_BUTTON.shouldNotBe(Condition.disabled);
        log.info("Delete first entry");
        return new EntryListPage();
    }

    /**
     * Delete all entries on entry list page.
     *
     * @return the entry list page
     */
    public EntryListPage deleteAllEntries() {
        ALL_ENTRIES_CHECKBOX.click();
        DELETE_ENTRIES_BUTTON.shouldBe(Condition.enabled);
        new Button().click(DELETE_ENTRIES_BUTTON);
        switchTo().alert().accept();
        DELETE_ENTRIES_BUTTON.shouldNotBe(Condition.disabled);
        log.info("Delete all entries");
        return new EntryListPage();
    }

    /**
     * Search by text on entry list page.
     *
     * @param text the text
     * @return the entry list page
     */
    public EntryListPage searchByEntryText(String text) {
        new Input("appendedInputButton").writeTextToInput(text);
        new Button().click(SEARCH_BUTTON);
        log.info("Search by '{}' text", text);
        return new EntryListPage();
    }

    /**
     * Search by tag on entry list page.
     *
     * @return the entry list page
     */
    public EntryListPage searchByTag() {
        new Button().click(TAG_FIELD);
        return new EntryListPage();
    }
}