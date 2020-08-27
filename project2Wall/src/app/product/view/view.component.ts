import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { IPost } from 'src/app/post/models/post';
import { PostService } from 'src/app/post/services/post.service';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import {  Router, ParamMap } from '@angular/router';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements OnInit {
  post$:Observable<IPost>;

  constructor(
    private postService:PostService,
    private route:ActivatedRoute,
    private router: Router,


  ) { }

  ngOnInit(): void {
    this.post$ = this.route.paramMap.pipe(
      switchMap((params: ParamMap) =>
          this.postService.getPostById(Number.parseInt(params.get('id')))
        ));
  }

  editPost(product:IPost):void{
      
    this.post$.subscribe(post =>{
      console.log('edit clicked');
      this.router.navigate(['posts/edit/'+post.id]);
    });
  }

  viewPost(){
    
  }

}
