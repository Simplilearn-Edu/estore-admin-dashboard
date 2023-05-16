import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

declare interface RouteInfo {
  path: string;
  title: string;
  icon: string;
  class: string;
}

export const ROUTES: RouteInfo[] = [
  { path: '/users', title: 'Users', icon: '', class: '' },
  { path: '/products', title: 'Products', icon: '', class: '' },
  { path: '/orders', title: 'Orders', icon: '', class: '' },
  { path: '/shipments', title: 'Shipments', icon: '', class: '' },
  { path: '/payments', title: 'Payments', icon: '', class: '' },
];

@Component({
  selector: 'app-admins',
  templateUrl: './admins.component.html',
  styleUrls: ['./admins.component.css']
})
export class AdminsComponent implements OnInit {

  public listTitles: any[] = [];
  public isCollapsed: boolean = true;

  constructor(
    public authService: AuthService
  ) { }

  ngOnInit(): void {
    this.listTitles = ROUTES.filter((listTitle) => listTitle);
  }

}
