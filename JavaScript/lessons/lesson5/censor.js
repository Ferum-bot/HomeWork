const censor = function() {
    const swapPattenrs = []

    function formatInput(input) {
        let result = input

        for (pattern of swapPattenrs) {
            const original = pattern.original
            const target = pattern.target
            const regExp = new RegExp(`${original}`)

            result = result.replace(regExp, target)
        }

        return result
    }

    return (firstInput, secondInput) => {

        if (firstInput === undefined) {
            return
        }

        if (secondInput !== undefined) {
            const pattern = {
                original: firstInput,
                target: secondInput
            }
            swapPattenrs.push(pattern)
            return
        }

        return formatInput(firstInput)
    }
}


const currentCensor = censor()

currentCensor('one', 'two')
currentCensor('world!', 'no')

const answer = currentCensor('Hello world! one')

console.log(answer)