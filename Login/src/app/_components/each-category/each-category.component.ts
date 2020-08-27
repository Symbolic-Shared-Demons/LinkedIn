import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { Category } from 'src/app/_models/Category';
import { CategoryServiceService } from '../../_services/category-service.service';

@Component({
  selector: 'app-each-category',
  templateUrl: './each-category.component.html',
  styleUrls: ['./each-category.component.css']
})
export class EachCategoryComponent implements OnInit {
  
  @Input() category: Category;
  @Output() onToggleEvent: EventEmitter<Category> = new EventEmitter();

  constructor(private CategoryService : CategoryServiceService) { }

  ngOnInit(): void {
    
  }


  onToggle(category){
    category.toggle = !category.toggle;
    console.log(category);
    this.onToggleEvent.emit(category);
  }

}
