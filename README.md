
## a) ¿Qué ventajas e inconvenientes encuentras al usar una base de datos documental con MongoDB?

### Ventajas
1. **Flexibilidad en el esquema**
    - MongoDB permite almacenar documentos con estructuras diferentes dentro de la misma colección, lo que facilita adaptarse a cambios en los requisitos de la aplicación.

2. **Escalabilidad horizontal**
    - Es fácil escalar MongoDB distribuyendo datos entre varios nodos, ideal para aplicaciones con grandes volúmenes de datos.

3. **Rendimiento**
    - MongoDB está optimizado para lecturas y escrituras rápidas, especialmente en datos que no requieren transacciones complejas.

4. **Integración con JSON**
    - Utiliza JSON o BSON, lo que facilita la integración con APIs y el manejo de datos en aplicaciones modernas.

5. **Documentos autocontenidos**
    - Toda la información relacionada con una entidad se guarda en un único documento, reduciendo la necesidad de unir datos de múltiples tablas.

### Inconvenientes
1. **Falta de esquemas estrictos**
    - La flexibilidad en el esquema puede llevar a inconsistencias si no se controla adecuadamente.

2. **Transacciones limitadas**
    - Aunque MongoDB soporta transacciones, no es tan robusto como las bases de datos relacionales en este aspecto.

3. **Curva de aprendizaje**
    - Requiere aprender conceptos nuevos, como agregaciones y operadores específicos, que pueden no ser intuitivos para desarrolladores acostumbrados a bases de datos relacionales.

4. **Sobrecarga de memoria**
    - El formato BSON, aunque rápido, puede consumir más espacio en disco y memoria que los datos en formato JSON.

---

## b) ¿Cuáles han sido los principales problemas que has tenido al desarrollar la aplicación?

1. **Gestión de esquemas dinámicos**
    - Al no haber un esquema fijo, fue necesario validar los datos manualmente para evitar inconsistencias.

2. **Errores en las consultas**
    - Configurar filtros y operadores de MongoDB correctamente para búsquedas y actualizaciones fue un desafío inicial.

3. **Integración con la interfaz gráfica**
    - Mapear datos entre documentos de MongoDB y la estructura requerida por JavaFX requirió un manejo detallado de las conversiones.

4. **Control de duplicados**
    - Asegurar que no se registraran juegos con títulos duplicados demandó verificaciones adicionales.

---

## c) ¿Cómo solucionarías el problema de la inconsistencia de datos en una base de datos documental?

1. **Validación en el nivel de aplicación**
    - Implementar validaciones estrictas antes de insertar o actualizar documentos en la base de datos. Esto incluye asegurarse de que todos los campos necesarios estén presentes y sean consistentes.

2. **Uso de esquemas con herramientas como Mongoose**
    - Aunque MongoDB no impone esquemas, es posible definirlos con herramientas de terceros o librerías específicas para garantizar la uniformidad de los documentos.

3. **Estrategias de migración de datos**
    - Si se realizan cambios en la estructura de los documentos, crear scripts de migración que actualicen los documentos existentes para cumplir con el nuevo formato.

4. **Auditorías regulares**
    - Realizar auditorías periódicas en los datos para identificar y corregir posibles inconsistencias antes de que afecten a la aplicación.

5. **Uso de validaciones en MongoDB**
    - Definir validadores de esquema en las colecciones utilizando la opción `jsonSchema` para garantizar que los documentos cumplan con los requisitos mínimos.

