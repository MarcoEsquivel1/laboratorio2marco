<%--
  Created by IntelliJ IDEA.
  User: Marco
  Date: 02/28/23
  Time: 11:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/layout/nav.jsp"></jsp:include>
<h1 class="mb-5" style="text-align: center">Nuevo Empleado</h1>

<form action="${pageContext.request.contextPath}/empleados/save" method="post">
    <div class="form-group my-3">
        <label for="nombre">Nombre</label>
        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre" maxlength="50" minlength="3" required>
    </div>
    <div class="form-group my-3">
        <label for="fecha">Fecha de Contratacion</label>
        <input type="date" class="form-control" id="fecha" name="fecha" placeholder="Fecha de Contratacion" required>
    </div>
    <div class="form-group my-3">
        <label for="salario">Salario</label>
        <input type="number" min="0" step="0.01" class="form-control" id="salario" name="salario" placeholder="Salario" required>
    </div>
    <div class="form-group my-3">
        <button type="submit" class="btn btn-primary">Guardar</button>
        <a href="${pageContext.request.contextPath}/empleados" class="btn btn-secondary">Cancelar</a>
    </div>
</form>
<jsp:include page="/layout/footer.jsp"></jsp:include>
