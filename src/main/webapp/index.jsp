<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<jsp:include page="includes/cabecera.jsp" >
  <jsp:param name="pagina" value="inicio" />
  <jsp:param name="title" value="Inicio" /> 
</jsp:include>

<h1>Ultimas peliculas añadidas</h1>
<h3>${encabezado}</h3>


<div class="btn-group">
	<button type="button" class="btn boton btn-succes dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		Distribuidoras
	</button>
	<div class="dropdown-menu">
		<a class="dropdown-item" href="inicio">TODAS</a>
		<c:forEach items="${distribuidoras}" var="distribuidora">
			<a class="dropdown-item" href="inicio?idDistribuidora=${distribuidora.id}&distribuidora=${distribuidora.nombre}">${distribuidora.nombre}</a>
		</c:forEach>
	</div>
</div>

<br><br>

<c:forEach items="${distribuidorasConPeliculas}" var="d">

	<h4>${d.nombre}</h4>
	<hr>
	
	<div class="row-card">	
	
		<c:forEach items="${d.peliculas}" var="p">
					
			<div class="card">
				<img src="${p.caratula}" class="card-img-top" alt="${p.nombre}">
				<div class="card-body">
					<h5 class="card-title">${p.nombre}</h5>
					<p><span class="badge badge-secondary">${p.distribuidora.nombre}</span></p>
					<p class="duracion">${p.duracion} minutos</p>	
					<p class="anio">${p.anio}</p>					    					    
				</div>
			</div>
			
		</c:forEach>
		
	</div>
	
</c:forEach>

<%@ include file="includes/pie.jsp" %>
