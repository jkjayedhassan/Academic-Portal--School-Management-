import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExamScheduleListComponent } from './exam-schedule-list.component';

describe('ExamScheduleListComponent', () => {
  let component: ExamScheduleListComponent;
  let fixture: ComponentFixture<ExamScheduleListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ExamScheduleListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExamScheduleListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
