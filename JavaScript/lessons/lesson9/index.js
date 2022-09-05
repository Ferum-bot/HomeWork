document.addEventListener('DOMContentLoaded', onInitApplication)

addTaskButton.addEventListener('click', onCreateTaskButtonClicked)
taskForm.addEventListener('submit', onFormSubmitClicked)


// Callbacks 

function onInitApplication() {
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
        .catch(showErrorNotification)
}

function onFormSubmitClicked(event) {
    event.preventDefault()
}

async function onCreateTaskButtonClicked() {
    const inputText = taskInput.value
    const userId = Number(userSelector.value)

    const createdTask = await createNewTask({
        userId: userId,
        title: inputText,
        completed: false,
    })
    showTask(createdTask)
}

async function onTaskCheckboxChanged(event) {
    const taskId = event.target.parentElement.dataset.id
    const checkboxValue = event.target.checked

    await changeTaskStatus(taskId, checkboxValue)
}

async function onTaskDeleteButtonClicked(event) {
    const taskId = event.target.parentElement.dataset.id

    await deleteTask(taskId)
    removeTask(taskId)
    removeTaskFromDocument(taskId)
}


// UI

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
    statusCheckBox.addEventListener('change', onTaskCheckboxChanged)

    const closeButton = document.createElement('span')
    closeButton.innerHTML = '&times;'
    closeButton.className = 'task-close'
    closeButton.addEventListener('click', onTaskDeleteButtonClicked)
    
    listItem.prepend(statusCheckBox)
    listItem.append(closeButton)

    taskList.prepend(listItem)
}

function removeTaskFromDocument(taskId) {
    const targetListItem = taskList.querySelector(`[data-id="${taskId}"]`)

    targetListItem.querySelector('input').removeEventListener('change', onTaskCheckboxChanged)
    targetListItem.querySelector('span').removeEventListener('click', onTaskDeleteButtonClicked)

    targetListItem.remove()
}

function createUserOption(user) {
    const option = document.createElement('option')
    option.value = user.id
    option.innerText = user.name

    userSelector.append(option)
}

function showErrorNotification(text) {
    window.alert(text)
}

// Data manipulation

function getUserNameById(id) {
    const targetUser = availableUsers.find((user) => user.id === id)
    return targetUser.name
}

function addNewTask(task) {
    currentTasks.prepend(task)
}

function removeTask(taskId) {
    currentTasks = currentTasks.filter((task) => task.id !== taskId)
}