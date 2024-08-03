# Instalación de Spring Boot

Para comenzar con un proyecto de Spring Boot, sigue los siguientes pasos:

1. **Ir al sitio web Spring Boot Initializr**: [Spring Boot Initializr](https://start.spring.io/)

2. **Configuración del proyecto**:
   - **Tipo de proyecto**: Maven
   - **Lenguaje**: Java
   - **Versión de Spring Boot**: 3.2.2 (puede haber versiones más recientes)
   - **Group**: `com`
   - **Artifact**: `nombre-aplicacion`
   - **Descripción**: `descripcion`
   - **Empaquetado**: 
     - JAR (para aplicaciones de escritorio y web)
     - WAR (para aplicaciones web)

3. **Configurar las dependencias**:
   - **Spring Data JPA**: Permite la conexión a la base de datos y otras operaciones relacionadas.
   - **MySQL Driver**: El controlador para conectarse a la base de datos MySQL.
   - **Lombok**: Ayuda a reducir el código en las entidades, eliminando la necesidad de escribir manualmente los getters y setters. (En inteliJ se necesita descargar el plugin de Lombok para que pueda soportar lombok).
   

### Ejemplo de configuración en Spring Boot Initializr

```plaintext
Group: com
Artifact: nombre-aplicacion
Name: nombre-aplicacion
Description: descripcion
Package name: com.nombre-aplicacion
Packaging: JAR
Java: 17 (o la versión que prefieras)
