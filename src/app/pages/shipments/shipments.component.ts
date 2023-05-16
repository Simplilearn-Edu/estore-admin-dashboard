import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { BaseUrls } from 'src/app/base-urls';
import { Shipments } from 'src/app/models/shipments';
import { DbService, Response } from 'src/app/services/db.service';

@Component({
  selector: 'app-shipments',
  templateUrl: './shipments.component.html',
  styleUrls: ['./shipments.component.css']
})
export class ShipmentsComponent implements OnInit {

  loader: boolean = false;
  shipmentForm: FormGroup | any;

  shipments: Shipments[] = [];
  shipmentStatusIdx: number = 0;
  shipmentStatus: { name: string; value: number }[] = [
    { name: 'Ready For Shipment', value: 0 },
    { name: 'In-Transit', value: 1 },
  ];

  constructor(
    private fb: FormBuilder,
    public db: DbService,
    private http: HttpClient,
    private modalService: NgbModal,
    private toast: ToastrService
  ) { }

  ngOnInit(): void {
    this.db.getShipments();
    this.db.shipments.subscribe((list) =>{
      if(list.length !== 0) {
        this.shipments = list;
        console.log(list); 
      }
    })
  }

  openUpdateModal(modalRef: any, shipmentModel: Shipments) {
    this.shipmentForm = this.fb.group({
      shipmentId: [shipmentModel.shipmentId],
      orderId: [shipmentModel.orderId],
      shipmentStatus: [shipmentModel.shipmentStatus],
      shipmentTitle: [shipmentModel.shipmentTitle],
      shipmentDate: [new Date()],
      shipmentMethod: [shipmentModel.shipmentMethod],
      shipmentCompany: [shipmentModel.shipmentCompany],
      expectedDeliveryDate: [shipmentModel.expectedDeliveryDate],
    });    
    this.modalService.open(modalRef);
  }

  onSelectChange(event: any) {  
    let shipmentStatusObj = this.shipmentStatus.find(x => x.value === Number(event.target.value));
    this.shipmentForm.patchValue({
      shipmentTitle: shipmentStatusObj?.name,
      shipmentStatus: shipmentStatusObj?.value
    });
  }

  updateShipmentDetails() {
    this.loader = true;
    let values = { ...this.shipmentForm.value };
    values.expectedDeliveryDate = new Date(values.expectedDeliveryDate);

    let formData = new FormData();
    Object.entries(values).forEach(([key, value]: [string, any], idx: number) => formData.append(key, value));

    this.http.post<Response>(BaseUrls.getUpdateUrl(BaseUrls.SHIPMENT_GROUPURL), formData)
      .subscribe({
        next: ({ data }) => {
          this.loader = false;
          this.toast.success("Shipments Details Updated", "Success");
          this.modalService.dismissAll();
        },
        error: (error) => {
          this.loader = false;
          this.toast.warning("Something went wrong!!", "Failed")
        }
      })
  }

  deleteShipment(shipmentId: any, idx: any) {
    this.http.get<Response>(`${BaseUrls.getDeleteUrl(BaseUrls.SHIPMENT_GROUPURL)}/${shipmentId}`)
      .subscribe({
        next: ({ data }) => {
          this.loader = false;
          this.shipments.splice(idx, 1);
          this.toast.success("Shipments Deleted", "Success");
          this.modalService.dismissAll();
        },
        error: (error) => {
          this.loader = false;
          this.toast.warning("Something went wrong!!", "Failed")
        }
      })
  }

}
