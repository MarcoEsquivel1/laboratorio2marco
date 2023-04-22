<%--
  Created by IntelliJ IDEA.
  User: Marco
  Date: 03/01/23
  Time: 12:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/layout/nav.jsp"></jsp:include>
<h1 class="mb-5" style="text-align: center">Actualizar Empleado</h1>

<c:if test="${not empty error}">
    <div class="alert alert-danger" role="alert">
        ${error}
    </div>
</c:if>
<form action="${pageContext.request.contextPath}/empleados/update" method="post">
    <input type="hidden" name="id" value="${empleado.idEmpleado}">
    <div class="form-group my-3">
        <label for="nombre">Nombre</label>
        <input type="text" class="form-control" id="nombre" name="nombre" value="${empleado.nombre}" maxlength="50" minlength="3" required>
    </div>
    <div class="form-group my-3">
        <label for="fecha">Fecha de Contratacion</label>
        <input type="date" class="form-control" id="fecha" name="fecha" value="${empleado.getFechaString()}" required>
    </div>
    <div class="form-group my-3">
        <label for="salario">Salario</label>
        <input type="number" class="form-control" id="salario" name="salario" value="${empleado.salario}" required>
    </div>
    <div class="form-group my-3">
        <button type="submit" class="btn btn-primary">Guardar</button>
    </div>
</form>
<jsp:include page="/layout/footer.jsp"></jsp:include>
