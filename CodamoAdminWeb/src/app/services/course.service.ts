import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Observable} from 'rxjs';
import {CourseResponse} from '../model/course-response';
import {CreateCourseRequest} from '../model/create-course-request';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(private httpClient: HttpClient) {
  }

  public getCourses(): Observable<Array<CourseResponse>> {
    return this.httpClient.get<Array<CourseResponse>>('/admin/course',
      {
        headers: new HttpHeaders({
          Authorization: 'Bearer ' + environment.accessToken
        }),
        withCredentials: true
      });
  }

  public getCourse(id: string): Observable<CourseResponse> {
    return this.httpClient.get<CourseResponse>('/admin/course/' + id,
      {
        headers: new HttpHeaders({
          Authorization: 'Bearer ' + environment.accessToken
        }),
        withCredentials: true
      });
  }

  public createCourse(createCourseRequest: CreateCourseRequest): Observable<CourseResponse> {
    return this.httpClient.post<CourseResponse>('/admin/course/',
      createCourseRequest,
      {
        headers: new HttpHeaders({
          Authorization: 'Bearer ' + environment.accessToken
        }),
        withCredentials: true
      });
  }
}
