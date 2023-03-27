<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp"/>


<h3>${title}</h3>
<c:if test="${username.present}"><!--como el valor username está solo presente una vez en el request y en nada más podemos usarlo así, si hubiera un username en otro scope
 se pondría requestScope.username-->
    <div class="alert alert-info">Hola <c:out value="${username.get()}" />, bienvenido!</div>
    <a class="btn btn-sm btn-primary" href="<c:out value="${pageContext.request.contextPath}" />/usuarios/form"> crear + </a>
</c:if>
<table class="table table-hover table-striped mt-2">
    <tr>
        <th>Id</th>
        <th>Username</th>
        <th>Email</th>
        <c:if test="${username.present}">
        <th>Editar</th>
        <th>Eliminar</th>
        </c:if>
    </tr>
    <c:forEach items="${usuarios}" var="u">
    <tr>
        <td>${u.id}</td>
        <td>${u.username}</td>
        <td>${u.email}</td>
        <c:if test="${username.present}">
        <td><a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/usuarios/form?id=<c:out value="${u.id}" />">Editar</a></td>
        <td><a class="btn btn-sm btn-danger" onclick="return confirm('¿Estas seguro de que deseas eliminar?');"
        href="${pageContext.request.contextPath}/usuarios/eliminar?id=<c:out value="${u.id}" />">Eliminar</a></td>
        </c:if>
    </tr>
    </c:forEach>
</table>

<jsp:include page="layout/footer.jsp"/>
