import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseWrapperHeaderComponent } from './course-wrapper-header.component';

describe('CourseWrapperHeaderComponent', () => {
  let component: CourseWrapperHeaderComponent;
  let fixture: ComponentFixture<CourseWrapperHeaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CourseWrapperHeaderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CourseWrapperHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
