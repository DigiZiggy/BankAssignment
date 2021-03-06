import {Component, OnDestroy, OnInit} from '@angular/core';
import {Account} from "../model/account";
import {Currency} from "../model/currency";
import {AccountService} from "../service/account.service";
import {ActivatedRoute, Router} from "@angular/router";
import {first} from "rxjs/operators";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-update-account',
  templateUrl: './update-account.component.html',
  styleUrls: ['./update-account.component.scss']
})
export class UpdateAccountComponent implements OnInit {

  id: number;
  Currency = Currency;
  account: Account = new Account();

  constructor(private route: ActivatedRoute,private router: Router, private accountService: AccountService) { }

  ngOnInit(): void {
    this.account = new Account();

    this.id = this.route.snapshot.params['id'];

    this.accountService.getAccountById(this.id)
      .subscribe(data => {
        console.log(data);
        this.account = data;
      }, error => console.log(error));
  }

  onSubmit() {
    this.updateAccount();
  }

  gotoList() {
    this.router.navigate(['/accounts']);
  }

  updateAccount() {
    this.accountService.updateAccount(this.id, this.account)
      .subscribe(result => {
        this.gotoList();
      }, error => console.error(error));
    this.account = new Account();
  }
}
