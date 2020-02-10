import { Component, OnInit } from '@angular/core';
import {Account} from "../model/account";
import {AccountService} from "../service/account.service";
import {ActivatedRoute, Router} from "@angular/router";
import {first} from "rxjs/operators";
import {FormControl, Validators} from "@angular/forms";

@Component({
  selector: 'app-account-transfer',
  templateUrl: './account-transfer.component.html',
  styleUrls: ['./account-transfer.component.scss']
})
export class AccountTransferComponent implements OnInit {

  id: number;
  account: Account;
  Accounts: Account[];
  toAccountId: number;
  amount: bigint;

  constructor(private route: ActivatedRoute, private router: Router, private accountService: AccountService) {
  }

  ngOnInit() {
    this.account = new Account();
    this.id = this.route.snapshot.params['id'];

    this.accountService.getAccountById(this.id)
      .subscribe(data => {
        console.log(data);
        this.account = data;
      }, error => console.log(error));

    console.log("id OF THIS ACCOUNT  " + this.id);
    this.accountService.getAllAccounts()
      .subscribe( data => {
        var accounts = [];
        data.forEach(account => {
          if (account.id != this.id) {
            accounts.push(account);
          }
        });
        this.Accounts = accounts;
      });
  }

  onSubmit() {
    // this.submitted = true;
    console.log(this.amount);
    console.log(this.toAccountId);
    if(!this.toAccountId) // if a is negative,undefined,null,empty value then...
    {
      this.toAccountId = 1;
    }
    this.accountService.transferMoney(this.account.id, this.toAccountId, this.amount)
      .pipe(first())
      .subscribe(data => console.log(data), error => console.log(error));
    this.account = new Account();
    this.router.navigate(['/accounts']);
  }

  list(){
    this.router.navigate(['accounts']);
  }
}
