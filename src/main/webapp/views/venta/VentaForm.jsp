<%-- 
    Document   : PedidoForm
    Created on : 03-01-2023, 01:48:09 PM
    Author     : Mery Acevedo
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/layout/nav.jsp"></jsp:include>
<h1 class="mb-5" style="text-align: center">Nueva venta</h1>

<form action="${pageContext.request.contextPath}/ventas/save" method="post">
    <input type="hidden" name="idcliente" value="${id}">
    <div class="form-group my-3">
        <label for="empleado">Empleadis</label>
        <select class="form-select" name="empleado" id="empleado" required>
            <c:forEach items="${empleadis}" var="empleado">
                <option value="${empleado.id_empleado}">${empleado.nombre}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group my-3">
        <label for="total">Total</label>
        <input type="number" step="0.01" class="form-control" id="total" min="0" lang="en" name="total" required>
    </div>
    <div class="form-group my-3">
        <label for="fecha">Fecha</label>
        <input type="date" class="form-control" id="fecha" name="fecha" placeholder="06/01/2023"  required>
    </div>
    <div class="form-group my-3">
        <button type="submit" class="btn btn-primary" >Guardar</button>
    </div>
    <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">
            ${error}
        </div>
    </c:if>
  </form>
<jsp:include page="/layout/footer.jsp"></jsp:include>
