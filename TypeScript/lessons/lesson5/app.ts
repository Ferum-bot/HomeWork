interface IUser {
    readonly name: string
    readonly surname: string
}

// @nullUser
@nullUserAdvanced
@setUsers(5)
class User implements IUser {

    @Max( { max: 100 } )
    readonly name: string = ""
    readonly surname: string = ""

    @Catch(true)
    randomName() {
        throw new Error('Error')
    }

    @Catch(true)
    randomNamePlus(@Positive() value: number) {

    }
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

function CreatedAt<T extends { new (...args: any[]): {} }>(constructor: T) {
    return class extends constructor {
        createdAt = new Date()
    }
}

type CreatedAt = {
    createdAt: Date
}

function methodDecorator(
    target: Object,
    propertyKey: string | symbol,
    descriptor: TypedPropertyDescriptor<(...args: any[]) => any>
): TypedPropertyDescriptor<(...args: any[]) => any> | void {
    console.log(target)
    console.log(propertyKey)
    descriptor.enumerable = true
    return descriptor
}

function Catch(rethrow: boolean = false) {
    return (
        target: Object,
        _: string | symbol,
        descriptor: TypedPropertyDescriptor<(...args: any[]) => any>
    ): TypedPropertyDescriptor<(...args: any[]) => any> | void => {
        const oldMethod = descriptor.value
        descriptor.value = async (...args: any[]) => {
            try {
                const result = oldMethod?.apply(target, args)
                return await result
            } catch(error) {
                if (error instanceof Error) {
                    console.log(error.message)
                }
                if (rethrow) {
                    throw error
                }
            }
        }
    }
}

function Max({ max }: { max: number }){
    return (
        target: Object,
        propertyKey: string | symbol
    ) => {
        let value: number

        const setter = function (newValue: number) {
            if (newValue > max) {
                console.log('Превышает максимальное значение')
            } else {
                value = newValue
            }
        }

        const getter = function() {
            return value
        }

        Object.defineProperty(target, propertyKey, {
                set: setter,
                get: getter
            })
    }
}

function Positive() {
    return (
        target: Object,
        propertyKey: string | symbol,
        parameterIndex: number
    ) => {

    }
}