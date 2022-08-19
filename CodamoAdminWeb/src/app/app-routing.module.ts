import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CoursesComponent} from './course/courses/courses.component';
import {CourseComponent} from './course/course/course.component';
import {MainComponent} from './main/main.component';
import {AddCourseComponent} from './course/add-course/add-course.component';

const routes: Routes = [
  { path: '', component: MainComponent },
  { path: 'courses', component: CoursesComponent },
  { path: 'courses/create', component: AddCourseComponent },
  { path: 'courses/:id', component: CourseComponent }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
