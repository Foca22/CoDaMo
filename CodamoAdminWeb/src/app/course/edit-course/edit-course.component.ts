import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {CourseService} from '../../services/course.service';
import {CourseResponse} from '../../model/course-response';
import {UpdateCourseRequest} from '../../model/update-course-request';

@Component({
  selector: 'app-edit-course',
  templateUrl: './edit-course.component.html',
  styleUrls: ['./edit-course.component.css']
})
export class EditCourseComponent implements OnInit {

  editCourseForm: FormGroup;
  id: string;
  placeholder = 'placeholder';

  livePreview;
  constructor(private fb: FormBuilder,
              private router: Router,
              private route: ActivatedRoute,
              private courseService: CourseService) {
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');

    this.courseService.getCourse(this.id).subscribe(course => {
      this.editCourseForm = this.fb.group({
        courseTitle: [course.title, [Validators.required, Validators.minLength(5)]],
        courseDescription: [course.description, [Validators.required, Validators.minLength(20)]]
      });
    });
  }
  contentChanged(obj: any) {
    console.log(obj.html);
    this.livePreview = obj.html;
  }

  editCourse(): void {
    const title = this.editCourseForm.get('courseTitle').getRawValue();
    const description = this.editCourseForm.get('courseDescription').getRawValue();
    const updateCourseRequest = {title, description, id: this.id} as UpdateCourseRequest;
    this.courseService.updateCourse(updateCourseRequest).subscribe((course: CourseResponse) => {
      this.router.navigate(['/courses/' + course.id]).then(r => r);
    });
  }
}
