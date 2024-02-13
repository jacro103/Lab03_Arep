# TALLER 3: MICROFRAMEWORKS WEB
Jose Alejandro Correa Rodrigez
## Descripción de la aplicación 

En este taller usted debe explorar la arquitectura del microframework WEB denominado sparkweb [sparkjava](https://sparkjava.com/).Este micro framework permite construir aplicaciones web de manera simple usando funciones lambda.

Para este ejercicio usted deb construir un  servidor web para soportar una funcionalidad similar a la de Spark. Su aplicación debe permitir por lo menos el registro de servicios get y post usando funciones lambda. Implemente igualmente funciones que le permitan configurar el directorio de los archivos estáticos, y otra que permita cambiar el tipo de la respuesta a "application/json". Para esto solo debe usar el API básico de Java. No utilice frameworks como Spark o Spring.

Su servidor debe:
* Permite configurar servicios web de tipo GET y POST usando funciones lambda.
* Entrega archivos estáticos como páginas HTML, CSS, JS e imágenes.
* Permite configurar el directorio de donde se leerán los archivos estáticos.


## Comenzando

Las siguientes instrucciones le permitirán obtener una copia del proyecto en funcionamiento en su máquina local para fines de desarrollo y prueba.


Realice los siguientes pasos para clonar el proyecto en su máquina local.

```bash
git clone 
cd AREP-LAB-03/

```

## Ejecutando la aplicación 

Para ejecutar la aplicación, ejecute el siguiente comando:

```bash
mvn clean compile exec:java '-Dexec.mainClass=edu.eci.arep.App'

```





## Ejecutando las pruebas 

Para ejecutar las pruebas, ejecute el siguiente comando:

```bash
mvn test

```


## Cómo se desarrollarían aplicaciones con este proyecto

Para desarrollar aplicaciones con este proyecto:

Se puede seleccionar la ruta especifica de los archivos que se desean leer.


## Arquitecura de la aplicación 


Los modulos de la aplicación son los siguientes:

* **Cache**: Contiene la implementación del caché de la aplicación para peliculas.
* **HttpServer**: Contiene la implementación del servidor web de la aplicación.
* **HttpMovieConnection**: Contiene la implementación del servicio de conexión a la API externa.
* **Spark**: Contiene la implementación del microframework web.