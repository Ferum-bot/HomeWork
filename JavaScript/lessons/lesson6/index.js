const products = [
    {
        id: 1,
        title: 'Lenovo Yoga',
        price: 3000,
    },
    {
        id: 2,
        title: 'Acer Aspire',
        price: 1800,
    },
    {
        id: 3,
        title: 'Dell Vostro',
        price: 3400
    },
];

let order = [];

const productIsInBasket= (productId) => {
    return order.find((el) => el.id === productId) !== undefined
}

function addToBasket(productId) {
    if (productIsInBasket(productId)) {
        window.alert(`Product is already in basket!`)
        return
    }


    const targetProduct = products.find((el) => el.id == productId)
    if (targetProduct === undefined) {
        return
    }
    order.push({...targetProduct})

    renderCart();
    rerenderTotalPrice();
}

function removeFromBasket(productId) {
    if (productIsInBasket(productId)) {
        order = order.filter((product) => product.id !== productId)
    }

    renderCart();
    rerenderTotalPrice();
}


function rerenderTotalPrice() {
    const totalPrice = order.reduce((acc, el) => {
        return acc + el.price
    }, 0) 

    document.getElementById('total').innerText = totalPrice;
}

function renderCart() {
    const cart = document.getElementById('basket-items');

    cart.innerHTML = '';
    order.forEach(item => {
        const el = document.createElement('li');
        el.innerText = item.title;
        el.onclick = () => removeFromBasket(item.id);
        cart.appendChild(el);
    })
}