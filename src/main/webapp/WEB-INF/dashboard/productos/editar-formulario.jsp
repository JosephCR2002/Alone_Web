<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Editar Producto | ALONE </title>
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
                        <h5 class="card-title">Editar un Producto</h5>
                        <form class="row g-3" method="post"
                              action="${pageContext.request.contextPath}/productos?action=update">
                            <div class="col-12"><label for="input1" class="form-label">Descripci&oacute;n</label>
                                <input type="text"
                                       class="form-control"
                                       id="input1"
                                       name="descripcion"
                                       value="${requestScope.producto.descripcion}"
                                       required>
                            </div>
                            <div class="col-12"><label for="input2" class="form-label">Precio</label>
                                <input type="number" class="form-control" id="input2" name="precio" required min="0"
                                       step="0.01"
                                       value="${requestScope.producto.precio}"
                                       onkeyup="if(value<0) value=0;">
                            </div>
                            <div class="col-12"><label for="input3" class="form-label">Categor&iacute;a</label>
                                <select name="categoriaId" class="form-select" id="input3" required>
                                    <c:forEach items="${requestScope.categorias}" var="c">
                                        <option value="${c.id}" ${c.id == requestScope.producto.categoria.id ? 'selected' : ''}>
                                            #${c.id} - ${c.descripcion}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-12"><label for="input4" class="form-label">Url de la Imagen</label>
                                <input type="url"
                                       class="form-control"
                                       id="input4"
                                       name="img"
                                       value="${requestScope.producto.imagen}"
                                       required>
                            </div>
                            <div class="col-12"><label for="input5" class="form-label">Estado</label>
                                <select name="estado" class="form-select" id="input5" required>
                                    <c:choose>
                                        <c:when test="${requestScope.producto.estado == 0}">
                                            <option value="0" selected>En Stock</option>
                                            <option value="1">Sin Stock</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="0">En Stock</option>
                                            <option value="1" selected>Sin Stock</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </div>
                            <div class="col-12"><label for="input6" class="form-label">Inventario</label>
                                <input type="number" class="form-control" id="input6" name="inventario" required min="0"
                                       onkeyup="if(value<0) value=0;"
                                       value="${requestScope.producto.inventario}"
                                >
                            </div>
                            <input type="hidden" value="${requestScope.producto.id}" name="id">
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