import { Component, OnInit } from '@angular/core';
import { Account } from '../model/account';
import { AccountService } from '../service/account.service';
import {Observable} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-account-list',
  templateUrl: './account-list.component.html',
  styleUrls: ['./account-list.component.scss']
})
export class AccountListComponent implements OnInit {
  accounts: Account[];

  constructor(private accountService: AccountService, private router: Router) {
  }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.accountService.getAllAccounts()
      .subscribe( data => {
        this.accounts = data;
        console.log(data)
      });
  }

  deleteAccount(id: number) {
    this.accountService.deleteAccount(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  accountTransfer(id: number){
    this.router.navigate(['transfer', id]);
  }

  updateAccount(id: number){
    this.router.navigate(['update', id]);
  }
}
