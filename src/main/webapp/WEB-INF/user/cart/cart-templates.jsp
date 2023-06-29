<template id="template-carrito">
    <tr>
        <th scope="row">
            <div class="d-flex align-items-center">
                <img src="https://i.imgur.com/2DsA49b.webp" class="img-fluid rounded-3"
                     style="width: 120px;" alt="Book">
                <div class="flex-column ms-4">
                    <p class="mb-2 h5 descripcion">Descripcion</p>
                    <p class="mb-0 small text-muted categoria">Categoria</p>
                </div>
            </div>
        </th>
        <td class="align-middle">
            <div class="d-flex flex-row align-items-center">
                <button class="btn btn-link px-2 fs-4">
                    <i class="bi bi-file-minus-fill"></i>
                </button>

                <p class="cantidad text-center m-0">2</p>

                <button class="btn btn-link px-2 fs-4">
                    <i class="bi bi-file-plus-fill"></i>
                </button>
            </div>
        </td>
        <td class="align-middle">
            <p class="mb-0 precio" style="font-weight: 500;">S/69</p>
        </td>
    </tr>
</template>

<template id="template-footer">
    <div class="d-flex align-items-center justify-content-between">
        <p class="mb-0 me-5 d-flex align-items-center justify-content-start">
            <span class="me-2">Total:</span> <span
                class="lead fw-normal total">S/6969</span>
        </p>

        <div class="d-flex justify-content-end align-items-center">
            <a class="mb-0 me-3 d-flex align-items-center btn btn-danger" id="vaciar-carrito">
                <i class="bi bi-trash-fill"></i>
                <span class="d-lg-block d-none ms-2">Vaciar Carrito</span>
            </a>

            <a class="mb-0 d-flex align-items-center btn btn-primary"
               onclick="
               if(confirm('&iquest;Est&aacute; seguro de realizar la orden?')){
                   confirmPayment()
               }">
                <i class="bi bi-arrow-right-circle"></i>
                <span class="d-lg-block d-none ms-2">Realizar la Orden</span>
            </a>
        </div>
    </div>
</template>
