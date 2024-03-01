# TALLER 4: ARQUITECTURAS DE SERVIDORES DE APLICACIONES, META PROTOCOLOS DE OBJETOS, PATRÓN IOC, REFLEXIÓN
Jose Alejandro Correa Rodrigez
## Descripción de la aplicación 

Para este taller los estudiantes deberán construir un servidor Web (tipo Apache) en Java. El servidor debe ser capaz de entregar páginas html e imágenes tipo PNG. Igualmente el servidor debe proveer un framework IoC para la construcción de aplicaciones web a partir de POJOS. Usando el servidor se debe construir una aplicación Web de ejemplo. El servidor debe atender múltiples solicitudes no concurrentes.

Para este taller desarrolle un prototipo mínimo que demuestre capcidades reflexivas de JAVA y permita por lo menos cargar un bean (POJO) y derivar una aplicación Web a partir de él. 

Debe entregar su trabajo al final del laboratorio. Luego puede complementar para entregarlo en 8 días. Se verificara y compararán el commit del día de inicio del laboratorio y el dela entrega final.

Su servidor debe:
* 1. El código fuente del proyecto y el ciclo de vida debe ser estructurado y manejado usando Maven.

* 2. El proyecto se debe almacenar en la cuenta de GitHub del estudiante.


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


Generar javadoc:

```bash
mvn site

```


## Ejecutando las pruebas 

Para ejecutar las pruebas, ejecute el siguiente comando:

```bash
mvn test

```


## Cómo se desarrollarían aplicaciones con este proyecto 🧩

Para desarrollar aplicaciones con este proyecto:

Crear componentes con la anonotación `@Component`.

```java
@Component
public class MyComponent {
    // ...
}
```

Crear los listeners con la anotación `@RequestMapping`.

```java
@RequestMapping(path = "/hello", method = HttpMethod.GET)
public static String hello() {
    return "Hello World!";
}

public class HelloController {

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
}
```



## Arquitecura de la aplicación 


Los modulos de la aplicación son los siguientes:

* **Cache**: Contiene la implementación del caché de la aplicación para peliculas.
* **HttpServer**: Contiene la implementación del servidor web de la aplicación.
* **HttpMovieConnection**: Contiene la implementación del servicio de conexión a la API externa.
* **Spark**: Contiene la implementación del microframework web.
* **GameShopService**: Contiene la implementación del servicio de la tienda de juegos.

* **Spring**: Contiene la implementación para cargar los componentes y los listeners.
* **ComponentLoader**: Contiene la implementación para cargar los componentes por medio de reflexión con la anotación @ComponentScan.