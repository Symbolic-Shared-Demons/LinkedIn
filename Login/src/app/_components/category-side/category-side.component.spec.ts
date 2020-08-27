import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CategorySideComponent } from './category-side.component';

describe('CategorySideComponent', () => {
  let component: CategorySideComponent;
  let fixture: ComponentFixture<CategorySideComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CategorySideComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CategorySideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
