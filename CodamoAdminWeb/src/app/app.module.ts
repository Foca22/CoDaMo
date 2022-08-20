import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {CommonModule} from '@angular/common';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {CoursesComponent} from './course/courses/courses.component';
import {CourseComponent} from './course/course/course.component';
import {MainComponent} from './main/main.component';
import {QuillModule} from 'ngx-quill';
import {AddCourseComponent} from './course/add-course/add-course.component';
import {ReactiveFormsModule} from '@angular/forms';
import {HeaderComponent} from './main/header/header.component';
import {EditCourseComponent} from './course/edit-course/edit-course.component';

@NgModule({
  declarations: [
    AppComponent,
    CoursesComponent,
    CourseComponent,
    MainComponent,
    AddCourseComponent,
    HeaderComponent,
    EditCourseComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CommonModule,
    ReactiveFormsModule,
    QuillModule.forRoot({
      modules: {
        syntax: true,              // Include syntax module
        // toolbar: [['code-block']]  // Include button in toolbar
      },
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
