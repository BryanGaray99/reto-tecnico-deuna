package com.saucedemo.framework.hooks;

import com.saucedemo.framework.core.AppiumDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import net.serenitybdd.core.Serenity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hooks para manejar el ciclo de vida de los tests
 * Configura el driver antes de cada escenario y lo limpia después
 * 
 * @author Framework Team
 * @version 1.0
 */
public class TestHooks {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TestHooks.class);
    
    /**
     * Hook que se ejecuta antes de cada escenario
     * Inicializa el driver de Appium y configura el entorno
     * 
     * @param scenario Escenario actual de Cucumber
     */
    @Before
    public void setUp(Scenario scenario) {
        LOGGER.info("=== Iniciando escenario: {} ===", scenario.getName());
        
        try {
            // Inicializar el driver de Appium
            AppiumDriverManager.getDriver();
            
            // Registrar información del escenario en Serenity
            Serenity.recordReportData().withTitle("Escenario Iniciado")
                    .andContents("Nombre: " + scenario.getName() + 
                               "\nTags: " + scenario.getSourceTagNames());
            
            LOGGER.info("Driver de Appium inicializado correctamente");
            
        } catch (Exception e) {
            LOGGER.error("Error al inicializar el driver de Appium: {}", e.getMessage(), e);
            throw new RuntimeException("No se pudo inicializar el driver de Appium", e);
        }
    }
    
    /**
     * Hook que se ejecuta después de cada escenario
     * Limpia el driver y registra el resultado del escenario
     * 
     * @param scenario Escenario actual de Cucumber
     */
    @After
    public void tearDown(Scenario scenario) {
        LOGGER.info("=== Finalizando escenario: {} ===", scenario.getName());
        
        try {
            // Registrar el resultado del escenario
            String status = scenario.isFailed() ? "FALLIDO" : "EXITOSO";
            LOGGER.info("Estado del escenario: {}", status);
            
            Serenity.recordReportData().withTitle("Escenario Finalizado")
                    .andContents("Nombre: " + scenario.getName() + 
                               "\nEstado: " + status +
                               "\nDuración: " + getScenarioDuration(scenario));
            
            // Si el escenario falló, registrar información adicional
            if (scenario.isFailed()) {
                LOGGER.error("El escenario falló: {}", scenario.getName());
                Serenity.recordReportData().withTitle("Error del Escenario")
                        .andContents("El escenario '" + scenario.getName() + "' falló");
            }
            
        } catch (Exception e) {
            LOGGER.error("Error durante el tearDown del escenario: {}", e.getMessage(), e);
        } finally {
            // Cerrar el driver de Appium
            try {
                AppiumDriverManager.quitDriver();
                LOGGER.info("Driver de Appium cerrado correctamente");
            } catch (Exception e) {
                LOGGER.warn("Error al cerrar el driver de Appium: {}", e.getMessage());
            }
        }
    }
    
    /**
     * Hook que se ejecuta después de cada paso
     * Registra información del paso actual
     * 
     * @param scenario Escenario actual de Cucumber
     */
    @After
    public void afterStep(Scenario scenario) {
        // Registrar información del paso actual si es necesario
        if (scenario.isFailed()) {
            LOGGER.warn("Paso falló en el escenario: {}", scenario.getName());
        }
    }
    
    /**
     * Calcula la duración del escenario
     * 
     * @param scenario Escenario de Cucumber
     * @return Duración en formato legible
     */
    private String getScenarioDuration(Scenario scenario) {
        // En una implementación real, aquí se calcularía la duración del escenario
        // Por ahora, retornamos un valor por defecto
        return "N/A";
    }
    
    /**
     * Hook para manejar excepciones no capturadas
     * 
     * @param scenario Escenario actual de Cucumber
     */
    @After
    public void handleExceptions(Scenario scenario) {
        if (scenario.isFailed()) {
            LOGGER.error("Excepción no capturada en el escenario: {}", scenario.getName());
            
            // Registrar información adicional sobre la excepción
            Serenity.recordReportData().withTitle("Excepción del Escenario")
                    .andContents("El escenario '" + scenario.getName() + "' falló con una excepción");
        }
    }
} 