package tests;

import org.testng.annotations.Test;

public class EntriesTests extends BaseTest {

    @Test
    public void createEntryTest() {
        loginSteps.login(USER, PASSWORD, LOGIN_URL);
        entryListPage.createEntry(ENTRY_TEXT);
    }
}