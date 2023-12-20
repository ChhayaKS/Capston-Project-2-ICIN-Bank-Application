import { Accounts } from './../../Model/accounts';
import { AccountServiceService } from 'src/app/Service/account-service.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-account-creation',
  templateUrl: './user-account-creation.component.html',
  styleUrls: ['./user-account-creation.component.css']
})
export class UserAccountCreationComponent implements OnInit{

  accountNumber: string = '';
  balancePrimary: number = 1000;
  balanceSavings: number = 1000;
  branch: string = '';
  accountCIFNumber: string = '';
  userName: string = '';
  netBanking: number = 0;

  constructor(
    private accService: AccountServiceService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  getCifNumber(){
    if (this.branch === 'Rajajinagar'){
      this.accountCIFNumber = 'ICINRAJ01'
    }else if(this.branch === 'Jayanagar'){
      this.accountCIFNumber = 'ICINJAY02'
    }else if(this.branch === 'Girinagar'){
      this.accountCIFNumber = 'ICINGIR03'
    }
    else{
      alert('Please choose one of the 3 available branches - Rajajinagar, Jayanagar or Girinagar')
    }
  }

  createAccount(){
    if (this.accountNumber === '' ||
      this.branch === '' ||
      this.accountCIFNumber === '' ||
      this.userName === ''){
      alert('Please enter the required details!');
      return;
  }

  let account = {
    accountNumber: this.accountNumber,
    userName: this.userName,
    accountCIFNumber: this.accountCIFNumber,
    branch: this.branch,
    netBanking: this.netBanking,
    balancePrimary: this.balancePrimary,
    balanceSavings: this.balanceSavings,
  } as Accounts

  this.accService.createAccount(account).subscribe(
    message => {
      if (message.message == 'Account created successfully!') {
        let mySuccessMessage = message.message + '\n' + 'Press OK to proceed to the registration page!'
        if (confirm(mySuccessMessage)) {
          this.router.navigate(['registration']);
        }
      } else {
        alert(message.message);
      }
    },
  )

}
}
