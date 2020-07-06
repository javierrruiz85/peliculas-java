<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<jsp:include page="../../includes/cabecera.jsp" >
  <jsp:param name="pagina" value="inicio" />
  <jsp:param name="title" value="Inicio" /> 
</jsp:include>

<h1>Listado de distribuidoras</h1>

<!-- En la siguiente linea, en el href, estamos llamando al controlador 'distribuidora' (DistribuidoraController) y le pasamos el id=0 (?id=0) -->
<a href="distribuidora?id=0">Crear nueva distribuidora</a>

<table class="tabla table table-striped table-bordered">
	<thead>
		<tr>
			<th>ID</th>
			<th>Nombre</th>
			<th>Operaciones</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${distribuidoras}" var="d">
			<tr>
				<td>${d.id}</td>
				<td>${d.nombre}</td>
				<td>
					<a href="distribuidora?id=${d.id}"><i class="fas icono fa-edit fa-2x" title="Editar distribuidora"></i> Editar &nbsp; </a>
					<a href="distribuidora?id=${d.id}&operacion=2"><i class="far icono fa-trash-alt fa-2x" title="Eliminar distribuidora"></i> Eliminar </a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<%@ include file="../../includes/pie.jsp" %>