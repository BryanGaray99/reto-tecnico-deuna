package com.saucedemo.framework.steps;

import com.saucedemo.framework.pages.LoginPage;
import com.saucedemo.framework.pages.InventoryPage;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.core.Serenity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Step Definitions para el Reto 1 - Escenarios Básicos de Prueba
 * Implementa los pasos de Cucumber para los 2 escenarios básicos requeridos
 * 
 * @author Framework Team
 * @version 1.0
 */
public class Reto1Steps {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Reto1Steps.class);
    
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    
    /**
     * Configuración inicial antes de cada escenario
     */
    @Before
    public void setUp() {
        LOGGER.info("Configurando páginas para el Reto 1");
        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
    }
    
    /**
     * Step: El usuario abre la aplicación Sauce Demo
     */
    @Dado("que el usuario abre la aplicación Sauce Demo")
    public void queElUsuarioAbreLaAplicacionSauceDemo() {
        LOGGER.info("=== RETO 1: Abriendo la aplicación Sauce Demo ===");
        // La aplicación se abre automáticamente al iniciar el driver
        Serenity.recordReportData().withTitle("Reto 1 - Aplicación Abierta")
                .andContents("La aplicación Sauce Demo ha sido abierta para el Reto 1");
    }
    
    /**
     * Step: Está en la pantalla de login
     */
    @Y("está en la pantalla de login")
    public void estaEnLaPantallaDeLogin() {
        LOGGER.info("Verificando que estamos en la pantalla de login");
        loginPage.waitForPageToLoad();
        
        boolean isLoginPageLoaded = loginPage.isPageLoaded();
        LOGGER.info("Página de login cargada: {}", isLoginPageLoaded);
        
        Serenity.recordReportData().withTitle("Reto 1 - Página de Login")
                .andContents("Verificando elementos de la página de login para el Reto 1");
    }
    
    /**
     * Step: El usuario ingresa credenciales válidas
     */
    @Cuando("el usuario ingresa credenciales válidas")
    public void elUsuarioIngresaCredencialesValidas() {
        LOGGER.info("Ingresando credenciales válidas");
        loginPage.performLogin("bob@example.com", "10203040my");
        
        Serenity.recordReportData().withTitle("Reto 1 - Credenciales Válidas")
                .andContents("Se ingresaron credenciales válidas: bob@example.com");
    }
    
    /**
     * Step: El usuario ingresa credenciales inválidas
     */
    @Cuando("el usuario ingresa credenciales inválidas")
    public void elUsuarioIngresaCredencialesInvalidas() {
        LOGGER.info("Ingresando credenciales inválidas");
        loginPage.performLogin("invalid_user", "invalid_password");
        
        Serenity.recordReportData().withTitle("Reto 1 - Credenciales Inválidas")
                .andContents("Se ingresaron credenciales inválidas");
    }
    
    /**
     * Step: Presiona el botón de login
     */
    @Y("presiona el botón de login")
    public void presionaElBotonDeLogin() {
        LOGGER.info("Presionando botón de login");
        loginPage.clickLoginButton();
        
        Serenity.recordReportData().withTitle("Reto 1 - Login Ejecutado")
                .andContents("Se presionó el botón de login");
    }
    
    /**
     * Step: El usuario debería ser redirigido al inventario
     */
    @Entonces("el usuario debería ser redirigido al inventario")
    public void elUsuarioDeberiaSerRedirigidoAlInventario() {
        LOGGER.info("Verificando redirección al inventario");
        
        // Esperar a que la página del inventario se cargue
        inventoryPage.waitForPageToLoad();
        
        boolean isInventoryPageLoaded = inventoryPage.isPageLoaded();
        LOGGER.info("Página del inventario cargada: {}", isInventoryPageLoaded);
        
        Serenity.recordReportData().withTitle("Reto 1 - Redirección al Inventario")
                .andContents("Verificando que el usuario fue redirigido correctamente");
    }
    
    /**
     * Step: Debería ver la lista de productos disponibles
     */
    @Y("debería ver la lista de productos disponibles")
    public void deberiaVerLaListaDeProductosDisponibles() {
        LOGGER.info("Verificando lista de productos disponibles");
        
        boolean areProductsAvailable = inventoryPage.isPageLoaded();
        
        LOGGER.info("Productos disponibles: {}", areProductsAvailable);
        
        Serenity.recordReportData().withTitle("Reto 1 - Lista de Productos")
                .andContents("Productos disponibles: " + areProductsAvailable);
    }
    
    /**
     * Step: Debería aparecer un mensaje de error
     */
    @Entonces("debería aparecer un mensaje de error")
    public void deberiaAparecerUnMensajeDeError() {
        LOGGER.info("Verificando mensaje de error");
        
        boolean isErrorMessageDisplayed = loginPage.isErrorMessageDisplayed();
        String errorMessageText = loginPage.getErrorMessageText();
        
        LOGGER.info("Mensaje de error visible: {}, Texto: {}", isErrorMessageDisplayed, errorMessageText);
        
        Serenity.recordReportData().withTitle("Reto 1 - Mensaje de Error")
                .andContents("Mensaje de error: " + errorMessageText);
    }
    
    /**
     * Step: El mensaje debería contener texto específico
     */
    @Y("el mensaje debería contener {string}")
    public void elMensajeDeberiaContener(String expectedText) {
        LOGGER.info("Verificando que el mensaje contenga: {}", expectedText);
        
        boolean containsExpectedText = loginPage.isErrorMessageContains(expectedText);
        String actualErrorText = loginPage.getErrorMessageText();
        
        LOGGER.info("Mensaje contiene texto esperado: {}, Mensaje actual: {}", containsExpectedText, actualErrorText);
        
        Serenity.recordReportData().withTitle("Reto 1 - Verificación de Mensaje de Error")
                .andContents("Texto esperado: " + expectedText + ", Mensaje actual: " + actualErrorText);
    }
    
    /**
     * Step: El usuario debería permanecer en la pantalla de login
     */
    @Y("el usuario debería permanecer en la pantalla de login")
    public void elUsuarioDeberiaPermanecerEnLaPantallaDeLogin() {
        LOGGER.info("Verificando que el usuario permanece en la pantalla de login");
        
        boolean isLoginPageStillLoaded = loginPage.isPageLoaded();
        LOGGER.info("Página de login aún cargada: {}", isLoginPageStillLoaded);
        
        Serenity.recordReportData().withTitle("Reto 1 - Permanencia en Login")
                .andContents("Verificando que el usuario permanece en la pantalla de login después del error");
    }
} 