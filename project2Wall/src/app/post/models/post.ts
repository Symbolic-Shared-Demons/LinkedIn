import { Lookup } from './lookup';

export interface IPost{
    id:number;
    category:Lookup;
    description:string;
    
}


export class Post implements IPost {
    category: Lookup;
    description: string;
    id:number;

    constructor(id?:number, category?:Lookup, description?:string ){
        this.category = category;
        this.description = description;
        this.id = id;
    }



}
