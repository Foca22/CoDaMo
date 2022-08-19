import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Observable} from 'rxjs';
import {CourseResponse} from '../model/course-response';

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
}
