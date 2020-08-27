import { Injectable } from '@angular/core';
import { Post, IPost } from '../models/post';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private posts:Array<Post> = [
      {id: 1, description:'This is the first job post', category:{id:1,category:1, description:'Technology'}},
      {id: 2, description:'This is the second job post', category:{id:2,category:2, description:'Medical'}},
      {id: 3, description:'This is the third job post', category:{id:3,category:3, description:'Full-time'}},
      {id: 4, description:'This is the fourth job post', category:{id:4,category:4, description:'Part-time'}}
  
  ];

  constructor() { }

  getAllPost():Observable<IPost[]>{
    return of(this.posts)
  }

  getPostById(id:number):Observable<IPost>{
    var post = this.posts.find(post => post.id === id);
    return of(post);

  }

  addNewPost(post:IPost):void {

    this.posts.sort(post => post.id)
    console.log (this.posts);
    this.posts.push(post);
  }

  deletePost(post:IPost):IPost[]{
    const index = this.posts.findIndex(post => post.id === post.id);
    const deletedItem  = this.posts.splice(index,1)
    return deletedItem; 

  }

}
