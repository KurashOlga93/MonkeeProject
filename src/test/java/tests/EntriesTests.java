package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EntriesTests extends BaseTest {

    @Test(description = "Login user, create new entry and check body text of created entry")
    public void createEntryTest() {
        loginSteps.login(USER, PASSWORD, LOGIN_URL);
        entrySteps.createEntryAndSave(ENTRY_TEXT);
        Assert.assertEquals(entryListPage.getFirstEntryBody().getText(), "This is my first note.");
    }

    @Test(description = "Login user, create new entry, open created entry and edit text, check edited text")
    public void editEntryTest() {
        loginSteps.login(USER, PASSWORD, LOGIN_URL);
        entrySteps.createEntryAndSave(ENTRY_TEXT);
        entrySteps.editExistEntryAndSave(CHANGE_ENTRY_TEXT);
        entryListPage.checkEntriesListSize(1);
        softAssert.assertEquals(entryListPage.getFirstEntryBody().getText(), "I would like to add something else here...");
        softAssert.assertAll();
    }

    @Test(description = "Login user, create two entries, delete the first entry and check that one entry remains")
    public void deleteFirstEntryTest() {
        loginSteps.login(USER, PASSWORD, LOGIN_URL);
        entrySteps.createEntryAndSave(ENTRY_TEXT);
        entrySteps.createEntryAndSave(ENTRY_TEXT);
        entrySteps.deleteFirstEntry();
        entryListPage.checkEntriesListSize(1);
    }

    @Test(description = "Login user, create two entries, delete all entries and check that there are no more entries")
    public void deleteAllEntriesTest() {
        loginSteps.login(USER, PASSWORD, LOGIN_URL);
        entrySteps.createEntryAndSave(ENTRY_TEXT);
        entrySteps.createEntryAndSave(ENTRY_TEXT);
        entrySteps.deleteAllEntries();
        entryListPage.checkEntriesListSize(0);
    }

    @Test(description = "Login user, create two different entries, search the text of one of the entries and check that it was found")
    public void searchFieldTest() {
        loginSteps.login(USER, PASSWORD, LOGIN_URL);
        entrySteps.createEntryAndSave(ENTRY_TEXT);
        entrySteps.createEntryAndSave(CHANGE_ENTRY_TEXT);
        entryListPage.searchByText("I would like")
                    .checkEntriesListSize(1);
        softAssert.assertTrue(entryListPage.getFirstEntryBody().getText().contains("something"));
    }
}