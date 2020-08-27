import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { CategoryServiceService } from '../../_services/category-service.service';

import {Category} from '../../_models/Category';
import {CatDB} from '../../_models/CatDB';

@Component({
  selector: 'app-category-side',
  templateUrl: './category-side.component.html',
  styleUrls: ['./category-side.component.css']
})
export class CategorySideComponent implements OnInit {

  @Output() onToggle: EventEmitter<Category> = new EventEmitter();
  

  allCategories:Category[];
  allCatDBs:CatDB[];

  constructor(private categoryService: CategoryServiceService) { 
    
  }

  ngOnInit(){

    this.allCategories = [];

    this.allCatDBs = [];

    this.categoryService.getAllCategories().subscribe(c => {
      this.allCatDBs = c;

      console.log(this.allCatDBs);
      this.setCategories(c);

    });

    

    
  }

  onToggleEvent(category: Category){
    console.log(category);
    this.onToggle.emit(category);
  }

  setCategories(cat:CatDB[]){
    for(let c of cat ){
      this.allCategories.push({
        id:c.id,
        title:c.title,
        toggle:false
      })
  }
  }

}
