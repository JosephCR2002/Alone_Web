<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sobre Nosotros | ALONE</title>
        <%@include file="/WEB-INF/commons/user-head-imports.jsp"%>
    </head>
    <body>

        <!-- navbar -->
        <jsp:include page="/WEB-INF/commons/user-header.jsp"/>
        <!-- navbar -->

        <!-- sub header -->
        <div class="sub-header" style="background-image: linear-gradient(rgba(0, 0, 0, 0.7), rgba(59,70,73,0.14)), url(${pageContext.request.contextPath}/assets/img/fondo3_1.jpg)">
            <h1 class="header-title fs-2">Nuestro Equipo</h1>
        </div>
        <!-- sub header -->
        

        <!-- our team -->
        <div class="container content p-4">

            <div class="d-flex justify-content-center flex-column text-center fs-5">
                <p style="color:red" class="fw-bold">
                    Cavero Ramos, Joseph Gianfranco</p>
                <p>Diseño del Inicio.</p>   
                <p> Registro y perfil</p>
                <p> de la pagina web.</p>

            </div>
        </div>


        <div class="container content p-4">


            <div class="d-flex justify-content-center flex-column text-center fs-5">
                <p style="color:red" class="fw-bold">
                    Zamata Chavez, Patrick Alvaro </p>
                <p> Diseño del portal.</p>
                <p> La vista de los </p>
                <p> productos de la web.</p>

            </div>
        </div>

        <div class="container content p-4">
            <div class="d-flex justify-content-center flex-column text-center fs-5">
                <p style="color:red" class="fw-bold">
                Zelada Diaz, Omar Fortunato</p>
                <p>Diseño de login.</p>
                <p>Implementacion de </p>
                <p>vistas de la web.</p>

            </div>
        </div>



      

        <!-- our team -->

        <!-- footer -->
        <jsp:include page="/WEB-INF/commons/user-footer.jsp"/>
        <!-- footer -->

        <%@include file="/WEB-INF/commons/user-footer-imports.jsp"%>

    </body>
</html>