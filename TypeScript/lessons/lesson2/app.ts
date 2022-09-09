function signIn(id: string | number) {
    console.log(id)

    if (typeof id === 'string') {
        console.log(id.toLocaleLowerCase())
    }
}

signIn(1)
signIn('Hello world')

type HttpMethod = 'post' | 'get'

function signWithAuth(url: string, method: HttpMethod): 1 | -1 {
    
    return -1
}