<%--
  Created by IntelliJ IDEA.
  User: Marco
  Date: 03/01/23
  Time: 12:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/layout/nav.jsp"></jsp:include>
<h1 class="mb-5" style="text-align: center">Eliminar Empleado</h1>

<c:if test="${not empty empleado}">
    <div class="alert alert-danger" role="alert">
        <h4 class="alert-heading">¿Está seguro de eliminar el empleado y todas sus ventas?</h4>
        <p>Nombre: ${empleado.nombre}</p>
        <p>Fecha de contratación: ${empleado.fechacontratacion}</p>
        <p>Salario: ${empleado.salario}</p>
    </div>
    <form action="${pageContext.request.contextPath}/empleados/destroy" method="post">
        <input type="hidden" name="id" value="${empleado.idEmpleado}">
        <button type="submit" class="btn btn-danger">Eliminar</button>
        <a href="${pageContext.request.contextPath}/empleados" class="btn btn-secondary">Cancelar</a>
</c:if>

<jsp:include page="/layout/footer.jsp"></jsp:include>
