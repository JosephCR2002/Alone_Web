<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Editar &Oacute;rden | ALONE </title>
    <%@include file="/WEB-INF/commons/dashboard-head-imports.jsp" %>
</head>
<body>
<!-- header -->
<jsp:include page="/WEB-INF/commons/dashboard-header.jsp"/>
<!-- header -->

<!-- aside -->
<jsp:include page="/WEB-INF/commons/dashboard-aside.jsp"/>
<!-- aside -->

<main id="main" class="main">
    <section class="section">
        <div class="row justify-content-center">
            <div class="col-lg-6 col-md-8">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Editar &Oacute;rden </h5>
                        <form class="row g-3" method="post"
                              action="${pageContext.request.contextPath}/ordenes?action=update">
                            <div class="col-12"><label for="input1" class="form-label">Usuario</label>
                                <select name="usuarioId" class="form-select" id="input1" required>
                                    <c:forEach items="${requestScope.usuarios}" var="u">
                                        <option value="${u.id}" ${u.id == requestScope.orden.usuario.id ? 'selected' : ''}>#${u.id} - ${u.nombre}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-12"><label for="input2" class="form-label">Producto</label>
                                <select name="productoId" class="form-select" id="input2" required>
                                    <c:forEach items="${requestScope.productos}" var="p">
                                        <option value="${p.id}" ${p.id == requestScope.orden.producto.id ? 'selected' : ''}>#${p.id} - ${p.descripcion}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-12"><label for="input3" class="form-label">Cantidad</label>
                                <input type="number" class="form-control" id="input3" name="cantidad" required min="0"
                                       onkeyup="if(value<0) value=0;" value="${requestScope.orden.detalleOrden.cantidad}">
                            </div>
                            <input type="hidden" value="${requestScope.orden.id}" name="id">
                            <input type="hidden" value="${requestScope.orden.producto.id}" name="oldId">
                            <div class="text-center"><input type="submit" class="btn btn-primary" value="Editar">
                            </div>
                        </form>
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