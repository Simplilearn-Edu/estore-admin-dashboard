import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { BaseUrls } from '../base-urls';
import { Products } from '../models/products';
import { Shipments } from '../models/shipments';
import { Users } from '../models/users';
import { Category } from './../models/category';

export class Response {
  code: any;
  message: any;
  data: any[] = [];
}
@Injectable({
  providedIn: 'root'
})
export class DbService {

  categoies: BehaviorSubject<Category[]> = new BehaviorSubject<Category[]>([]);
  categoriesRetreivedBool: boolean = false;
  
  products: BehaviorSubject<Products[]> = new BehaviorSubject<Products[]>([]);
  productsRetreivedBool: boolean = false;
  
  users: BehaviorSubject<Users[]> = new BehaviorSubject<Users[]>([]);
  usersRetreivedBool: boolean = false;

  shipments: BehaviorSubject<Shipments[]> = new BehaviorSubject<Shipments[]>([]);
  shipmentRetreivedBool: boolean = false;

  constructor(
    private httpClient: HttpClient
  ) { }

  getProducts() {
    this.httpClient.get(BaseUrls.getUrl(BaseUrls.PRODUCT_GROUPURL))
    .subscribe({
      next: ({code, data, message}: any) => {          
        this.products.next(data);
        this.productsRetreivedBool = true;          
      },
      error: (error) => {
        console.log(error);
      }
    })
  }

  getCategories() {
    this.httpClient.get(BaseUrls.getUrl(BaseUrls.CATEGORIES_GROUPURL))
    .subscribe({
      next: ({code, data, message}: any) => {
        this.categoies.next(data);
        this.categoriesRetreivedBool = true;          
      },
      error: (error) => {
        console.log(error);
      }
    })
  }

  getUsers() {
    this.httpClient.get(BaseUrls.getUrl(BaseUrls.USER_GROUPURL))
    .subscribe({
      next: ({code, data, message}: any) => {
        this.users.next(data);
        this.usersRetreivedBool = true;          
      },
      error: (error) => {
        console.log(error);
      }
    })
  }

  getShipments(shipmentStatus: number = 0) {
    this.httpClient.get<Response>(`${BaseUrls.getUrl(BaseUrls.SHIPMENT_GROUPURL)}/${shipmentStatus}`)
      .subscribe({
        next: ({ data }) => {
          this.shipments.next(data);
          this.shipmentRetreivedBool = true;
        },
        error: (error) => {
          console.log(error);
        }
      })
  }
}
