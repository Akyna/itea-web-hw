const addProduct = async (id) => {
    try {
        const formData = new FormData();
        formData.append("productId", id);

        const result = await axios.post(
            'cart',
            new URLSearchParams({
                productId: id,
            })
        )
        const element = document.getElementById('app-cart-count-quantity');
        if (result.data.quantity) {
            element.innerHTML = result.data.quantity;
            element.classList.remove("hidden-content");
        }

    } catch (err) {
        if (err.response) {
            // The client was given an error response (5xx, 4xx)
            console.warn(err.response.data);
        } else if (err.request) {
            // The client never received a response, and the request was never left
            console.warn(err.request);
        } else {
            // Anything else
            console.warn('Error', err.message);
        }
    }
}
