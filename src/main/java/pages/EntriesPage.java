package pages;


import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class EntriesPage extends BasePage {

    public EntriesPage() {
    }

    private final SelenideElement USER_INPUT = $("#create-entry");
    private final SelenideElement createEntryButton = $x("//*[@id='create-entry']");


}
