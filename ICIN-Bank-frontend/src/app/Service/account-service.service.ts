import { Message } from './../Model/message';
import { Accounts } from './../Model/accounts';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountServiceService {

  private baseURL = 'http://localhost:8086/account';

  constructor(private http: HttpClient) { }
  
  getAccountByNumber(accountNumber: string) : Observable<Accounts> {
    // return this.http.get<Accounts>(`${this.baseURL}/${accountNumber}`)
    return this.http.get<any>(this.baseURL+"/"+accountNumber);

  }
  getAllAccounts():Observable<Array<Accounts>>{
    return this.http.get<Array<Accounts>>(`${this.baseURL}/accounts`); 
  }
  createAccount(account:Accounts): Observable<Message>{
    return this.http.post<Message>(`${this.baseURL}/add-account`,account);
  }
  enableNetBanking(status:Number, accountNumber:String): Observable<Message>{
    return this.http.get<Message>(`${this.baseURL}/netbankingenable/${status}/${accountNumber}`);
  }
  getAllUnregisteredUsers(status:number):Observable<Array<Accounts>>{
    return this.http.get<Array<Accounts>>(`${this.baseURL}/unregisteredUsers`);
  }
  depositPrimary(balance:number, accountNumber:String): Observable<Message>{
    return this.http.get<Message>(`${this.baseURL}/depositPrimary/${balance}/${accountNumber}`)
  }
  depositSavings(balance:number, accountNumber:String): Observable<Message>{
    return this.http.get<Message>(`${this.baseURL}/depositSavings/${balance}/${accountNumber}`)
  }
  withdrawPrimary(balance:number, accountNumber:String): Observable<Message>{
    return this.http.get<Message>(`${this.baseURL}/withdrawPrimary/${balance}/${accountNumber}`)
  }
  withdrawSavings(balance:number, accountNumber:String): Observable<Message>{
    return this.http.get<Message>(`${this.baseURL}/withdrawSavings/${balance}/${accountNumber}`)
  }

}
