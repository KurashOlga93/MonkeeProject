package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.*;

@Getter
public class EntryListPage extends BasePage {

    public EntryListPage() {
    }

    private static final SelenideElement SEARCH_FIELD = $x("(//*[@type='checkbox'])[2]");
    private static final SelenideElement FIRST_ENTRY_CHECKBOX = $x("(//*[@type='checkbox'])[2]");
    private static final SelenideElement ALL_ENTRIES_CHECKBOX = $x("(//*[@type='checkbox'])[1]");
    private static final SelenideElement DELETE_ENTRIES_BUTTON = $x("//*[@id='delete-entries']");
    private final SelenideElement createEntryButton = $x("//*[@id='create-entry']");
    private final SelenideElement entriesBody = $x("//*[@class=' entries__body']");
    private final SelenideElement firstEntryBody = $x("(//*[@class=' entries__body'])[1]");
    private final SelenideElement entriesContainer = $x("//*[contains(@class, 'entries__entry-container')]");


    public EntryListPage isOpened() {
        createEntryButton.shouldBe(Condition.visible);
        return this;
    }

    public EntryPage openEntryPage() {
        isOpened();
        createEntryButton.click();
        return new EntryPage();
    }

    public int checkEntriesListSize() {
        ElementsCollection entriesList = $$x("//*[contains(@class, 'entries__entry-container')]");
        return entriesList.size();
    }

    public EntryListPage deleteFirstEntry() {
        FIRST_ENTRY_CHECKBOX.click();
        DELETE_ENTRIES_BUTTON.shouldBe(Condition.enabled);
        DELETE_ENTRIES_BUTTON.click();
        switchTo().alert().accept();
        DELETE_ENTRIES_BUTTON.shouldNotBe(Condition.disabled);
        return new EntryListPage();
    }

    public EntryListPage deleteAllEntries() {
        ALL_ENTRIES_CHECKBOX.click();
        DELETE_ENTRIES_BUTTON.shouldBe(Condition.enabled);
        DELETE_ENTRIES_BUTTON.click();
        switchTo().alert().accept();
        DELETE_ENTRIES_BUTTON.shouldNotBe(Condition.disabled);
        return new EntryListPage();
    }
}