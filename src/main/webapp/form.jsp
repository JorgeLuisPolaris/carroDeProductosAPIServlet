<%@page contentType="text/html" pageEncoding="UTF-8"
import="java.time.format.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp"/>

<h3>${title}</h3>

<form action="${pageContext.request.contextPath}/productos/form" method="post">
    <div class="row mb-2">
        <label for="nombre" class="col-form-label col-sm-2">Nombre</label>
        <div class="col-sm-4">
            <input type="text" name="nombre" id="nombre" value="${producto.nombre}" class="form-control">
        </div>
        <c:if test="${errores != null && errores.containsKey('nombre')}">
        <div style="color:red;"><${errores.nombre}> </div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label for="precio" class="col-form-label col-sm-2">Precio</label>
        <div class="col-sm-4">
            <input type="number" name="precio" id="precio" value="${producto.precio > 0 ? producto.precio: ""}" class="form-control">
        </div>
         <c:if test="${errores != null && not empty errores.precio}">
                <div style="color:red;"><${errores.precio}> </div>
         </c:if>
         </div>
    <div class="row mb-2">
        <label for="sku" class="col-form-label col-sm-2">Sku</label>
        <div class="col-sm-4">
            <input type="text" name="sku" id="sku" value="${producto.sku}" class="form-control">
        </div>
         <c:if test="${errores != null && not empty errores.sku}">
               <div style="color:red;"><${errores.sku}> </div>
         </c:if>
    </div>
    <div class="row mb-2">
        <label for="fecha_registro" class="col-form-label col-sm-2">Fecha registro</label>
        <div class="col-sm-4">
            <input type="date" name="fecha_registro" id="fecha_registro" value="${producto.fecha!=null ? producto.fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")): ""}" class="form-control">
        </div>
        <c:if test="${fecha_registro != null && not empty fecha_registro.sku}">
                       <div style="color:red;"><${fecha_registro.sku}> </div>
         </c:if>

    </div>
    <div class="row mb-2">
        <label for="categoria" class="col-form-label col-sm-2">Categoria</label>
        <div class="col-sm-4">
            <select name="categoria" id="categoria" class="form-select class="col-form-label col-sm-2>
                <option value="">--- Seleccionar ---</option>
                <c:forEach items="${categorias}" var="c">
                <option value="${c.id}" ${c.id.equals(producto.categoria.id)? "selected": ""}>${c.nombre}</option>
                </c:forEach>
            </select>
        </div>
        <c:if test="${categoria != null && not empty categoria.sku}">
             <div style="color:red;"><${categoria.sku}> </div>
        </c:if>

    </div>
    <div class="row mb-2">
       <div>
        <input class="btn btn-primary" type="submit" value="${(producto.id!=null&&producto.id>0)?"editar":"Crear"}">

        </div>
         <input type="hidden" name="id"  value="${producto.id}">
    </div>
</form>
<jsp:include page="layout/footer.jsp"/>
