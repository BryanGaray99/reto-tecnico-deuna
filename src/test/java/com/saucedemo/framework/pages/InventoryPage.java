package com.saucedemo.framework.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Página del Inventario de Productos de la aplicación Sauce Demo
 * Contiene todos los elementos y métodos relacionados con la gestión de productos
 * 
 * @author Framework Team
 * @version 1.0
 */
public class InventoryPage extends BasePage {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryPage.class);
    
    // Localizadores de elementos principales
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/productIV")
    private WebElementFacade productImage;
    
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/cartBt")
    private WebElementFacade addToCartButton;
    
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/cartIV")
    private WebElementFacade cartIcon;
    
    // Localizadores usando By
    private static final By PRODUCT_IMAGE_LOCATOR = By.id("com.saucelabs.mydemoapp.android:id/productIV");
    private static final By ADD_TO_CART_BUTTON_LOCATOR = By.id("com.saucelabs.mydemoapp.android:id/cartBt");
    private static final By CART_ICON_LOCATOR = By.id("com.saucelabs.mydemoapp.android:id/cartIV");
    private static final By PRODUCT_ITEMS_LOCATOR = By.xpath("//android.view.ViewGroup[contains(@bounds, '[52,989][519,1838]')]");
    
    /**
     * Constructor de la página del inventario
     */
    public InventoryPage() {
        super();
    }
    
    /**
     * Verifica si la página del inventario está cargada correctamente
     * 
     * @return true si la página está cargada
     */
    @Override
    public boolean isPageLoaded() {
        try {
            return isElementVisible(PRODUCT_IMAGE_LOCATOR) && 
                   isElementVisible(CART_ICON_LOCATOR);
        } catch (Exception e) {
            LOGGER.error("Error al verificar si la página del inventario está cargada: {}", e.getMessage());
            return false;
        }
    }
    
    /**
     * Hace clic en la imagen del producto
     */
    public void clickProductImage() {
        LOGGER.info("Haciendo clic en la imagen del producto");
        clickElement(PRODUCT_IMAGE_LOCATOR);
    }
    
    /**
     * Hace clic en el botón de agregar al carrito
     */
    public void clickAddToCartButton() {
        LOGGER.info("Haciendo clic en el botón de agregar al carrito");
        clickElement(ADD_TO_CART_BUTTON_LOCATOR);
    }
    
    /**
     * Hace clic en el icono del carrito
     */
    public void clickCartIcon() {
        LOGGER.info("Haciendo clic en el icono del carrito");
        clickElement(CART_ICON_LOCATOR);
    }
    
    /**
     * Obtiene la lista de nombres de productos
     * 
     * @return Lista de nombres de productos
     */
    public List<String> getProductNames() {
        LOGGER.debug("Obteniendo lista de nombres de productos");
        return findAll(PRODUCT_ITEMS_LOCATOR)
                .stream()
                .map(WebElementFacade::getText)
                .collect(java.util.stream.Collectors.toList());
    }
    
    /**
     * Busca un producto específico por nombre
     * 
     * @param productName Nombre del producto a buscar
     * @return true si el producto está presente
     */
    public boolean isProductPresent(String productName) {
        List<String> productNames = getProductNames();
        boolean isPresent = productNames.contains(productName);
        LOGGER.debug("Buscando producto '{}'. Presente: {}", productName, isPresent);
        return isPresent;
    }
    
    /**
     * Hace clic en el botón "ADD TO CART" para un producto específico
     * 
     * @param productName Nombre del producto
     */
    public void addProductToCart(String productName) {
        LOGGER.info("Agregando producto al carrito: {}", productName);
        By addToCartButtonLocator = By.xpath(String.format(
            "//android.widget.TextView[@text='%s']/following-sibling::android.widget.TextView[@text='ADD TO CART']", 
            productName));
        clickElement(addToCartButtonLocator);
    }
    
    /**
     * Hace clic en el botón "REMOVE" para un producto específico
     * 
     * @param productName Nombre del producto
     */
    public void removeProductFromCart(String productName) {
        LOGGER.info("Removiendo producto del carrito: {}", productName);
        By removeButtonLocator = By.xpath(String.format(
            "//android.widget.TextView[@text='%s']/following-sibling::android.widget.TextView[@text='REMOVE']", 
            productName));
        clickElement(removeButtonLocator);
    }
    
    /**
     * Verifica si un producto está en el carrito
     * 
     * @param productName Nombre del producto
     * @return true si el producto está en el carrito
     */
    public boolean isProductInCart(String productName) {
        By removeButtonLocator = By.xpath(String.format(
            "//android.widget.TextView[@text='%s']/following-sibling::android.widget.TextView[@text='REMOVE']", 
            productName));
        return isElementVisible(removeButtonLocator);
    }
    
    /**
     * Verifica si un producto puede ser agregado al carrito
     * 
     * @param productName Nombre del producto
     * @return true si el producto puede ser agregado
     */
    public boolean canAddProductToCart(String productName) {
        By addToCartButtonLocator = By.xpath(String.format(
            "//android.widget.TextView[@text='%s']/following-sibling::android.widget.TextView[@text='ADD TO CART']", 
            productName));
        return isElementVisible(addToCartButtonLocator);
    }
    
    /**
     * Obtiene el número total de productos disponibles
     * 
     * @return Número de productos
     */
    public int getTotalProductsCount() {
        List<String> productNames = getProductNames();
        return productNames.size();
    }
    
    /**
     * Obtiene el número de productos en el carrito
     * 
     * @return Número de productos en el carrito
     */
    public int getCartItemsCount() {
        String badgeText = getCartBadgeText();
        try {
            return Integer.parseInt(badgeText);
        } catch (NumberFormatException e) {
            LOGGER.warn("No se pudo parsear el número de items del carrito: {}", badgeText);
            return 0;
        }
    }
    
    /**
     * Hace scroll hacia un producto específico
     * 
     * @param productName Nombre del producto
     */
    public void scrollToProduct(String productName) {
        By productLocator = By.xpath(String.format("//android.widget.TextView[@text='%s']", productName));
        scrollToElement(productLocator);
    }
    
    /**
     * Hace scroll hacia abajo en la lista de productos
     */
    public void scrollDownInProducts() {
        LOGGER.debug("Haciendo scroll hacia abajo en la lista de productos");
        scrollDown();
    }
    
    /**
     * Hace scroll hacia arriba en la lista de productos
     */
    public void scrollUpInProducts() {
        LOGGER.debug("Haciendo scroll hacia arriba en la lista de productos");
        scrollUp();
    }
    
    /**
     * Espera hasta que la página del inventario esté completamente cargada
     */
    public void waitForPageToLoad() {
        LOGGER.debug("Esperando que la página del inventario se cargue completamente");
        waitForElementVisible(PRODUCT_IMAGE_LOCATOR);
        waitForElementVisible(CART_ICON_LOCATOR);
    }
    
    /**
     * Verifica si hay productos disponibles
     * 
     * @return true si hay productos disponibles
     */
    public boolean areProductsAvailable() {
        return getTotalProductsCount() > 0;
    }
} 