import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DashboardService } from '../../../dashboard.service';

@Component({
  selector: 'app-attendance-record-form',
  templateUrl: './attendance-record-form.component.html',
  styleUrls: ['./attendance-record-form.component.scss']
})
export class AttendanceRecordFormComponent implements OnInit {

  classes: any[] = [];
  departments: any[] = [];
  students: any[] = [];
  stClass: number = 0;
  selectedDepartment: number = 0;
  studentAttendances: boolean[] = [];
  modelName = 'attendance';

  constructor(private fb: FormBuilder, private dashboardService: DashboardService) {

  }

  ngOnInit() {
    // Fetch classes from the backend
    this.dashboardService.findAll('studyclass').subscribe(data => {
      this.classes = data.data;

    });
  }



  onSubmit() {
    const attendanceData = this.studentAttendances.map((attendance, index) => {
      return this.studentAttendances[index] ? {
        studentId: this.students[index].id,
        isPresent: attendance,
        classId: this.stClass,
        departmentId: this.selectedDepartment
      } : null;
      console.log
    }).filter((attendance) => attendance);
    console.log(this.studentAttendances);

    if (attendanceData.length) {
      this.dashboardService.saveClassAttendance(attendanceData, this.modelName).subscribe(response => {
        console.log('Attendance saved successfully', response);
      });
    }
  }

  onClassChange(event: any) {
    const classId = event.target.value;
    if (classId) {
      const currentClass = this.classes.find((c: any) => c.id == classId);
      this.departments = currentClass.departments || [];
    }
  }

  fetchStudents() {
    if (this.stClass && this.selectedDepartment) {
      this.dashboardService.getStudentsByClassAndDepartment(this.stClass, this.selectedDepartment).subscribe(data => {
        this.students = data.data;
      });
    }
  }

  toggleAttendance(idx: number) {
    this.studentAttendances[idx] = !this.studentAttendances[idx];
  }


}
