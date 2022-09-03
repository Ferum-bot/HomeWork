const fn1 = async () => {
    setTimeout(() => {
        console.log('Function 1')
    }, 2000)
}

const fn2 = async () => {
    setTimeout(() => {
        console.log('Function 2')
    }, 3000)
}

const fn3 = async () => {
    setTimeout(() => {
        console.log('Function 3')
    }, 4000)
}

Promise.all([fn1(), fn2(), fn3()])
    .then(() => {
        console.log('Result')
    })