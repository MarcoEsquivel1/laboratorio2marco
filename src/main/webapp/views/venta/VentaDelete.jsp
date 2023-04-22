<%-- 
    Document   : PedidoDelete
    Created on : 03-01-2023, 01:48:52 PM
    Author     : Mery Acevedo
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/layout/nav.jsp"></jsp:include>
<h1 class="mb-5" style="text-align: center">Eliminar venta</h1>

<c:if test="${not empty venta}">
    <div class="alert alert-danger" role="alert">
        <h4 class="alert-heading">¿Está seguro de eliminar la venta?</h4>
        <p>Total: ${venta.totalventa}</p>
        <p>Fecha: ${venta.getFechaString()}</p>

        <hr>
        <form action="${pageContext.request.contextPath}/ventas/destroy" method="post">
            <input type="hidden" name="id" value="${venta.idVenta}">
            <button type="submit" class="btn btn-danger">Eliminar</button>
            <a href="${pageContext.request.contextPath}/ventas?id=${venta.idEmpleado}" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>
</c:if>

<jsp:include page="/layout/footer.jsp"></jsp:include>
