import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-permission-list',
  templateUrl: './permission-list.component.html',
  styleUrl: './permission-list.component.scss'
})
export class PermissionListComponent implements OnInit {
  displayedColumns: string[] = ['name', 'componentKey', 'description', 'category', 'actions'];
  dataSource = new MatTableDataSource<Permission>(ELEMENT_DATA);

  constructor() { }

  ngOnInit(): void {
  }

  addPermission() {
    // Logic to add a new permission
  }

  edit(index: number) {
    // Logic to edit the permission at the given index
  }

  delete(index: number) {
    // Logic to delete the permission at the given index
  }
}

export interface Permission {
  name: string;
  componentKey: string;
  description: string;
  category: string;
}

const ELEMENT_DATA: Permission[] = [
  {name: 'Permission 1', componentKey: 'key1', description: 'Description 1', category: 'Category 1'},
  {name: 'Permission 2', componentKey: 'key2', description: 'Description 2', category: 'Category 2'},
  // Add more data as needed
];