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

  description;

  newCourseForm = this.fb.group({
    courseTitle: ['', [Validators.required, Validators.minLength(5)]],
    courseShortDescription: ['', [Validators.required, Validators.minLength(20)]]
  });

  constructor(private fb: FormBuilder,
              private router: Router,
              private courseService: CourseService) {
  }

  ngOnInit(): void {
  }

  contentChanged(obj: any): void {
    this.description = obj.html;
  }

  createCourse(): void {
    const title = this.newCourseForm.get('courseTitle').getRawValue();
    const shortDescription = this.newCourseForm.get('courseShortDescription').getRawValue();
    const description = this.description;
    const createCourseRequest = {title, shortDescription, description} as CreateCourseRequest;
    this.courseService.createCourse(createCourseRequest).subscribe((course: CourseResponse) => {
      this.router.navigate(['/courses/' + course.id]).then(r => r);
    });
  }
}
