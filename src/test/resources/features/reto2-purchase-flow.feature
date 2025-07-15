@reto2 @purchase @regression
Feature: Reto 2 - Flujo Completo de Compra
  Como usuario de la aplicación Sauce Demo
  Quiero poder realizar un proceso de compra completo
  Para validar la funcionalidad de e-commerce

  Background:
    Given que el usuario abre la aplicación Sauce Demo
    And está en la pantalla de login

  @smoke @purchase @positive
  Scenario: Flujo de compra - Selección de productos y validación de error en checkout
    # Paso 1: Login exitoso
    When el usuario ingresa el usuario "standard_user"
    And ingresa la contraseña "secret_sauce"
    And presiona el botón de login
    Then el usuario debería ser redirigido al inventario
    And debería ver el título "PRODUCTS"
    
    # Paso 2: Seleccionar primer producto (Backpack Red)
    When el usuario selecciona el producto "Sauce Labs Backpack"
    And presiona el botón "ADD TO CART"
    Then el botón debería cambiar a "REMOVE"
    And el contador del carrito debería incrementar en 1
    
    # Paso 3: Seleccionar segundo producto (Backpack Orange)
    When el usuario selecciona el producto "Sauce Labs Fleece Jacket"
    And presiona el botón "ADD TO CART"
    Then el botón debería cambiar a "REMOVE"
    And el contador del carrito debería incrementar en 1
    
    # Paso 4: Ir al carrito de compras
    When el usuario presiona el ícono del carrito
    Then debería ser redirigido a la página del carrito
    And debería ver el título "YOUR CART"
    And debería ver los productos seleccionados en el carrito
    
    # Paso 5: Eliminar un producto del carrito
    When el usuario presiona el botón "REMOVE" para el producto "Sauce Labs Backpack"
    Then el producto "Sauce Labs Backpack" debería ser removido del carrito
    And el contador del carrito debería decrementar en 1
    
    # Paso 6: Proceder al checkout
    When el usuario presiona el botón "CHECKOUT"
    Then debería ser redirigido a la página de información de checkout
    And debería ver el título "CHECKOUT: YOUR INFORMATION"
    
    # Paso 7: Intentar continuar sin ingresar información
    When el usuario presiona el botón "CONTINUE" sin ingresar información
    Then debería aparecer un mensaje de error
    And el mensaje debería contener "First Name is required"
    And el caso debería fallir si el mensaje de error no existe 