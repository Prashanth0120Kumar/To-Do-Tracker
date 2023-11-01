import { TASK } from "./Task";
export type USER=
{
    userEmail:string;
    userPassword:string;
    userName:String;
    tasks:TASK[];
}