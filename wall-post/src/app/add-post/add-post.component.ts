import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent implements OnInit {
  private category: string;
  private job_post: string;

  constructor() {} 
   posts = [];

  ngOnInit(): void {
  }
   addPost(title,content){
     let post = {"title":title.value, "content": content.value}
    this.posts.push(post);
    localStorage.setItem("posts", JSON.stringify(this.posts));
    title.value="";
    content.value="";
    alert("post submitted")
  
   }

  //   if (title === undefined) {
  //     res
  //       .status(400)
  //       .send({ message: 'Please provide your post title', status: false });
  //     return;
  //   }

  //   if (content === undefined) {
  //     res
  //       .status(400)
  //       .send({ message: 'Please provide your post body', status: false });
  //     return;
  //   }

  //   if (title.length <= 5) {
  //     res.status(400).send({
  //       message: 'Post title should be more than 5 characters',
  //       status: false,
  //     });
  //     return;
  //   }

  //   if (content.length <= 6) {
  //     res.status(400).send({
  //       message: 'Post body should be more than 6 characters',
  //       status: false,
  //     });
  //     return;
  //   }

  //   let post = {"title": title.value, "content":content.value};
  //   if(localStorage.getItem("posts")){
  //     this.posts= JSON.parse(localStorage.getItem("posts"));
  //   }
  
  
}
