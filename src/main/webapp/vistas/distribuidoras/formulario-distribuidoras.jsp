<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<jsp:include page="../../includes/cabecera.jsp" >
  <jsp:param name="pagina" value="distribuidoras" />
  <jsp:param name="title" value="Distribuidoras" /> 
</jsp:include>

<h1>Crear distribuidora</h1>

<form action="distribuidora" method="post">
	<div>
		<label for="id">Id:</label>
		<input type="text" name="id" id="id" value="${distribuidora.id}" readonly class="form-control">
	</div>
	<div>
		<label for="nombre">Nombre:</label>
		<input type="text" name="nombre" id="nombre" value="${distribuidora.nombre}" autofocus class="form-control">
	</div>
	<br>
	<input type="submit" value="Guardar" class="btn boton btn-primary btn-block">
</form>

<%@ include file="../../includes/pie.jsp" %>