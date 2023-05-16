import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminsComponent } from './layouts/admins/admins.component';
import { AuthsComponent } from './layouts/auths/auths.component';
import { ToastrModule } from 'ngx-toastr';
import { AuthService } from './services/auth.service';
import { DbService } from './services/db.service';

@NgModule({
  declarations: [
    AppComponent,
    AdminsComponent,
    AuthsComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    ToastrModule.forRoot({
      timeOut: 3000,
      positionClass: 'toast-top-right',
      preventDuplicates: true,
      countDuplicates: true,
      tapToDismiss: true
    }),
  ],
  providers: [AuthService, DbService],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  bootstrap: [AppComponent]
})
export class AppModule { }
