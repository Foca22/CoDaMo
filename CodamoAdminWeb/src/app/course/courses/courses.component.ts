import { Component, OnInit } from '@angular/core';
import {CourseResponse} from '../../model/course-response';
import {CourseService} from '../../services/course.service';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {
  public courses: Array<CourseResponse> = [];

  public constructor(private courseService: CourseService) {
  }

  ngOnInit(): void {
    this.courseService.getCourses().subscribe(courses => {
      console.log(courses);
      this.courses = courses;
    });
  }

}
