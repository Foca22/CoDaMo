import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-course-wrapper-menu',
  templateUrl: './course-wrapper-menu.component.html',
  styleUrls: ['./course-wrapper-menu.component.css']
})
export class CourseWrapperMenuComponent implements OnInit {

  public chapterState = ChapterState;
  public lessonType = LessonType;
  public chapters: Array<Chapter> = [
    {
      title: 'Introduction',
      state: ChapterState.DONE,
      lessons: []
    },
    {
      title: 'Hello World',
      state: ChapterState.IN_PROGRESS,
      lessons: [
        {
          title: 'Getting Started',
          type: LessonType.DOC
        },
        {
          title: 'Reading Code',
          type: LessonType.DOC
        },
        {
          title: 'Hello World',
          type: LessonType.EXERCISE
        },
        {
          title: 'Comments',
          type: LessonType.DOC
        },
        {
          title: 'Let\'s write some comments',
          type: LessonType.EXERCISE
        },
        {
          title: 'Writing to console',
          type: LessonType.DOC
        },
        {
          title: 'Hello World on two lines',
          type: LessonType.EXERCISE
        },
        {
          title: 'Hello World Quiz',
          type: LessonType.QUIZ
        }
      ]
    },
    {
      title: 'Variables',
      state: ChapterState.LOCKED,
      lessons: [
        {
          title: 'Variables',
          type: LessonType.DOC
        },
        {
          title: 'Hello World with Variables - Part 1',
          type: LessonType.EXERCISE
        },
        {
          title: 'Hello World with Variables - Part 2',
          type: LessonType.EXERCISE
        },
        {
          title: 'String quiz',
          type: LessonType.QUIZ
        }
      ]
    }
  ];

  constructor() { }

  ngOnInit(): void {
  }

}

export interface Chapter {
  title: string;
  lessons: Array<Lesson>;
  state: ChapterState;
}

export interface Lesson {
  title: string;
  type: LessonType;
}

export enum LessonType {
  DOC,
  EXERCISE,
  QUIZ
}

export enum ChapterState {
  DONE,
  IN_PROGRESS,
  LOCKED
}