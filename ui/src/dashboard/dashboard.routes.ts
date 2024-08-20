import { Routes } from "@angular/router";
import { StudentFormComponent } from "./components/student/student-form/student-form.component";
import { StudentListComponent } from "./components/student/student-list/student-list.component";
import { MainComponent } from "./layout/main/main.component";
import { PermissionFormComponent } from "./auth/permission/permission-form/permission-form.component";
import { PermissionListComponent } from "./auth/permission/permission-list/permission-list.component";
import { RoleFormComponent } from "./auth/role/role-form/role-form.component";
import { RoleListComponent } from "./auth/role/role-list/role-list.component";
import { UserFormComponent } from "./auth/user/user-form/user-form.component";
import { UserListComponent } from "./auth/user/user-list/user-list.component";
import { AttendanceRecordFormComponent } from "./components/attendance/attendance-record-form/attendance-record-form.component";
import { AttendanceRecordListComponent } from "./components/attendance/attendance-record-list/attendance-record-list.component";
import { TeacherFormComponent } from "./components/teacher/teacher-form/teacher-form.component";
import { TeacherListComponent } from "./components/teacher/teacher-list/teacher-list.component";
import { SubjectFormComponent } from "./components/subject/subject-form/subject-form.component";
import { SubjectListComponent } from "./components/subject/subject-list/subject-list.component";
import { DepartmentFormComponent } from "./components/department/department-form/department-form.component";
import { ClassFormComponent } from "./components/class/class-form/class-form.component";
import { ClassListComponent } from "./components/class/class-list/class-list.component";
import { DepartmentListComponent } from "./components/department/department-list/department-list.component";
import { ExamScheduleListComponent } from "./components/exam/exam-schedule-list/exam-schedule-list.component";
import { ExamScheduleFormComponent } from "./components/exam/exam-schedule-form/exam-schedule-form.component";
import { EaxmResultFormComponent } from "./components/result/eaxm-result-form/eaxm-result-form.component";
import { EaxmResultListComponent } from "./components/result/eaxm-result-list/eaxm-result-list.component";
import { FeesListComponent } from "./components/fees/fees-list/fees-list.component";
import { FeesFormComponent } from "./components/fees/fees-form/fees-form.component";
import { HolidayFormComponent } from "./components/notice/holiday/holiday-form/holiday-form.component";
import { HolidayListComponent } from "./components/notice/holiday/holiday-list/holiday-list.component";

export const dashboardRoutes: Routes = [
    {
        path: '', component: MainComponent,
        children: [
            { path: 'permission-form', component: PermissionFormComponent },
            { path: 'permission-list', component: PermissionListComponent },

            { path: 'role-form', component: RoleFormComponent },
            { path: 'role-list', component: RoleListComponent },

            { path: 'user-form', component: UserFormComponent },
            { path: 'user-list', component: UserListComponent },


            { path: 'student-form', component: StudentFormComponent },
            { path: 'student-list', component: StudentListComponent },

            { path: 'attendance-record-form', component: AttendanceRecordFormComponent },
            { path: 'attendance-record-list', component: AttendanceRecordListComponent },

            { path: 'teacher-form', component: TeacherFormComponent },
            { path: 'teacher-list', component: TeacherListComponent },

            { path: 'subject-form', component: SubjectFormComponent },
            { path: 'subject-list', component: SubjectListComponent },


            { path: 'department-form', component: DepartmentFormComponent },
            { path: 'department-list', component: DepartmentListComponent },

            { path: 'class-form', component: ClassFormComponent },
            { path: 'class-list', component: ClassListComponent },  

            {path: 'exam-schedule-list', component: ExamScheduleListComponent}, 
            {path: 'exam-schedule-form', component: ExamScheduleFormComponent},

            {path: 'exam-result-form', component: EaxmResultFormComponent}, 
            {path: 'exam-result-list', component: EaxmResultListComponent},

            {path: 'fees-list', component:FeesListComponent},
            {path: 'fees-form', component:FeesFormComponent},

            {path: 'holiday-form', component: HolidayFormComponent},
            {path: 'holiday-list', component: HolidayListComponent},

          



        ]
    }
];