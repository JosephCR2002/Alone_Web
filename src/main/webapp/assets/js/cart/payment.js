const confirmPayment = async () => {
    const location = window.location.hostname;
    const settings = {
        method: 'POST',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(carrito)
    };
    try {
        const fetchResponse = await fetch('cart?action=payment', settings);
        const data = await fetchResponse.json();
        if (data===true){
            carrito = {}
            localStorage.setItem('carrito', JSON.stringify(carrito))

            window.location.href = 'cart?action=view&page=payment_success'
        } else {
            alert('Vaya, algo salio mal :(')
        }
    } catch (e) {
        alert('Vaya, algo salio mal :(')
        console.log(e);
    }

}