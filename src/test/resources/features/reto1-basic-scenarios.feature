@reto1 @basic @regression
Feature: Reto 1 - Escenarios Básicos de Prueba
  Como usuario de la aplicación Sauce Demo
  Quiero poder realizar acciones básicas en la aplicación
  Para validar la funcionalidad principal

  Background:
    Given que el usuario abre la aplicación Sauce Demo
    And está en la pantalla de login

  @smoke @positive
  Scenario: Escenario 1 - Login exitoso y navegación al inventario
    When el usuario ingresa el usuario "standard_user"
    And ingresa la contraseña "secret_sauce"
    And presiona el botón de login
    Then el usuario debería ser redirigido al inventario
    And debería ver el título "PRODUCTS"
    And debería ver la lista de productos disponibles
    And cada producto debería mostrar su nombre y precio

  @smoke @negative
  Scenario: Escenario 2 - Login fallido con credenciales inválidas
    When el usuario ingresa el usuario "invalid_user"
    And ingresa la contraseña "wrong_password"
    And presiona el botón de login
    Then debería aparecer un mensaje de error
    And el mensaje debería contener "Username and password do not match"
    And el usuario debería permanecer en la pantalla de login 