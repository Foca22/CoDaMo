import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {CommonModule} from '@angular/common';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {CoursesComponent} from './course/courses/courses.component';
import {CourseComponent} from './course/course/course.component';
import {MainComponent} from './main/main.component';
import {QuillModule} from 'ngx-quill';
import {ReactiveFormsModule} from '@angular/forms';
import {HeaderComponent} from './main/header/header.component';
import {AddEditCourseComponent} from './course/add-edit-course/add-edit-course.component';
import {LoginComponent} from './auth/login/login.component';
import {AuthGuard} from './helpers/auth.guard';
import {AuthInterceptor} from './helpers/auth.interceptor';
import { ToastrModule } from 'ngx-toastr';
import { ProfileComponent } from './profile/profile/profile.component';
import { HeaderProfileComponent } from './main/header/header-profile/header-profile.component';
import { HeaderNotificationComponent } from './main/header/header-notification/header-notification.component';
import { HeaderLogoComponent } from './main/header/header-logo/header-logo.component';
import { HeaderMenuComponent } from './main/header/header-menu/header-menu.component';

@NgModule({
  declarations: [
    AppComponent,
    CoursesComponent,
    CourseComponent,
    MainComponent,
    HeaderComponent,
    AddEditCourseComponent,
    LoginComponent,
    ProfileComponent,
    HeaderProfileComponent,
    HeaderNotificationComponent,
    HeaderLogoComponent,
    HeaderMenuComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CommonModule,
    ReactiveFormsModule,
    ToastrModule.forRoot(),
    QuillModule.forRoot({
      modules: {
        syntax: true,              // Include syntax module
        // toolbar: [['code-block']]  // Include button in toolbar
      },
    })
  ],
  providers: [
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
