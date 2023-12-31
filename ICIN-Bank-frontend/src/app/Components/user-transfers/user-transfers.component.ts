import { DataServiceService } from 'src/app/Service/data-service.service';
import { Component, OnInit } from '@angular/core';
import { Accounts } from 'src/app/Model/accounts';
import { Transactions } from 'src/app/Model/transactions';
import { TransactionsService } from 'src/app/Service/transactions.service';
import { AccountServiceService } from 'src/app/Service/account-service.service';

@Component({
  selector: 'app-user-transfers',
  templateUrl: './user-transfers.component.html',
  styleUrls: ['./user-transfers.component.css']
})
export class UserTransfersComponent implements OnInit{

  transferAccountNumber: string = '';
  selfAccountNumber: string = '';
  transferAccountType: string = '';
  selfAccountType: string = '';
  isChecked: boolean = false;
  tranferAmount: number;
  transferMessage: string = '';
  myAccount: Accounts;
  primaryBalance: number = 0;
  savingsBalance: number = 0;
  isRegistered:number;
  accountNumber: string;

  constructor(
    private dataService: DataServiceService,
    private accountService: AccountServiceService,
    private transactionService: TransactionsService
  ) { }

  ngOnInit(): void {

this.accountNumber= this.dataService.getUser().accountNumber;
 this.getAll();
  }
  getAll(){
    this.accountService.getAccountByNumber(this.accountNumber).subscribe(
        account => {
          this.myAccount = account;
          this.selfAccountNumber = this.myAccount.accountNumber;
          this.primaryBalance = this.myAccount.balancePrimary;
          this.savingsBalance = this.myAccount.balanceSavings;
          this.isRegistered = this.myAccount.netBanking;
        }
      )
  }

  changed(event) {
    if (event.target.checked) {
      this.isChecked = true;
      this.transferAccountNumber = this.myAccount.accountNumber;
    } else {
      this.isChecked = false;
      this.transferAccountNumber = '';
    }
  }

  transferMoney() {
    if (this.transferAccountNumber === '' ||
      this.transferAccountType === '' ||
      this.selfAccountNumber === '' ||
      this.selfAccountType === '' ||
      this.transferMessage === '') {
      alert('You need to enter all the details above!');
      return;
    }

    if (!(this.selfAccountType === 'Savings' || this.selfAccountType === 'Primary')) {
      alert('Wrong Self Account Type! Please type either Savings or Primary only!');
      return;
    }

    if (!(this.transferAccountType === 'Savings' || this.transferAccountType === 'Primary')) {
      alert('Wrong Transfer Account Type! Please type either Savings or Primary only!');
      return;
    }

    if (!(this.selfAccountNumber === this.myAccount.accountNumber)) {
      alert('Your account number is wrong!');
      return;
    }

    if (this.isChecked) {
      if (this.selfAccountType === this.transferAccountType) {
        alert('Illegal transaction!');
        return;
      }
    }

    if (this.selfAccountType === 'Primary' && this.myAccount.balancePrimary < this.tranferAmount) {
      alert(`You only have Rs. ${this.myAccount.balancePrimary} in your primary account\nYou need Rs. ${Math.round(this.tranferAmount - this.myAccount.balancePrimary)} more to transfer`);
      return;
    }

    if (this.selfAccountType === 'Savings' && this.myAccount.balanceSavings < this.tranferAmount) {
      alert(`You only have Rs. ${this.myAccount.balanceSavings} in your savings account\nYou need Rs. ${Math.round(this.tranferAmount - this.myAccount.balanceSavings)} more to transfer!`);
      return;
    }
    if(this.myAccount.netBanking== 0){
      alert('You need access to perform transfers. Please contact your bank administrator');
      return;
    }

    let transaction = {
      fromAccountNumber: this.selfAccountNumber,
      toAccountNumber: this.transferAccountNumber,
      fromAccountType: this.selfAccountType,
      toAccountType: this.transferAccountType,
      transferAmount: this.tranferAmount,
      transferMessage: this.transferMessage,
      transferStatus: 0
    } as Transactions;

    this.transactionService.putTransaction(transaction)
    .subscribe(
      message => {
        alert(message.message);
      },
      error => console.log(error),
      () => {
        this.transferAccountNumber = '';
        this.transferAccountType = '';
        this.selfAccountNumber = '';
        this.selfAccountType = '';
        this.tranferAmount = 0;
        this.isChecked = false;
        this.transferMessage = '';
      }
    )
  }

  selectSelfPrimary(event) {
    if(event.target.checked) {
      this.selfAccountType = 'Primary';
    } else {
      this.selfAccountType = 'Savings';
    }
  }

  selectSelfSavings(event) {
    if(event.target.checked) {
      this.selfAccountType = 'Savings';
    } else {
      this.selfAccountType = 'Primary';
    }
  }

}
