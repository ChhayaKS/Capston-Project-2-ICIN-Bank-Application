import { Injectable } from '@angular/core';
import { Users } from '../Model/users';
import { Accounts } from '../Model/accounts';

@Injectable({
  providedIn: 'root'
})
export class DataServiceService {
  private account:Accounts=null;
  private user: Users=null;
  private isSafe: boolean=false;

  constructor() { }
  public getAccount() : Accounts {
    return this.account;
  }

  public setAccount(account: Accounts) {
    this.account = account;
  }

  public getUser() : Users {
    return this.user;
  }

  public setUser(user: Users) {
    this.user = user;
  }

  public getIsSafe() : boolean {
    return this.isSafe;
  }

  public setIsSafe(isSafe: boolean) {
    this.isSafe = isSafe;
  }
}
