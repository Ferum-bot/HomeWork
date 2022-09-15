interface IUser {
    readonly name: string
    readonly surname: string
}

// @nullUser
@nullUserAdvanced
@setUsers(5)
class User implements IUser {

    readonly name: string = ""
    readonly surname: string = ""

}

function nullUser(target: Function) {
    target.prototype.name = "decorator name"
    target.prototype.surname = "decorator surname"
}

function nullUserAdvanced<T extends { new (...args: any[]): {} }>(constructor: T) {
    return class extends constructor {
        name = "advanced name"
        surname = "advanced surname"
    }
}

function setUsers(users: number) {
    return (target: Function) => {
        target.prototype.name = users
    }
}