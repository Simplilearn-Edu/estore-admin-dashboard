import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { BaseUrls } from 'src/app/base-urls';
import { Orders } from 'src/app/models/orders';
import { Response } from 'src/app/services/db.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  orderObservable: Observable<any[]> = new Observable();
  orderStatusIdx: number = 0;
  orderStatus: { name: string; value: number }[] = [
    { name: 'Placed', value: 0 },
    { name: 'Accepted', value: 1 },
    { name: 'Delivered', value: 2 },
    { name: 'Cancelled', value: 3 },
  ];

  loader: boolean = false;
  viewOrderBool: boolean = false;
  viewOrderIdx: number | undefined;

  orderModel: any;
  orders: Orders[] = []; 

  constructor(
    private httpClient: HttpClient,
    private toast: ToastrService,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
    this.getOrders();
    // this.orderObservable = this.httpClient.get<any[]>("./../../assets/json/orders.json");
  }

  changeOrderStatus(orderStatusIdx: number) {
    // this.orderStatusIdx = orderStatusIdx;
  }

  openViewModal(orderModel: Orders, viewOrderIdx: number) {
    this.viewOrderBool = true;
    this.viewOrderIdx = viewOrderIdx;
    this.orderModel = orderModel;
  }

  openOrderUpdateModal(modalRef: any, orderModel: Orders) {
    this.modalService.open(modalRef, { size: 'sm' })
    this.orderModel = orderModel;
  }

  updateOrderStatus() {
    const getFormData = (obj: any) => {
      let formData = new FormData();
      Object.entries(obj).forEach(([key, value]: [string, any], idx: number) => formData.append(key, value));
      return formData;
    }

    this.loader = true;
    if(this.orderModel.orderStatus === 1) {
      let shipmentObj = {
        orderId: this.orderModel.orderId,
        shipmentStatus: 0,
        shipmentTitle: "Ready For Shipment",
        shipmentDate: new Date(),
        shipmentMethod: null,
        shipmentCompany: null,
        expectedDeliveryDate: new Date()
      }

      this.httpClient.post(BaseUrls.getAddUrl(BaseUrls.SHIPMENT_GROUPURL), getFormData(shipmentObj)).subscribe();
    }

    this.httpClient.post<Response>(BaseUrls.getUpdateUrl(BaseUrls.ORDER_GROUPURL), getFormData(this.orderModel))
      .subscribe({
        next: ({ code, message, data }) => {
          this.toast.success(message, "Success");
          this.loader = false;
          this.modalService.dismissAll();
        }, 
        error: (error) => {
          this.loader = false;
          console.log(error);
          this.toast.warning("Something went wrong!!", "Failed")
        }
      })
  }

  closeViewModal() {
    delete this.viewOrderIdx;
    delete this.orderModel;
    this.viewOrderBool = false;
  }

  getOrders(orderStatus: number = 0, orderStatusIdx: number = 0) {
    this.httpClient.get<Response>(`${BaseUrls.BASE_HREF}/${BaseUrls.ORDER_GROUPURL}/get-orders/${orderStatus}`)
      .subscribe({
        next: ({ data }) => {
          if(data.length !== 0) {
            this.orderStatusIdx = orderStatusIdx;
            this.orders = data;        
          } else {
            this.toast.info("No Records Founds", "");
          }
        },
        error: (error) => {
          console.log(error);
          this.toast.warning("Something went wrong!!", "Failed")
        }
      })
  }

}
