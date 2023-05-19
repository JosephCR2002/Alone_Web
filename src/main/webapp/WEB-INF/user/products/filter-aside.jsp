<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside id="sidebar" class="sidebar rounded">
    <ul class="sidebar-nav" id="sidebar-nav">
        <li class="nav-item"><a class="nav-link " href="${pageContext.request.contextPath}/clothes"> <i
                class="bi bi-grid"></i> <span>Todos Los Productos</span> </a></li>
        <li class="nav-item">
            <a class="nav-link collapsed" data-bs-target="#tags-nav" data-bs-toggle="collapse" href="#">
                <i class="bi bi-tag"></i><span>Categor&iacute;as</span><i class="bi bi-chevron-down ms-auto"></i>
            </a>
            <ul id="tags-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
                <c:forEach items="${requestScope.categorias}" var="c">
                    <li><a href="${pageContext.request.contextPath}/clothes?view=tag&id=${c.id}"> <i
                            class="bi bi-circle"></i><span>${c.descripcion}</span> </a></li>
                </c:forEach>
            </ul>
            <a class="nav-link collapsed" data-bs-target="#gender-nav" data-bs-toggle="collapse" href="#">
                <i class="bi bi-gender-ambiguous"></i><span>G&eacute;neros</span><i class="bi bi-chevron-down ms-auto"></i>
            </a>
            <ul id="gender-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
                <li><a href="${pageContext.request.contextPath}/clothes?view=gender&id=0"> <i
                        class="bi bi-circle"></i><span>Hombre</span> </a></li>
                <li><a href="${pageContext.request.contextPath}/clothes?view=gender&id=1"> <i
                        class="bi bi-circle"></i><span>Mujer</span> </a></li>
            </ul>
        </li>
    </ul>
</aside>