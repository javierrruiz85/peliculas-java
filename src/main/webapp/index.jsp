<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<jsp:include page="includes/cabecera.jsp" >
  <jsp:param name="pagina" value="inicio" />
  <jsp:param name="title" value="Inicio" /> 
</jsp:include>

<h1>Tabla peliculas</h1>

<br>

<table class="tabla table table-striped table-bordered">
	<thead>
		<tr>
			<th>Id</th>
			<th>Nombre</th>					
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${peliculas}" var="p">
			<tr>
				<td>${p.id}</td> 
				<td>${p.nombre}</td>		
			</tr>
		</c:forEach>
	</tbody>
</table>

<%@ include file="includes/pie.jsp" %>
