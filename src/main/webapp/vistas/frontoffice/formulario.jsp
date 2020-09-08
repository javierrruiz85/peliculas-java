<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<jsp:include page="../../includes/cabecera-office.jsp" />
<jsp:include page="../../includes/navbar-office.jsp" />


<h1>Crear pelicula</h1>



<form action="crear-pelicula" method="post">
	<div class="form-group">
		<label for="id">ID:</label>
		<input type="text" name="id" id="id" value="${pelicula.id}" readonly class="form-control">
	</div>
	<div class="form-group">
		<label for="nombre">Titulo:</label>
		<input type="text" name="nombre" id="nombre" value="${pelicula.nombre}" class="form-control" placeholder="Escribe el titulo de la pelicula">
	</div>
	<div class="form-group">
		<label for="duracion">Duracion:</label>
		<input type="number" name="duracion" id="duracion" value="${pelicula.duracion}" class="form-control" placeholder="Escribe la duracion (en minutos)">
	</div>
	<div class="form-group">
		<label for="anio">Año:</label>
		<input type="number" name="anio" id="anio" value="${pelicula.anio}" class="form-control" placeholder="Escribe el año de lanzamiento">
	</div>
	<div class="form-group">
		<select class="custom-select" name="id_distribuidora">
			<c:forEach items="${distribuidoras}" var="distribuidora">
				<option value="${distribuidora.id}"  ${ ( distribuidora.id eq pelicula.distribuidora.id ) ? "selected" : "" }>${distribuidora.nombre}</option>
			</c:forEach>
		</select>
	</div>
	<div class="form-group">
		<label for="caratula">Caratula:</label>
		<input type="text" name="caratula" id="caratula" value="${pelicula.caratula}" class="form-control">
	</div>
	<input type="submit" value="Guardar" class="btn boton btn-primary btn-block">
</form>


<%@ include file="../../includes/pie-office.jsp" %>  
                        