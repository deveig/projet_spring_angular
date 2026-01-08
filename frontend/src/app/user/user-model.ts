import { Token } from "./token";


export interface UserModel {
    id?: number;
    lastName?: string;
    firstName?: string;
    address?: string;
    phoneNumber?: string;
    email?: string;
    username: string;
    password: string;
    token?: Token;
}
