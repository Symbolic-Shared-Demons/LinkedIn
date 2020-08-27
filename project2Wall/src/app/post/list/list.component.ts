import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { IPost } from '../models/post';
import { PostService } from '../services/post.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  posts:Observable<IPost[]> = null;

  constructor(
    private postservice: PostService
  ) { }

  ngOnInit(): void {

    this.posts = this.postservice.getAllPost();
  }

  deletePost(post):void{
    this.postservice.deletePost(post);
  }

}
