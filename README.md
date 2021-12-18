# Api Rest H2 Spring Boot

_Se expone una api rest que consulta a la base de datos en memoria (H2) el producto con mejor tarifa_

## Comenzando 🚀

_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas._

### Ejecutando la aplicación 🔧

```
mvn clean package
```

_Luego de compilar todo el proyecto y generarse el empaquetado en la carpeta target se ejecuta_

```
java -jar target/spring-boot-h2-test-0.0.1-SNAPSHOT.jar
```

## Ejecutando las pruebas ⚙️

_Consta de:_
* Cinco pruebas que verifican que a través del endpoint con ciertos parametros de entrada se obtenga el producto con mejor tarifa
* Una prueba para simular un objeto de tipo respuesta a través del servicio
* Y una prueba para verificar que se obtiene un producto específico del repositorio de datos en memoria

```
mvn test
```

## Construido con 🛠️

* [Spring Boot](https://spring.io/projects/spring-boot) - Herramienta para crear el proyecto Spring
* [Spring](https://spring.io) - El framework web usado
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [JUnit](https://junit.org/junit5/) - Framework para automatizar las pruebas unitarias y de integración
* [Mockito](https://site.mockito.org) - Libreria para simular objetos reales a través de mocks

## Autor ✒️

* **Fernando Salazar** - *Test H2 Spring Boot* - [FernandoJS5](https://github.com/FernandoJSR5)
