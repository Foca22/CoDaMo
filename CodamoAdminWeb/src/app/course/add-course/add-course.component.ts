import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, Validators} from '@angular/forms';
import {CourseService} from '../../services/course.service';
import {CreateCourseRequest} from '../../model/create-course-request';
import {CourseResponse} from '../../model/course-response';
import {Router} from '@angular/router';

@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.css']
})
export class AddCourseComponent implements OnInit {

  newCourseForm = this.fb.group({
    courseTitle: ['', [Validators.required, Validators.minLength(5)]],
    courseDescription: ['', [Validators.required, Validators.minLength(20)]]
  });

  constructor(private fb: FormBuilder,
              private router: Router,
              private courseService: CourseService) {
  }

  ngOnInit(): void {
  }

  createCourse(): void {
    const title = this.newCourseForm.get('courseTitle').getRawValue();
    const description = this.newCourseForm.get('courseDescription').getRawValue();
    const createCourseRequest = {title, description} as CreateCourseRequest;
    this.courseService.createCourse(createCourseRequest).subscribe((course: CourseResponse) => {
      this.router.navigate(['/courses/' + course.id]).then(r => r);
    });
  }
}
