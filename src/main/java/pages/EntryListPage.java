package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
@Getter
public class EntryListPage extends BasePage {

    private static final SelenideElement SEARCH_FIELD = $x("//*[@type='search']");
    private static final SelenideElement SEARCH_BUTTON = $x("//*[@title='Search']");
    private static final SelenideElement FIRST_ENTRY_CHECKBOX = $x("(//*[@type='checkbox'])[2]");
    private static final SelenideElement ALL_ENTRIES_CHECKBOX = $x("(//*[@type='checkbox'])[1]");
    private static final SelenideElement DELETE_ENTRIES_BUTTON = $x("//*[@id='delete-entries']");
    private final SelenideElement createEntryButton = $x("//*[@id='create-entry']");
    private final SelenideElement entriesBody = $x("//*[@class=' entries__body']");
    private final SelenideElement firstEntryBody = $x("(//*[@class=' entries__body'])[1]");
    private final ElementsCollection entriesList = $$x("//*[contains(@class, 'entries__entry-container')]");

    public EntryListPage() {
    }

    /**
     * Is opened entry list page.
     *
     * @return the entry list page
     */
    public EntryListPage isOpened() {
        createEntryButton.shouldBe(Condition.visible);
        return this;
    }

    /**
     * Open entry page entry page.
     *
     * @return the entry page
     */
    public EntryPage openEntryPage() {
        isOpened();
        createEntryButton.click();
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
    }

    /**
     * Delete first entry entry list page.
     *
     * @return the entry list page
     */
    public EntryListPage deleteFirstEntry() {
        FIRST_ENTRY_CHECKBOX.click();
        DELETE_ENTRIES_BUTTON.shouldBe(Condition.enabled);
        DELETE_ENTRIES_BUTTON.click();
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
        DELETE_ENTRIES_BUTTON.click();
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
    public EntryListPage searchByText(String text) {
        SEARCH_FIELD.setValue(text);
        SEARCH_BUTTON.click();
        log.info("Search by '{}' text", text);
        return new EntryListPage();
    }
}