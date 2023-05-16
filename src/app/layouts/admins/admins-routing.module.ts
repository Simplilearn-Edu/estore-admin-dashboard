import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from 'src/app/guards/auth.guard';
import { CategoriesComponent } from 'src/app/pages/categories/categories.component';
import { OrdersComponent } from 'src/app/pages/orders/orders.component';
import { PaymentsComponent } from 'src/app/pages/payments/payments.component';
import { ProductsComponent } from 'src/app/pages/products/products.component';
import { ShipmentsComponent } from 'src/app/pages/shipments/shipments.component';
import { UsersComponent } from 'src/app/pages/users/users.component';

const routes: Routes = [
  { path: 'users', component: UsersComponent,canActivate: [AuthGuard] },
  { path: 'products/categories', component: CategoriesComponent,canActivate: [AuthGuard] },
  { path: 'products', component: ProductsComponent,canActivate: [AuthGuard] },
  { path: 'orders', component: OrdersComponent,canActivate: [AuthGuard] },
  { path: 'shipments', component: ShipmentsComponent,canActivate: [AuthGuard] },
  { path: 'payments', component: PaymentsComponent,canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminsRoutingModule { }
