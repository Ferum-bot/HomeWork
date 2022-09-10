class Cart {

    private currentProducts: Array<Product> = new Array()

    private delivery?: Delivery

    public addProduct(product: Product) {
        this.currentProducts.push(product)
    }

    public removeProductById(productId: bigint) {
        this.currentProducts = this.currentProducts.filter((product) => {
            product.getProductId() !== productId
        })
    }

    public countProductsCost(): BigInt {
        return this.currentProducts.reduce((value, product) => {
            return value.valueOf() + product.getProductCost().valueOf();
        }, BigInt(0))
    }

    public setProductsDelivery(delivery: Delivery) {
        this.delivery = delivery
    }

    public checkout(): boolean {
        return this.currentProducts.length !== 0 
        && this.delivery !== undefined
        && this.delivery !== null
    }
}

class Product {

    private id: BigInt
    private name: string
    private cost: BigInt

    public constructor(
        productId: BigInt,
        productName: string,
        productCost: BigInt
    ) {
        this.id = productId
        this.name = productName
        this.cost = productCost
    }

    public getProductId(): BigInt {
        return this.id
    }

    public getProductName(): string {
        return this.name
    }

    public getProductCost(): BigInt {
        return this.cost
    }
}

interface Delivery {

    incomeDate: Date
}

interface HomeDelivery extends Delivery {

    homeAddress: string
}

interface ShopDelvivery extends Delivery {

    shopId: BigInt
}


function testCase1() {
    const cart = new Cart()
    const product1 = new Product(
        BigInt(1),
        'Продукт 1',
        BigInt(100)
    )
    const product2 = new Product(
        BigInt(2),
        'Продукт 2',
        BigInt(50)
    )
    const delivery = {
        incomeDate: new Date(),
        homeAddress: 'Moscow'
    }

    cart.addProduct(product1)
    cart.addProduct(product2)
    cart.setProductsDelivery(delivery)

    console.log(cart.checkout())
}

function testCase2() {
    const cart = new Cart()
    const product1 = new Product(
        BigInt(1),
        'Продукт 1',
        BigInt(100)
    )
    const product2 = new Product(
        BigInt(2),
        'Продукт 2',
        BigInt(50)
    )

    cart.addProduct(product1)
    cart.addProduct(product2)

    console.log(cart.checkout())
}

function testCase3() {
    const cart = new Cart()
    const delivery = {
        incomeDate: new Date(),
        homeAddress: 'Moscow'
    }

    cart.setProductsDelivery(delivery)

    console.log(cart.checkout())
}


testCase1()
testCase2()
testCase3()