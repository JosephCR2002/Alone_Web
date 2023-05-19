<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Listado de Productos | ALONE </title>
    <%@include file="/WEB-INF/commons/dashboard-head-imports.jsp" %>
</head>
<body>
<!-- header -->
<jsp:include page="/WEB-INF/commons/dashboard-header.jsp"/>
<!-- header -->

<!-- aside -->
<jsp:include page="/WEB-INF/commons/dashboard-aside.jsp"/>
<!-- aside -->
<c:if test="${requestScope.msg != null || !requestScope.msg eq ''}">
    <script>alert(${msg})</script>
</c:if>
<main id="main" class="main">
    <section class="section">
        <div class="row">
            <div class="col-lg-12">
                <div class="card overflow-auto">
                    <div class="card-body">
                        <h5 class="card-title" style="padding-left: 6px !important; font-size: x-large !important;">
                            Listado de Productos</h5>
                        <table class="table table-borderless datatable">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Descripci&oacute;n</th>
                                <th scope="col">Categor&iacute;a</th>
                                <th scope="col">Precio</th>
                                <th scope="col">Imagen</th>
                                <th scope="col">Estado</th>
                                <th scope="col">Inventario</th>
                                <th scope="col">Fecha de Creaci&oacute;n</th>
                                <th scope="col" colspan="2"></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.productos}" var="p">
                                <tr>
                                    <th scope="row">#${p.id}</th>
                                    <td>${p.descripcion}</td>
                                    <td>#${p.categoria.id} - ${p.categoria.descripcion}</td>
                                    <td>S/${p.precio}</td>
                                    <td class="text-center"><img src="${p.imagen}" alt=""
                                                                 style="max-height: 10rem !important;"></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${p.estado == 0}">
                                                En Stock
                                            </c:when>
                                            <c:otherwise>
                                                Sin Stock
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${p.inventario}</td>
                                    <td>${p.fechaCreacion}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/productos?view=update-form&id=${p.id}"
                                           onclick="return confirm('&iquest;Est&aacute; seguro que desea editar los datos del producto?');"
                                           class="btn btn-success d-flex justify-content-center"
                                           style="gap: 0.5rem !important; width: max-content !important;">
                                            Editar <i class="bi bi-pencil"></i>
                                        </a>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/productos?view=delete&id=${p.id}"
                                           onclick="return confirm('&iquest;Est&aacute; seguro que desea eliminar el producto?');"
                                           class="btn btn-danger d-flex justify-content-center"
                                           style="gap: 0.5rem !important; width: max-content !important;">
                                            Eliminar <i class="bi bi-trash"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div style="padding: 8px 10px !important;">
                            <a href="${pageContext.request.contextPath}/productos?view=add-form"
                               class="btn btn-primary">A&ntilde;adir un Producto</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>
<jsp:include page="/WEB-INF/commons/dashboard-footer.jsp"/>
<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
        class="bi bi-arrow-up-short"></i></a>
<%@include file="/WEB-INF/commons/dashboard-scripts-imports.jsp" %>

</body>
</html>