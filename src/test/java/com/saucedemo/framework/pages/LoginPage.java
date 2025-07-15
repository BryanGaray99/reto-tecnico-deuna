package com.saucedemo.framework.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Página de Login de la aplicación Sauce Demo
 * Contiene todos los elementos y métodos relacionados con la autenticación
 * 
 * @author Framework Team
 * @version 1.0
 */
public class LoginPage extends BasePage {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginPage.class);
    
    // Localizadores de elementos usando @FindBy
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/nameET")
    private WebElementFacade usernameField;
    
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/passwordET")
    private WebElementFacade passwordField;
    
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/loginBtn")
    private WebElementFacade loginButton;
    
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/errorTV")
    private WebElementFacade errorMessage;
    
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/menuIV")
    private WebElementFacade menuButton;
    
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/itemTV")
    private WebElementFacade menuItem;
    
    // Localizadores adicionales usando By
    private static final By USERNAME_FIELD_LOCATOR = By.id("com.saucelabs.mydemoapp.android:id/nameET");
    private static final By PASSWORD_FIELD_LOCATOR = By.id("com.saucelabs.mydemoapp.android:id/passwordET");
    private static final By LOGIN_BUTTON_LOCATOR = By.id("com.saucelabs.mydemoapp.android:id/loginBtn");
    private static final By ERROR_MESSAGE_LOCATOR = By.id("com.saucelabs.mydemoapp.android:id/errorTV");
    private static final By MENU_BUTTON_LOCATOR = By.id("com.saucelabs.mydemoapp.android:id/menuIV");
    private static final By MENU_ITEM_LOCATOR = By.id("com.saucelabs.mydemoapp.android:id/itemTV");
    
    /**
     * Constructor de la página de login
     */
    public LoginPage() {
        super();
    }
    
    /**
     * Verifica si la página de login está cargada correctamente
     * 
     * @return true si la página está cargada
     */
    @Override
    public boolean isPageLoaded() {
        try {
            return isElementVisible(USERNAME_FIELD_LOCATOR) && 
                   isElementVisible(PASSWORD_FIELD_LOCATOR) && 
                   isElementVisible(LOGIN_BUTTON_LOCATOR);
        } catch (Exception e) {
            LOGGER.error("Error al verificar si la página de login está cargada: {}", e.getMessage());
            return false;
        }
    }
    
    /**
     * Ingresa el nombre de usuario en el campo correspondiente
     * 
     * @param username Nombre de usuario a ingresar
     */
    public void enterUsername(String username) {
        LOGGER.info("Ingresando nombre de usuario: {}", username);
        typeText(USERNAME_FIELD_LOCATOR, username);
    }
    
    /**
     * Ingresa la contraseña en el campo correspondiente
     * 
     * @param password Contraseña a ingresar
     */
    public void enterPassword(String password) {
        LOGGER.info("Ingresando contraseña");
        typeText(PASSWORD_FIELD_LOCATOR, password);
    }
    
    /**
     * Hace clic en el botón de login
     */
    public void clickLoginButton() {
        LOGGER.info("Haciendo clic en el botón de login");
        clickElement(LOGIN_BUTTON_LOCATOR);
    }
    
    /**
     * Realiza el proceso completo de login
     * 
     * @param username Nombre de usuario
     * @param password Contraseña
     */
    public void performLogin(String username, String password) {
        LOGGER.info("Realizando login con usuario: {}", username);
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
    
    /**
     * Limpia el campo de nombre de usuario
     */
    public void clearUsernameField() {
        LOGGER.debug("Limpiando campo de nombre de usuario");
        waitForElementVisible(USERNAME_FIELD_LOCATOR).clear();
    }
    
    /**
     * Limpia el campo de contraseña
     */
    public void clearPasswordField() {
        LOGGER.debug("Limpiando campo de contraseña");
        waitForElementVisible(PASSWORD_FIELD_LOCATOR).clear();
    }
    
    /**
     * Verifica si aparece un mensaje de error
     * 
     * @return true si hay un mensaje de error visible
     */
    public boolean isErrorMessageDisplayed() {
        try {
            return isElementVisible(ERROR_MESSAGE_LOCATOR);
        } catch (Exception e) {
            LOGGER.debug("No se encontró mensaje de error");
            return false;
        }
    }
    
    /**
     * Obtiene el texto del mensaje de error
     * 
     * @return Texto del mensaje de error
     */
    public String getErrorMessageText() {
        if (isErrorMessageDisplayed()) {
            return getElementText(ERROR_MESSAGE_LOCATOR);
        }
        return "";
    }
    
    /**
     * Verifica si el mensaje de error contiene un texto específico
     * 
     * @param expectedText Texto esperado en el mensaje de error
     * @return true si el mensaje contiene el texto esperado
     */
    public boolean isErrorMessageContains(String expectedText) {
        String actualErrorText = getErrorMessageText();
        boolean contains = actualErrorText.contains(expectedText);
        LOGGER.debug("Verificando mensaje de error. Esperado: '{}', Actual: '{}', Contiene: {}", 
                    expectedText, actualErrorText, contains);
        return contains;
    }
    
    /**
     * Verifica si el campo de nombre de usuario está habilitado
     * 
     * @return true si el campo está habilitado
     */
    public boolean isUsernameFieldEnabled() {
        return isElementEnabled(USERNAME_FIELD_LOCATOR);
    }
    
    /**
     * Verifica si el campo de contraseña está habilitado
     * 
     * @return true si el campo está habilitado
     */
    public boolean isPasswordFieldEnabled() {
        return isElementEnabled(PASSWORD_FIELD_LOCATOR);
    }
    
    /**
     * Verifica si el botón de login está habilitado
     * 
     * @return true si el botón está habilitado
     */
    public boolean isLoginButtonEnabled() {
        return isElementEnabled(LOGIN_BUTTON_LOCATOR);
    }
    
    /**
     * Obtiene el texto del campo de nombre de usuario
     * 
     * @return Texto del campo de usuario
     */
    public String getUsernameFieldText() {
        return getElementText(USERNAME_FIELD_LOCATOR);
    }
    
    /**
     * Obtiene el texto del campo de contraseña
     * 
     * @return Texto del campo de contraseña
     */
    public String getPasswordFieldText() {
        return getElementText(PASSWORD_FIELD_LOCATOR);
    }
    
    /**
     * Verifica si los campos están vacíos
     * 
     * @return true si ambos campos están vacíos
     */
    public boolean areFieldsEmpty() {
        String usernameText = getUsernameFieldText();
        String passwordText = getPasswordFieldText();
        return usernameText.isEmpty() && passwordText.isEmpty();
    }
    
    /**
     * Espera hasta que la página de login esté completamente cargada
     */
    public void waitForPageToLoad() {
        LOGGER.debug("Esperando que la página de login se cargue completamente");
        waitForElementVisible(USERNAME_FIELD_LOCATOR);
        waitForElementVisible(PASSWORD_FIELD_LOCATOR);
        waitForElementVisible(LOGIN_BUTTON_LOCATOR);
    }
} 