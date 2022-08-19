import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseWrapperFooterComponent } from './course-wrapper-footer.component';

describe('CourseWrapperFooterComponent', () => {
  let component: CourseWrapperFooterComponent;
  let fixture: ComponentFixture<CourseWrapperFooterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CourseWrapperFooterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CourseWrapperFooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
