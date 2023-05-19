<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Productos | ALONE</title>
    <%@include file="/WEB-INF/commons/user-head-imports.jsp" %>
</head>

<body>
<!-- navbar -->
<jsp:include page="/WEB-INF/commons/user-header.jsp"/>
<!-- navbar -->

<!-- products -->
<div class="container overflow-hidden">

    <div class="row gx-5 mt-lg-4 mt-sm-4">
        <div class="col-lg-3 col-12">
            <div class="row gx-3">
                <jsp:include page="/WEB-INF/user/products/filter-aside.jsp"/>
            </div>
        </div>
        <div class="col-lg-9 col-12">
            <div class="row g-3 mb-5">
                <div class="col-12 p-3 rounded" style="background-color: white !important;">
                    <h4> Est&aacute;s viendo: <strong class="fw-bolder">${requestScope.title}</strong></h4>
                </div>
            </div>
            <div class="row g-3 rounded" style="background-color: white !important;">
                <c:forEach items="${requestScope.productos}" var="p">
                    <div class="col-sm-4 p-lg-3 p-sm-3 p-4">
                        <div class="form-group">
                            <div class="card">
                                <div class="card-header">
                                    <label class="col-sm-12 text-center">${p.descripcion}</label>
                                </div>
                                <div class="card-body text-center">
                                    <img src="${p.imagen}" style="max-height: 10rem !important;">
                                </div>
                                <div class="card-footer">
                                    <div class="col-sm-12 text-center fs-4">
                                        <label>S/${p.precio}</label>
                                    </div>
                                    <div class="col-sm-12 text-center card-btns">
                                        <a href="${pageContext.request.contextPath}/clothes?view=id&id=${p.id}"
                                           class="btn btn2 btn-outline-primary d-flex" style="gap: 0.5rem">Ver m&aacute;s<i
                                                class="bi bi-eye"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
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