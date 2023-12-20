import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ChequeBooks } from '../Model/cheque-books';
import { Message } from '../Model/message';
import { Users } from '../Model/users';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private baseURL = 'http://localhost:8086/user';

  constructor(private http: HttpClient) { }

  getAllUsers():Observable<Array<Users>>{
      return this.http.get<Array<Users>>(`${this.baseURL}/users`); 
    }

  putUser(user: Users) : Observable<Message> {
    let targetURL = '/addUser'
    return this.http.post<Message>(this.baseURL+targetURL, user);
  }

  checkUserByIdAndPassword(userId: String, password: string) : Observable<Message> {
    let targetURL = `/check/${userId}/${password}`;
    return this.http.get<Message>(this.baseURL+targetURL);
  }

  getAllBlockedUser() : Observable<Array<Users>> {
    return this.http.get<Array<Users>>(`${this.baseURL}/blocked-users`);
  }

  blockUser(userId: string) : Observable<Message> {
    return this.http.get<Message>(`${this.baseURL}/block/${userId}`);
  }

  unblockUser(userId: string) : Observable<Message> {
    return this.http.get<Message>(`${this.baseURL}/unblock/${userId}`);
  }

  getUser(userId: string) : Observable<Users> {
    return this.http.get<Users>(`${this.baseURL}/${userId}`);
  }

  updateLoginPassword(newPassword: string, accountNumber: string) {
    return this.http.get<Message>(`${this.baseURL}/update/loginPassword/${newPassword}/${accountNumber}`)
  }

  updateTransactionPassword(newPassword: string, accountNumber: string) {
    return this.http.get<Message>(`${this.baseURL}/update/transactionPassword/${newPassword}/${accountNumber}`)
  }

  requestChequeBook(accountNumber: string, accountType: string) {
    return this.http.get<Message>(`${this.baseURL}/request-cheque-book/${accountNumber}/${accountType}`)
  }

  getChequeBooks(accountNumber: string) : Observable<Array<ChequeBooks>> {
    return this.http.get<Array<ChequeBooks>>(`${this.baseURL}/cheque-books/${accountNumber}`);
  }

  getAllChequeBookRequests() : Observable<Array<ChequeBooks>> {
    return this.http.get<Array<ChequeBooks>>(`${this.baseURL}/cheque-books-requests`);
  }

  acceptChequeBookRequest(chequeBookNumber: string) : Observable<Message> {
    return this.http.get<Message>(`${this.baseURL}/cheque-books-requests/accept/${chequeBookNumber}`);
  }
}
