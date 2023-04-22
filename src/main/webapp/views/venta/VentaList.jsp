<%--
  Created by IntelliJ IDEA.
  User: Marco
  Date: 02/20/23
  Time: 4:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/layout/nav.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1 class="mb-3" style="text-align: center">Ventas</h1>

<form action="${pageContext.request.contextPath}/ventas/save" method="post">
    <c:if test="${pageContext.request.getParameter('id') != null}">
        <input type="number" name="idEmpleado" id="idEmpleado" value="${pageContext.request.getParameter('id')}" hidden="hidden">
    </c:if>
    <div class="row">
        <div class="col">
            <div class="form-group my-3">
                <label for="total">Total</label>
                <input type="number" step="0.01" class="form-control" id="total" min="0" lang="en" name="total" required>
            </div>
        </div>
        <div class="col">
            <div class="form-group my-3">
                <label for="fecha">Fecha</label>
                <input type="date" class="form-control" id="fecha" name="fecha" placeholder="06/01/2023"  required>
            </div>
        </div>
        <div class="col">
            <div class="form-group my-3">
                <button type="submit" class="btn btn-primary mt-4">Guardar</button>
            </div>
        </div>
    </div>
</form>

<table id="ventasTable" class="table table-striped table-hover mt-2">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Fecha</th>
        <th scope="col">Empleado</th>
        <th scope="col">Total</th>
        <th scope="col">Acciones</th>
    </tr>
    </thead>
    <tbody>

    <c:if test="${ventas != null}">
        <c:forEach items="${ventas}" var="venta">
            <tr>
                <td>${venta.idVenta}</td>
                <td>${venta.getFechaString()}</td>
                <td>${venta.empleadosByIdEmpleado.nombre}</td>
                <td>${venta.totalventa}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/ventas/edit?id=${venta.idVenta}" class="btn btn-primary">Editar</a>
                    <a href="${pageContext.request.contextPath}/ventas/delete?id=${venta.idVenta}" class="btn btn-danger">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
    </c:if>
    <c:if test="${error != null}">
        <div class="alert alert-danger" role="alert">
                ${error}
        </div>
    </c:if>
    </tbody>
</table>
<script>
    $(document).ready(function() {
        $('#ventasTable').DataTable();
    } );
</script>
<jsp:include page="/layout/footer.jsp"></jsp:include>
