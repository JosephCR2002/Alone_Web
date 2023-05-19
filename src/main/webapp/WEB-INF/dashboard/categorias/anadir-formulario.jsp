<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>A&ntilde;adir Categor&iacute;a | ALONE </title>
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
        <div class="row justify-content-center">
            <div class="col-lg-6 col-md-8">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">A&ntilde;adir una Categor&iacute;a</h5>
                        <form class="row g-3" method="post" action="${pageContext.request.contextPath}/categorias?action=add">
                            <div class="col-12"><label for="input1" class="form-label">Descripci&oacute;n</label>
                                <input type="text"
                                       class="form-control"
                                       id="input1"
                                       name="descripcion"
                                       required>
                            </div>
                            <div class="col-12"><label for="input2" class="form-label">G&eacute;nero</label>
                                <select name="genero" class="form-select" id="input2" required>
                                    <option value="0" selected>Hombre</option>
                                    <option value="1">Mujer</option>
                                </select>
                            </div>
                            <div class="col-12"><label for="input3" class="form-label">Url de la Imagen</label>
                                <input type="url"
                                       class="form-control"
                                       id="input3"
                                       name="img"
                                       required>
                            </div>
                            <div class="text-center"><input type="submit" class="btn btn-primary" value="A&ntilde;adir">
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