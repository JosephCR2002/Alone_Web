<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carrito | ALONE</title>
    <%@include file="/WEB-INF/commons/user-head-imports.jsp" %>
</head>
<body style="height: 100vh !important;">

<!-- navbar -->
<jsp:include page="/WEB-INF/commons/user-header.jsp"/>
<!-- navbar -->

<section style="height: 63.1% !important;">
    <div class="container h-100 pt-5" id="carrito-parent">
        <div id="carrito">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col">

                    <div class="card mb-4 rounded p-3"
                         style="background-color: white !important;
                            box-shadow: 0px 0 30px rgb(1 41 112 / 10%) !important;
                            border: none !important;">

                        <!-- Carrito -->
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col">Producto</th>
                                    <th scope="col">Cantidad</th>
                                    <th scope="col">Precio</th>
                                </tr>
                                </thead>

                                <tbody id="items"></tbody>

                            </table>
                        </div>
                        <!-- Carrito -->
                    </div>

                    <!-- Total de Compra -->
                    <div class="card rounded p-3"
                         style="background-color: white !important;
                            box-shadow: 0px 0 30px rgb(1 41 112 / 10%) !important;
                            border: none !important;">
                        <div class="card-body p-0">
                            <div id="carrito-footer"></div>
                        </div>
                    </div>
                    <!-- Total de Compra -->
                </div>
            </div>
        </div>
    </div>
</section>

<!-- templates -->
<%@include file="/WEB-INF/user/cart/cart-templates.jsp"%>
<!-- templates -->

<!-- footer -->
<jsp:include page="/WEB-INF/commons/user-footer.jsp"/>
<!-- footer -->

<%@include file="/WEB-INF/commons/user-footer-imports.jsp" %>
<script src="${pageContext.request.contextPath}/assets/js/cart/cart.js?v=1.99"></script>

</body>
</html>
