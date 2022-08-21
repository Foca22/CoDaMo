import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CoursesComponent} from './course/courses/courses.component';
import {CourseComponent} from './course/course/course.component';
import {MainComponent} from './main/main.component';
import {AddEditCourseComponent} from './course/add-edit-course/add-edit-course.component';
import {LoginComponent} from './auth/login/login.component';
import {AuthGuard} from './helpers/auth.guard';
import {ProfileComponent} from './profile/profile/profile.component';

const routes: Routes = [
  { path: '', component: MainComponent, canActivate : [AuthGuard]  },
  { path: 'login', component: LoginComponent, canActivate : [AuthGuard] },
  { path: 'profile', component: ProfileComponent, canActivate : [AuthGuard] },
  { path: 'courses', component: CoursesComponent, canActivate : [AuthGuard] },
  { path: 'courses/create', component: AddEditCourseComponent, canActivate : [AuthGuard] },
  { path: 'courses/:id', component: CourseComponent, canActivate : [AuthGuard] },
  { path: 'courses/:id/edit', component: AddEditCourseComponent, canActivate : [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
