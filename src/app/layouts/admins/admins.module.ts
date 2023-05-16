import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminsRoutingModule } from './admins-routing.module';
import { ProductsComponent } from '../../pages/products/products.component';
import { OrdersComponent } from '../../pages/orders/orders.component';
import { UsersComponent } from '../../pages/users/users.component';
import { ShipmentsComponent } from '../../pages/shipments/shipments.component';
import { PaymentsComponent } from '../../pages/payments/payments.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CategoriesComponent } from '../../pages/categories/categories.component';


@NgModule({
  declarations: [
    ProductsComponent,
    OrdersComponent,
    UsersComponent,
    ShipmentsComponent,
    PaymentsComponent,
    CategoriesComponent
  ],
  imports: [
    CommonModule,
    AdminsRoutingModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class AdminsModule { }
