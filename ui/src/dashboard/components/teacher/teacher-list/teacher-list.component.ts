import { Component, ViewChild } from '@angular/core';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { defaultPage, Page } from '../../../../model/page.model';
import { DashboardService } from '../../../dashboard.service';
import { PageRequest } from '../../../../model/page-request.model';
import { Response } from '../../../../model/Response.model';

@Component({
  selector: 'app-teacher-list',
  templateUrl: './teacher-list.component.html',
  styleUrl: './teacher-list.component.scss'
})
export class TeacherListComponent {
  // teacherList: any[] = [];
  @ViewChild(MatSort) sort!: MatSort;

  displayColumns: string[] = ['fullName', 'contactNumber', 'department', 'employeeId', 'academicSession', 'action'];
  dataSource = new MatTableDataSource<any>([]);
  page: Page<any> = defaultPage;
  filterText?: string;
  timer: any = null;

  constructor(private dashboardService: DashboardService,) { }

  fetchPage(pageNumber: number, PageSize: number) { 
    const params = new PageRequest(pageNumber, PageSize);
    params.sortColumn = this.sort?.active;

    params.search = this.filterText ;
   
    params.order = this.sort?.direction ;

    this.dashboardService.findInPage('teacher', params).subscribe((
      data: Response<Page<any>>) => {
      console.log(data)
      this.dataSource.data = data.data.content;
      this.page = data.data;
    });
  }

  ngOnInit(): void {

    this.fetchPage(0, 10);
  }



    onEdit(teacherId: number) {
      console.log('Edit:', teacherId);
    }
    onDelete(teacherId: number) {
      console.log('Delete:', teacherId);
    }

    pageChanged(event: any) {
      this.fetchPage(event.pageIndex, event.pageSize);
    }
  
   

    onSortChange(sortState: Sort) {
      this.fetchPage(0, this.page.size);
    
    }

    onSearch() {
      if (this.timer) {
        clearTimeout(this.timer);
      }
      this.timer = setTimeout(() => {
        this.fetchPage(0, 10);
      }, 500);
    }
}
