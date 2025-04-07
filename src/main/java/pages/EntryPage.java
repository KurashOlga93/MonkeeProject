package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Selenide.$x;

@Log4j2
@Getter
public class EntryPage extends BasePage{

    private static final SelenideElement ENTRY_AREA = $x("//*[@id='editable']");
    private static final SelenideElement BACK_TO_OVERVIEW_BUTTON = $x("//*[@id='back-to-overview']");
    private static final SelenideElement SAVE_ENTRY_BUTTON = $x("//*[@title='Save']");
    private static final SelenideElement ENTRY_TOOLBAR = $x("//*[@class='cke_top']");

    public EntryPage() {
    }

    public EntryPage fillEntryForm(String text) {
        ENTRY_AREA.click();
        ENTRY_TOOLBAR.shouldBe(Condition.visible);
        ENTRY_AREA.setValue(text);
        log.info("Fill entry form with text: '{}'", text);
        return new EntryPage();
    }

    public EntryListPage saveEntry() {
        SAVE_ENTRY_BUTTON.click();
        BACK_TO_OVERVIEW_BUTTON.click();
        return new EntryListPage();
    }
}