let carrito = {}

document.addEventListener('DOMContentLoaded', e => {
    if (localStorage.getItem('carrito')) {
        carrito = JSON.parse(localStorage.getItem('carrito'))
    }
});

// Traer productos
const addProduct = async (id) => {
    const res = await fetch('cart?action=find&id=' + id);
    const data = await res.json()
    setCarrito(data)
    alert("¡Producto añadido al carrito!")
}

const setCarrito = producto => {
    if (carrito.hasOwnProperty(producto.id)) {
        producto.cantidad = carrito[producto.id].cantidad + 1
    }

    carrito[producto.id] = {...producto}

    localStorage.setItem('carrito', JSON.stringify(carrito));
}
