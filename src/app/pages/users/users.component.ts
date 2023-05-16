import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Users } from 'src/app/models/users';
import { DbService } from 'src/app/services/db.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  // usersObservable = new Observable<any[]>();
  users: Users[] = [];
  
  constructor(
    // private httpClient: HttpClient
    private db: DbService
  ) { }

  ngOnInit(): void {
    this.db.getUsers();
    this.db.users.subscribe((list) => {
      if(list.length !== 0) this.users = list;
    })
    // this.usersObservable = this.httpClient.get<any[]>("./../../assets/json/users.json");
  }

}
