# peliculas-java

Proyecto web en **Java EE** con un patron **MVC** contra una bbdd **Mysql**
para gestionar peliculas. 


![screenshot 1]( img01.jpg?raw=true)


## Tecnologia

- Maven 4.0.0
- Java 8
- Java Servlet Api 3.1.0
- JSP 2.2
- JSTL 1.2
- Javax Validation Api 1.1
- Bootstrap 4.5.0
- FontAwesome 5.13.0
- Datatables.net 1.10.21

Mas detalles sobre las depencias de este proyecto: [pom.xml](https://github.com/javierrruiz85/peliculas-java/blob/master/pom.xml)




## Configuración de la BBDD

Para crear la bbdd disponemos de un scripts en la raiz del proyecto, el cual crea la BBDD **peliculas**, las tablas e inserta datos de prueba en estas.

![screenshot 2]( img02.jpg?raw=true)

Para realizar la conexión a la bbdd cambiar el siguiente fichero **/peliculas-java/src/main/java/casa/mi/modelo/conexion/ConnectionManager.java**





## Ejecutar Proyecto

Necesitamos un servidor de aplicaciones, en este caso hemos utilizado **Apache Tomcat 8.5**.

Podemos navegar por los enlaces de la cabecera de forma libre.
Para entrar a la administración debemos *Iniciar Sesión*. Existen dos roles:

1. Administrador   **[admin,123456]** : permisos totales para cambiar cualquier pelicula
2. Usuario Normal  **[pepe, 123456]** : permismos para cambiar solo las peliculas de ese usuario

*Algunas contraseñas estan haseadas en MD5 dentro de la bbdd.*


## Estructura Clases del proyecto

Se puede consultar la documentacion [JavaDoc API](https://github.com/javierrruiz85/peliculas-java/tree/master/src/main/webapp/doc) la cual esta accesible en la barra de navegacion.

Intersante mirar los siguientes packages de java:

- **casa.mi.listener** Listener que se ejecuta al arrancar la APP
- **casa.mi.controller.backoffice** Controladores para los administradores
- **casa.mi.controller.frontoffice** Controladores para usuarios normales
- **casa.mi.modelo.pojo** Pojos para crear objetos en java
- **casa.mi.modelo.dao** DAO para relacionar los Pojos de Java con las tablas de la BBDD
- **casa.mi.seguridad** Filtros de seguridad


