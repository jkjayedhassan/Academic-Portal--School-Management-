import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Permission } from '../../permission/permission-list/permission-list.component';
import { PermissionService } from '../../permission/permission.service';

@Component({
  selector: 'app-role-list',
  templateUrl: './role-list.component.html',
  styleUrl: './role-list.component.scss'
})
export class RoleListComponent implements OnInit {

  displayedColumns: string[] = ['name','permissions','actions'];
  dataSource: Permission[] = [];

  constructor(private service: PermissionService, private router: Router) { }

  ngOnInit(): void {}

  addRole() {
    console.log('Add Role clicked');
    // Implement your logic here to add a role
  }

   delete(index: number): void {

  }

  edit(index: number): void {
    // Implement edit logic here
    console.log('Edit role at index:', index);
  }
}