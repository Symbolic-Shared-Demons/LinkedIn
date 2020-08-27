import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs'; 

import { Category } from '../_models/Category';
import { CatDB } from '../_models/CatDB';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}

@Injectable({
  providedIn: 'root'
})
export class CategoryServiceService {


  allCategoryUrl:string = 'http://localhost:8080/LinkedIn_backend_war_exploded/api/categories';
  eachCategoryUrl:string = 'url';

  filterCategories: number[];
  allCategories:Category[];
  dbCategories: any[];

  constructor(private http:HttpClient) {
  //   this.filterCategories = [0];
  //   this.http.get<any[]>(this.allCategoryUrl).subscribe( dbCategories => {
  //     this.dbCategories = dbCategories;
  // });

  // console.log(this.dbCategories);
  // for(let category of this.dbCategories){
  //   this.allCategories.push({
  //     id: category.id,
  //     title:category.name,
  //     toggle: false
  //   });
  // }

   }

  getAllCategories(): Observable<CatDB[]> {
    return this.http.get<CatDB[]>(this.allCategoryUrl);

  }



}
