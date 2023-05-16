import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthsRoutingModule } from './auths-routing.module';
import { LoginComponent } from '../../pages/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    CommonModule,
    AuthsRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule
  ]
})
export class AuthsModule { }
