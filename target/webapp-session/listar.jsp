<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp"/>


<h3>${title}</h3>
<c:if test="${username.present}"><!--como el valor username está solo presente una vez en el request y en nada más podemos usarlo así, si hubiera un username en otro scope
 se pondría requestScope.username-->
    <div class="alert alert-info">Hola <c:out value="${username.get()}" />, bienvenido!</div>
    <a class="btn btn-sm btn-primary" href="<c:out value="${pageContext.request.contextPath}" />/productos/form"> crear + </a>
</c:if>
<table class="table table-hover table-striped mt-2">
    <tr>
        <th>Id</th>
        <th>Nombre</th>
        <th>Categoria</th>
        <c:if test="${username.present}">
        <th>Precio</th>
        <th>Agregar</th>
        <th>Editar</th>
        <th>Eliminar</th>
        </c:if>
    </tr>
    <c:forEach items="${productos}" var="p">
    <tr>
        <td>${p.id}</td>
        <td>${p.nombre}</td>
        <td>${p.categoria.nombre}</td>
        <c:if test="${username.present}">
        <td>"${p.precio}"</td>
        <td><a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/carro/agregar?id=<c:out value="${p.id}" />">Agregar al carro</a></td>
        <td><a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/productos/form?id=<c:out value="${p.id}" />">Editar</a></td>
        <td><a class="btn btn-sm btn-danger" onclick="return confirm('¿Estas seguro de que deseas eliminar?');"
        href="${pageContext.request.contextPath}/productos/eliminar?id=<c:out value="${p.id}" />">Eliminar</a></td>
        </c:if>
    </tr>
    </c:forEach>
</table>
<p>${applicationScope.mensaje}</p>
<p>${requestScope.mensaje}</p>
<jsp:include page="layout/footer.jsp"/>
