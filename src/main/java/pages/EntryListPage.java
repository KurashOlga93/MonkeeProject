package pages;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class EntryListPage extends BasePage {

    public EntryListPage() {
    }

    private final SelenideElement DELETE_ENTRIES_BUTTON = $("//*[@id='delete-entries']");
    private final SelenideElement ENTRY_AREA = $("//*[@id='editable']");
    private final SelenideElement createEntryButton = $x("//*[@id='create-entry']");
    private final SelenideElement BACK_TO_OVERVIEW_BUTTON = $x("//*[@id='back-to-overview']");

    public EntryListPage isOpened() {
        createEntryButton.shouldBe(Condition.visible);
        return this;
    }

    public EntryListPage createEntry(String text) {
        isOpened();
        createEntryButton.click();
        ENTRY_AREA.click();
        ENTRY_AREA.setValue(text);
        BACK_TO_OVERVIEW_BUTTON.click();
        return new EntryListPage();
    }


}
