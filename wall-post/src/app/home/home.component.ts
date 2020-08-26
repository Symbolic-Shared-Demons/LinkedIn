import { Component, OnInit } from '@angular/core';
import { PostServiceService } from '../post-service.service';
import { Post } from '../models/post';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  posts: Post[];

  newPost:Post = new Post("", "");

  constructor(private postService: PostServiceService) { }

  ngOnInit(): void {
    this.postService.getAllPosts().subscribe((p: Post[]) => (this.posts= p));
    console.log(this.posts)
  }

  deletePost(post){
    let index = this.posts.indexOf(post);
    window.localStorage.removeItem(post.title);
    console.log(index);


  }

}
