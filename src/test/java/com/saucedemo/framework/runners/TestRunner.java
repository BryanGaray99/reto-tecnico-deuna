package com.saucedemo.framework.runners;

import io.cucumber.junit.platform.engine.Cucumber;
import net.serenitybdd.cucumber.CucumberWithSerenity;

/**
 * Test Runner principal para ejecutar los tests de Appium con Serenity BDD
 * Configura la ejecución de Cucumber con JUnit 5 y Serenity
 * 
 * @author Framework Team
 * @version 1.0
 */
@Cucumber
public class TestRunner {
    
    /**
     * Constructor privado para evitar instanciación
     */
    private TestRunner() {
        // Constructor privado para clase de configuración
    }
} 