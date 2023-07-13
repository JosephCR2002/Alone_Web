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
        <div class="container overflow-auto">
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
        </div>
        <!-- product cards -->
        <div class="d-flex justify-content-center align-items-center p-1">
            <h4 class="fw-bold">RECOMENDACIONES</h4>
        </div>
        <div class="container content " style="margin-top: 0.25rem !important"> 
            <h3 class="text-center pt-4">Nuestros Productos M&aacute;s Comprados</h3>
            <div class="row">
                <c:forEach items="${requestScope.productosTop}" var="p" end="5">
                    <div class="col-sm-4 p-lg-3 p-sm-3 p-4">
                        <div class="form-group">
                            <div class="card">
                                <div class="card-header">
                                    <label class="col-sm-12 text-center">${p.descripcion}</label>
                                </div>
                                <div class="card-body text-center">
                                    <img src="${p.imagen}" style="max-height: 10rem !important;" alt="${p.descripcion}"
                                         class="img-fluid">
                                </div>
                                <div class="card-footer">
                                    <div class="col-sm-12 text-center fs-4">
                                        <label>S/${p.precio}</label>
                                    </div>
                                    <div class="col-sm-12 text-center card-btns">
                                        <a href="${pageContext.request.contextPath}/clothes?view=id&id=${p.id}"
                                           class="btn btn2 btn-outline-primary d-flex" style="gap: 0.5rem"><i
                                                class="bi bi-eye"></i></a>
                                        <a onclick="addAndPrintCart(${p.id});"
                                           class="btn btn2 btn-outline-danger d-flex" style="gap: 0.5rem"><i
                                                class="bi bi-plus"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="text-center px-3 pb-3 pt-md-3">
                <a href="${pageContext.request.contextPath}/clothes?view=bestSellers" class="btn btn-view-more btn-lg">Ver M&aacute;s</a>
            </div>
        </div>
        <!-- templates -->
        <%@include file="/WEB-INF/user/cart/cart-templates.jsp"%>
        <!-- templates -->

        <!-- footer -->
        <jsp:include page="/WEB-INF/commons/user-footer.jsp"/>
        <!-- footer -->

        <%@include file="/WEB-INF/commons/user-footer-imports.jsp" %>
        <script src="${pageContext.request.contextPath}/assets/js/cart/cart.js?v=1.99"></script>
        <script src="${pageContext.request.contextPath}/assets/js/cart/payment.js?v=1"></script>
        <script src="${pageContext.request.contextPath}/assets/js/cart/addAndPrintCart.js"></script>
    </body>
</html>
