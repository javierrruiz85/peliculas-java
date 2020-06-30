<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<jsp:include page="../../includes/cabecera.jsp" >
  <jsp:param name="pagina" value="inicio" />
  <jsp:param name="title" value="Inicio" /> 
</jsp:include>

<h1>Tabla peliculas</h1>


<br>
<a href="crear-pelicula">Insertar una nueva pelicula</a>
<br>

<div class="btn-group">
	<button type="button" class="btn boton btn-succes dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		Distribuidoras
	</button>
	<div class="dropdown-menu">
		<c:forEach items="${distribuidoras}" var="distribuidora">
			<a class="dropdown-item" href="inicio?idDistribuidora=${distribuidora.id}">${distribuidora.nombre}</a>
		</c:forEach>
	</div>
</div>

<table class="tabla table table-striped table-bordered">
	<thead>
		<tr>
			<th>Id</th>
			<th>Nombre</th>
			<th>Duracion</th>	
			<th>Año</th>
			<th>Distribuidora</th>	
			<th>Caratula</th>
			<th>Operaciones</th>						
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${peliculas}" var="p">
			<tr>
				<td>${p.id}</td> 
				<td>${p.nombre}</td>
				<td>${p.duracion}</td>
				<td>${p.anio}</td>
				<td>${p.distribuidora.nombre}</td>
				<td><div class="img-tabla"><img src="${p.caratula}" alt="imagen..."></div></td>
				<td>
					<!-- En la linea siguiente, rara vez el href va a una .jsp, es mejor ir directo al controlador (crear-pelicula) -->
					<a href="crear-pelicula?id=${p.id}"><i class="fas icono fa-edit fa-2x" title="Editar pelicula"></i> Editar &nbsp; </a>
						
					<a href="eliminar-pelicula?id=${p.id}"><i class="far icono fa-trash-alt fa-2x" title="Eliminar pelicula"></i> Eliminar </a>
					
				</td>		
			</tr>
		</c:forEach>
	</tbody>
</table>

<%@ include file="../../includes/pie.jsp" %>
