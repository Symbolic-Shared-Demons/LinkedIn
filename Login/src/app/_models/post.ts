import { User } from './user';
import { CatDB } from './CatDB';

export class Post{
    id: number;
    desc: String;
    postCat: CatDB;
}