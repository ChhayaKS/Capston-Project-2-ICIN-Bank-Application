import { Accounts } from 'src/app/Model/accounts';
import { AccountServiceService } from './../../Service/account-service.service';
import { DataServiceService } from './../../Service/data-service.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user-accounts',
  templateUrl: './user-accounts.component.html',
  styleUrls: ['./user-accounts.component.css']
})
export class UserAccountsComponent implements OnInit{
  accountNumber: string = '';
  userName: string = '';
  accountCIFNumber: string = '';
  branch: string = '';
  balancePrimary: number = 0;
  balanceSavings: number = 0;

  constructor(private dataService: DataServiceService, private accountService: AccountServiceService,private route:ActivatedRoute) { }

  ngOnInit(): void {
  //  this.accountService.getAccountByNumber(this.dataService.getUser().accountNumber) .subscribe

  //  (account => 
  //   {
  //     this.accountNumber = account.accountNumber;
  //     this.userName=account.userName;
  //     this.accountCIFNumber=  account.accountCIFNumber;
  //     this.branch= account.branch;
  //     this.balancePrimary=  account.balancePrimary;
  //      this.balanceSavings =account.balanceSavings;
  //      } 
  //     ,
  //     error => console.log(error)
  //   )
  // }

  this.accountNumber= this.dataService.getUser().accountNumber;
  this.getAll();
  }

getAll(){
  this.accountService.getAccountByNumber(this.accountNumber).subscribe
  (account => 
   {
     this.accountNumber = account.accountNumber;
     this.userName=account.userName;
     this.accountCIFNumber=  account.accountCIFNumber;
     this.branch= account.branch;
     this.balancePrimary=  account.balancePrimary;
      this.balanceSavings =account.balanceSavings;
      } )
 }

  

  }


