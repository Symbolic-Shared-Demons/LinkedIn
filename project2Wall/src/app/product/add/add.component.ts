import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Lookup } from 'src/app/post/models/lookup';
import { LookupService } from 'src/app/post/services/lookup.service';
import { Post } from 'src/app/post/models/post';
import { PostService } from 'src/app/post/services/post.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  postForm = this.fb.group({});
  categories: Observable<Lookup[]>;

  formSubmitted = false;

  constructor(private fb:FormBuilder,
    private lookupservice:LookupService,
    private postservice:PostService,
    private router: Router

    ) { }

  ngOnInit(): void {

    this.postForm.addControl('id', new FormControl('', ))
    this.postForm.addControl('description', new FormControl('', [Validators.required]))
    this.postForm.addControl('category', new FormControl('', [Validators.required]))
    this.categories = this.lookupservice.getPostCategories();

  }


  save ($event):void{
    this.formSubmitted = true;
    if(this.postForm.valid){

      return;
    }  
    
    this.savePost();
    this.router.navigate(['/posts']);
    
  }

  saveAndContinue($event):void {
    this.formSubmitted = true;
    if(this.postForm.valid){
      return;
    }
    this.savePost;


  }

  private savePost(){

    const post = new Post();
    post.id = this.postForm.get('id').value;
    
    post.description = this.postForm.get('description').value;
    console.log("post.description ->" + post.description  )
    post.category = this.getLookupObjFromDescription(this.postForm.get('category').value);
    this.postservice.addNewPost(post);
    console.log (post);
  }

  getLookupObjFromDescription(description: string):Lookup{
    var lookup:Lookup = null;
    const subscription = this.categories.subscribe(lookups => {
      lookup  = lookups.find(item => item.description == description)
    })
    subscription.unsubscribe();
    return lookup;
  }

}
