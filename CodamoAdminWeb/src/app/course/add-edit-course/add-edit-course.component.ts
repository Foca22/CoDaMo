import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {CourseService} from '../../services/course.service';
import {CreateCourseRequest} from '../../model/create-course-request';
import {CourseResponse} from '../../model/course-response';
import {UpdateCourseRequest} from '../../model/update-course-request';

@Component({
  selector: 'app-add-edit-course',
  templateUrl: './add-edit-course.component.html',
  styleUrls: ['./add-edit-course.component.css']
})
export class AddEditCourseComponent implements OnInit {

  isUpdate = false;
  id: string;

  courseForm = this.fb.group({
    courseTitle: ['', [Validators.required, Validators.minLength(5)]],
    courseShortDescription: ['', [Validators.required, Validators.minLength(20)]],
    courseDescription: ['', [Validators.required, Validators.minLength(20)]]
  });

  constructor(private fb: FormBuilder,
              private router: Router,
              private route: ActivatedRoute,
              private courseService: CourseService) {
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');

    if (this.id) {
      this.isUpdate = true;
      this.courseService.getCourse(this.id).subscribe(course => {
        this.courseForm = this.fb.group({
          courseTitle: [course.title, [Validators.required, Validators.minLength(5)]],
          courseDescription: [course.description, [Validators.required, Validators.minLength(20)]],
          courseShortDescription: [course.shortDescription, [Validators.required, Validators.minLength(20)]]
        });
      });
    }
  }

  createOrUpdateCourse(): void {
    const title = this.courseForm.get('courseTitle').getRawValue();
    const shortDescription = this.courseForm.get('courseShortDescription').getRawValue();
    const description = this.courseForm.get('courseDescription').getRawValue();

    const response = this.isUpdate ?
      this.courseService.updateCourse({id: this.id, title, shortDescription, description} as UpdateCourseRequest) :
      this.courseService.createCourse({title, shortDescription, description} as CreateCourseRequest);

    response.subscribe((course: CourseResponse) => {
      this.router.navigate(['/courses/' + course.id]).then(r => r);
    });
  }

  getDraftTitle(): string {
    return this.courseForm.get('courseTitle').getRawValue() ? this.courseForm.get('courseTitle').getRawValue() : '';
  }

  getDraftShortDescription(): string {
    return this.courseForm.get('courseShortDescription').getRawValue() ? this.courseForm.get('courseShortDescription').getRawValue() : '';
  }

  getDraftDescription(): string {
    return this.courseForm.get('courseDescription').getRawValue() ? this.courseForm.get('courseDescription').getRawValue() : '';
  }
}
