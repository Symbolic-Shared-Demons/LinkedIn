import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EachCategoryComponent } from './each-category.component';

describe('EachCategoryComponent', () => {
  let component: EachCategoryComponent;
  let fixture: ComponentFixture<EachCategoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EachCategoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EachCategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
