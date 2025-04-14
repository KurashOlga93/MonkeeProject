package steps;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import pages.EntryListPage;
import pages.EntryPage;

import static com.codeborne.selenide.Condition.*;
@Log4j2
public class EntrySteps {

    EntryPage entryPage;
    EntryListPage entryListPage;

    public EntrySteps() {
        this.entryPage = new EntryPage();
        this.entryListPage = new EntryListPage();
    }

    @Step("Open entry page, write a text and save entry")
    public EntrySteps createEntryAndSave(String text) {
        entryListPage.openEntryPage();
        entryPage.fillEntryForm(text);
        entryPage.saveEntry();
        return this;
    }

    @Step("Open exist entry, edit text and save")
    public EntrySteps editExistEntryAndSave(String text) {
        entryListPage.getEntriesBody().click();
        entryPage.fillEntryForm(text);
        entryPage.saveEntry();
        return this;
    }

    @Step("Select first entry by checkbox and delete")
    public EntrySteps deleteFirstEntry() {
        entryListPage.deleteFirstEntry();
        return this;
    }

    @Step("Select all entries by main checkbox and delete")
    public EntrySteps deleteAllEntries() {
        entryListPage.deleteAllEntries();
        return this;
    }

    @Step("Check body text for first entry")
    public void checkFirstEntryText (String expectedResult) {
        Assert.assertEquals(entryListPage.getFirstEntryBody().getText(), expectedResult);
    }

    @Step("Check entries list size")
    public EntrySteps checkEntriesListSize (int size) {
        entryListPage.checkEntriesListSize(size);
        return this;
    }

    @Step("Search by text")
    public EntrySteps searchByText (String text) {
        entryListPage.searchByText(text);
        return this;
    }

    @Step("Check first entry contains text")
    public void checkFirstEntryContainsText (String text) {
        Assert.assertTrue(entryListPage.getFirstEntryBody().getText().contains(text));
    }

    @Step("Check date and time of created entry")
    public void checkDateAndTimeOfCreatedEntry(String text) {
        entryListPage.openEntryPage();
        entryPage.fillEntryForm(text);
        String creatingData = entryPage.getDateAndTimeWhenCreatingEntry();
        entryPage.saveEntry();
        SelenideElement el = entryListPage.getEntryCreatedDate();
        el.shouldBe(visible);
        el.shouldHave(attribute("title", creatingData));
    }

    @Step("Create an entry with tag and check that new entry contains created tag")
    public void createEntryWithTag(String text, String tag, String expectedResult) {
        entryListPage.openEntryPage();
        entryPage.fillEntryForm(text);
        entryPage.createTag(tag);
        entryPage.saveEntry();
        Assert.assertTrue(entryListPage.getEntryTag().getText().contains(expectedResult));
        log.info("Created entry have a tag with name '{}'", entryListPage.getEntryTag().getText());
    }

    @Step("Create an entry with tag, and second entry without tag, search entry by tag")
    public EntrySteps searchEntriesByTag(String text, String tag) {
        entryListPage.openEntryPage();
        entryPage.fillEntryForm(text);
        entryPage.createTag(tag);
        entryPage.saveEntry();
        entryListPage.openEntryPage();
        entryPage.fillEntryForm(text);
        entryPage.saveEntry();
        entryListPage.searchByTag();
        return this;
    }
}