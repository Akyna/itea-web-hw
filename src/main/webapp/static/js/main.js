const addProduct = async (id) => {
    try {
        const formData = new FormData();
        formData.append("product_id", id);
        formData.append("email", "sds@koza.com");
        const result = await axios.post('cart', formData, {headers: {'content-type': 'application/x-www-form-urlencoded'}})

        const element = document.getElementById('app-cart-count-quantity');
        element.innerHTML = result.data.quantity;
        element.classList.remove("hidden-content");
    } catch (e) {
        console.warn(e);
    }
}
