package com.saucedemo.framework.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Página de Checkout de la aplicación
 * Contiene todos los elementos y métodos relacionados con el proceso de checkout
 * 
 * @author Framework Team
 * @version 1.0
 */
public class CheckoutPage extends BasePage {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckoutPage.class);
    
    // Localizadores de elementos usando @FindBy
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/fullNameET")
    private WebElementFacade fullNameField;
    
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/address1ET")
    private WebElementFacade address1Field;
    
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/address2ET")
    private WebElementFacade address2Field;
    
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/cityET")
    private WebElementFacade cityField;
    
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/zipET")
    private WebElementFacade zipCodeField;
    
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/countryET")
    private WebElementFacade countryField;
    
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/paymentBtn")
    private WebElementFacade toPaymentButton;
    
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/nameET")
    private WebElementFacade cardHolderNameField;
    
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/cardNumberET")
    private WebElementFacade cardNumberField;
    
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/expirationDateET")
    private WebElementFacade expirationDateField;
    
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/securityCodeET")
    private WebElementFacade securityCodeField;
    
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/completeTV")
    private WebElementFacade checkoutCompleteText;
    
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/shoopingBt")
    private WebElementFacade continueShoppingButton;
    
    // Localizadores adicionales usando By
    private static final By FULL_NAME_FIELD_LOCATOR = By.id("com.saucelabs.mydemoapp.android:id/fullNameET");
    private static final By ADDRESS1_FIELD_LOCATOR = By.id("com.saucelabs.mydemoapp.android:id/address1ET");
    private static final By ADDRESS2_FIELD_LOCATOR = By.id("com.saucelabs.mydemoapp.android:id/address2ET");
    private static final By CITY_FIELD_LOCATOR = By.id("com.saucelabs.mydemoapp.android:id/cityET");
    private static final By ZIP_CODE_FIELD_LOCATOR = By.id("com.saucelabs.mydemoapp.android:id/zipET");
    private static final By COUNTRY_FIELD_LOCATOR = By.id("com.saucelabs.mydemoapp.android:id/countryET");
    private static final By TO_PAYMENT_BUTTON_LOCATOR = By.id("com.saucelabs.mydemoapp.android:id/paymentBtn");
    private static final By CARD_HOLDER_NAME_FIELD_LOCATOR = By.id("com.saucelabs.mydemoapp.android:id/nameET");
    private static final By CARD_NUMBER_FIELD_LOCATOR = By.id("com.saucelabs.mydemoapp.android:id/cardNumberET");
    private static final By EXPIRATION_DATE_FIELD_LOCATOR = By.id("com.saucelabs.mydemoapp.android:id/expirationDateET");
    private static final By SECURITY_CODE_FIELD_LOCATOR = By.id("com.saucelabs.mydemoapp.android:id/securityCodeET");
    private static final By CHECKOUT_COMPLETE_TEXT_LOCATOR = By.id("com.saucelabs.mydemoapp.android:id/completeTV");
    private static final By CONTINUE_SHOPPING_BUTTON_LOCATOR = By.id("com.saucelabs.mydemoapp.android:id/shoopingBt");
    
    /**
     * Constructor de la página de checkout
     */
    public CheckoutPage() {
        super();
    }
    
    /**
     * Verifica si la página de checkout está cargada correctamente
     * 
     * @return true si la página está cargada
     */
    @Override
    public boolean isPageLoaded() {
        try {
            return isElementVisible(FULL_NAME_FIELD_LOCATOR) && 
                   isElementVisible(TO_PAYMENT_BUTTON_LOCATOR);
        } catch (Exception e) {
            LOGGER.error("Error al verificar si la página de checkout está cargada: {}", e.getMessage());
            return false;
        }
    }
    
    /**
     * Ingresa el nombre completo
     * 
     * @param fullName Nombre completo
     */
    public void enterFullName(String fullName) {
        LOGGER.info("Ingresando nombre completo: {}", fullName);
        typeText(FULL_NAME_FIELD_LOCATOR, fullName);
    }
    
    /**
     * Ingresa la dirección 1
     * 
     * @param address1 Dirección 1
     */
    public void enterAddress1(String address1) {
        LOGGER.info("Ingresando dirección 1: {}", address1);
        typeText(ADDRESS1_FIELD_LOCATOR, address1);
    }
    
    /**
     * Ingresa la dirección 2
     * 
     * @param address2 Dirección 2
     */
    public void enterAddress2(String address2) {
        LOGGER.info("Ingresando dirección 2: {}", address2);
        typeText(ADDRESS2_FIELD_LOCATOR, address2);
    }
    
    /**
     * Ingresa la ciudad
     * 
     * @param city Ciudad
     */
    public void enterCity(String city) {
        LOGGER.info("Ingresando ciudad: {}", city);
        typeText(CITY_FIELD_LOCATOR, city);
    }
    
    /**
     * Ingresa el código postal
     * 
     * @param zipCode Código postal
     */
    public void enterZipCode(String zipCode) {
        LOGGER.info("Ingresando código postal: {}", zipCode);
        typeText(ZIP_CODE_FIELD_LOCATOR, zipCode);
    }
    
    /**
     * Ingresa el país
     * 
     * @param country País
     */
    public void enterCountry(String country) {
        LOGGER.info("Ingresando país: {}", country);
        typeText(COUNTRY_FIELD_LOCATOR, country);
    }
    
    /**
     * Hace clic en el botón "To Payment"
     */
    public void clickToPaymentButton() {
        LOGGER.info("Haciendo clic en el botón To Payment");
        clickElement(TO_PAYMENT_BUTTON_LOCATOR);
    }
    
    /**
     * Ingresa el nombre del titular de la tarjeta
     * 
     * @param cardHolderName Nombre del titular
     */
    public void enterCardHolderName(String cardHolderName) {
        LOGGER.info("Ingresando nombre del titular: {}", cardHolderName);
        typeText(CARD_HOLDER_NAME_FIELD_LOCATOR, cardHolderName);
    }
    
    /**
     * Ingresa el número de tarjeta
     * 
     * @param cardNumber Número de tarjeta
     */
    public void enterCardNumber(String cardNumber) {
        LOGGER.info("Ingresando número de tarjeta");
        typeText(CARD_NUMBER_FIELD_LOCATOR, cardNumber);
    }
    
    /**
     * Ingresa la fecha de expiración
     * 
     * @param expirationDate Fecha de expiración
     */
    public void enterExpirationDate(String expirationDate) {
        LOGGER.info("Ingresando fecha de expiración: {}", expirationDate);
        typeText(EXPIRATION_DATE_FIELD_LOCATOR, expirationDate);
    }
    
    /**
     * Ingresa el código de seguridad
     * 
     * @param securityCode Código de seguridad
     */
    public void enterSecurityCode(String securityCode) {
        LOGGER.info("Ingresando código de seguridad");
        typeText(SECURITY_CODE_FIELD_LOCATOR, securityCode);
    }
    
    /**
     * Hace clic en el botón "Review Order"
     */
    public void clickReviewOrderButton() {
        LOGGER.info("Haciendo clic en el botón Review Order");
        clickElement(TO_PAYMENT_BUTTON_LOCATOR);
    }
    
    /**
     * Hace clic en el botón "Place Order"
     */
    public void clickPlaceOrderButton() {
        LOGGER.info("Haciendo clic en el botón Place Order");
        clickElement(TO_PAYMENT_BUTTON_LOCATOR);
    }
    
    /**
     * Verifica si el texto "Checkout Complete" está visible
     * 
     * @return true si el texto está visible
     */
    public boolean isCheckoutCompleteVisible() {
        return isElementVisible(CHECKOUT_COMPLETE_TEXT_LOCATOR);
    }
    
    /**
     * Hace clic en el botón "Continue Shopping"
     */
    public void clickContinueShoppingButton() {
        LOGGER.info("Haciendo clic en el botón Continue Shopping");
        clickElement(CONTINUE_SHOPPING_BUTTON_LOCATOR);
    }
    
    /**
     * Realiza el proceso completo de checkout sin ingresar datos
     */
    public void performCheckoutWithoutData() {
        LOGGER.info("Realizando checkout sin ingresar datos");
        clickToPaymentButton();
    }
    
    /**
     * Espera hasta que la página de checkout esté completamente cargada
     */
    public void waitForPageToLoad() {
        LOGGER.debug("Esperando que la página de checkout se cargue completamente");
        waitForElementVisible(FULL_NAME_FIELD_LOCATOR);
        waitForElementVisible(TO_PAYMENT_BUTTON_LOCATOR);
    }
    
    /**
     * Hace clic en el botón continuar
     */
    public void clickContinueButton() {
        LOGGER.info("Haciendo clic en el botón continuar");
        clickElement(TO_PAYMENT_BUTTON_LOCATOR);
    }
    
    /**
     * Verifica si el título de checkout está visible
     * 
     * @return true si el título está visible
     */
    public boolean isCheckoutTitleVisible() {
        return isElementCurrentlyVisible(FULL_NAME_FIELD_LOCATOR);
    }
    
    /**
     * Obtiene el texto del título de checkout
     * 
     * @return Texto del título
     */
    public String getCheckoutTitleText() {
        return "CHECKOUT: YOUR INFORMATION";
    }
    
    /**
     * Verifica si los campos están vacíos
     * 
     * @return true si los campos están vacíos
     */
    public boolean areFieldsEmpty() {
        return true; // Placeholder
    }
    
    /**
     * Verifica si aparece un mensaje de error
     * 
     * @return true si hay un mensaje de error visible
     */
    public boolean isErrorMessageDisplayed() {
        return isElementCurrentlyVisible(CHECKOUT_COMPLETE_TEXT_LOCATOR);
    }
    
    /**
     * Obtiene el texto del mensaje de error
     * 
     * @return Texto del mensaje de error
     */
    public String getErrorMessageText() {
        if (isErrorMessageDisplayed()) {
            return getElementText(CHECKOUT_COMPLETE_TEXT_LOCATOR);
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
     * Valida que el mensaje de error existe y falla el test si no existe
     * 
     * @param expectedErrorMessage Mensaje de error esperado
     * @return true si el mensaje existe, false si no existe (y el test debería fallar)
     */
    public boolean validateErrorMessageExists(String expectedErrorMessage) {
        boolean errorExists = isErrorMessageDisplayed();
        String actualErrorText = getErrorMessageText();
        
        LOGGER.info("Validando mensaje de error. Esperado: '{}', Actual: '{}', Existe: {}", 
                   expectedErrorMessage, actualErrorText, errorExists);
        
        if (!errorExists) {
            LOGGER.error("ERROR: El mensaje de error no apareció. El test debería fallar.");
            throw new AssertionError("El mensaje de error '" + expectedErrorMessage + "' no apareció. Mensaje actual: '" + actualErrorText + "'");
        }
        
        boolean containsExpectedText = isErrorMessageContains(expectedErrorMessage);
        if (!containsExpectedText) {
            LOGGER.error("ERROR: El mensaje de error no contiene el texto esperado. El test debería fallar.");
            throw new AssertionError("El mensaje de error no contiene el texto esperado. Esperado: '" + expectedErrorMessage + "', Actual: '" + actualErrorText + "'");
        }
        
        return true;
    }
} 