package tests;

import org.testng.annotations.Test;

public class EntriesTests extends BaseTest implements ITestConstants {

    @Test(description = "Login user, create new entry and check body text of created entry")
    public void createEntryTest() {
        loginSteps.login(USER, PASSWORD, LOGIN_URL)
                .createEntryAndSave(ENTRY_TEXT)
                .checkFirstEntryText(ENTRY_TEXT);
    }

    @Test(description = "Login user, create new entry, open created entry and edit text, check edited text")
    public void editEntryTest() {
        loginSteps.login(USER, PASSWORD, LOGIN_URL)
                .createEntryAndSave(ENTRY_TEXT)
                .editExistEntryAndSave(CHANGE_ENTRY_TEXT)
                .checkEntriesListSize(1)
                .checkFirstEntryText(CHANGE_ENTRY_TEXT);
    }

    @Test(description = "Login user, create two entries, delete the first entry and check that one entry remains")
    public void deleteFirstEntryTest() {
        loginSteps.login(USER, PASSWORD, LOGIN_URL)
                .createEntryAndSave(ENTRY_TEXT)
                .createEntryAndSave(ENTRY_TEXT)
                .deleteFirstEntry()
                .checkEntriesListSize(1);
    }

    @Test(description = "Login user, create two entries, delete all entries and check that there are no more entries")
    public void deleteAllEntriesTest() {
        loginSteps.login(USER, PASSWORD, LOGIN_URL)
                .createEntryAndSave(ENTRY_TEXT)
                .createEntryAndSave(ENTRY_TEXT)
                .deleteAllEntries()
                .checkEntriesListSize(0);
    }

    @Test(description = "Login user, create two different entries, search the text of one of the entries and check that it was found")
    public void searchFieldTest() {
        loginSteps.login(USER, PASSWORD, LOGIN_URL)
                .createEntryAndSave(ENTRY_TEXT)
                .createEntryAndSave(CHANGE_ENTRY_TEXT)
                .searchByText(SEARCH_TEXT)
                .checkEntriesListSize(1)
                .checkFirstEntryContainsText("something");
    }

    @Test(description = "")
    public void checkDateAndTimeOfEntry() {
        loginSteps.login(USER, PASSWORD, LOGIN_URL);
        entrySteps.checkDateAndTimeOfCreatedEntry(ENTRY_TEXT);

    }
}