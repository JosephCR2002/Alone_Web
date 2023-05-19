<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Listado de Categor&iacute;as | ALONE </title>
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
                            Listado de Categor&iacute;as</h5>
                        <table class="table table-borderless datatable">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Descripci&oacute;n</th>
                                <th scope="col">G&eacute;nero</th>
                                <th scope="col">Imagen</th>
                                <th scope="col" colspan="2"></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.categorias}" var="c">
                                <tr>
                                    <th scope="row">#${c.id}</th>
                                    <td>${c.descripcion}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${c.genero == 0}">
                                                Hombre
                                            </c:when>
                                            <c:otherwise>
                                                Mujer
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="text-center"><img src="${c.img}" alt=""
                                                                 style="max-height: 10rem !important;"></td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/categorias?view=update-form&id=${c.id}"
                                           onclick="return confirm('&iquest;Est&aacute; seguro que desea editar los datos de la categor&iacute;a?');"
                                           class="btn btn-success d-flex justify-content-center"
                                           style="gap: 0.5rem !important; width: max-content !important;">
                                            Editar <i class="bi bi-pencil"></i>
                                        </a>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/categorias?view=delete&id=${c.id}"
                                           onclick="return confirm('&iquest;Est&aacute; seguro que desea eliminar la categor&iacute;a?');"
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
                            <a href="${pageContext.request.contextPath}/categorias?view=add-form"
                               class="btn btn-primary">A&ntilde;adir una Categor&iacute;a</a>
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