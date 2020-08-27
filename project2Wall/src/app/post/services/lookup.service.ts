import { Injectable } from '@angular/core';
import { Lookup } from '../models/lookup';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LookupService {

  private postCategories:Array<Lookup> = [

    {id: 1, category: 1, description: "Technology"},
    {id: 2, category: 1, description: "Part-time"},
    {id: 3, category: 1, description: "Full-Time"},
    {id: 4, category: 1, description: "Medical"},

  ]

  getPostCategories():Observable<Lookup[]>{
    return of(this.postCategories) 

  }


  constructor() { }
}
