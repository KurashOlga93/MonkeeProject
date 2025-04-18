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
    public void searchEntryTest() {
        loginSteps.login(USER, PASSWORD, LOGIN_URL)
                .createEntryAndSave(ENTRY_TEXT)
                .createEntryAndSave(CHANGE_ENTRY_TEXT)
                .searchByText(SEARCH_TEXT)
                .checkEntriesListSize(1)
                .checkFirstEntryContainsText("something");
    }

    @Test(description = "Login, create entry and save date/time when entry created, check date/time of entry in entries list")
    public void checkDateAndTimeOfEntryTest() {
        loginSteps.login(USER, PASSWORD, LOGIN_URL);
        entrySteps.checkDateAndTimeOfCreatedEntry(ENTRY_TEXT);
    }

    @Test(description = "Login, create entry with tag, check that entry contains a created tag")
    public void checkEntryTagTest() {
        loginSteps.login(USER, PASSWORD, LOGIN_URL);
        entrySteps.checkNewEntryWithTag(ENTRY_TEXT, TAG_NAME, "Apple");
    }

    @Test(description = "Login, create entry with tag and entry without tag, search one entry by tag")
    public void searchEntriesByTagTest() {
        loginSteps.login(USER, PASSWORD, LOGIN_URL);
        entrySteps.searchEntriesByTag(ENTRY_TEXT, TAG_NAME)
                .checkEntriesListSize(1);
    }
}