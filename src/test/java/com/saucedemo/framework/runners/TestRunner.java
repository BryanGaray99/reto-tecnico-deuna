package com.saucedemo.framework.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Test Runner principal para ejecutar los tests de Appium con Serenity BDD
 * Configura la ejecución de Cucumber con JUnit 4 y Serenity
 * 
 * @author Framework Team
 * @version 1.0
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.saucedemo.framework.steps", "com.saucedemo.framework.hooks"},
    tags = "@reto1 or @reto2",
    plugin = {
        "pretty",
        "html:target/cucumber-reports",
        "json:target/cucumber-reports/cucumber.json"
    },
    monochrome = true,
    dryRun = false
)
public class TestRunner {
    
    /**
     * Constructor privado para evitar instanciación
     */
    private TestRunner() {
        // Constructor privado para clase de configuración
    }
} 