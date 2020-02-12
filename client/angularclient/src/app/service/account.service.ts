import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { Account } from '../model/account';
import {Observable} from "rxjs";
import {Transfer} from "../model/transfer";

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
    return this.http.put(`${this.accountsUrl}/edit/${id}`, value);
  }

  deleteAccount(id: number): Observable<any> {
    return this.http.delete(`${this.accountsUrl}/delete/${id}`);
  }

  getAllAccounts(): Observable<any> {
    return this.http.get(`${this.accountsUrl}`);
  }

  createAccount(account: Account): Observable<Object> {
    return this.http.post(`${this.accountsUrl}/add`, account);
  }

  createTransfer(id: number, transfer: Transfer): Observable<Object> {
    return this.http.post(`${this.accountsUrl}/transfer/${id}`, transfer);
  }
}
