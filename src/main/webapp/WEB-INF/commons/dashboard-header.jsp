<header id="header" class="header fixed-top d-flex align-items-center">
    <div class="d-flex align-items-center justify-content-between"><a
            href="${pageContext.request.contextPath}/dashboard" class="logo d-flex align-items-center"> <img
            src="${pageContext.request.contextPath}/assets/img/loto_logo.png" alt=""> <span class="d-none d-lg-block">Dashboard</span>
    </a> <i class="bi bi-list toggle-sidebar-btn"></i></div>
    <nav class="header-nav ms-auto">
        <ul class="d-flex align-items-center">
            <li class="nav-item dropdown pe-3">
                <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
                    <span class="d-none d-md-block dropdown-toggle ps-2">${sessionScope.usuario.nombre}</span>
                </a>
                <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
                    <li><a class="dropdown-item d-flex align-items-center"
                           href="${pageContext.request.contextPath}/index"> <i class="bi bi-house"></i>
                        <span>Volver Al Inicio</span> </a></li>
                    <li><a class="dropdown-item d-flex align-items-center"
                           href="${pageContext.request.contextPath}/logout"> <i class="bi bi-box-arrow-right"></i>
                        <span>Cerrar Sesi&oacute;n</span> </a></li>
                </ul>
            </li>
        </ul>
    </nav>
</header>
