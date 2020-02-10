import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AccountListComponent } from './account-list/account-list.component';
import { CreateAccountComponent } from './create-account/create-account.component';
import { AccountService } from './service/account.service';
import { UpdateAccountComponent } from './update-account/update-account.component';
import {AccountTransferComponent} from "./account-transfer/account-transfer.component";
import {CustomFormsModule} from "./custom-forms.module";

@NgModule({
  declarations: [
    AppComponent,
    CreateAccountComponent,
    AccountTransferComponent,
    AccountListComponent,
    UpdateAccountComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CustomFormsModule,
    FormsModule
  ],
  providers: [AccountService],
  bootstrap: [AppComponent]
})
export class AppModule { }
