package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("auth")
public class LoginTest extends BaseTest {
    @Test
    @DisplayName("MDA-17: Login con una cuenta válida")
    public void successfulLogin(){
        loginPage().openLoginPage();
        loginPage().loginSuccess("bod@example.com","10203040");
        Assertions.assertTrue(catalogPage().isTitleDisplayed());
    }

    @Test
    @DisplayName("MDA-18: Login con una cuenta bloqueada / locked out")
    public void lockedoutLogin(){
        loginPage().openLoginPage();
        loginPage().loginSuccess("alice@example.com","10203040");
        Assertions.assertTrue(loginPage().isLocketOutErrorDisplayed());
    }
}
