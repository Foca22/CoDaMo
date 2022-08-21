import {Component, OnInit} from '@angular/core';
import {CourseResponse} from '../../model/course/course-response';
import {CourseService} from '../../services/course.service';
import {ActivatedRoute} from '@angular/router';
import * as Quill from 'Quill';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {
  public course: CourseResponse;

  constructor(private courseService: CourseService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {

    const id: string = this.route.snapshot.paramMap.get('id');

    this.courseService.getCourse(id).subscribe(course => {
      this.course = course;
    });
  }

}
