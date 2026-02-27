export class UserDto {
    id?: string;
    email?: string;
    username?: string;

    constructor(init?: Partial<UserDto>) {
        Object.assign(this, init);
    }
}