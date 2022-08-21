import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CourseResponse} from '../model/course/course-response';
import {CreateCourseRequest} from '../model/course/create-course-request';
import {UpdateCourseRequest} from '../model/course/update-course-request';
import {AuthenticationService} from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(private httpClient: HttpClient) {
  }

  public getCourses(): Observable<Array<CourseResponse>> {
    return this.httpClient.get<Array<CourseResponse>>('/admin/course');
  }

  public getCourse(id: string): Observable<CourseResponse> {
    return this.httpClient.get<CourseResponse>('/admin/course/' + id);
  }

  public createCourse(createCourseRequest: CreateCourseRequest): Observable<CourseResponse> {
    return this.httpClient.post<CourseResponse>('/admin/course/', createCourseRequest);
  }

  public updateCourse(updateCourseRequest: UpdateCourseRequest): Observable<CourseResponse> {
    return this.httpClient.put<CourseResponse>('/admin/course/', updateCourseRequest);
  }
}
