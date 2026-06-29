# TechStore - Arquitectura de Microservicios

## Descripción del Proyecto
TechStore es una solución de backend para una tienda de tecnología de alto rendimiento. El proyecto implementa una arquitectura de microservicios utilizando Java 21 y Spring Boot 4.0.6, con un enfoque prioritario en la escalabilidad, la trazabilidad de operaciones y una gestión de datos relacional robusta.

## Equipo de Desarrollo
* Felipe Baez
* Jose "Lalo" Muñoz

## Metodología de Trabajo
El desarrollo se llevó a cabo de manera colaborativa utilizando GitHub como plataforma principal de control de versiones. La estrategia de trabajo consistió en:
* División de tareas por microservicios específicos.
* Actualización constante de avances mediante commits y push frecuentes al repositorio.
* Análisis y revisión cruzada de código para asegurar la integración correcta de las funcionalidades desarrolladas por cada integrante.

## Microservicios y Funcionalidades (11 Servicios)
El sistema gestiona el ciclo de vida completo de un e-commerce a través de los siguientes módulos:
* Usuario: Administración de perfiles y credenciales.
* Producto: Catálogo técnico detallado de hardware.
* Inventario: Control de existencias en tiempo real.
* Categoría: Clasificación lógica de productos.
* Marca: Gestión de fabricantes asociados.
* Carrito: Persistencia de selecciones de compra.
* CarritoDetalle: Desglose de ítems y cantidades en el carro.
* Orden: Consolidación de pedidos realizados.
* DetalleOrden: Líneas de venta específicas por orden.
* Pago: Procesamiento de transacciones con integración externa.
* Envío: Gestión de logística y seguimiento de paquetes.

## Stack Tecnológico
* Lenguaje: Java 21.
* Framework: Spring Boot 4.0.6 con Spring Data JPA y WebMVC.
* Base de Datos: MySQL administrada vía Laragon.
* Librerías Clave:
    * Lombok: Para la optimización y limpieza del código fuente.
    * SLF4J: Implementación de logs para trazabilidad y auditoría técnica.
    * Jakarta Validation: Garantía de integridad de datos mediante reglas en modelos y controladores.
    * WebClient: Consumo de APIs externas en tiempo real (integración con mindicador.cl para consulta de divisas).

## Características Técnicas Destacadas
* CRUD Completo: Operaciones GET, POST, PUT y DELETE implementadas rigurosamente en todas las entidades.
* Manejo Global de Errores: Centralización de excepciones mediante @ControllerAdvice para estandarizar las respuestas REST hacia el cliente.
* Validaciones de Integridad: Uso de la anotación @Valid para asegurar la consistencia y seguridad de los datos de entrada.
* Patrón DTO: Implementación de Data Transfer Objects para optimizar la transferencia de información y proteger la integridad de las entidades del dominio.

## Configuración y Ejecución

### Requisitos Técnicos
* Java Development Kit (JDK) 21.
* Servidor MySQL (Laragon recomendado) activo en el puerto 3306.

### Pasos para la Ejecución
1. Base de Datos: Crear una base de datos denominada techstore_db.
2. Persistencia: El sistema está configurado para generar las tablas y relaciones automáticamente al iniciar mediante la propiedad ddl-auto=update.
3. Inicio: Ejecutar la aplicación desde la clase TechstoreApplication.java.
4. Acceso API: Los endpoints están disponibles para pruebas en el puerto local: http://localhost:8080/api/v1/# TechStore - Arquitectura de Microservicios

## Descripción del Proyecto
TechStore es una solución de backend para una tienda de tecnología de alto rendimiento. El proyecto implementa una arquitectura de microservicios utilizando Java 21 y Spring Boot 4.0.6, con un enfoque prioritario en la escalabilidad, la trazabilidad de operaciones y una gestión de datos relacional robusta.

## Equipo de Desarrollo
* Felipe Baez
* Jose "Lalo" Muñoz

## Metodología de Trabajo
El desarrollo se llevó a cabo de manera colaborativa utilizando GitHub como plataforma principal de control de versiones. La estrategia de trabajo consistió en:
* División de tareas por microservicios específicos.
* Actualización constante de avances mediante commits y push frecuentes al repositorio.
* Análisis y revisión cruzada de código para asegurar la integración correcta de las funcionalidades desarrolladas por cada integrante.

## Microservicios y Funcionalidades (11 Servicios)
El sistema gestiona el ciclo de vida completo de un e-commerce a través de los siguientes módulos:
* Usuario: Administración de perfiles y credenciales.
* Producto: Catálogo técnico detallado de hardware.
* Inventario: Control de existencias en tiempo real.
* Categoría: Clasificación lógica de productos.
* Marca: Gestión de fabricantes asociados.
* Carrito: Persistencia de selecciones de compra.
* CarritoDetalle: Desglose de ítems y cantidades en el carro.
* Orden: Consolidación de pedidos realizados.
* DetalleOrden: Líneas de venta específicas por orden.
* Pago: Procesamiento de transacciones con integración externa.
* Envío: Gestión de logística y seguimiento de paquetes.

## Stack Tecnológico
* Lenguaje: Java 21.
* Framework: Spring Boot 4.0.6 con Spring Data JPA y WebMVC.
* Base de Datos: MySQL administrada vía Laragon.
* Librerías Clave:
    * Lombok: Para la optimización y limpieza del código fuente.
    * SLF4J: Implementación de logs para trazabilidad y auditoría técnica.
    * Jakarta Validation: Garantía de integridad de datos mediante reglas en modelos y controladores.
    * WebClient: Consumo de APIs externas en tiempo real (integración con mindicador.cl para consulta de divisas).

## Características Técnicas Destacadas
* CRUD Completo: Operaciones GET, POST, PUT y DELETE implementadas rigurosamente en todas las entidades.
* Manejo Global de Errores: Centralización de excepciones mediante @ControllerAdvice para estandarizar las respuestas REST hacia el cliente.
* Validaciones de Integridad: Uso de la anotación @Valid para asegurar la consistencia y seguridad de los datos de entrada.
* Patrón DTO: Implementación de Data Transfer Objects para optimizar la transferencia de información y proteger la integridad de las entidades del dominio.

## Configuración y Ejecución

### Requisitos Técnicos
* Java Development Kit (JDK) 21.
* Servidor MySQL (Laragon recomendado) activo en el puerto 3306.

### Pasos para la Ejecución
1. Base de Datos: Crear una base de datos denominada techstore_db.
2. Persistencia: El sistema está configurado para generar las tablas y relaciones automáticamente al iniciar mediante la propiedad ddl-auto=update.
3. Inicio: Ejecutar la aplicación desde la clase TechstoreApplication.java.
4. Acceso API: Los endpoints están disponibles para pruebas en el puerto local: http://localhost:8080/api/v1/
