<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro | ALONE</title>
    <%@include file="/WEB-INF/commons/user-head-imports.jsp" %>
</head>
<body>

<!-- navbar -->
<jsp:include page="/WEB-INF/commons/user-header.jsp"/>
<!-- navbar -->

<c:if test="${requestScope.msg != null || !requestScope.msg eq ''}">
    <script>alert(${msg})</script>
</c:if>

<!-- sign up -->
<div class="container content">
    <div class="row">
        <div class="col-md-4 side black">
            <h3>&iquest;Ya tienes una cuenta?</h3>
            <p class="m-0">Inicia sesi&oacute;n ahora</p>
            <div class="text-center p-3">
                <a href="login.jsp" class="btn btn-login">Iniciar Sesi&oacute;n</a>
            </div>
        </div>
        <div class="col-md-8 side">
            <div class="card" style="border: none !important;">
                <div class="card-body">
                    <h3 class="card-title">Crear una Cuenta</h3>
                    <form action="${pageContext.request.contextPath}/signup" method="post" class="row g-3 justify-content-center">
                        <div class="col-12 col-lg-8">
                            <label for="input1" class="form-label">Nombre</label>
                            <input type="text" class="form-control" id="input1" name="nombre" required>
                        </div>
                        <div class="col-12 col-lg-8">
                            <label for="input2" class="form-label">T&eacute;lefono</label>
                            <input type="tel" class="form-control" id="input2" name="telefono" maxlength="9" required>
                        </div>
                        <div class="col-12 col-lg-8">
                            <label for="input3" class="form-label">Correo Electr&oacute;nico</label>
                            <input type="email" class="form-control" id="input3" name="email" required>
                        </div>
                        <div class="col-12 col-lg-8">
                            <label for="input4" class="form-label">Contrase&ntilde;a</label>
                            <input type="password" class="form-control" id="input4" name="password" required>
                        </div>
                        <input type="hidden" name="nivel" value="0">
                        <div class="text-center">
                            <input type="submit" class="btn btn-primary px-3" value="Registrarse">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- sign up -->

<!-- footer -->
<jsp:include page="/WEB-INF/commons/user-footer.jsp"/>
<!-- footer -->

<%@include file="/WEB-INF/commons/user-footer-imports.jsp" %>

</body>
</html>