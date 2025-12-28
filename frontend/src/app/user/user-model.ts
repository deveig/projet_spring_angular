import { Token } from "./token";


export interface UserModel {
    id?: number;
    lastname?: string;
    firstname?: string;
    address?: string;
    phoneNumber?: string;
    email?: string;
    username: string;
    password: string;
    token?: Token;
}
