<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pago Exitoso | ALONE</title>
    <%@include file="/WEB-INF/commons/user-head-imports.jsp" %>
</head>

<body>

<!-- navbar -->
<jsp:include page="/WEB-INF/commons/user-header.jsp"/>
<!-- navbar -->

<section>
    <div class="container h-100 pt-5">
        <div class="d-flex justify-content-center align-content-center align-items-center flex-column">
            <img src="${pageContext.request.contextPath}/assets/img/succesful_payment.png"
                 class="img-fluid mb-3" style="max-height: 25rem !important; width: max-content !important;"
                 alt="Pago Exitoso">

            <h3 class="mt-3 fs-3 text-center">&iexcl;Hemos recibido tu &oacute;rden!</h3>
            <p class="mt-2 text-center">Gracias por realizar tu orden en Alone. No te olvides de enviar la captura de tu yape al 957
                112 710 con tu nombre y productos para procedir con el env&iacute;o</p>
            <a href="${pageContext.request.contextPath}/index" class="mt-3 small text-center">Continuar Comprando</a>
        </div>
    </div>
</section>

<!-- templates -->
<%@include file="/WEB-INF/user/cart/cart-templates.jsp" %>
<!-- templates -->

<!-- footer -->
<jsp:include page="/WEB-INF/commons/user-footer.jsp"/>
<!-- footer -->

<%@include file="/WEB-INF/commons/user-footer-imports.jsp" %>

</body>
</html>