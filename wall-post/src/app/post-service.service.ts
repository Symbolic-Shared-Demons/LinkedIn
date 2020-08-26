import { Injectable } from '@angular/core';
import { Post } from './models/post';
import { HttpClient } from '@angular/common/http'
import { Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostServiceService {
  private posts: Post[];


  constructor(private http: HttpClient){
    console.log('creating posts services');
  }

  getPostByTitle(title: string): Observable<Post[]>{

    return this.http.get<Post[]>('http://localhost/3000/posts?title=$(title)');
  }

  getAllPosts(): Observable<Post[]> {
    return this.http.get<Post[]>('http://localhost:3000/posts');

  }
}
