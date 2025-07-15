package com.saucedemo.framework.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Página del Carrito de Compras
 * Contiene todos los elementos y métodos relacionados con el carrito
 * 
 * @author Framework Team
 * @version 1.0
 */
public class CartPage extends BasePage {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CartPage.class);
    
    // Localizadores de elementos usando @FindBy
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/removeBt")
    private WebElementFacade removeButton;
    
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/cartBt")
    private WebElementFacade checkoutButton;
    
    // Localizadores adicionales usando By
    private static final By REMOVE_BUTTON_LOCATOR = By.id("com.saucelabs.mydemoapp.android:id/removeBt");
    private static final By CHECKOUT_BUTTON_LOCATOR = By.id("com.saucelabs.mydemoapp.android:id/cartBt");
    
    /**
     * Constructor de la página del carrito
     */
    public CartPage() {
        super();
    }
    
    /**
     * Verifica si la página del carrito está cargada correctamente
     * 
     * @return true si la página está cargada
     */
    @Override
    public boolean isPageLoaded() {
        try {
            return isElementVisible(CHECKOUT_BUTTON_LOCATOR);
        } catch (Exception e) {
            LOGGER.error("Error al verificar si la página del carrito está cargada: {}", e.getMessage());
            return false;
        }
    }
    
    /**
     * Hace clic en el botón de eliminar producto
     */
    public void clickRemoveButton() {
        LOGGER.info("Haciendo clic en el botón de eliminar producto");
        clickElement(REMOVE_BUTTON_LOCATOR);
    }
    
    /**
     * Hace clic en el botón de checkout
     */
    public void clickCheckoutButton() {
        LOGGER.info("Haciendo clic en el botón de checkout");
        clickElement(CHECKOUT_BUTTON_LOCATOR);
    }
    
    /**
     * Verifica si el botón de eliminar está visible
     * 
     * @return true si el botón está visible
     */
    public boolean isRemoveButtonVisible() {
        return isElementVisible(REMOVE_BUTTON_LOCATOR);
    }
    
    /**
     * Verifica si el botón de checkout está habilitado
     * 
     * @return true si el botón está habilitado
     */
    public boolean isCheckoutButtonEnabled() {
        return isElementEnabled(CHECKOUT_BUTTON_LOCATOR);
    }
    
    /**
     * Espera hasta que la página del carrito esté completamente cargada
     */
    public void waitForPageToLoad() {
        LOGGER.debug("Esperando que la página del carrito se cargue completamente");
        waitForElementVisible(CHECKOUT_BUTTON_LOCATOR);
    }
    
    /**
     * Obtiene la lista de productos en el carrito
     * 
     * @return Lista de productos en el carrito
     */
    public java.util.List<String> getCartItems() {
        LOGGER.debug("Obteniendo lista de productos en el carrito");
        return java.util.Arrays.asList("Producto 1", "Producto 2"); // Placeholder
    }
    
    /**
     * Remueve un producto específico del carrito
     * 
     * @param productName Nombre del producto a remover
     */
    public void removeProductFromCart(String productName) {
        LOGGER.info("Removiendo producto del carrito: {}", productName);
        clickRemoveButton();
    }
    
    /**
     * Verifica si un producto está en el carrito
     * 
     * @param productName Nombre del producto
     * @return true si el producto está en el carrito
     */
    public boolean isProductInCart(String productName) {
        LOGGER.debug("Verificando si el producto está en el carrito: {}", productName);
        return true; // Placeholder
    }
    
    /**
     * Obtiene el número de productos en el carrito
     * 
     * @return Número de productos en el carrito
     */
    public int getCartItemsCount() {
        LOGGER.debug("Obteniendo número de productos en el carrito");
        return 1; // Placeholder
    }
} 