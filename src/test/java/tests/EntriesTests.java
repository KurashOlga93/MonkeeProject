package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EntriesTests extends BaseTest {

    @Test
    public void createEntryTest() {
        loginSteps.login(USER, PASSWORD, LOGIN_URL);
        entrySteps.createEntryAndSave(ENTRY_TEXT);
        Assert.assertEquals(entryListPage.getFirstEntryBody().getText(), "This is my first note.");
    }

    @Test
    public void editEntryTest() {
        loginSteps.login(USER, PASSWORD, LOGIN_URL);
        entrySteps.createEntryAndSave(ENTRY_TEXT);
        entrySteps.editExistEntryAndSave(ENTRY_TEXT1);
        softAssert.assertEquals(entryListPage.getFirstEntryBody().getText(), "I would like to add something else here...");
        softAssert.assertEquals(entryListPage.checkEntriesListSize(), 1);
        softAssert.assertAll();
    }

    @Test
    public void deleteFirstEntryTest() {
        loginSteps.login(USER, PASSWORD, LOGIN_URL);
        entrySteps.createEntryAndSave(ENTRY_TEXT);
        entrySteps.createEntryAndSave(ENTRY_TEXT);
        entrySteps.deleteFirstEntry();
        Assert.assertEquals(entryListPage.checkEntriesListSize(), 1);
    }

    @Test
    public void deleteAllEntriesTest() {
        loginSteps.login(USER, PASSWORD, LOGIN_URL);
        entrySteps.createEntryAndSave(ENTRY_TEXT);
        entrySteps.createEntryAndSave(ENTRY_TEXT);
        entrySteps.deleteAllEntries();
        Assert.assertEquals(entryListPage.checkEntriesListSize(), 0);
    }

    @Test
    public void searchFieldTest() {
        loginSteps.login(USER, PASSWORD, LOGIN_URL);
        entrySteps.createEntryAndSave(ENTRY_TEXT);
        entrySteps.createEntryAndSave(ENTRY_TEXT1);

        softAssert.assertEquals(entryListPage.checkEntriesListSize(), 1);
        softAssert.assertTrue(entryListPage.getFirstEntryBody().getText().contains("something"));
    }
}