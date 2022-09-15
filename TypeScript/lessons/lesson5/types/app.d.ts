interface IUser {
    readonly name: string;
    readonly surname: string;
}
declare class User implements IUser {
    readonly name: string;
    readonly surname: string;
}
declare function nullUser(target: Function): void;
declare function nullUserAdvanced<T extends {
    new (...args: any[]): {};
}>(constructor: T): {
    new (...args: any[]): {
        name: string;
        surname: string;
    };
} & T;
//# sourceMappingURL=app.d.ts.map