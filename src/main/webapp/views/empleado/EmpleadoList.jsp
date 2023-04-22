<%--
  Created by IntelliJ IDEA.
  User: Marco
  Date: 03/22/23
  Time: 2:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/layout/nav.jsp"></jsp:include>

<h1 class="mb-3" style="text-align: center">Empleados</h1>
<a href="${pageContext.request.contextPath}/empleados/create" class="btn btn-success my-3 btn-sm">Agregar</a>
    <table id="empleadosTable" class="table table-striped table-hover">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Nombre</th>
            <th scope="col">Fecha Contratación</th>
            <th scope="col">Salario</th>
            <th scope="col">Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${empleados}" var="empleado">
            <tr>
                <th scope="row"><c:out value="${empleado.idEmpleado}"/></th>
                <td><c:out value="${empleado.nombre}"/></td>
                <td><c:out value="${empleado.getFechaString()}"/></td>
                <td><c:out value="${empleado.salario}"/></td>
                <td>
                    <a href="${pageContext.request.contextPath}/ventas?id=${empleado.idEmpleado}" class="btn btn-primary">Ventas</a>
                    <a href="${pageContext.request.contextPath}/empleados/edit?id=${empleado.idEmpleado}" class="btn btn-primary">Editar</a>
                    <a href="${pageContext.request.contextPath}/empleados/delete?id=${empleado.idEmpleado}" class="btn btn-danger">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <script>
        $(document).ready(function () {
            $('#empleadosTable').DataTable();
        });
    </script>
<jsp:include page="/layout/footer.jsp"></jsp:include>
