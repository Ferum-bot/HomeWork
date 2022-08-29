const riddle = {
    question: 'Висит груша нельзя скушать',
    correctAnswer: 'лампочка',
    hints: ['это съедобное', 'это фрукт'],
    tries: 3,
    checkAnswer(answer) {
        if (answer === this.correctAnswer) {
            window.alert('You are right')
            console.log('Answer is right')
            return
        }

        this.tries !== 0 ? this.tries-- : this.tries = 0
        if (this.tries === 0) {
            window.alert('Game is over')
            console.log('Game is over')
            return
        }

        window.alert('Wrong answer')
        console.log('Wrong answer')

        this.showHint()
    },
    showHint() {
        const hintIndex = Math.ceil(Math.random() * this.hints.length) - 1
        const hintValue = this.hints[hintIndex]

        document.getElementById('hint').innerText = hintValue
    }
}

window.onload = function() {
    document.getElementById('riddle').innerText = riddle.question;
}

function check() {
    const input = document.getElementsByTagName('input')[0];

    const guessedAnswer = input.value;

    if (guessedAnswer) {
        riddle.checkAnswer(guessedAnswer)
    }
}