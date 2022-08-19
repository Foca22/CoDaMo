import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseWrapperCommentsComponent } from './course-wrapper-comments.component';

describe('CourseWrapperCommentsComponent', () => {
  let component: CourseWrapperCommentsComponent;
  let fixture: ComponentFixture<CourseWrapperCommentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CourseWrapperCommentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CourseWrapperCommentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
