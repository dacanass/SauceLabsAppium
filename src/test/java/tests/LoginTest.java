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
        sideMenu().goToLogin();
        loginPage().loginSuccess("bod@example.com","10203040");
        Assertions.assertTrue(catalogPage().isTitleDisplayed());
    }

    @Test
    @DisplayName("MDA-18: Login con una cuenta bloqueada / locked out")
    public void lockedoutLogin(){
        sideMenu().goToLogin();
        loginPage().loginSuccess("alice@example.com","10203040");
        Assertions.assertTrue(loginPage().isLocketOutErrorDisplayed(), "¡Error! El mensaje de validación para una cuenta bloqueada no apareció.");
    }

    @Test
    @DisplayName("MDA-19: Verificar mensaje de error cuando no se proporciona el usuario")
    public void emptyUsernameError(){
        sideMenu().goToLogin();
        loginPage().loginSuccess("","10203040");
        Assertions.assertTrue(loginPage().isUsernameRequiredErrorDisplayed(), "¡Error! El mensaje de validación para el usuario vacío no apareció.");
    }

    @Test
    @DisplayName("MDA-20: Verificar mensaje de error cuando no se proporciona la contraseña")
    public void emptyPasswordError(){
        sideMenu().goToLogin();
        loginPage().loginSuccess("bod@example.com","");
        Assertions.assertTrue(loginPage().isPasswordRequiredErrorDisplayed(), "¡Error! El mensaje de validación para la contraseña vacía no apareció.");
    }

}
