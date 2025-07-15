package com.saucedemo.framework.pages;

import com.saucedemo.framework.core.AppiumDriverManager;
import io.appium.java_client.AppiumDriver;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * Clase base para todas las páginas del framework
 * Proporciona métodos comunes para testing móvil con Appium y Serenity
 * 
 * @author Framework Team
 * @version 1.0
 */
public abstract class BasePage extends PageObject {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);
    protected static final int EXPLICIT_WAIT_TIMEOUT = 20;
    protected static final int IMPLICIT_WAIT_TIMEOUT = 10;
    
    /**
     * Constructor de la clase base
     */
    public BasePage() {
        super(AppiumDriverManager.getDriver());
    }
    
    /**
     * Obtiene el driver de Appium actual
     * 
     * @return AppiumDriver actual
     */
    protected AppiumDriver getAppiumDriver() {
        return AppiumDriverManager.getDriver();
    }
    
    /**
     * Espera explícita hasta que un elemento sea visible
     * 
     * @param locator Localizador del elemento
     * @return WebElementFacade del elemento encontrado
     */
    protected WebElementFacade waitForElementVisible(By locator) {
        LOGGER.debug("Esperando elemento visible: {}", locator);
        return element(locator).waitUntilVisible();
    }
    
    /**
     * Espera explícita hasta que un elemento sea clickeable
     * 
     * @param locator Localizador del elemento
     * @return WebElementFacade del elemento encontrado
     */
    protected WebElementFacade waitForElementClickable(By locator) {
        LOGGER.debug("Esperando elemento clickeable: {}", locator);
        WebDriverWait wait = new WebDriverWait(getAppiumDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return element(locator);
    }
    
    /**
     * Hace clic en un elemento con espera explícita
     * 
     * @param locator Localizador del elemento
     */
    protected void clickElement(By locator) {
        LOGGER.debug("Haciendo clic en elemento: {}", locator);
        waitForElementClickable(locator).click();
    }
    
    /**
     * Escribe texto en un campo con espera explícita
     * 
     * @param locator Localizador del elemento
     * @param text Texto a escribir
     */
    protected void typeText(By locator, String text) {
        LOGGER.debug("Escribiendo texto '{}' en elemento: {}", text, locator);
        WebElementFacade element = waitForElementVisible(locator);
        element.clear();
        element.type(text);
    }
    
    /**
     * Obtiene el texto de un elemento
     * 
     * @param locator Localizador del elemento
     * @return Texto del elemento
     */
    protected String getElementText(By locator) {
        LOGGER.debug("Obteniendo texto del elemento: {}", locator);
        return waitForElementVisible(locator).getText();
    }
    
    /**
     * Verifica si un elemento está visible
     * 
     * @param locator Localizador del elemento
     * @return true si el elemento está visible, false en caso contrario
     */
    protected boolean isElementVisible(By locator) {
        try {
            return element(locator).isVisible();
        } catch (Exception e) {
            LOGGER.debug("Elemento no visible: {}", locator);
            return false;
        }
    }
    
    /**
     * Verifica si un elemento está presente en el DOM
     * 
     * @param locator Localizador del elemento
     * @return true si el elemento está presente, false en caso contrario
     */
    protected boolean isElementPresent(By locator) {
        try {
            return element(locator).isPresent();
        } catch (Exception e) {
            LOGGER.debug("Elemento no presente: {}", locator);
            return false;
        }
    }
    
    /**
     * Hace scroll hacia un elemento específico
     * 
     * @param locator Localizador del elemento objetivo
     */
    protected void scrollToElement(By locator) {
        LOGGER.debug("Haciendo scroll hacia elemento: {}", locator);
        WebElement element = getAppiumDriver().findElement(locator);
        JavascriptExecutor js = getAppiumDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    /**
     * Hace scroll hacia abajo en la pantalla
     */
    protected void scrollDown() {
        LOGGER.debug("Haciendo scroll hacia abajo");
        JavascriptExecutor js = getAppiumDriver();
        js.executeScript("window.scrollBy(0, 500);");
    }
    
    /**
     * Hace scroll hacia arriba en la pantalla
     */
    protected void scrollUp() {
        LOGGER.debug("Haciendo scroll hacia arriba");
        JavascriptExecutor js = getAppiumDriver();
        js.executeScript("window.scrollBy(0, -500);");
    }
    
    /**
     * Espera hasta que un elemento desaparezca
     * 
     * @param locator Localizador del elemento
     */
    protected void waitForElementToDisappear(By locator) {
        LOGGER.debug("Esperando que elemento desaparezca: {}", locator);
        WebDriverWait wait = new WebDriverWait(getAppiumDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    
    /**
     * Obtiene el atributo de un elemento
     * 
     * @param locator Localizador del elemento
     * @param attribute Nombre del atributo
     * @return Valor del atributo
     */
    protected String getElementAttribute(By locator, String attribute) {
        LOGGER.debug("Obteniendo atributo '{}' del elemento: {}", attribute, locator);
        return waitForElementVisible(locator).getAttribute(attribute);
    }
    
    /**
     * Verifica si un elemento está habilitado
     * 
     * @param locator Localizador del elemento
     * @return true si el elemento está habilitado, false en caso contrario
     */
    protected boolean isElementEnabled(By locator) {
        try {
            return element(locator).isEnabled();
        } catch (Exception e) {
            LOGGER.debug("Elemento no habilitado: {}", locator);
            return false;
        }
    }
    
    /**
     * Espera un tiempo específico en milisegundos
     * 
     * @param milliseconds Tiempo a esperar en milisegundos
     */
    protected void waitForMilliseconds(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.warn("Espera interrumpida: {}", e.getMessage());
        }
    }
    
    /**
     * Obtiene el título de la página actual
     * 
     * @return Título de la página
     */
    protected String getPageTitle() {
        return getAppiumDriver().getTitle();
    }
    
    /**
     * Verifica si el texto está presente en la página
     * 
     * @param text Texto a buscar
     * @return true si el texto está presente, false en caso contrario
     */
    protected boolean isTextPresent(String text) {
        try {
            return getAppiumDriver().getPageSource().contains(text);
        } catch (Exception e) {
            LOGGER.debug("Texto no presente: {}", text);
            return false;
        }
    }
    
    /**
     * Método abstracto para verificar que la página está cargada
     * Cada página debe implementar su propia lógica de verificación
     * 
     * @return true si la página está cargada correctamente
     */
    public abstract boolean isPageLoaded();
} 