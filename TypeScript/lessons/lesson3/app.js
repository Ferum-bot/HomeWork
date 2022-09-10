"use strict";
class Cart {
    constructor() {
        this.currentProducts = new Array();
    }
    addProduct(product) {
        this.currentProducts.push(product);
    }
    removeProductById(productId) {
        this.currentProducts = this.currentProducts.filter((product) => {
            product.getProductId() !== productId;
        });
    }
    countProductsCost() {
        return this.currentProducts.reduce((value, product) => {
            return value.valueOf() + product.getProductCost().valueOf();
        }, BigInt(0));
    }
    setProductsDelivery(delivery) {
        this.delivery = delivery;
    }
    checkout() {
        return this.currentProducts.length !== 0
            && this.delivery !== undefined
            && this.delivery !== null;
    }
}
class Product {
    constructor(productId, productName, productCost) {
        this.id = productId;
        this.name = productName;
        this.cost = productCost;
    }
    getProductId() {
        return this.id;
    }
    getProductName() {
        return this.name;
    }
    getProductCost() {
        return this.cost;
    }
}
function testCase1() {
    const cart = new Cart();
    const product1 = new Product(BigInt(1), 'Продукт 1', BigInt(100));
    const product2 = new Product(BigInt(2), 'Продукт 2', BigInt(50));
    const delivery = {
        incomeDate: new Date(),
        homeAddress: 'Moscow'
    };
    cart.addProduct(product1);
    cart.addProduct(product2);
    cart.setProductsDelivery(delivery);
    console.log(cart.checkout());
}
function testCase2() {
    const cart = new Cart();
    const product1 = new Product(BigInt(1), 'Продукт 1', BigInt(100));
    const product2 = new Product(BigInt(2), 'Продукт 2', BigInt(50));
    cart.addProduct(product1);
    cart.addProduct(product2);
    console.log(cart.checkout());
}
function testCase3() {
    const cart = new Cart();
    const delivery = {
        incomeDate: new Date(),
        homeAddress: 'Moscow'
    };
    cart.setProductsDelivery(delivery);
    console.log(cart.checkout());
}
testCase1();
testCase2();
testCase3();
