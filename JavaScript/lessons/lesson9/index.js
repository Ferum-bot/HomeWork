document.addEventListener('DOMContentLoaded', initApplication)

function initApplication() {
    Promise.all([getAllTasks(), getUsers()])
        .then((results) => {
            [currentTasks, availableUsers] = results

            currentTasks.forEach((task) => {
                showTask(task)
            })
            availableUsers.forEach((user) => {
                createUserOption(user)
            })
        })
}

function showTask(task) {
    const { id, userId, title, completed } = task

    const listItem = document.createElement('li')
    const userName = getUserNameById(userId)
    listItem.className = 'todo-item'
    listItem.dataset.id = id
    listItem.innerHTML = `<span>${title} <i>by</i> <b>${userName}</b></span>`

    const statusCheckBox = document.createElement('input')
    statusCheckBox.type = 'checkbox'
    statusCheckBox.checked = completed

    const closeButton = document.createElement('span')
    closeButton.innerHTML = '&times;'
    closeButton.className = 'task-close'
    
    listItem.prepend(statusCheckBox)
    listItem.append(closeButton)

    taskList.prepend(listItem)
}

function createUserOption(user) {
    const option = document.createElement('option')
    option.value = user.id
    option.innerText = user.name

    userSelector.append(option)
}

function getUserNameById(id) {
    const targetUser = availableUsers.find((user) => user.id === id)
    return targetUser.name
}