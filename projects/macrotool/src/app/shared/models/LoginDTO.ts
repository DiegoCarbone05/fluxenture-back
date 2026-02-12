export class LoginDTO {
    mail: string = '';
    password: string = '';

    constructor(init?: Partial<LoginDTO>) {
        Object.assign(this, init);
    }
}