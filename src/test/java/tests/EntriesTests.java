package tests;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

import static tests.LoginTests.*;

public class EntriesTests extends BaseTest{

    @Test
    public void createEntryTest(){
        loginSteps.login(USER, PASSWORD, LOGIN_URL);
    }
}
