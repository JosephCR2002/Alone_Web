<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio | ALONE</title>
    <%@include file="/WEB-INF/commons/user-head-imports.jsp" %>
</head>
<body>

<!-- navbar -->
<jsp:include page="/WEB-INF/commons/user-header.jsp"/>
<!-- navbar -->

<!-- header -->
<div class="d-flex justify-content-center align-items-center text-center">
    <h1 style="z-index: 100"
        class="position-absolute header-title">
        Somos ALONE</h1>
    <div class="slider overflow-hidden">
        <div class="swiper-container mySwiper">
            <div class="swiper-wrapper">
                <div class="swiper-slide"
                     style="background-image: linear-gradient(rgba(0, 0, 0, 0.7), rgba(59,70,73,0.14)), url(${pageContext.request.contextPath}/assets/img/fondo_1.jpg);">
                </div>
                <div class="swiper-slide"
                     style="background-image: linear-gradient(rgba(0, 0, 0, 0.7), rgba(59,70,73,0.14)), url(${pageContext.request.contextPath}/assets/img/fondo1.jpeg);">
                </div>
                <div class="swiper-slide"
                     style="background-image: linear-gradient(rgba(0, 0, 0, 0.7), rgba(59,70,73,0.14)), url(${pageContext.request.contextPath}/assets/img/fondo3.jpg);">
                </div>
            </div>
        </div>
    </div>
</div>
<!-- header -->

<!-- tags -->
<div class="container content" style="height: 23rem !important;">
    <h1 class="text-center py-4">Nuestras Categor&iacute;as</h1>
    <div class="overflow-hidden pt-3">
        <div class="row swiper-container tags">
            <div class="swiper-wrapper">
                <c:forEach items="${requestScope.categorias}" var="c">
                    <div class="swiper-slide px-3">
                        <a class="d-block text-center text-decoration-none"
                           href="${pageContext.request.contextPath}/clothes?view=tag&id=${c.id}"
                           style="color: black !important;">
                            <img src="${c.img}" class="img-fluid rounded" style="max-height: 10rem !important;"
                                 alt="${c.descripcion}">
                            <p class="pt-3">${c.descripcion}</p>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="swiper-pagination position-relative"></div>
</div>
<!-- tags -->

<!-- product cards -->
<div class="container content pt-3">
    <h1 class="text-center pt-4">Nuestros Productos M&aacute;s Comprados</h1>
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
                                <a onclick="addProduct(${p.id})"
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

<div class="container content pt-3">
    <h1 class="text-center pt-4">&Uacute;ltima Moda en Hombres</h1>
    <div class="row">
        <c:forEach items="${requestScope.productosHombre}" var="p" end="5">
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
                                <a onclick="addProduct(${p.id})"
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
        <a href="${pageContext.request.contextPath}/clothes?view=gender&id=0" class="btn btn-view-more btn-lg">Ver M&aacute;s</a>
    </div>
</div>

<div class="container content pt-3">
    <h1 class="text-center pt-4">&Uacute;ltima Moda en Mujeres</h1>
    <div class="row">
        <c:forEach items="${requestScope.productosMujer}" var="p" end="5">
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
                                <a onclick="addProduct(${p.id})"
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
        <a href="${pageContext.request.contextPath}/clothes?view=gender&id=1" class="btn btn-view-more btn-lg">Ver M&aacute;s</a>
    </div>
</div>
<!-- product cards -->

<!-- footer -->
<jsp:include page="/WEB-INF/commons/user-footer.jsp"/>
<!-- footer -->

<%@include file="/WEB-INF/commons/user-footer-imports.jsp" %>
</body>
</html>