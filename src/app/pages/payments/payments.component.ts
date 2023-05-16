import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { BaseUrls } from 'src/app/base-urls';
import { Response } from 'src/app/services/db.service';

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrls: ['./payments.component.css']
})
export class PaymentsComponent implements OnInit {

  payments: any[] = [];

  constructor(
    private http: HttpClient,
    private toast: ToastrService
  ) { }

  ngOnInit(): void {
    this.getPayments();
  }

  getPayments() {
    this.http.get<Response>(BaseUrls.getUrl(BaseUrls.ORDER_GROUPURL))
      .subscribe({
        next: ({ data }) => {
          this.payments = data;
          console.log(data);
          
        },
        error: (error) => {
          console.log(error);
          this.toast.warning("Something went wrong!!", "Failed")
        }
      })
  }
}
