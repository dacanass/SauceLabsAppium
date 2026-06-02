package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {
    @Test
    public void successfullLoginVerification(){
        loginPage().openLoginPage();
        loginPage().loginSucces("bod@example.com","10203040");
        Assertions.assertTrue(catalogPage().isTitleDisplayed());
    }
}
