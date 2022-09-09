

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
const newSkills: Array<string> = []

enum QuastionStatus {
    PUBLISHED = 'published',
    DRAFT = 'draft',
    DELETED = 'deleted'
}

async function getFaqs(req: { 
    topicId: number,
    status: QuastionStatus
 }): Promise<{
    quastion: string,
    answer: string,
    tags: string[],
    likes: number,
    statis: QuastionStatus
 }[]> {
    const res = await fetch('/faqs', {
        method: 'POST',
        body: JSON.stringify(req)
    })
    const data = await res.json()
    return data
 }