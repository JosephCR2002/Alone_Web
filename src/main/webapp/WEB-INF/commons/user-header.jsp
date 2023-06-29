<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar sticky-top navbar-expand-lg" id="navbar">
    <div class="container-fluid px-5">
        <a class="navbar-brand d-flex align-items-center gap-3" href="${pageContext.request.contextPath}/index">
            <img src="${pageContext.request.contextPath}/assets/img/loto_logo.png" alt="">
            <p>ALONE</p>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                aria-expanded="false" aria-label="Toggle navigation">
            <span><i class="bi bi-list" style="color: white; font-size: xx-large"></i></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/clothes">Productos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/about.jsp">Nosotros</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/team.jsp">Equipo</a>
                </li>
                <c:choose>
                    <c:when test="${sessionScope.usuario == null}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/login.jsp">Iniciar Sesi&oacute;n</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">
                                ${sessionScope.usuario.nombre}
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown" style="background-color: hsl(240, 2%, 9%);transition: all 300ms ease;">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/profile?id=${sessionScope.usuario.id}">Mi Perfil</a></li>
                                <c:if test="${sessionScope.usuario.nivel == 1}">
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/dashboard">Dashboard</a></li>
                                </c:if>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Cerrar Sesi&oacute;n</a></li>
                            </ul>
                        </li>
                    </c:otherwise>
                </c:choose>
                <li class="nav-item">
                    <a class="nav-link" style="font-size: large !important;" href="${pageContext.request.contextPath}/cart?action=view&page=cart" ><i class="bi bi-cart-fill"></i></a>
                </li>
            </ul>
        </div>
    </div>
</nav>