<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${requestScope.u.nombre} | ALONE</title>
    <%@include file="/WEB-INF/commons/user-head-imports.jsp" %>
</head>
<body>

<!-- navbar -->
<jsp:include page="/WEB-INF/commons/user-header.jsp"/>
<!-- navbar -->

<!-- sub header -->
<div class="sub-header"
     style="background-image: linear-gradient(rgba(0, 0, 0, 0.7), rgba(59,70,73,0.14)), url(${pageContext.request.contextPath}/assets/img/fondo3_1.jpg)">
    <h1 class="header-title fs-2">Mi Perfil</h1>
</div>
<!-- sub header -->

<c:if test="${requestScope.msg != null || !requestScope.msg eq ''}">
    <script>alert(${msg})</script>
</c:if>

<!-- profile -->
<section class="container p-5">
    <div class="row">
        <div class="col-xl-4">
            <div class="card border-0">
                <div class="card-body profile-card py-5 d-flex flex-column align-items-center">
                    <h2>${requestScope.u.nombre}</h2>
                    <h4 class="text-muted">${requestScope.u.nivel == 1 ? 'Administrador' : 'Cliente'}</h4>
                </div>
            </div>
        </div>
        <div class="col-xl-8">
            <div class="card border-0">
                <div class="card-body py-5">
                    <h3 class="card-title text-center">Editar Datos</h3>
                    <form action="${pageContext.request.contextPath}/profile" method="post" class="row g-3 justify-content-center">
                        <div class="col-12 col-lg-8">
                            <label for="input1" class="form-label">Nombre</label>
                            <input type="text" class="form-control" id="input1" name="nombre" value="${requestScope.u.nombre}" required>
                        </div>
                        <div class="col-12 col-lg-8">
                            <label for="input2" class="form-label">T&eacute;lefono</label>
                            <input type="tel" class="form-control" id="input2" name="telefono" value="${requestScope.u.telefono}" maxlength="9" required>
                        </div>
                        <div class="col-12 col-lg-8">
                            <label for="input3" class="form-label">Correo Electr&oacute;nico</label>
                            <input type="email" class="form-control" id="input3" name="email" value="${requestScope.u.email}" required>
                        </div>
                        <div class="col-12 col-lg-8">
                            <label for="input4" class="form-label">Contrase&ntilde;a</label>
                            <input type="password" class="form-control" id="input4" name="password" value="${requestScope.u.password}" required>
                        </div>
                        <input type="hidden" name="nivel" value="${requestScope.u.nivel}">
                        <input type="hidden" name="id" value="${requestScope.u.id}">
                        <div class="text-center">
                            <input type="submit" class="btn btn-primary px-3" value="Actualizar">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- profile -->

<!-- footer -->
<jsp:include page="/WEB-INF/commons/user-footer.jsp"/>
<!-- footer -->

<%@include file="/WEB-INF/commons/user-footer-imports.jsp" %>

</body>
</html>
