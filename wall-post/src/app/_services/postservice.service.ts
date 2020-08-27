import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post } from '../models/post';

@Injectable({
  providedIn: 'root'
})
export class PostserviceService {

  

  constructor(private http: HttpClient) { 
  }

  getAllPosts(): Observable<Post[]>{
    return this.http.get<Post[]>("http://localhost:8080/LinkedIn_backend_war_exploded/api/posts");
  }

  addPost(p:Post): Observable<User>{
    u:User = localStorage.getItem('user');
    return this.http.get<User>("http://localhost:8080/LinkedIn_backend_war_exploded/api/users/addPost/" + u.id,p);
  }

  deletePost(p:Post): Observable<any>{
    return this.http.get<any>("http://localhost:8080/LinkedIn_backend_war_exploded/api/posts/delete/"+p.id);
  }

  applyPost(p:Post): Observable<User>{
    u:User = localStorage.getItem('user');
    return this.http.get<User>("http://localhost:8080/LinkedIn_backend_war_exploded/api/users/"+u.username+"/apply/"+p.id);
  }
}
