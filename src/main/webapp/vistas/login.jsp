<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<jsp:include page="../includes/cabecera.jsp" >
  <jsp:param name="pagina" value="crear-pelicula" />
  <jsp:param name="title" value="Crear pelicula" /> 
</jsp:include>


<h1>Iniciar Sesion</h1>

<br>

<p>Para acceder; user = admin, pass = admin</p>

<form action="login" method="post" class="form-login">
	<div class="form-group">
		<label for="nombre">Nombre:</label>
		<input type="text" name="nombre" value="${nombre}" id="nombre" placeholder="Tu nombre" class="form-control">
	</div>
	<div class="form-group">
		<label for="pass">Contraseña:</label>
		<input type="password" name="pass" id="pass" placeholder="Tu contraseña" class="form-control">
	</div>
	<br>
	<input type="submit" value="Iniciar sesion" class="btn boton btn-primary btn-block">
</form>


<%@ include file="../includes/pie.jsp" %>
