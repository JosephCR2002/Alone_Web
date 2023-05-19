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
                <div class="col-12 p-3 rounded" style="background-color: white !important;">
                    <h4> Est&aacute;s viendo: <strong class="fw-bolder">${p.descripcion}</strong></h4>
                </div>
            </div>
            <div class="row g-3 rounded" style="background-color: white !important;">
                <div class="card border-0 rounded">
                    <div class="card-body text-center">
                        <img src="${p.imagen}" style="max-height: 30rem !important;">
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-3 col-12">
            <div class="row gx-3">
                <div class="card border-0 rounded">
                    <div class="card-header" style="background-color: transparent !important;">
                        <label class="text-center fs-2">${p.descripcion}</label>
                    </div>
                    <div class="card-body">
                        <label class="fs-4">S/${p.precio}</label>
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