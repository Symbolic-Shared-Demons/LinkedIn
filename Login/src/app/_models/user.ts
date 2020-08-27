import { Post } from './post';
import { CatDB } from './CatDB';

export class User {
    id: string;
    username: string;
    password: string;
    firstName: string;
    lastName: string;
    token: string;
    userCats: CatDB[];
    appliedPosts: Post[];
    userPosts: Post[];
}