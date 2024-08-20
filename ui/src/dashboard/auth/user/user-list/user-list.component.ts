import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { DashboardService } from '../../../dashboard.service';

// interface User {
//   name: string;
//   username: string;
//   password: string;
//   roles: string;
// }

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {

  displayedColumns: string[] = ['name', 'username', 'password', 'roles', 'actions'];
  users= new MatTableDataSource<any>([]);
  constructor(  private dashboardService: DashboardService) {
  
   
  }

 
  ngOnInit(): void {

    this.dashboardService.findAll('user').subscribe({
      next: (data) => {
        this.users = data.data;
        console.log(data.data);
      },
      error(err) {
        console.log(err);
      },
    });


  }

  edit(index: number): void {
    // Implement edit logic here
    console.log('Edit user at index:', index);
  }
}
