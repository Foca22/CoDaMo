import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseWrapperMenuComponent } from './course-wrapper-menu.component';

describe('CourseWrapperMenuComponent', () => {
  let component: CourseWrapperMenuComponent;
  let fixture: ComponentFixture<CourseWrapperMenuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CourseWrapperMenuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CourseWrapperMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
