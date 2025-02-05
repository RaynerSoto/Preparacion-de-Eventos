# Indicaciones del sistema desarrollado:

## Datos del desarrollador:
* Nombre: Rayner Alejandro Soto Martínez
* [Linkedin](https://www.linkedin.com/in/rayner-alejandro/)
* [Correo](mailto:raynersoto01@gmail.com)
* [GitHub](https://github.com/RaynerSoto)

## Explicación del contexto general de la aplicación:
La prueba técnica para el puesto de Back-end JR en Arkon Data está diseñada para evaluar las capacidades técnicas del candidato en aspectos clave como creatividad en la resolución de problemas, calidad del código, seguimiento de especificaciones y eficiencia en las soluciones propuestas. Los candidatos deben crear un servicio para la administración de boletos de eventos, incluyendo la definición, actualización, eliminación, venta y canje de boletos, así como el detalle del evento. La información debe ser persistida en una base de datos y el stack tecnológico es de libre elección. Además, se valora la inclusión de Docker, tests unitarios y el uso de GraphQL como puntos extra. Se recomienda seguir un proceso de planificación y documentación detallada en el repositorio de GitHub. Este proyecto no solo busca evaluar las habilidades técnicas, sino también la capacidad de los candidatos para trabajar de manera colaborativa y proponer ideas innovadoras.

## Diseño y solución de la aplicación:

### Extracción de requisitos:
* El sistema debe permitir crear, modificar o eliminar un evento
* El sistema debe permitir generar y canjear un ticket de un evento ya insertado previamente
* El sistema debe permitir ver reportes, que aunque no se hayn especificado, pueden apoyar la solución
* El sistema debería en un caso real de desarrollo, tener un sistema de seguridad.
* EL sistema debería tener la información que se almacena con diversas medidas de seguridad como cifrado de los datos y una buena configuración de CORS

### Arquitectura del sistema:

* Arquitectura monolítica: Las arquitecturas monolíticas son uno de los enfoques más básicos en el diseño de sistemas de software, donde todo el sistema está contenido en un solo bloque cohesivo. Este tipo de arquitectura es ampliamente utilizado en proyectos sencillos, pruebas técnicas y en aquellos proyectos donde la escalabilidad y la tolerancia a fallos no son requisitos fundamentales.
* Arquitectura cliente - servidor: La arquitectura más utilizada en el desarrollo web
* Arquitectura modelo - vista - controlador: La arquitectura utilizada de base por Spring Framework

### Otras arquitecturas que se podrían haber seleccionado:
* Arquitectura de microservicios
* Arquitectura orientada a servicios
* Arquitectura Cloud-Native

### Stack Tecnológico empleado:
* Java
* Spring Framework(Spring Boot, Spring Data JPA, Spring Data JDBC)
* PostgreSQL 12
* PgAdmin 4
* SQL
* PL/SQL
* Flyway
* JUnit 5
* Git/GitHub
* Docker

Nota: No voy a representar tantos diagramas porque el tamaño del proyecto es muy pequeño

### Diagrama de casos de uso del sistema:
[Ver diagrama](diagramas/Captura%20de%20pantalla%202025-02-04%20185119.png)

Notas: 
* Los casos de uso en color azul han sido desarrollados y probados
* Los casos de uso en color verde han sido referenciados en este artículo, pero no han sido desarrollado

### Diagrama de clases:
[Ver diagrama](diagramas/Captura%20de%20pantalla%202025-02-04%20192948.png)

### Descripción del empaquetado:
Ahora procedo a describir el empaquetado del sistema:
* Controller: Todos los endpoints del sistema están dentro de los controller
* DTO: Todos los procesos de manejo de datos previo a la persistencia, se hace a través de los DTO que sirven para que el modelo de clases de datos sea innasesible y que los DTO ofrezcan una flexibilidad a la hora de su creación
* Model: Modelo de clases para la persistencia del sistema
* Repository: Modelo de interfaces que aseguran la persistencia, manejor y control de datos
* Service: Servicios interno de la aplicación
* Utils: Otras clases/librerías útiles del sistema

Este es el resumen de todo el diseño y arquitectura del sistema. Me hubiese encantando dar más detalles, pero por motivos de tiempo
he decidido no desarrollar

## Ejecución del sistema
Pasos para la ejecucuión del sistema:
1. Verificar que el puerto 8080 se encuentre despejado para la ejecución del proyecto
2. Asignar el nombre de usuario y la password de la base de datos o definir las variables de entorno con los nombre definidos en la application.yml
3. Crear una base de datos en PostgreSQL con el nombre: Evento
4. Compilar el proyecto y acceder a la dirección: http://localhost:8080/. Recuerde que el 8080 es si mantiene el mismo puerto
5. Esto le permite acceder a [Open API 3.0 o Swagger](diagramas/Open%20Api%203.0.png) como es mayormente conocido para que así puede y ver probar todos los endpoints del sistema
6. La base de datos ya que trabaja con Flyway se carga sola, pero la entregaré vacía para que pueda trabajar con ella

En la zona de Test podemos enconrar la realización de pruebas simples para comprobar que todo el sistema estuviera trabajando correctamente