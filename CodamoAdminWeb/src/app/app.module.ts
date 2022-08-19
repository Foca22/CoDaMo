import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ChaptersComponent } from './course/chapters/chapters.component';
import { LessonsComponent } from './course/lessons/lessons.component';
import { LessonComponent } from './course/lesson/lesson.component';
import { ExerciseComponent } from './course/exercise/exercise.component';
import { TestComponent } from './course/test/test.component';
import { QuizComponent } from './course/quiz/quiz.component';
import { CourseComponent } from './course/course.component';
import { CourseWrapperComponent } from './course/course-wrapper/course-wrapper.component';
import { CourseWrapperHeaderComponent } from './course/course-wrapper/course-wrapper-header/course-wrapper-header.component';
import { CourseWrapperFooterComponent } from './course/course-wrapper/course-wrapper-footer/course-wrapper-footer.component';
import { CourseWrapperMenuComponent } from './course/course-wrapper/course-wrapper-menu/course-wrapper-menu.component';
import { CourseWrapperCommentsComponent } from './course/course-wrapper/course-wrapper-comments/course-wrapper-comments.component';

@NgModule({
  declarations: [
    AppComponent,
    ChaptersComponent,
    LessonsComponent,
    LessonComponent,
    ExerciseComponent,
    TestComponent,
    QuizComponent,
    CourseComponent,
    CourseWrapperComponent,
    CourseWrapperHeaderComponent,
    CourseWrapperFooterComponent,
    CourseWrapperMenuComponent,
    CourseWrapperCommentsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
