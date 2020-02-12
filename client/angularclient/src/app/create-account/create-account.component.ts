import {Component, OnInit} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AccountService } from '../service/account.service';
import { Account } from '../model/account';
import {Currency} from "../model/currency";

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.scss']
})
export class CreateAccountComponent implements OnInit {

  account: Account = new Account();
  Currency = Currency;
  submitted = false;

  constructor(private route: ActivatedRoute, private router: Router, private accountService: AccountService) {
    this.account = new Account();
  }

  ngOnInit() {
  }

  onSubmit() {
    this.submitted = true;
    this.accountService.createAccount(this.account).subscribe(
      result => {
        this.gotoList();
      }, error => console.error(error));

    this.account = new Account();
  }

  gotoList(){
    this.router.navigate(['accounts']);
  }
}
