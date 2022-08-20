import {CourseState} from './course-state';

export interface CourseResponse {
  id: string;
  title: string;
  shortDescription: string;
  description: string;
  price: number;
  discount: number;
  newPrice: number;
  discountExpiration: string;
  courseState: CourseState;
}
