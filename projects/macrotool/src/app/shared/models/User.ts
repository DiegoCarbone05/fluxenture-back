export class User {
    id?: string;
    mail: string = '';
    username: string = '';
    password: string = '';

    constructor(init?: Partial<User>) {
        Object.assign(this, init);
    }
}