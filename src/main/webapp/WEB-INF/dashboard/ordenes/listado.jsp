<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Listado de &Oacute;rdenes | ALONE </title>
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
                            Listado de &Oacute;rdenes</h5>
                        <table class="table table-borderless datatable">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Usuario</th>
                                <th scope="col">Producto</th>
                                <th scope="col">Precio Unitario</th>
                                <th scope="col">Cantidad</th>
                                <th scope="col">Precio Total</th>
                                <th scope="col">Fecha de Creaci&oacute;n</th>
                                <th scope="col" colspan="2"></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.ordenes}" var="o">
                                <tr>
                                    <th scope="row">#${o.id}</th>
                                    <td>#${o.usuario.id} - ${o.usuario.nombre}</td>
                                    <td>#${o.producto.id} - ${o.producto.descripcion}</td>
                                    <td>S/${o.producto.precio}</td>
                                    <td>${o.detalleOrden.cantidad}</td>
                                    <td>${o.detalleOrden.precio}</td>
                                    <td>${o.fechaCreacion}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ordenes?view=update-form&id=${o.id}"
                                           onclick="return confirm('&iquest;Est&aacute; seguro que desea editar la orden?');"
                                           class="btn btn-success d-flex justify-content-center"
                                           style="gap: 0.5rem !important; width: max-content !important;">
                                            Editar <i class="bi bi-pencil"></i>
                                        </a>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ordenes?view=delete&id=${o.id}&productoId=${o.producto.id}"
                                           onclick="return confirm('&iquest;Est&aacute; seguro que desea eliminar la orden?');"
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
                            <a href="${pageContext.request.contextPath}/ordenes?view=add-form"
                               class="btn btn-primary">A&ntilde;adir una &Oacute;rden</a>
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