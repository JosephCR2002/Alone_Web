<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesi&oacute;n | ALONE</title>
    <%@include file="/WEB-INF/commons/user-head-imports.jsp" %>
</head>
<body>

<!-- navbar -->
<jsp:include page="/WEB-INF/commons/user-header.jsp"/>
<!-- navbar -->

<c:if test="${requestScope.msg != null || !requestScope.msg eq ''}">
    <script>alert(${msg})</script>
</c:if>

<!-- login -->
<div class="container content">
    <div class="row">
        <div class="col-md-4 side black">
            <h3>&iquest;Eres nuevo por aqu&iacute;?</h3>
            <p class="m-0">Crea una cuenta</p>
            <div class="text-center p-3"><a href="signup.jsp" class="btn btn-login">Registrarse</a></div>
        </div>
        <div class="col-md-8 side">
            <div class="card" style="border: none !important;">
                <div class="card-body">
                    <h3 class="card-title mb-4">Iniciar Sesi&oacute;n</h3>
                    <form action="${pageContext.request.contextPath}/login" method="post" class="row g-3">
                        <div class="col-12">
                            <label for="input1" class="form-label">Correo Electr&oacute;nico</label>
                            <input type="email" class="form-control" id="input1" name="email" required>
                        </div>
                        <div class="col-12">
                            <label for="input2" class="form-label">Contrase&ntilde;a</label>
                            <input type="password" class="form-control" id="input2" name="password" required>
                        </div>
                        <div class="text-center">
                            <input type="submit" class="btn btn-primary px-3 mt-4" value="Iniciar Sesi&oacute;n">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- login -->


<!-- footer -->
<jsp:include page="/WEB-INF/commons/user-footer.jsp"/>
<!-- footer -->

<%@include file="/WEB-INF/commons/user-footer-imports.jsp" %>

</body>
</html>