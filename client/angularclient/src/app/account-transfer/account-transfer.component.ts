import { Component, OnInit } from '@angular/core';
import {Account} from "../model/account";
import {AccountService} from "../service/account.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Transfer} from "../model/transfer";

@Component({
  selector: 'app-account-transfer',
  templateUrl: './account-transfer.component.html',
  styleUrls: ['./account-transfer.component.scss']
})
export class AccountTransferComponent implements OnInit {

  id: number;
  sourceAccount: Account;
  targetAccount: Account;
  Accounts: Account[];
  targetAccountId: number;
  amount: bigint;
  transfer: Transfer = new Transfer();

  constructor(private route: ActivatedRoute, private router: Router, private accountService: AccountService) {
  }

  ngOnInit() {
    this.sourceAccount = new Account();
    this.id = this.route.snapshot.params['id'];

    this.accountService.getAccountById(this.id)
      .subscribe(data => {
        console.log(data);
        this.sourceAccount = data;
      }, error => console.log(error));

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
    if(!this.targetAccountId) // if a is negative,undefined,null,empty value then...
    {
      this.targetAccountId = this.Accounts[0].id;
    }

    this.transfer.amount = this.amount;
    this.transfer.sourceAccount = this.sourceAccount;
    this.accountService.createTransfer(this.targetAccountId, this.transfer)
      .subscribe(result => {
        this.gotoList();
      }, error => console.error(error));

    this.sourceAccount = new Account();
    this.transfer = new Transfer();
  }

  gotoList(){
    this.router.navigate(['accounts']);
  }
}
