

function getFullName(user: { firstName: string, surName: string}): string {
    return `${user.firstName} ${user.surName}`
}

const user = {
    firstName: 'First name',
    surName: 'Sur name',
    city: 'Moscow',
    age: 20
}

getFullName(user)

const skills: readonly string[] = ['dev', 'devops']