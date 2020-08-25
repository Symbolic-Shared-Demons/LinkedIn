import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  posts=[];
  // newPost:Post = new Post('', false, '');

  constructor() { }

  ngOnInit(): void {
    this.posts=JSON.parse(localStorage.getItem("posts"));
    console.log(this.posts)
  }

  deletePost(post){
    let index = this.posts.indexOf(post);
    window.localStorage.removeItem(post.title);
    console.log(index);


  }

}
