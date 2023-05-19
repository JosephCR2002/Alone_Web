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
            <h1 class="header-title fs-2">Sobre Nosotros</h1>
        </div>
        <!-- sub header -->

        <!-- about -->
        <div class="container content p-4">
            <div class="row" style="margin-top: 50px;">
                <div class="col-md-6 py-3 py-md-0">
                    <div class="card d-flex justify-content-center border-0 align-content-center" style="flex-wrap: wrap !important">
                        <img src="${pageContext.request.contextPath}/assets/img/jun.jpg" alt="" style="max-height:20rem ; width: 20rem " class="rounded-circle" >
                    </div>
                </div>
                <div class="col-md-6 p-lg-5 py-md-0 text-center ">
                    <h1>Mision</h1>
                    <p class="fs-5 px-3">Proporcionar a nuestros clientes una experiencia de compra en línea única y excepcional, ofreciendo una amplia selección de ropa sostenible y ética, acompañada de un servicio al cliente excepcional y un compromiso con la transparencia en todos nuestros procesos.

                    </p>
                </div>
            </div>
            <div class="row" style="margin-top: 50px;">
                <div class="col-md-6 p-lg-5 py-md-0 text-center " >
                     <h1>Vision</h1>
                    <p class="fs-5 px-3">Ser la marca de moda en línea líder y más reconocida por su compromiso con la sostenibilidad y la ética en la producción de ropa.
                    </p>
                </div>
                <div class="col-md-6 py-3 py-md-0">
                    <div class="card d-flex justify-content-center border-0 align-content-center " style="flex-wrap: wrap !important">
                        <img src="${pageContext.request.contextPath}/assets/img/women4.jpg" alt="" style="max-height:20rem ; width: 20rem" class="rounded-circle" >
                    </div>
                </div>
            </div>
            <div class="row" style="margin-top: 50px;"> 
                <div class="col-12 text-center d-flex justify-content-center align-content-center py-lg-4 flex-column " style="flex-wrap:wrap !important ; gap:2rem"> 
                    <h2>Ubica a ALONE:</h2>
                    <div>
                    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d62443.43396738442!2d-77.10297295317552!3d-11.994237799999999!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x9105ce543477f8d9%3A0x9d64f6794789376b!2sMegaPlaza%20Independencia!5e0!3m2!1sen!2spe!4v1654819253887!5m2!1sen!2spe" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                    </div>
                    <p> TIENDA CC. MEGAPLAZA NORTE
                        Av. Alfredo Mendiola 1400 Int. 106 A (CC. Megaplaza), Independencia
                    </p>
                </div>
            </div>

        </div>
        <!-- about -->

        <!-- footer -->
        <jsp:include page="/WEB-INF/commons/user-footer.jsp"/>
        <!-- footer -->

        <%@include file="/WEB-INF/commons/user-footer-imports.jsp"%>

    </body>
</html>