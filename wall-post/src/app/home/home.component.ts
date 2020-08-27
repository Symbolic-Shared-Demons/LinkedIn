import { Component, OnInit } from '@angular/core';
import { Post } from '../models/post';
import { PostserviceService } from '../_services/postservice.service';
import { SelectMultipleControlValueAccessor } from '@angular/forms';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  posts: Post[];


  constructor(private postService:PostserviceService) { }

  ngOnInit(): void {

    this.posts = [];

    this.postService.getAllPosts().subscribe((p: Post[]) => {
      (this.posts= p)
    });
    console.log(this.posts)
  }

  deletePost(post){
    let index = this.posts.indexOf(post);
    window.localStorage.removeItem(post.title);
    console.log(index);


  }


}
