const addAndPrintCart = async (id) => {
    await addProduct(id);
    pintarCarrito();
}
