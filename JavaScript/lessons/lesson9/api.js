const host = 'https://jsonplaceholder.typicode.com'

async function getData(url) {
    const response = await fetch(url)
    return await response.json()
}

async function getUsers() {
    const url = host + '/users'
    return await getData(url)
}

async function getAllTasks() {
    const url = host + '/todos'
    return await getData(url)
}

async function createNewTask(taskData) {
    const url = host = '/todos'
    const response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(taskData),
    })
}

async function changeTaskStatus() {

}

async function deleteTask() {

}