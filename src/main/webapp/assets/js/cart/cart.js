const container = document.getElementById('carrito')
const parentContainer = document.getElementById('carrito-parent')
const items = document.getElementById('items')
const footer = document.getElementById('carrito-footer')
const templateFooter = document.getElementById('template-footer').content
const templateCarrito = document.getElementById('template-carrito').content
const fragment = document.createDocumentFragment()

// Eventos
items.addEventListener('click', e => {
    btnAumentarDisminuir(e)
})

document.addEventListener('DOMContentLoaded', e => {
    if (localStorage.getItem('carrito')) {
        carrito = JSON.parse(localStorage.getItem('carrito'))
    }
    pintarCarrito()
});

const pintarCarrito = () => {
    items.innerHTML = ''
    localStorage.setItem('carrito', JSON.stringify(carrito));

    if (Object.keys(carrito).length === 0) {
        parentContainer.classList.add('d-flex', 'align-items-center', 'justify-content-center')

        container.innerHTML = `
        <div class="card mb-4 d-flex align-items-center justify-content-center rounded p-3" 
             style="background-color: white !important;
                        box-shadow: 0px 0 30px rgb(1 41 112 / 10%) !important;
                        border: none !important;
                        height: 15rem !important;">
             <span class="fs-3 text-center">¡Carrito vacío, comienza a comprar!</span>
             <img src="" alt="">
        </div>
        `
        return
    }

    Object.values(carrito).forEach(producto => {
        templateCarrito.querySelector('.descripcion').textContent = producto.descripcion
        templateCarrito.querySelector('.categoria').textContent = producto['categoria'].descripcion
        templateCarrito.querySelector('.cantidad').textContent = producto.cantidad
        templateCarrito.querySelector('.precio').textContent = producto.precio * producto.cantidad
        templateCarrito.querySelector('img').setAttribute('src', producto.imagen)

        //botones
        templateCarrito.querySelector('.bi-file-minus-fill').dataset.id = producto.id
        templateCarrito.querySelector('.bi-file-plus-fill').dataset.id = producto.id

        const clone = templateCarrito.cloneNode(true)
        fragment.appendChild(clone)
    })
    items.appendChild(fragment)

    pintarFooter()
}

const pintarFooter = () => {
    footer.innerHTML = ''

    // sumar cantidad y sumar totales
    const nCantidad = Object.values(carrito).reduce((acc, {cantidad}) => acc + cantidad, 0)
    const nPrecio = Object.values(carrito).reduce((acc, {cantidad, precio}) => acc + cantidad * precio, 0)
    // console.log(nPrecio)

    templateFooter.querySelector('.total').textContent = nPrecio

    const clone = templateFooter.cloneNode(true)
    fragment.appendChild(clone)

    footer.appendChild(fragment)

    const boton = document.querySelector('#vaciar-carrito')
    boton.addEventListener('click', () => {
        if (!confirm('¿Está seguro que desea eliminar todos los productos del carrito?')) {
            return;
        }

        carrito = {}
        pintarCarrito()
    })

}

const btnAumentarDisminuir = e => {
    // console.log(e.target.classList.contains('btn-info'))
    if (e.target.classList.contains('bi-file-plus-fill')) {
        const producto = carrito[e.target.dataset.id]
        producto.cantidad++
        carrito[e.target.dataset.id] = {...producto}
        pintarCarrito()
    }

    if (e.target.classList.contains('bi-file-minus-fill')) {
        const producto = carrito[e.target.dataset.id]
        producto.cantidad--
        if (producto.cantidad === 0) {
            delete carrito[e.target.dataset.id]
        } else {
            carrito[e.target.dataset.id] = {...producto}
        }
        pintarCarrito()
    }
    e.stopPropagation()
}
