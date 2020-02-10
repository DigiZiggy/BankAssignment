import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AccountListComponent } from './account-list/account-list.component';
import { CreateAccountComponent } from './create-account/create-account.component';
import {UpdateAccountComponent} from "./update-account/update-account.component";
import {AccountTransferComponent} from "./account-transfer/account-transfer.component";

const routes: Routes = [
  { path: 'accounts', component: AccountListComponent },
  { path: 'add', component: CreateAccountComponent },
  { path: 'update/:id', component: UpdateAccountComponent },
  { path: 'transfer/:id', component: AccountTransferComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
