## Descripción
Automatizar la verificación del inicio de sesión utilizando credenciales válidas en la aplicación nativa de Android de Sauce Labs.

## Precondiciones
- Dispositivo Android (Emulador) iniciado.
- Aplicación MyDemoApp instalada.

## Pasos de Prueba (Test Steps)
1. Abrir el menú lateral de la aplicación.
2. Seleccionar la opción "Log In".
3. Ingresar el usuario válido (`bob@example.com`).
4. Ingresar la contraseña válida (`10203040`).
5. Presionar el botón de inicio de sesión.

## Resultado Esperado
El usuario es redirigido correctamente a la pantalla del catálogo de productos y se visualiza el título principal de la tienda.