function generateRandomNumber() {
    return Math.ceil(Math.random() * 9)
}

let currentSecretNumber = -1
let currentTries = 0

function startGame() {
    currentSecretNumber = generateRandomNumber()
    currentTries = 0

    console.log(`Generated secret number is: ${currentSecretNumber}`)
}

function numberIsSecret(number) {
    currentTries++
    console.log(`Current tries: ${currentTries}`)

    if (number === currentSecretNumber) {
        window.alert('Nice try! You are right!')
    } else {
        window.alert('Not exactly')
    }
}

