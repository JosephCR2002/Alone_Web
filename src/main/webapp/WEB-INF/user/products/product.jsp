<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${requestScope.p.descripcion} | ALONE</title>
    <%@include file="/WEB-INF/commons/user-head-imports.jsp" %>
</head>

<body>
<!-- navbar -->
<jsp:include page="/WEB-INF/commons/user-header.jsp"/>
<!-- navbar -->

<!-- products -->
<div class="container overflow-hidden">

    <div class="row gx-5 mt-lg-4 mt-sm-4">
        <div class="col-lg-9 col-12">
            <div class="row g-3 mb-5">
                <div class="col-12 p-3 rounded" style="background-color: white !important; box-shadow: 0px 0 30px rgb(1 41 112 / 10%) !important;">
                    <h4> Est&aacute;s viendo: <strong class="fw-bolder">${p.descripcion}</strong></h4>
                </div>
            </div>
            <div class="row g-3 rounded" style="background-color: white !important; box-shadow: 0px 0 30px rgb(1 41 112 / 10%) !important;">
                <div class="card border-0 rounded">
                    <div class="card-body text-center">
                        <img src="${p.imagen}" class="img-fluid rounded-3" style="height: 30rem !important;" alt="${p.descripcion}">
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-3 col-12">
            <div class="row gx-3 rounded" style="box-shadow: 0px 0 30px rgb(1 41 112 / 10%) !important;">
                <div class="card border-0 rounded">
                    <div class="card-header" style="background-color: transparent !important;">
                        <label class="text-center fs-2">${p.descripcion}</label>
                    </div>
                    <div class="card-body d-flex align-items-center justify-content-center flex-column">
                        <label class="fs-3 mb-3">S/${p.precio}</label>

                        <a onclick="addProduct(${p.id})"
                           class="btn btn2 btn-danger d-flex" style="gap: 0.5rem"><i
                                class="bi bi-plus"></i> A&ntilde;adir al carrito</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- products -->

<!-- footer -->
<jsp:include page="/WEB-INF/commons/user-footer.jsp"/>
<!-- footer -->

<%@include file="/WEB-INF/commons/user-footer-imports.jsp" %>

</body>
</html>