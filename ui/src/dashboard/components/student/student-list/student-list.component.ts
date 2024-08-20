import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { PageRequest } from '../../../../model/page-request.model';
import { defaultPage, Page } from '../../../../model/page.model';
import { Response } from '../../../../model/Response.model';
import { DashboardService } from '../../../dashboard.service';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrl: './student-list.component.scss'
})
export class StudentListComponent  {
  @ViewChild(MatSort) sort!: MatSort;
  
  displayColumns: string[] = ['firstName', 'lastName', 'roll', 'stClass', 'department',  'session'];
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

    this.dashboardService.findInPage('student', params).subscribe((data: Response<Page<any>>) => {
      console.log(data)
      this.dataSource.data = data.data.content;
      this.page = data.data;
    });
  }

  ngOnInit(): void {

    this.fetchPage(0, 10);
  }



    onEdit(studentId: number) {
      console.log('Edit:', studentId);
    }
    onDelete(studentId: number) {
      console.log('Delete:', studentId);
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
