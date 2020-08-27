import { Component, OnInit, Input } from '@angular/core';
import { Post } from '../models/post';
import { PostserviceService } from '../_services/postservice.service';

@Component({
  selector: 'app-each-post',
  templateUrl: './each-post.component.html',
  styleUrls: ['./each-post.component.css']
})
export class EachPostComponent implements OnInit {

  @Input() post:Post;

  constructor(private postService: PostserviceService) { }

  ngOnInit(): void {
  }

  onApply(p:Post){
    this.postService.applyPost(this.post).subscribe(u =>{
      localStorage.setItem('user',u);
      
    }
      );
  }

}
