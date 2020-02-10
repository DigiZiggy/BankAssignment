import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Account } from '../model/account';
import {Observable} from "rxjs";

@Injectable()
export class AccountService {

  private accountsUrl: string;

  constructor(private http: HttpClient) {
    this.accountsUrl = 'http://localhost:8080/accounts';
  }

  getAccountById(id: number): Observable<any> {
    return this.http.get(`${this.accountsUrl}/${id}`);
  }

  updateAccount(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.accountsUrl}/${id}`, value);
  }

  deleteAccount(id: number): Observable<any> {
    return this.http.delete(`${this.accountsUrl}/${id}`);
  }

  getAllAccounts(): Observable<any> {
    return this.http.get(`${this.accountsUrl}`);
  }

  createAccount(account: Account): Observable<Object> {
    return this.http.post(`${this.accountsUrl}`, account);
  }

  transferMoney(from_id: number, to_id: number, amount: any): Observable<Object> {
    console.log("IN TRANSFERMONEY method client side");
    console.log("AMOUNT IS  " + amount);
    console.log(this.http.put(`${this.accountsUrl}/from/${from_id}/to/${to_id}?amount=${to_id}`, amount));
    return this.http.put(`${this.accountsUrl}/from/${from_id}/to/${to_id}`,
      {},
      { params: { amount: amount } });
  }
}
