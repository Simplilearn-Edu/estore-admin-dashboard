import { Injectable } from '@angular/core';
import { S3, config, Credentials } from 'aws-sdk';

@Injectable({
  providedIn: 'root'
})
export class AwsService {

  BUCKET_NAME: string = 'estore-bucket';

  constructor() { 
    // AWS Credentials
    config.update({
      region: 'ap-south-1',
      credentials: new Credentials('AKIASFQH3BHIQQUCIC6J', 'SEHkdxUUm/RhH25p3dyJcG/TzCp6/td6b4HbZlGQ')
    })
  }

  getS3Ref() {
    return new S3({
      apiVersion: '2006-03-01',
      params: {
        Bucket: this.BUCKET_NAME
      }
    })
  }
}
