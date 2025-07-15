package com.saucedemo.framework.core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import net.serenitybdd.core.webdriver.driverproviders.DriverCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import java.net.URL;
import java.time.Duration;

/**
 * Clase principal para gestionar el WebDriver de Appium
 * Maneja la configuración y creación de drivers para Android e iOS
 * 
 * @author Framework Team
 * @version 1.0
 */
public class AppiumDriverManager {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AppiumDriverManager.class);
    private static AppiumDriver driver;
    
    // URLs de configuración
    private static final String APPIUM_SERVER_URL = getProperty("appium.server.url", "http://localhost:4723");
    private static final String APPIUM_SERVER_PATH = getProperty("appium.server.path", "/wd/hub");
    
    // Configuraciones de espera
    private static final int IMPLICIT_WAIT = Integer.parseInt(getProperty("appium.implicit.wait", "10"));
    private static final int EXPLICIT_WAIT = Integer.parseInt(getProperty("appium.explicit.wait", "20"));
    
    /**
     * Constructor privado para evitar instanciación
     */
    private AppiumDriverManager() {
        // Constructor privado para patrón Singleton
    }
    
    /**
     * Obtiene la instancia del driver de Appium
     * Si no existe, lo crea con las configuraciones apropiadas
     * 
     * @return Instancia del AppiumDriver
     */
    public static AppiumDriver getDriver() {
        if (driver == null) {
            driver = createDriver();
        }
        return driver;
    }
    
    /**
     * Crea un nuevo driver de Appium basado en la plataforma configurada
     * 
     * @return AppiumDriver configurado
     */
    private static AppiumDriver createDriver() {
        try {
            String platformName = getPlatformName();
            DesiredCapabilities capabilities = createCapabilities(platformName);
            
            LOGGER.info("Creando driver de Appium para plataforma: {}", platformName);
            
            AppiumDriver newDriver;
            if ("Android".equalsIgnoreCase(platformName)) {
                newDriver = new AndroidDriver(new URL(APPIUM_SERVER_URL + APPIUM_SERVER_PATH), capabilities);
            } else if ("iOS".equalsIgnoreCase(platformName)) {
                newDriver = new IOSDriver(new URL(APPIUM_SERVER_URL + APPIUM_SERVER_PATH), capabilities);
            } else {
                throw new IllegalArgumentException("Plataforma no soportada: " + platformName);
            }
            
            // Configurar timeouts
            configureTimeouts(newDriver);
            
            LOGGER.info("Driver de Appium creado exitosamente");
            return newDriver;
            
        } catch (Exception e) {
            LOGGER.error("Error al crear el driver de Appium: {}", e.getMessage(), e);
            throw new RuntimeException("No se pudo crear el driver de Appium", e);
        }
    }
    
    /**
     * Crea las capacidades del driver según la plataforma
     * 
     * @param platformName Nombre de la plataforma (Android/iOS)
     * @return DesiredCapabilities configuradas
     */
    private static DesiredCapabilities createCapabilities(String platformName) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        
        if ("Android".equalsIgnoreCase(platformName)) {
            configureAndroidCapabilities(capabilities);
        } else if ("iOS".equalsIgnoreCase(platformName)) {
            configureIOSCapabilities(capabilities);
        }
        
        return capabilities;
    }
    
    /**
     * Configura las capacidades específicas para Android
     * 
     * @param capabilities Objeto DesiredCapabilities a configurar
     */
    private static void configureAndroidCapabilities(DesiredCapabilities capabilities) {
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:platformName", "Android");
        capabilities.setCapability("appium:deviceName", getDeviceName("android"));
        capabilities.setCapability("appium:platformVersion", getPlatformVersion("android"));
        capabilities.setCapability("appium:app", getAppPath("android"));
        capabilities.setCapability("appium:appPackage", getAppPackage("android"));
        capabilities.setCapability("appium:appActivity", getAppActivity("android"));
        capabilities.setCapability("appium:autoGrantPermissions", true);
        capabilities.setCapability("appium:noReset", false);
        capabilities.setCapability("appium:newCommandTimeout", 300);
        capabilities.setCapability("appium:autoAcceptAlerts", true);
        
        LOGGER.info("Capacidades de Android configuradas");
    }
    
    /**
     * Configura las capacidades específicas para iOS
     * 
     * @param capabilities Objeto DesiredCapabilities a configurar
     */
    private static void configureIOSCapabilities(DesiredCapabilities capabilities) {
        capabilities.setCapability("appium:automationName", "XCUITest");
        capabilities.setCapability("appium:platformName", "iOS");
        capabilities.setCapability("appium:deviceName", getDeviceName("ios"));
        capabilities.setCapability("appium:platformVersion", getPlatformVersion("ios"));
        capabilities.setCapability("appium:app", getAppPath("ios"));
        capabilities.setCapability("appium:autoAcceptAlerts", true);
        capabilities.setCapability("appium:noReset", false);
        capabilities.setCapability("appium:newCommandTimeout", 300);
        
        LOGGER.info("Capacidades de iOS configuradas");
    }
    
    /**
     * Configura los timeouts del driver
     * 
     * @param driver AppiumDriver a configurar
     */
    private static void configureTimeouts(AppiumDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
        LOGGER.info("Timeouts configurados - Implícito: {}s, Explícito: {}s", IMPLICIT_WAIT, EXPLICIT_WAIT);
    }
    
    /**
     * Obtiene una propiedad del sistema con valor por defecto
     * 
     * @param key Clave de la propiedad
     * @param defaultValue Valor por defecto
     * @return Valor de la propiedad
     */
    private static String getProperty(String key, String defaultValue) {
        return System.getProperty(key, defaultValue);
    }
    
    /**
     * Obtiene el nombre de la plataforma desde las variables de entorno
     * 
     * @return Nombre de la plataforma
     */
    private static String getPlatformName() {
        return getProperty("webdriver.capabilities.appium:platformName", "Android");
    }
    
    /**
     * Obtiene el nombre del dispositivo según la plataforma
     * 
     * @param platform Plataforma (android/ios)
     * @return Nombre del dispositivo
     */
    private static String getDeviceName(String platform) {
        return getProperty("saucedemo.device." + platform, 
            "Android".equalsIgnoreCase(platform) ? "sdk_gphone64_x86_64" : "iPhone Simulator");
    }
    
    /**
     * Obtiene la versión de la plataforma
     * 
     * @param platform Plataforma (android/ios)
     * @return Versión de la plataforma
     */
    private static String getPlatformVersion(String platform) {
        return getProperty("saucedemo.os.version." + platform,
            "Android".equalsIgnoreCase(platform) ? "16" : "15.0");
    }
    
    /**
     * Obtiene la ruta de la aplicación
     * 
     * @param platform Plataforma (android/ios)
     * @return Ruta de la aplicación
     */
    private static String getAppPath(String platform) {
        return getProperty("saucedemo.app." + platform,
            "C:\\Universidad\\Reto\\Framework-appium\\mda-2.2.0-25.apk");
    }
    
    /**
     * Obtiene el package name de la aplicación
     * 
     * @param platform Plataforma (android/ios)
     * @return Package name de la aplicación
     */
    private static String getAppPackage(String platform) {
        return getProperty("saucedemo.app.package." + platform,
            "Android".equalsIgnoreCase(platform) ? "com.saucelabs.mydemoapp.android" : "com.saucelabs.mydemoapp.ios");
    }
    
    /**
     * Obtiene la activity principal de la aplicación
     * 
     * @param platform Plataforma (android/ios)
     * @return Activity principal de la aplicación
     */
    private static String getAppActivity(String platform) {
        return getProperty("saucedemo.app.activity." + platform,
            "Android".equalsIgnoreCase(platform) ? "com.saucelabs.mydemoapp.android.view.activities.SplashActivity" : "com.saucelabs.mydemoapp.ios.MainActivity");
    }
    
    /**
     * Cierra el driver actual y lo establece como null
     */
    public static void quitDriver() {
        if (driver != null) {
            try {
                LOGGER.info("Cerrando driver de Appium");
                driver.quit();
            } catch (Exception e) {
                LOGGER.warn("Error al cerrar el driver: {}", e.getMessage());
            } finally {
                driver = null;
            }
        }
    }
    
    /**
     * Verifica si el driver está activo
     * 
     * @return true si el driver está activo, false en caso contrario
     */
    public static boolean isDriverActive() {
        return driver != null;
    }
} 