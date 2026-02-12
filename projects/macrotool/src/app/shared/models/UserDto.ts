export class UserDto {
    id?: string;
    email?: string;
    name?: string;

    constructor(init?: Partial<UserDto>) {
        Object.assign(this, init);
    }
}