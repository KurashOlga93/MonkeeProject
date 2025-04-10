package steps;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.Waiter;
import io.qameta.allure.Step;
import org.testng.Assert;
import pages.EntryListPage;
import pages.EntryPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

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
    public void checkDateAndTimeOfCreatedEntry (String text) {
        entryListPage.openEntryPage();
        entryPage.fillEntryForm(text);
        String creatingData = entryPage.getDateAndTimeWhenCreatingEntry();
        entryPage.saveEntry();
        SelenideElement element = entryListPage.getDateAndTimeOfCreatedEntry(creatingData);
        element.shouldBe(exist, Duration.ofSeconds(5));
    }
}