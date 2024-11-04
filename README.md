## **Instrucciones para el Proyecto**

### Importante

La base de datos **no contiene datos iniciales** cargados, por lo que será necesario añadirlos manualmente para probar la funcionalidad completa.

El proyecto ha sido **probado en Postman y Swagger** para asegurar el funcionamiento de los endpoints.

> **Nota Importante**  
> Al enviar objetos a través de Postman, sigue las especificaciones descritas en la documentación de Swagger. Si los datos enviados no cumplen con las relaciones establecidas en la base de datos, puede haber problemas como la falta de eliminación de datos o conflictos en las relaciones.

---

### **Cómo iniciar el proyecto**

1. **Clonar el repositorio**:

   Descarga el proyecto en una carpeta y ábrelo con tu editor de código favorito (se recomienda **Visual Studio Code** o **IntelliJ IDEA**).

2. **Requisitos de Java**:

   Asegúrate de tener instalada la versión de Java adecuada para tu sistema.

3. **Ejecutar el proyecto y revisar la API**:

   Una vez iniciado el proyecto, puedes acceder a la documentación de la API para empezar las pruebas visitando el siguiente enlace en tu navegador:

   [Documentación Swagger](http://localhost:8080/swagger-ui/index.html#/ "Abrir Swagger UI")

4. **Consultar la base de datos**:

   Puedes ver el contenido de las tablas y los datos que hayas insertado a través de la consola de la base de datos integrada.

   [H2 Console](http://localhost:8080/h2-console/ "Abrir H2 Console")

   **Configuración en la H2 Console**:

   En el campo JDBC URL, ingresa la siguiente dirección para conectarte a la base de datos:

   ```bash
   jdbc:h2:file:./data/testDB
   ```
