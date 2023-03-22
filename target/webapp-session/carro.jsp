<%@page contentType="text/html" pageEncoding="UTF-8" import="org.jorgemendez.apiservlet.webapp.session.model.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp"/>
<h3>${title}</h3>
<c:choose>
<c:when test="${sessionScope.carro == null || sessionScope.carro.items.isEmpty()}">
<div class="alert alert-warning">Lo sentimos no hay productos en el carro de compras!</div>
</c:when>
<c:otherwise>

<form name="formcarro" action="${pageContext.request.contextPath}/carro/actualizar" method="post">
<table class="table table-hover table-striped">
    <tr>
        <th>id</th>
        <th>nombre</th>
        <th>precio</th>
        <th>cantidad</th>
        <th>total</th>
        <th>borrar</th>
    </tr>
    <c:forEach items="${carro.items}" var="item">
    <tr>
        <td>${item.producto.id}</td>
        <td>${item.producto.nombre}</td>
        <td>${item.producto.precio}</td>
        <td><input type="text" size="4" name="cant_${item.producto.id}" value="${item.cantidad}" /></td>
        <td>${item.importe}</td>
        <td><input type="checkbox" value="{item.producto.id}" name="deleteProductos" /></td>
    </tr>
    </c:forEach>
    <tr>
        <td colspan="5" style="text-align: right">Total:</td>
        <td>${sessionScope.carro.total} </td>
    </tr>
</table>
<a href="javascript:document.formcarro.submit();" class="btn btn-primary">Actualizar</a>
</form>
</c:otherwise >
</c:choose>

<div class="my-2">
    <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-secondary">volver</a> <br>
    <a href="${pageContext.request.contextPath}/productos" class="btn btn-success">seguir comprando</a>
</div>
<jsp:include page="layout/footer.jsp"/>
