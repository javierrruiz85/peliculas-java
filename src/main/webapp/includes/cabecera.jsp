
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<!doctype html>
<html lang="es">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- fontawesome 5 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/estilos.css">
    
    <!-- datatables -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">



    <title> ${param.title} | Ejercicios</title>
  </head>
  <body>
    <nav class="navbar navbar-expand-md navbar-light fixed-top bg-info">
        <!-- logo -->
        <a class="navbar-brand" href="peliculas">
            <i class="fab fa-fort-awesome-alt"></i>
        </a>

        <!-- icono para desplegar menu en moviles -->
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
      
        <!-- lista enlaces -->
        <div class="collapse navbar-collapse" id="navbarsExampleDefault">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item ${ ( 'inicio' eq param.pagina ) ? 'active' : '' } ">
              <a class="nav-link" href="peliculas">Inicio</a>
            </li>
            <li class="nav-item ${ ( 'crear-pelicula' eq param.pagina ) ? 'active' : '' } ">
              <a class="nav-link" href="crear-pelicula">Crear pelicula</a>
            </li>
            
            
        
        </div>
      </nav>
      
      <main role="main" class="container">
      
      
      	<jsp:include page="alerta.jsp"></jsp:include>
      
      
      