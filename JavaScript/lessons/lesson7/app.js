const spoilerBlock = document.querySelector('#spoiler')
const toggleButton = document.querySelector('#toggle')

toggleButton.addEventListener('click', onToggleButtonClicked)

function onToggleButtonClicked(event) {
    const classes = spoilerBlock.classList

    if (classes.contains('closed')) {
        showBlock()
    } else {
        hideBlock()
    }
}

function onKeyPressed(event) {
    if (event.key === 'Escape') {
        hideBlock()
        event.preventDefault()
    }
}

function showBlock() {
    const classes = spoilerBlock.classList

    classes.remove('closed')
    document.addEventListener('keydown', onKeyPressed)
}

function hideBlock() {

    const classes = spoilerBlock.classList

    classes.add('closed')
    document.removeEventListener('keydown', onKeyPressed)
}

