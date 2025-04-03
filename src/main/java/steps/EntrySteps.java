package steps;

import io.qameta.allure.Step;
import pages.EntryListPage;
import pages.EntryPage;

public class EntrySteps {

    EntryPage entryPage;
    EntryListPage entryListPage;

    public EntrySteps() {
        this.entryPage = new EntryPage();
        this.entryListPage = new EntryListPage();
    }

    @Step("Open entry page, write a text and save entry")
    public void createEntryAndSave(String text) {
        entryListPage.openEntryPage();
        entryPage.fillEntryForm(text);
        entryPage.saveEntry();
    }

    @Step("Open exist entry6 edit text and save")
    public void editExistEntryAndSave(String text) {
        entryListPage.getEntriesBody().click();
        entryPage.fillEntryForm(text);
        entryPage.saveEntry();
    }

    @Step("Open exist entry6 edit text and save")
    public void deleteFirstEntry() {
        entryListPage.deleteFirstEntry();
    }

    @Step("Open exist entry6 edit text and save")
    public void deleteAllEntries() {
        entryListPage.deleteAllEntries();
    }
}
