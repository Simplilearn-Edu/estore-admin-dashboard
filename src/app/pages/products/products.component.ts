import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { combineLatest, Observable } from 'rxjs';
import { BaseUrls } from 'src/app/base-urls';
import { Category } from 'src/app/models/category';
import { Products } from 'src/app/models/products';
import { AwsService } from 'src/app/services/aws.service';
import { DbService } from 'src/app/services/db.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  selectedImageIdx: number = 0;
  thumbnailImageIdx: number = 0;
  tempImageFiles: any[] = [];
  
  updation: boolean = false;
  loader: boolean = false;

  productForm: FormGroup = new FormGroup({});

  productModel: Products | undefined;

  products: Products[] = [];
  categories: Category[] = [];
  // public prodObservable: Observable<any[]> = new Observable();
  // public categoryObservable: Observable<any[]> = new Observable();

  constructor(
    private fb: FormBuilder,
    private db: DbService,
    private aws: AwsService,
    private httpClient: HttpClient,
    private modalService: NgbModal,
    private toast: ToastrService
  ) { }

  ngOnInit(): void {
    this.db.getProducts();
    this.db.getCategories();

    combineLatest([this.db.products, this.db.categoies])
      .subscribe(([products, categories]) => {
        if(products.length !== 0) this.products = products;
        if(categories.length !== 0) this.categories = categories;
      });
  }

  openModal(modal: any, prd: Products | null = null) {
    this.tempImageFiles = []; 
    this.initialiseModal(prd);
    this.modalService.open(modal, { size: "xl" });
  }

  initialiseModal(productObj: Products | null) {
    if (productObj == null) {
      this.updation = false;
      this.productForm = this.fb.group({
        productId: [null],
        productTitle: [null],
        productCode: [null],
        price: [0],
        images: this.fb.array([]),
        thumbnailImage: [0],
        productDescription: [null],
        categoryId: [null],
        active: [1],
        addedOn: [new Date()],
        rating: [0]
      });
    } else {
      this.updation = true;
      this.productForm = this.fb.group({
        productId: [productObj.productId],
        productTitle: [productObj.productTitle],
        productCode: [productObj.productCode],
        price: [productObj.price],
        images: [productObj.images],
        thumbnailImage: [productObj.thumbnailImage || 0],
        productDescription: [productObj.productDescription],
        categoryId: [productObj.categoryId],
        active: [productObj.active],
        addedOn: [productObj.addedOn],
        rating: [productObj.rating]
      });
      this.onSelectOption(productObj.categoryId);
      this.tempImageFiles = productObj.images || [];
    }
  }

  onSelectOption(category: any) {
    this.productForm.patchValue({
      categoryId: category
    })
  }

  compareByCategoryId(category1: Category, category2: Category) {
    return category1 && category2 && category1.categoryId === category2.categoryId;
  }

  checkImageFileType(event: any) {
    let fileList: File[] = Object.assign([], event.target.files);
    fileList.forEach((file: any, idx: number) => {
      if (
        file.type == "image/png" ||
        file.type == "image/jpeg" ||
        file.type == "image/jpg"
      ) {
        this.tempImageFiles.push(file);
      } else {
        // this.toast.warning("Only .png/.jpeg/.jpg file format accepted!!", file.name + " will not accepted.");
      }
    });
  }

  removeImage(idx: number) {
    this.tempImageFiles.splice(idx, 1);
  }

  changeThumbnailImageIdx(idx: number) {
    this.productForm.patchValue({
      thumbnailImage: idx
    })
  }

  openImageModal(modal: any, imageUrls: string[], thumbnailImageIdx: number) {
    this.tempImageFiles = [...imageUrls];
    this.thumbnailImageIdx = thumbnailImageIdx;
    this.modalService.open(modal, { 
      size: "xl",
      scrollable: true 
    });
  }

  viewProductDetails(modal: any, productObj: Products) {
    this.productModel = productObj;
    this.modalService.open(modal, { size: 'lg' });
  }

  uploadFileToS3(file: any) {
    return new Promise((resolve, reject) => {
      const s3Bucket = this.aws.getS3Ref();
      const params = {
        Bucket: this.aws.BUCKET_NAME,
        Key: file.name,
        Body: file,
        ACL: 'public-read',
        ContentType: file.type
      }

      s3Bucket.upload(params, {}).send((error, response) => {
        if(error) reject(error);
        if(response) resolve(response);
      })
    })
  }

  async uploadImages(): Promise<any> {
    let imageDownloadedUrl: string[] = [];
    for (let file of this.tempImageFiles) {
      if (file instanceof File) {
        let s3Response: any = await this.uploadFileToS3(file);
        imageDownloadedUrl.push(s3Response['Location'])
      } else {
        imageDownloadedUrl.push(file);
      }
    }
    return imageDownloadedUrl;
  }

  checkFileType(value: any) {
    return typeof value !== 'string' ? value.name : String(value).substring(String(value).lastIndexOf("/")).replace("/", "");
  }

  async saveProduct() {
    this.loader = true;
    let values = { ...this.productForm.value };
    values.images = await this.uploadImages();
    let formData = new FormData();

    Object.entries(values).forEach(([key, value]: [string, any], idx) => formData.append(key, value));
    if(!this.updation) formData.delete('productId');

    let httpRef;
    if(!this.updation) {
      httpRef = this.httpClient.post(BaseUrls.getAddUrl(BaseUrls.PRODUCT_GROUPURL), formData)
    } else {
      httpRef = this.httpClient.post(BaseUrls.getUpdateUrl(BaseUrls.PRODUCT_GROUPURL), formData)
    }

    httpRef.subscribe({
      next: (value) => {
        this.loader = false;
        this.db.getProducts();
        this.modalService.dismissAll();
        this.toast.success(`${this.updation ? 'Updated' : 'Added'} Successfully`, "Product");
      },
      error: (error) => { 
        console.log(error);
        this.toast.warning("Something went wrong!! Please Try Again...", "Failed");
        this.loader = false;
      }
    })
  }

  deleteProducts(id: any, idx: number) {
    this.httpClient.get(`${BaseUrls.getDeleteUrl(BaseUrls.PRODUCT_GROUPURL)}/${id}`)
      .subscribe({
        next: (value) => {
          this.products.splice(idx, 1);
          this.toast.success(`Product with ${id}`, 'Deleted Successfully');
        }, 
        error: (error) => {
          this.toast.warning("Something went wrong!! Please Try Again...", "Failed");
        }
      })
  }

  openImage(url: string) {
    window.open(url, "_blank")
  }

}
