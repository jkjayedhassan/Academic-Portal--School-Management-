import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTreeModule } from '@angular/material/tree';
import { RouterModule } from '@angular/router';
import { PermissionFormComponent } from './auth/permission/permission-form/permission-form.component';
import { PermissionListComponent } from './auth/permission/permission-list/permission-list.component';
import { RoleFormComponent } from './auth/role/role-form/role-form.component';
import { RoleListComponent } from './auth/role/role-list/role-list.component';
import { UserFormComponent } from './auth/user/user-form/user-form.component';
import { UserListComponent } from './auth/user/user-list/user-list.component';
import { AttendanceRecordFormComponent } from './components/attendance/attendance-record-form/attendance-record-form.component';
import { AttendanceRecordListComponent } from './components/attendance/attendance-record-list/attendance-record-list.component';

import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { ClassFormComponent } from './components/class/class-form/class-form.component';
import { ClassListComponent } from './components/class/class-list/class-list.component';
import { DepartmentFormComponent } from './components/department/department-form/department-form.component';
import { DepartmentListComponent } from './components/department/department-list/department-list.component';
import { ExamScheduleFormComponent } from './components/exam/exam-schedule-form/exam-schedule-form.component';
import { ExamScheduleListComponent } from './components/exam/exam-schedule-list/exam-schedule-list.component';
import { EaxmResultFormComponent } from './components/result/eaxm-result-form/eaxm-result-form.component';
import { EaxmResultListComponent } from './components/result/eaxm-result-list/eaxm-result-list.component';
import { StudentFormComponent } from './components/student/student-form/student-form.component';
import { StudentListComponent } from './components/student/student-list/student-list.component';
import { StudentService } from './components/student/student.service';
import { SubjectFormComponent } from './components/subject/subject-form/subject-form.component';
import { SubjectListComponent } from './components/subject/subject-list/subject-list.component';
import { TeacherFormComponent } from './components/teacher/teacher-form/teacher-form.component';
import { TeacherListComponent } from './components/teacher/teacher-list/teacher-list.component';
import { dashboardRoutes } from './dashboard.routes';
import { DashboardService } from './dashboard.service';
import { HeaderComponent } from './layout/header/header.component';
import { MainComponent } from './layout/main/main.component';
import { SidenavComponent } from './layout/sidenav/sidenav.component';
import { FeesFormComponent } from './components/fees/fees-form/fees-form.component';
import { FeesListComponent } from './components/fees/fees-list/fees-list.component';
import { HolidayFormComponent } from './components/notice/holiday/holiday-form/holiday-form.component';
import { HolidayListComponent } from './components/notice/holiday/holiday-list/holiday-list.component';

@NgModule({
  declarations: [
    HeaderComponent,
    SidenavComponent,   
    MainComponent,
    StudentFormComponent,
    StudentListComponent,
    PermissionFormComponent,
    PermissionListComponent,
    RoleFormComponent,
    RoleListComponent,
    UserFormComponent,
    UserListComponent,
    AttendanceRecordFormComponent,
    AttendanceRecordListComponent,
    TeacherFormComponent,
    TeacherListComponent,
    SubjectFormComponent,
    SubjectListComponent,
    DepartmentFormComponent,
    DepartmentListComponent,
    ClassFormComponent,
    ClassListComponent,
    ExamScheduleFormComponent,
    ExamScheduleListComponent,
    EaxmResultFormComponent,
    EaxmResultListComponent,
    FeesFormComponent,
    FeesListComponent,
    HolidayFormComponent,
    HolidayListComponent
   

    
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    MatTreeModule,
    MatSidenavModule,
    MatToolbarModule,
    MatMenuModule,
    MatIconModule,
    MatDividerModule,
    MatListModule,
    RouterModule.forChild(dashboardRoutes),
    ReactiveFormsModule,
    MatSelectModule,
    MatFormFieldModule,
    MatButtonModule,
    MatExpansionModule,
    MatTableModule,
    MatIconModule,
    MatPaginator,
    MatSortModule,
    MatPaginatorModule,
  ],
  providers: [provideHttpClient(withInterceptorsFromDi()), DashboardService, StudentService, ]

})
export class DashboardModule { }
